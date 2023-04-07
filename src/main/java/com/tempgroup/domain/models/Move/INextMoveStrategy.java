package com.tempgroup.domain.models.Move;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Player.APlayer;
import com.tempgroup.domain.models.Point;
import com.tempgroup.domain.models.ScoreCard.IScoreCard;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;
import java.util.List;

public interface INextMoveStrategy {

    public abstract Tile getNextMove(GameController game, Tile[][] tileMatrix, Tile[] choiceTiles);
    public abstract void getNextHabitatMove(GameController game, ArrayList<Tile> playerTiles, ArrayList<Habitat> choiceHabitats);
}
