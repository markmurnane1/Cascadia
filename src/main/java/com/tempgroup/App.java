package com.tempgroup;

import java.util.ArrayList;
import java.util.Random;

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

        Scoring scoreCard = new Scoring();


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



            for (int i = 0; i < 4; i++) {
                habitatChoice.add(game.getHabitatFromBag());
                tilesChoice.add(game.getTileFromTileBag());
                tilesChoice.get(i).setX(i * 4);
                tilesChoice.get(i).setY(0);
            }
            game.addChoiceTilesToChoiceBoard(tilesChoice);


            printer.displayChoiceTiles(game.getChoiceBoard());
            printer.println("");
            printer.displayHabitatChoices(habitatChoice);//Shows the 4 tiles available to select

            if(game.handleHabitatCulling(habitatChoice))
            {
                printer.println("New Wildlife tokens: ");
                printer.displayHabitatChoices(habitatChoice);
            }


            int tileChoice = (Integer.parseInt(config.prompt("\n[" + players.get(game.getCurrPlayer()).getName() + "]" + "Enter which tile you choose (1-4)")) - 1);
            int rowChoice = Integer.parseInt(config.prompt("Enter the row to place tile"));
            int columnChoice = Integer.parseInt(config.prompt("Enter column to place title"));

            //Tile Placement
            while(!game.checkTilePlacementPossible(columnChoice, rowChoice))
            {
                rowChoice = Integer.parseInt(config.prompt("Invalid Coordinates: Please try again\nrow: "));
                columnChoice = Integer.parseInt(config.prompt("column: "));

            }
            Tile chosenTile = tilesChoice.get(tileChoice);
            tilesChoice.remove(tileChoice);
            chosenTile.setX(columnChoice * 4);
            chosenTile.setY(rowChoice * 4);
            players.get(game.getCurrPlayer()).addTileToPlayerTiles(chosenTile);

            //remove unused tiles back to bag
            for(int i = 0; i < tilesChoice.size(); i++)
            {
                game.returnTileToBag(tilesChoice.get(i));
                tilesChoice.remove(tilesChoice.get(i));
            }




            Habitat h = habitatChoice.get(tileChoice);
            rowChoice = Integer.parseInt(config.prompt("Enter the row to place Wildlife token: "));
            columnChoice = Integer.parseInt(config.prompt("Enter column to place Wildlife token: "));

            //placing HabitatToken
            for(int i = 0; i < currTiles.size(); i++)
            {
                if(currTiles.get(i).getX() == columnChoice * 4 && currTiles.get(i).getY() == rowChoice * 4)
                {

                    Tile modifiedTile = currTiles.get(i);
                    modifiedTile.habitats.clear();
                    modifiedTile.habitats.add(h);
                    habitatChoice.remove(h);
                    players.get(game.getCurrPlayer()).removeTileFromPlayerTiles(currTiles.get(i));
                    players.get(game.getCurrPlayer()).addTileToPlayerTiles(modifiedTile);

                }
            }

            for(int i = 0; i < habitatChoice.size(); i++) game.returnHabitatToBag(habitatChoice.get(i));
            habitatChoice.clear();



            game.endTurn();
        }

        //Scoring
        int scoringCardNumber = new Random().nextInt(2) + 1;
        for(int i = 0; i < players.size(); i++)
        {
            players.get(i).addToScore(scoreCard.bearScoringCard(players.get(i).getPlayerTiles(), scoringCardNumber));
            players.get(i).addToScore(scoreCard.elkScoringCard(players.get(i).getPlayerTiles(), scoringCardNumber));
            players.get(i).addToScore(scoreCard.foxScoringCard(players.get(i).getPlayerTiles(), scoringCardNumber));
            players.get(i).addToScore(scoreCard.hawkScoringCard(players.get(i).getPlayerTiles(), scoringCardNumber));
            players.get(i).addToScore(scoreCard.salmonScoringCard(players.get(i).getPlayerTiles(), scoringCardNumber));
        }
    }
}