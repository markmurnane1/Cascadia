package com.tempgroup;

import java.util.ArrayList;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.application.controllers.InputController;
import com.tempgroup.application.controllers.PrintController;
import com.tempgroup.domain.models.GameConfiguration;
import com.tempgroup.domain.models.Player;

public class App {
    public static void main(String[] args) {
        PrintController printer = new PrintController();
        InputController input = new InputController();

        GameConfiguration config = new GameConfiguration();
        GameController game = new GameController(config);

        ArrayList<Player> players = game.getPlayers();
        System.out.println("\nPlaying order is:");
        for (int i = 0; i < players.size(); i++) {
            System.out.println(String.format("(%d): %s", i + 1, players.get(i).getName()));
        }
        System.out.println();
    }
}