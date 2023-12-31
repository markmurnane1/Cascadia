package com.tempgroup.domain.models.Move;

import java.util.ArrayList;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Tile;
import com.tempgroup.domain.models.ScoreCard.AScoreCard;

public interface INextMoveStrategy {

    public abstract Tile getNextMove(GameController game, Tile[][] tileMatrix, ArrayList<Tile> playerTiles,
            ArrayList<Tile> choiceTiles, AScoreCard scoreCard);

    public abstract void getNextHabitatMove(GameController game, ArrayList<Tile> playerTiles,
            ArrayList<Habitat> choiceHabitats, AScoreCard scoreCard, Tile[][] matrix);
}
