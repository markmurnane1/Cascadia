package com.tempgroup;

import java.util.ArrayList;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.application.controllers.InputController;
import com.tempgroup.application.controllers.PrintController;
import com.tempgroup.domain.models.*;

public class App {
    public static void main(String[] args) {
        PrintController printer = new PrintController();
        InputController input = new InputController();

        GameConfiguration config = new GameConfiguration();
        GameController game = new GameController(config);

        ArrayList<Player> players = game.getPlayers();
        ArrayList<Tile> tilesChoice = new ArrayList<>();
        ArrayList<Habitat> habitatChoice = new ArrayList<>();
        ArrayList<Tile> currTiles;



        System.out.println("\nPlaying order is:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(String.format("(%d): %s", i + 1, players.get(i).getName()));
        }



        while(config.running)
        {
            game.clearGameBoard();
            currTiles = players.get(game.getCurrPlayer()).getPlayerTiles();
            for(int i = 0; i < currTiles.size(); i++)
            {
                currTiles.get(i).addTileToBoard(game.getGameBoard());
            }
            printer.displayGameBoard(game.getGameBoard());

            habitatChoice.clear(); // temporary - we need to return unused tokens back to bag
            tilesChoice.clear(); // temporary -


            for (int i = 0; i < 4; i++) {
                tilesChoice.add(game.getTileFromTileBag());
                tilesChoice.get(i).setX(i * 4);
                tilesChoice.get(i).setY(0);
            }
            game.addChoiceTilesToChoiceBoard(tilesChoice);

            for (int i = 0; i < 4; i++) {
                habitatChoice.add(game.getHabitatFromBag());
            }


            printer.displayChoiceTiles(game.getChoiceBoard());
            printer.println("");
            printer.displayHabitatChoices(habitatChoice);//Shows the 4 tiles available to select



            int tileChoice = (Integer.parseInt(config.prompt("\n[" + players.get(game.getCurrPlayer()).getName() + "]" + "Enter which tile you choose (1-4)")) - 1);
            int rowChoice = Integer.parseInt(config.prompt("Enter the row to place tile"));
            int columnChoice = Integer.parseInt(config.prompt("Enter column to place title"));

            while(!game.checkTilePlacementPossible(columnChoice, rowChoice))
            {
                rowChoice = Integer.parseInt(config.prompt("Invalid Coordinates: Please try again\nrow: "));
                columnChoice = Integer.parseInt(config.prompt("column: "));

            }
            Tile chosenTile = tilesChoice.get(tileChoice);
            chosenTile.setX(columnChoice * 4);
            chosenTile.setY(rowChoice * 4);

            players.get(game.getCurrPlayer()).addTileToPlayerTiles(chosenTile);


            game.endTurn();

        }

    }
}