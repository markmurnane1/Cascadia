package com.tempgroup.domain.models.Move;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.ScoreCard.AScoreCard;
import com.tempgroup.domain.models.Utility.Point;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomMoveStrategy implements INextMoveStrategy{
    @Override
    public Tile getNextMove(GameController game, Tile[][] tileMatrix, ArrayList<Tile> playerTiles, ArrayList<Tile> choiceTiles, AScoreCard scoreCard) {
        List<Point> moves = game.validMoves(tileMatrix, playerTiles);

        Random random = new Random();

        int index = random.nextInt(moves.size());

        int row = moves.get(index).x;
        int column = moves.get(index).y;


        Tile t = choiceTiles.get(random.nextInt(choiceTiles.size()));
        t.setX(row);
        t.setY(column);

        return t;
    }

    @Override
    public void getNextHabitatMove(GameController game, ArrayList<Tile> playerTiles, ArrayList<Habitat> choiceHabitats, AScoreCard scoreCard, Tile[][] matrix) {
        //get a random Habitat
        Random random = new Random();

        Habitat h = choiceHabitats.get(random.nextInt(choiceHabitats.size()));

        List<Tile> possibleTilesToPlaceHabitat = game.getValidHabitatMoves(playerTiles, choiceHabitats.get(0));

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
