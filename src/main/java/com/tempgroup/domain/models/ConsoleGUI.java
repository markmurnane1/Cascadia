package com.tempgroup.domain.models;

public class ConsoleGUI {

    private int TILESIZE = 4;

    private int WIDTH = 200;
    private int HEIGHT = 200;

    public ConsoleGUI(){

    }
    public void display(Board board){
        String[][] gui = board.getBoard();
    }
}
