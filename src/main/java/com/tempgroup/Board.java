package com.tempgroup;

//game model class that contains all game information
import java.util.*;

public class Board {

    private int HEIGHT = 50;
    private int WIDTH = 50;
    private ArrayList<Player> players;
    private String[][] board;

    public Board() {

        players = new ArrayList<Player>();
        board = new String[HEIGHT][WIDTH];
    }
    public void playersSetup(String name) {

        Player player = new Player();
        player.setName(name);
        players.add(player);

    }
    public void randomizePlayers() {
        Collections.shuffle(players);
    }
    public void displayMoveOrder(int index) {

        System.out.println("It is " + players.get(index).getName() + "'s turn");
    }
    public boolean isGameOver()
    {
        return false;
    }

    public void populateBoard() {

        for(int y = 0; y < HEIGHT; y++)
        {
            for(int x = 0; x < WIDTH; x++)
            {
                board[y][x] = "|";
            }
        }
    }
    public void drawTiles(Tile t) {
        for(int y = 0; y < HEIGHT; y++)
        {
            for(int x = 0; x < WIDTH; x++)
            {
                if(t.getX() == x && t.getY() == y)
                {
                    for(int y2 = y; y2 <= y+3; y2++)
                    {
                        for(int x2 = x; x2 <= x+3; x2++)
                        {
                            board[y2][x2] = t.getColour() + "/" + DisplayColour.RESET;
                        }

                    }

                }
            }

        }
    }
    public void drawBoard() {
        for(int i = 0; i < WIDTH; i++)
        {
            for(int j = 0; j < HEIGHT; j++)
            {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }
    public void displayWelcome() {
        System.out.println("Welcome to Cascadia");
        System.out.println("");
        System.out.println("Enter Q to quit");
        System.out.println("Please enter the number of users (2-4)");

    }
}
