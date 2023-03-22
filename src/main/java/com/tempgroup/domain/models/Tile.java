package com.tempgroup.domain.models;

import com.tempgroup.DisplayColour;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Tile {

    public List<Habitat> habitats; //tile can have up to 3 wildlife token
    public List<Terrain> terrains; //tiles can have upto 2 terrains
    private int x, y; // Coords of top-left



    public Tile() //constructor for tile with random Terrains and habitats
    {

        int numOfTerrains = new Random().nextInt(2) + 1;
        int numOfHabitats = new Random().nextInt(2) + 1;

        habitats = new ArrayList<Habitat>();
        for(int i = 0; i < numOfHabitats; i++)
        {
             int pickHabitat = new Random().nextInt(HabitatToken.values().length);
             Habitat h = new Habitat(HabitatToken.VALUES.get(pickHabitat));
             habitats.add(h);
        }

        terrains = new ArrayList<Terrain>();
        for(int i = 0; i < numOfTerrains; i++)
        {
            int pickTerrain = new Random().nextInt(TerrainType.values().length);
            Terrain t = new Terrain(TerrainType.VALUES.get(pickTerrain));
            terrains.add(t);
        }
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


    public void addTileToBoard(Board board) // Graphically represent the tile on the string matrix board as a 4X4 tile
    {
        if(terrains.size() == 1)
        {
            for (int i = x; i < x + 4; i++) {
                for (int j = y; j < y + 4; j++) {

                    board.setBoardPos(i,j, terrains.get(0).getColour() + "#" + DisplayColour.RESET);
                }
            }
            if(habitats.size() == 1)
            {
                board.setBoardPos(x+1,y+1, habitats.get(0).getColour() + habitats.get(0).getSymbol() + DisplayColour.RESET );
            }
            if(habitats.size() == 2)
            {
                board.setBoardPos(x+1,y+1, habitats.get(0).getColour() + habitats.get(0).getSymbol() + DisplayColour.RESET );
                board.setBoardPos(x+2,y+1, habitats.get(1).getColour() + habitats.get(1).getSymbol() + DisplayColour.RESET );
            }
            if(habitats.size() == 3)
            {
                board.setBoardPos(x+1,y+1, habitats.get(0).getColour() + habitats.get(0).getSymbol() + DisplayColour.RESET );
                board.setBoardPos(x+2,y+1, habitats.get(1).getColour() + habitats.get(1).getSymbol() + DisplayColour.RESET );
                board.setBoardPos(x+1,y+2, habitats.get(2).getColour() + habitats.get(2).getSymbol() + DisplayColour.RESET );
            }
        }
        if(terrains.size() == 2)
        {
            for (int i2 = x; i2 < x + 4; i2++) {
                for (int j2 = y; j2 < y + 4; j2++) {

                    board.setBoardPos(i2,j2, terrains.get(0).getColour() + "#" + DisplayColour.RESET);
                }
            }
            for (int i3 = x; i3 < x + 4; i3++) {
                for (int j3 = y+2; j3 < y + 4; j3++) {

                    board.setBoardPos(i3,j3, terrains.get(1).getColour() + "#" + DisplayColour.RESET);
                }
            }
            if(habitats.size() == 1)
            {
                board.setBoardPos(x+1,y+1, habitats.get(0).getColour() + habitats.get(0).getSymbol() + DisplayColour.RESET );
            }
            if(habitats.size() == 2)
            {
                board.setBoardPos(x+1,y+1, habitats.get(0).getColour() + habitats.get(0).getSymbol() + DisplayColour.RESET );
                board.setBoardPos(x+2,y+1, habitats.get(1).getColour() + habitats.get(1).getSymbol() + DisplayColour.RESET );
            }
            if(habitats.size() == 3)
            {
                board.setBoardPos(x+1,y+1, habitats.get(0).getColour() + habitats.get(0).getSymbol() + DisplayColour.RESET );
                board.setBoardPos(x+2,y+1, habitats.get(1).getColour() + habitats.get(1).getSymbol() + DisplayColour.RESET );
                board.setBoardPos(x+1,y+2, habitats.get(2).getColour() + habitats.get(2).getSymbol() + DisplayColour.RESET );
            }
        }
    }


}
