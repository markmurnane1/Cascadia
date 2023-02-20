package com.tempgroup;


import java.util.*;
import com.tempgroup.application.services.PrinterService;

public class App {
    public static void main(String[] args) {

        Board board = new Board();
        Scanner in = new Scanner(System.in);



        board.setup(in);

        board.populateBoard();

        //testing ascii
        Tile t = new Tile(25, 25, DisplayColour.BLUE, new Habitat("FOX"));
        Tile t1 = new Tile(25, 29, DisplayColour.RED, new Habitat("HAWK"));
        Tile t2 = new Tile(29, 25, DisplayColour.YELLOW, new Habitat("FOX"));
        board.drawTiles(t);
        board.drawTiles(t1);
        board.drawTiles(t2);
        board.drawBoard();

        /*do{
            int iterator = 0;

            board.populateBoard();
            board.drawTiles(board.players.get(iterator));
            board.drawBoard();

            iterator++;

            if(iterator == (players.size() - 1)
            {
                iterator = 0;
            }

        } while(!command.isQuit() && !board.isGameOver()); */
    }

}
