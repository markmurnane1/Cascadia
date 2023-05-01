package com.tempgroup.domain.models.Player;

import com.tempgroup.domain.models.ScoreCard.AScoreCard;
import com.tempgroup.domain.models.Utility.Constants;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;

public abstract class APlayer {
    private String name;
    private int turnsTaken;
    private ArrayList<Tile> playerTiles;
    private Tile[][] playerTileMatrix;

    APlayer(String name){
        this.name = name;
        turnsTaken = 0;
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
    public String getName()
    {
        return name;
    }
    public int getTurnsTaken()
    {
        return this.turnsTaken;
    }
    public void incrementTurnTaken()
    {
        this.turnsTaken++;
    }
    public ArrayList<Tile> getPlayerTiles()
    {
        return this.playerTiles;
    }
    public Tile[][] getPlayerTileMatrix()
    {
        return this.playerTileMatrix;
    }
    public void addTileToPlayerTiles(Tile t)
    {
        playerTileMatrix[t.getX()][t.getY()] = t;
        playerTiles.add(t);
    }
    public abstract void takeTileTurn(ArrayList<Tile> choiceTiles, AScoreCard scoreCard);
    public abstract void takeHabitatTurn(ArrayList<Habitat> choiceHabitat, AScoreCard scoreCard);
}
