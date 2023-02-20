package com.tempgroup;

//this class contains all player information
import java.util.*;
public class Player {

    private int score;
    private String name;
    private List<Tile> tiles; //Each player will have their own list of tiles we'll print on they're turn

    public Player() {
        tiles = new ArrayList<>();
    }
    public Player(String userName) {
        name = userName;
    }

    public void setName(String username) { this.name = username; }
    public void setScore(int x) { this.score = x; }

    public int getScore() { return this.score; }
    public String getName() { return this.name; }

    public void generateStarterHabitatTiles()
    {

    }
    public void generateTiles()
    {

    }
    public void generateHabitats()
    {

    }
}
