package com.tempgroup.domain.models.Player;

import com.tempgroup.domain.models.Constants;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Point;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;

public abstract class APlayer {
    private String name;
    private int score;
    private ArrayList<Tile> playerTiles; //useful for placing habitats
    private Tile[][] playerTileMatrix;

    APlayer(String name){
        this.name = name;
        this.score = 0;
        playerTiles = new ArrayList<>();
        playerTileMatrix = new Tile[Constants.WIDTH][Constants.HEIGHT];

        for(int i = 0; i < Constants.WIDTH; i++){
            for(int j = 0; j < Constants.HEIGHT; j++){
                Tile t = new Tile();
                t.setX(i);
                t.setY(j);
                playerTileMatrix[i][j] = t;
            }
        }
    }
    public String getName(){
        return name;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
    public ArrayList<Tile> getPlayerTiles() {
        return this.playerTiles;
    }
    public Tile[][] getPlayerTileMatrix(){ return this.playerTileMatrix; }
    public void addTileToPlayerTiles(Tile t) {
        playerTileMatrix[t.getX()][t.getY()] = t;
        playerTiles.add(t);
    }
    public void removeTileFromPlayerTiles(Tile t) {
        Tile t2 = new Tile(); //empty tile
        t2.setX(t.getX());
        t2.setY(t.getY());
        playerTileMatrix[t.getX()][t.getY()] = t2;

    }
    public abstract void takeTileTurn(Tile[] choiceTiles);
    public abstract void takeHabitatTurn(ArrayList<Habitat> choiceHabitat);
}
