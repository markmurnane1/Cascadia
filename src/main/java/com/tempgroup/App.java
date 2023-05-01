package com.tempgroup;

import java.util.ArrayList;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.application.controllers.PrintController;
import com.tempgroup.domain.models.*;
import com.tempgroup.domain.models.Player.APlayer;
import com.tempgroup.domain.models.ScoreCard.AScoreCard;
import com.tempgroup.domain.models.ScoreCard.ScoreCardA;
import com.tempgroup.domain.models.Utility.Constants;

public class App {
    public static void main(String[] args) {
        PrintController printer = new PrintController();
        GameConfiguration config = new GameConfiguration();
        GameController game = new GameController(config);

        ArrayList<APlayer> players = game.getPlayers();
        ArrayList<Habitat> habitatChoice = new ArrayList<>();
        AScoreCard scoreCard = new ScoreCardA();

        Tile[][] currTiles;
        ArrayList<Tile> tilesChoice = new ArrayList<>();

        Board currBoard = game.getGameBoard();

        while(players.get(game.getCurrPlayer()).getTurnsTaken() <= Constants.TURNS)
        {
            currTiles = players.get(game.getCurrPlayer()).getPlayerTileMatrix();


            //Update Model
            game.updateDeck(tilesChoice, habitatChoice);
            game.updateView(tilesChoice, currBoard, currTiles);

            //Display
            printer.displayView(currBoard, game.getChoiceBoard(), habitatChoice, players.get(game.getCurrPlayer()).getName());

            //Take Turn
            players.get(game.getCurrPlayer()).takeTileTurn(tilesChoice, scoreCard);
            players.get(game.getCurrPlayer()).takeHabitatTurn(habitatChoice, scoreCard);
            game.endTurn(tilesChoice, habitatChoice);

            config.prompt("Press any key to continue...");
        }

        for(APlayer player : players)
        {
            printer.displayScoring(scoreCard, player);
        }


       
    }
}