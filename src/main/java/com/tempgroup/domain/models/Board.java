package com.tempgroup.domain.models;

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

}
