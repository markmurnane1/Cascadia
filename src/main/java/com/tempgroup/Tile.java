package com.tempgroup;

public class Tile {

    private int x;
    private int y;
    private String colour;

    public Tile(int x_, int y_, String colour_) {

            x = x_;
            y = y_;

        colour = colour_;
    }
    public int getX() {
        return this.x;
    }
    public int getY() {
        return this.y;
    }
    public String getColour() {
        return this.colour;
    }
}
