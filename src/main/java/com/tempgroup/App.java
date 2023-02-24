package com.tempgroup;

import com.tempgroup.application.controller.InputController;
import com.tempgroup.domain.models.GameConfiguration;

public class App {
    public static void main(String[] args) {
        InputController input = new InputController();
        GameConfiguration config = input.setupGame();
    }

}
