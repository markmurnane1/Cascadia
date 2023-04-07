package com.tempgroup;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.application.controllers.InputController;
import com.tempgroup.application.controllers.PrintController;
import com.tempgroup.domain.models.*;
import com.tempgroup.domain.models.Player.APlayer;

public class App {
    public static void main(String[] args) {
        PrintController printer = new PrintController();
        InputController input = new InputController();
        GameConfiguration config = new GameConfiguration();
        GameController game = new GameController(config);
        Scoring scoreCard = new Scoring();

        ArrayList<APlayer> players = game.getPlayers();
        ArrayList<Habitat> habitatChoice = new ArrayList<>();

        Tile[][] currTiles; // Temporary tile matrix for each turn
        Tile[] tilesChoice = new Tile[Constants.TILE_CHOICE];

        Board currBoard = game.getGameBoard();

        while(config.running)
        {


            currTiles = players.get(game.getCurrPlayer()).getPlayerTileMatrix();

            currBoard.matrixToBoard(currTiles);
            printer.displayGameBoard(currBoard);

            for (int i = 0; i < 4; i++) {
                habitatChoice.add(game.getHabitatFromBag());
                tilesChoice[i] = game.getTileFromTileBag();
                tilesChoice[i].setX(i);
                tilesChoice[i].setY(0);
            }
            game.addChoiceTilesToChoiceBoard(tilesChoice);


            printer.displayChoiceTiles(game.getChoiceBoard());
            printer.println("");
            printer.displayHabitatChoices(habitatChoice);//Shows the 4 tiles available to select

            players.get(game.getCurrPlayer()).takeTileTurn(tilesChoice);
            players.get(game.getCurrPlayer()).takeHabitatTurn(habitatChoice);

            for(int i = 0; i < habitatChoice.size(); i++){
                game.returnHabitatToBag(habitatChoice.get(i));
            }
            habitatChoice.clear();

            

            config.prompt("Press any key to continue...");
            if(game.checkGameOver()) config.running = false;
            game.endTurn();
        }

       
    }
}