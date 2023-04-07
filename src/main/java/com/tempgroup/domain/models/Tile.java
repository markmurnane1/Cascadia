package com.tempgroup.domain.models;

import com.tempgroup.DisplayColour;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tile {

    public List<Habitat> habitats; //tile can have up to 3 wildlife token
    public List<Terrain> terrains; //tiles can have upto 2 terrains
    public Habitat finalHabitat;
    boolean wildLifeTokenPlaced = false;
    private int x, y;



    public Tile()
    {
        habitats = new ArrayList<>();
        terrains = new ArrayList<>();
    }
    public Tile(Terrain terrain, Habitat t)
    {


        habitats = new ArrayList<Habitat>();
        habitats.add(t);

        terrains = new ArrayList<Terrain>();
        terrains.add(terrain);

    }

    public Tile(Terrain terrain, Terrain terrainTwo, Habitat t) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);

        terrains = new ArrayList<Terrain>();
        terrains.add(terrain);
        terrains.add(terrainTwo);

    }
    public Tile(Terrain terrain, Terrain terrainTwo, Habitat t, Habitat t2) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);
        habitats.add(t2);

        terrains = new ArrayList<Terrain>();
        terrains.add(terrain);
        terrains.add(terrainTwo);

    }
    public Tile(Terrain terrain, Terrain terrainTwo, Habitat t, Habitat t2, Habitat t3) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);
        habitats.add(t2);
        habitats.add(t3);

        terrains = new ArrayList<Terrain>();
        terrains.add(terrain);
        terrains.add(terrainTwo);

    }
    public Tile(Terrain terrain, Habitat t, Habitat t2, Habitat t3) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);
        habitats.add(t2);
        habitats.add(t3);

        terrains = new ArrayList<Terrain>();
        terrains.add(terrain);

    }
    public Tile(Terrain terrain, Habitat t, Habitat t2) {
        habitats = new ArrayList<Habitat>();
        habitats.add(t);
        habitats.add(t2);

        terrains = new ArrayList<Terrain>();
        terrains.add(terrain);

    }
    public int getX() { return this.x; }
    public int getY() { return this.y; }

    public void setX(int _x) { this.x = _x; }
    public void setY(int _y) { this.y = _y; }

    public boolean emptyTile(){
        return this.habitats.isEmpty() && this.terrains.isEmpty();
    }


}
