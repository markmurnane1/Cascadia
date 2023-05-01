package com.tempgroup.domain.models.ScoreCard;

import com.tempgroup.domain.models.HabitatToken;
import com.tempgroup.domain.models.Terrain;
import com.tempgroup.domain.models.TerrainType;
import com.tempgroup.domain.models.Tile;
import com.tempgroup.domain.models.Utility.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AScoreCard {

    public abstract int SalmonScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles);

    public abstract int ElkScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles);

    public abstract int BearScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles);

    public abstract int HawkScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles);

    public abstract int FoxScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles);

    public int score(Tile[][] matrix, ArrayList<Tile> playerTiles) {

        return SalmonScoreCard(matrix, playerTiles) + ElkScoreCard(matrix, playerTiles) + BearScoreCard(matrix, playerTiles) + HawkScoreCard(matrix, playerTiles) + FoxScoreCard(matrix, playerTiles);
    }

    ArrayList<HabitatToken> getAllNeighboursWildLifeToken(Tile[][] matrix, Tile t) {
        ArrayList<HabitatToken> neighbours = new ArrayList<>();

        if (matrix[t.getX() + 1][t.getY()].finalHabitat != null)
            neighbours.add(matrix[t.getX() + 1][t.getY()].finalHabitat.getToken());
        if (matrix[t.getX() - 1][t.getY()].finalHabitat != null)
            neighbours.add(matrix[t.getX() - 1][t.getY()].finalHabitat.getToken());
        if (matrix[t.getX()][t.getY() + 1].finalHabitat != null)
            neighbours.add(matrix[t.getX()][t.getY() + 1].finalHabitat.getToken());
        if (matrix[t.getX()][t.getY() - 1].finalHabitat != null)
            neighbours.add(matrix[t.getX()][t.getY() - 1].finalHabitat.getToken());

        return neighbours;
    }

    ArrayList<Tile> getAllNeighbours(Tile[][] matrix, Tile t) {
        ArrayList<Tile> neighbours = new ArrayList<>();


        if (matrix[t.getX() + 1][t.getY()].terrains.size() > 0) neighbours.add(matrix[t.getX() + 1][t.getY()]);
        if (matrix[t.getX() - 1][t.getY()].terrains.size() > 0) neighbours.add(matrix[t.getX() - 1][t.getY()]);
        if (matrix[t.getX()][t.getY() + 1].terrains.size() > 0) neighbours.add(matrix[t.getX()][t.getY() + 1]);
        if (matrix[t.getX()][t.getY() - 1].terrains.size() > 0) neighbours.add(matrix[t.getX()][t.getY() - 1]);

        return neighbours;
    }
    public int countLargestConnectedHabitat(Tile[][] matrix, ArrayList<Tile> playerTiles)
    {
        int count;
        int x,y;
        ArrayList<Integer> lineCount = new ArrayList<>();

        //Since we check every Elk wildlife tile in each direction, we never want to begin the start of a new line
        //on a tile that has already been scored because we can assume we have already counted that line
        List<Tile> alreadyCountedTiles = new ArrayList<Tile>();

        for(Tile t : playerTiles)
        {
            TerrainType currentType = t.terrains.get(0).getTerrain();
            count = 1;
            alreadyCountedTiles.add(t);
            x = t.getX();
            y = t.getY();

            //Keep counting in each direction for terrains wildlifes
            while(matrix[x+1][y].terrains.size() > 0 && !alreadyCountedTiles.contains(matrix[x+1][y]))
            {
                alreadyCountedTiles.add(matrix[x+1][y]);

                if(terrainsTouching(t, matrix[x+1][y], currentType))
                {
                    count++;
                    x += 1;
                }

            }
            while(matrix[x-1][y].terrains.size() > 0 && !alreadyCountedTiles.contains(matrix[x-1][y]))
            {
                alreadyCountedTiles.add(matrix[x-1][y]);

                if(terrainsTouching(t, matrix[x-1][y], currentType))
                {
                    count++;
                    x -= 1;
                }

            }
            while(matrix[x][y+1].terrains.size() > 0 && !alreadyCountedTiles.contains(matrix[x][y+1]))
            {
                alreadyCountedTiles.add(matrix[x][y+1]);

                if(terrainsTouching(t, matrix[x][y+1], currentType))
                {
                    count++;
                    y += 1;
                }

            }
            while(matrix[x][y-1].terrains.size() > 0 && !alreadyCountedTiles.contains(matrix[x][y-1]))
            {
                alreadyCountedTiles.add(matrix[x][y-1]);

                if(terrainsTouching(t, matrix[x][y-1], currentType))
                {
                    count++;
                    y -= 1;
                }

            }

            lineCount.add(count);
        }



        return Collections.max(lineCount);
    }

    private boolean terrainsTouching(Tile tileOne, Tile tileTwo, TerrainType type) {
        int x1 = tileOne.getX();
        int y1 = tileOne.getY();
        int x2 = tileTwo.getX();
        int y2 = tileTwo.getY();
        /*
             ----
             #### |
             #### | if tile has two terrains the first one will refer to the top and right
             #### | position of the tile
             #### | So if the tile has two terrains and the other tile has two terrains the second
                    tile terrain must be terrain.get(0)

            terrain.get(1) will refer to bottom and left
        */
        if (tileOne.terrains.isEmpty() || tileTwo.terrains.isEmpty()) return false;

        if (tileOne.terrains.size() == 1 && tileTwo.terrains.size() == 1) {

            return tileOne.terrains.get(0).getTerrain() == type && tileTwo.terrains.get(0).getTerrain() == type;

        }

        if (tileOne.terrains.size() == 2 && tileTwo.terrains.size() == 2) {
            if (tileOne.terrains.get(0).getTerrain() == tileTwo.terrains.get(1).getTerrain() && (x2 == x1 + 1 || y2 == y1 - 1)) {
                return true;
            }
            return tileOne.terrains.get(1).getTerrain() == tileTwo.terrains.get(0).getTerrain() && (x2 == x1 - 1 || y2 == y1 + 1);
        }

        return false;
    }
}