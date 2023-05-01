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

        AScoreCard scoreCard = new ScoreCardA();

        ArrayList<APlayer> players = game.getPlayers();
        ArrayList<Tile> tilesChoice = new ArrayList<>();
        ArrayList<Habitat> habitatChoice = new ArrayList<>();

        while(players.get(game.getCurrPlayer()).getTurnsTaken() < Constants.TURNS)
        {
            APlayer currentPlayer = players.get(game.getCurrPlayer());

            //Update Model
            game.updateDeck(tilesChoice, habitatChoice);
            game.updateView(tilesChoice, game.getGameBoard(), currentPlayer.getPlayerTileMatrix());

            //Display View
            printer.displayView(game.getGameBoard(), game.getChoiceBoard(), habitatChoice, currentPlayer.getName());

            //Take Turn
            currentPlayer.takeTileTurn(tilesChoice, scoreCard);
            currentPlayer.takeHabitatTurn(habitatChoice, scoreCard);
            game.endTurn(tilesChoice, habitatChoice);

            config.prompt("Press any key to continue...");
        }

        for(APlayer player : players)
        {
            printer.displayScoring(scoreCard, player);
        }

    }
}