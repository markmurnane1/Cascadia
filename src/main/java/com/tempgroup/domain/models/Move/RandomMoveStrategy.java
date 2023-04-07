package com.tempgroup.domain.models.Move;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Point;
import com.tempgroup.domain.models.ScoreCard.IScoreCard;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMoveStrategy implements INextMoveStrategy{
    @Override
    public Tile getNextMove(GameController game, Tile[][] tileMatrix, Tile[] choiceTiles) {
        List<Point> moves = game.validMoves(tileMatrix);

        Random random = new Random();

        int row = moves.get(random.nextInt(moves.size())).x;
        int column = moves.get(random.nextInt(moves.size())).y;

        Tile t = choiceTiles[random.nextInt(choiceTiles.length)];
        t.setX(row);
        t.setY(column);

        return t;
    }

    @Override
    public void getNextHabitatMove(GameController game, ArrayList<Tile> playerTiles, ArrayList<Habitat> choiceHabitats) {
        //get a random Habitat
        Random random = new Random();

        Habitat h = choiceHabitats.get(random.nextInt(choiceHabitats.size()));

        List<Tile> possibleTilesToPlaceHabitat = game.getValidHabitatMoves(playerTiles, h);

        if(possibleTilesToPlaceHabitat.size() > 0){

            Tile t = possibleTilesToPlaceHabitat.get(random.nextInt(possibleTilesToPlaceHabitat.size()));
            t.habitats.clear();
            t.habitats.add(h);
            t.finalHabitat = h;
        }else{
            System.out.println("No Valid Habitat Placement. Returning to bag.");
        }


    }

}
