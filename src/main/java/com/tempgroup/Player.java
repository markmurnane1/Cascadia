package com.tempgroup;

//this class contains all player information

public class Player {

    private int score = 0;
    private String name;

    public Player() {

    }

    public void setName(String username) { this.name = username; }
    public void setScore(int x) { this.score = x; }

    public int getScore() { return this.score; }
    public String getName() { return this.name; }
}
