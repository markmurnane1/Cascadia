package com.tempgroup;


import java.util.*;
import com.sun.tools.jdeprscan.scan.Scan;
import com.tempgroup.application.services.PrinterService;

public class App {
    public static void main(String[] args) {

        Board board = new Board();
        Scanner in = new Scanner(System.in);

        boolean setupComplete = false;
        int numUsers = 0;
        int iterator = 0;
        String name = "";
        board.displayWelcome();

        while(true) {
            try{
                numUsers = in.nextInt();
                if(numUsers >= 2 && numUsers <= 4)
                {
                    break;
                }else{
                    System.out.println("Please enter a num between 2-4");
                }

            } catch (Exception e) {
                System.out.println("Please enter a number between 2-4");
            }
        }
        for(int i = 1; i < (numUsers + 1); i++)
        {
            System.out.println("Enter username for player " + i);
            name = in.next();
            board.playersSetup(name);
        }
        board.populateBoard();

        //testing ascii
        Tile t = new Tile(25, 25, DisplayColour.BLUE);
        Tile t1 = new Tile(25, 29, DisplayColour.RED);
        Tile t2 = new Tile(29, 25, DisplayColour.YELLOW);
        board.drawTiles(t);
        board.drawTiles(t1);
        board.drawTiles(t2);
        board.drawBoard();
        board.displayMoveOrder(iterator);
        /*do{
            int iterator = 0;
        } while(!command.isQuit() && !board.isGameOver()); */
    }

}
