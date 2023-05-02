package com.tempgroup.domain.models.Player;

import com.tempgroup.domain.models.ScoreCard.AScoreCard;
import com.tempgroup.domain.models.Utility.Constants;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;

public abstract class APlayer {
    private String name;
    private int turnsTaken;
    private int natureTokens;
    private ArrayList<Tile> playerTiles;
    private Tile[][] playerTileMatrix;

    APlayer(String name) {
        this.name = name;
        this.turnsTaken = 0;
        this.natureTokens = 0;
        this.playerTiles = new ArrayList<>();
        this.playerTileMatrix = new Tile[Constants.WIDTH][Constants.HEIGHT];

        // Add empty tiles
        for (int i = 0; i < Constants.WIDTH; i++) {
            for (int j = 0; j < Constants.HEIGHT; j++) {
                Tile t = new Tile();
                t.setX(i);
                t.setY(j);
                this.playerTileMatrix[i][j] = t;
            }
        }
    }

    public void addNatureToken() {
        this.natureTokens++;
    }

    public void spendNatureToken() {
        this.natureTokens--;
    }

    public int getNatureToken() {
        return this.natureTokens;
    }

    public String getName() {
        return name;
    }

    public int getTurnsTaken() {
        return this.turnsTaken;
    }

    public void incrementTurnTaken() {
        this.turnsTaken++;
    }

    public ArrayList<Tile> getPlayerTiles() {
        return this.playerTiles;
    }

    public Tile[][] getPlayerTileMatrix() {
        return this.playerTileMatrix;
    }

    public void addTileToPlayerTiles(Tile t) {
        this.playerTileMatrix[t.getX()][t.getY()] = t;
        this.playerTiles.add(t);
    }

    public abstract void takeTileTurn(ArrayList<Tile> choiceTiles, AScoreCard scoreCard);

    public abstract void takeHabitatTurn(ArrayList<Habitat> choiceHabitat, AScoreCard scoreCard);
}
