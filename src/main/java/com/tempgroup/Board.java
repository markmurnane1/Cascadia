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
        board[t.getY() + 1][t.getX() + 1] = t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET;
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
    public void setup(Scanner in)
    {
        this.displayWelcome();

        int numUsers;
        String name = "";
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
            this.playersSetup(name);
        }
    }
}
