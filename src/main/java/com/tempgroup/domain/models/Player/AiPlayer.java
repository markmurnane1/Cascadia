package com.tempgroup.domain.models.Player;

import java.util.ArrayList;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Tile;
import com.tempgroup.domain.models.Move.INextMoveStrategy;
import com.tempgroup.domain.models.ScoreCard.AScoreCard;

public class AiPlayer extends APlayer {

    private INextMoveStrategy iNextMoveStrategy;
    private GameController game;

    public AiPlayer(String name, INextMoveStrategy iNextMoveStrategy, GameController game) {
        super(name);
        this.iNextMoveStrategy = iNextMoveStrategy;
        this.game = game;
    }

    @Override
    public void takeTileTurn(ArrayList<Tile> choiceTiles, AScoreCard scoreCard) {
        Tile t = this.iNextMoveStrategy.getNextMove(game, this.getPlayerTileMatrix(), this.getPlayerTiles(),
                choiceTiles, scoreCard);
        addTileToPlayerTiles(t);
    }

    public void takeHabitatTurn(ArrayList<Habitat> choiceHabitat, AScoreCard scoreCard) {
        this.iNextMoveStrategy.getNextHabitatMove(game, this.getPlayerTiles(), choiceHabitat, scoreCard,
                this.getPlayerTileMatrix());
        this.incrementTurnTaken();
    }
}
