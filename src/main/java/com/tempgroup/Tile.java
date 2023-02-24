package com.tempgroup;

import java.util.ArrayList;
import java.util.List;

public class Tile {
    private int x;
    private int y;
    public List<Habitat> habitats;
    private String colour;

    public Tile(int x_, int y_, String colour_, Habitat t) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);

        x = x_;
        y = y_;

        colour = colour_;
    }

    public Tile(int x_, int y_, String colour_, Habitat t, Habitat t2) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);
        habitats.add(t2);

        x = x_;
        y = y_;

        colour = colour_;
    }

    public Tile(int x_, int y_, String colour_, Habitat t, Habitat t2, Habitat t3) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);
        habitats.add(t2);
        habitats.add(t3);

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