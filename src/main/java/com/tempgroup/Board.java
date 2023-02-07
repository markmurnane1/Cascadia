package com.tempgroup;

//game model class that contains all game information
import java.util.*;

public class Board {

    private ArrayList<Player> players;

    public Board() {
        players = new ArrayList<Player>();
    }
    public void playersSetup(String name) {

        Player player = new Player();
        player.setName(name);
        players.add(player);

    }
    public void randomizePlayers() {
        Collections.shuffle(players);
    }
    public void displayMoveOrder() {
        System.out.println("\n|ORDER|\n");
        for(int i = 0; i < players.size(); i++)
        {

            System.out.print((i + 1) + ": " + players.get(i).getName() + " ");
        }
        System.out.println("");
    }
}
