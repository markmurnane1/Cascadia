package com.tempgroup.domain.models;

import com.tempgroup.domain.models.Utility.DisplayColour;
import com.tempgroup.domain.models.Utility.Constants;

public class Board
{
    private int height;
    private int width;
    private String[][] board;

    public Board(int width, int height)
    {
        this.height = height;
        this.width = width;
        board = new String[width][height];

    }
    public String[][] getBoard()
    {
        return this.board;
    }
    public void setBoardPos(int x, int y, String token)
    {
        board[x][y] = token;
    }
    public String getBoardPos(int x, int y)
    {
        return board[x][y];
    }
    public int getHeight()
    {
        return this.height;
    }
    public int getWidth()
    {
        return this.width;
    }

    public void matrixToBoard(Tile[][] matrix){

        clearBoard();

        for(int i = 0; i < Constants.WIDTH; i++){
            for(int j = 0; j < Constants.HEIGHT; j++){
                this.addTileToBoard(matrix[i][j]);
            }
        }
    }
    private void clearBoard() {
        for (int row = 0; row < this.getWidth(); row++) {
            for (int column = 0; column < this.getHeight(); column++) {
                this.setBoardPos(row, column, DisplayColour.RESET + "#" + DisplayColour.RESET);
            }
        }
    }

    public void addTileToBoard(Tile t) // Graphically represent the tile on the string matrix board as a 4X4 tile
    {
        int x = t.getX() * Constants.TILESIZE;
        int y = t.getY() * Constants.TILESIZE;

        if(t.terrains.size() == 1)
        {
            for (int i = x; i < x + 4; i++) {
                for (int j = y; j < y + 4; j++) {

                    this.setBoardPos(i,j, t.terrains.get(0).getColour() + "#" + DisplayColour.RESET);
                }
            }
            if(t.habitats.size() == 1)
            {
                this.setBoardPos(x+1,y+1, t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET );
            }
            if(t.habitats.size() == 2)
            {
                this.setBoardPos(x+1,y+1, t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET );
                this.setBoardPos(x+2,y+1, t.habitats.get(1).getColour() + t.habitats.get(1).getSymbol() + DisplayColour.RESET );
            }
            if(t.habitats.size() == 3)
            {
                this.setBoardPos(x+1,y+1, t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET );
                this.setBoardPos(x+2,y+1, t.habitats.get(1).getColour() + t.habitats.get(1).getSymbol() + DisplayColour.RESET );
                this.setBoardPos(x+1,y+2, t.habitats.get(2).getColour() + t.habitats.get(2).getSymbol() + DisplayColour.RESET );
            }
        }
        if(t.terrains.size() == 2)
        {
            for (int i2 = x; i2 < x + 4; i2++) {
                for (int j2 = y; j2 < y + 4; j2++) {

                    this.setBoardPos(i2,j2, t.terrains.get(0).getColour() + "#" + DisplayColour.RESET);
                }
            }
            for (int i3 = x; i3 < x + 4; i3++) {
                for (int j3 = y+2; j3 < y + 4; j3++) {

                    this.setBoardPos(i3,j3, t.terrains.get(1).getColour() + "#" + DisplayColour.RESET);
                }
            }
            if(t.habitats.size() == 1)
            {
                this.setBoardPos(x+1,y+1, t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET );
            }
            if(t.habitats.size() == 2)
            {
                this.setBoardPos(x+1,y+1, t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET );
                this.setBoardPos(x+2,y+1, t.habitats.get(1).getColour() + t.habitats.get(1).getSymbol() + DisplayColour.RESET );
            }
            if(t.habitats.size() == 3)
            {
                this.setBoardPos(x+1,y+1, t.habitats.get(0).getColour() + t.habitats.get(0).getSymbol() + DisplayColour.RESET );
                this.setBoardPos(x+2,y+1, t.habitats.get(1).getColour() + t.habitats.get(1).getSymbol() + DisplayColour.RESET );
                this.setBoardPos(x+1,y+2, t.habitats.get(2).getColour() + t.habitats.get(2).getSymbol() + DisplayColour.RESET );
            }
        }
        if(t.habitats.isEmpty() && t.terrains.isEmpty()){
            for(int i = x; i < x + 4; i++){
                for(int j = y; j < y + 4; j++){
                    this.setBoardPos(i,j, DisplayColour.RESET + "#" + DisplayColour.RESET);
                }
            }
        }
    }
}
