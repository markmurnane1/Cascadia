package com.tempgroup.domain.models.Move;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Tile;
import com.tempgroup.domain.models.ScoreCard.AScoreCard;
import com.tempgroup.domain.models.Utility.Point;

public class MaxScoreStrategy implements INextMoveStrategy {
    @Override
    public Tile getNextMove(GameController game, Tile[][] tileMatrix, ArrayList<Tile> playerTiles,
            ArrayList<Tile> choiceTiles, AScoreCard scoreCard) {

        List<Point> moves = game.validMoves(tileMatrix, playerTiles);
        int count;
        int max = scoreCard.countLargestConnectedHabitat(tileMatrix, playerTiles);
        Tile maxTile = new Tile();

        for (Tile t : choiceTiles) {
            playerTiles.add(t);
            for (Point move : moves) {

                t.setX(move.x);
                t.setY(move.y);
                tileMatrix[move.x][move.y] = t;

                count = scoreCard.countLargestConnectedHabitat(tileMatrix, playerTiles);

                if (count > max) {
                    max = count;
                    maxTile.terrains.addAll(t.getTerrains());
                    maxTile.habitats.addAll(t.getHabitats());
                    maxTile.setX(move.x);
                    maxTile.setY(move.y);
                }

                // Reset modified position
                tileMatrix[move.x][move.y] = null;
                tileMatrix[move.x][move.y] = new Tile();
            }
            playerTiles.remove(t);
        }

        // If no tile placement results in a higher score choose a random tile placement
        if (maxTile.emptyTile()) {
            Random random = new Random();

            int index = random.nextInt(moves.size());

            int column = moves.get(index).x;
            int row = moves.get(index).y;

            maxTile = choiceTiles.get(random.nextInt(choiceTiles.size()));
            maxTile.setX(column);
            maxTile.setY(row);
        } else {

        }

        return maxTile;
    }

    @Override
    public void getNextHabitatMove(GameController game, ArrayList<Tile> playerTiles, ArrayList<Habitat> choiceHabitats,
            AScoreCard scoreCard, Tile[][] matrix) {
        int count;
        int max = 0;
        Tile maxTile = new Tile();
        Habitat maxFinalHabitat = null;
        boolean madeMove = false;

        for (Habitat habitat : choiceHabitats) {
            ArrayList<Tile> tiles = game.getValidHabitatMoves(playerTiles, habitat);

            for (Tile tile : tiles) {
                // store original tile state
                List<Habitat> originalHabitat = new ArrayList<>(tile.habitats);

                tile.habitats.clear();
                tile.finalHabitat = habitat;

                count = scoreCard.score(matrix, playerTiles);

                if (count > max) {
                    max = count;

                    // storing tile state which gave us the most score
                    maxTile = tile;
                    maxFinalHabitat = habitat;
                }

                // restoring original tile state
                tile.habitats.clear();
                tile.habitats.addAll(originalHabitat);
                tile.finalHabitat = null;
            }
        }
        // if maxFinalHabiat == null no specific move gave us a better score
        if (maxFinalHabitat != null) {
            maxTile.habitats.clear();
            maxTile.habitats.add(maxFinalHabitat);
            maxTile.finalHabitat = maxFinalHabitat;
            madeMove = true;
            // Check if keystone tile
            if (maxTile.isKeyStoneTile()) {
                game.getPlayers().get(game.getCurrPlayer()).addNatureToken();
            }
        } else {

            // In this case we will just choose a random move
            Random random = new Random();

            for (Habitat h : choiceHabitats) {
                List<Tile> randomMoves = game.getValidHabitatMoves(playerTiles, h);

                if (randomMoves.size() > 0) {
                    Tile t = randomMoves.get(random.nextInt(randomMoves.size()));
                    t.habitats.clear();
                    t.habitats.add(h);
                    t.finalHabitat = h;
                    madeMove = true;
                    // Check if keystone tile
                    if (t.isKeyStoneTile()) {
                        game.getPlayers().get(game.getCurrPlayer()).addNatureToken();
                    }
                    break;
                }
            }
        }
        if (!madeMove) {
            System.out.println("No valid wildlife token placement...returning tokens to bag");
        }
    }
}
