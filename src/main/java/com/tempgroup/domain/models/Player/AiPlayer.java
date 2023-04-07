package com.tempgroup.domain.models.Player;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Move.INextMoveStrategy;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;

public class AiPlayer extends APlayer {

    private INextMoveStrategy iNextMoveStrategy;
    private GameController game;

    public AiPlayer(String name, INextMoveStrategy iNextMoveStrategy, GameController game){
        super(name);
        this.iNextMoveStrategy = iNextMoveStrategy;
        this.game = game;
    }

    @Override
    public void takeTileTurn(Tile[] choiceTiles) {
        Tile t = this.iNextMoveStrategy.getNextMove(game, this.getPlayerTileMatrix(), choiceTiles);
        addTileToPlayerTiles(t);
    }
    public void takeHabitatTurn(ArrayList<Habitat> choiceHabitat) {
        this.iNextMoveStrategy.getNextHabitatMove(game, this.getPlayerTiles(), choiceHabitat);
    }
}
