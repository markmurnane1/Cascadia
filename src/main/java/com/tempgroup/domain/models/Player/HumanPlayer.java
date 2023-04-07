package com.tempgroup.domain.models.Player;

import com.tempgroup.application.controllers.GameController;
import com.tempgroup.domain.models.Habitat;
import com.tempgroup.domain.models.Point;
import com.tempgroup.domain.models.Tile;

import java.util.ArrayList;
import java.util.Scanner;

public class HumanPlayer extends APlayer {

	private GameController game;
	private Scanner userInput;

	public HumanPlayer(String name, GameController game) {
		super(name);
		this.game = game;

	}


	@Override
	public void takeTileTurn(Tile[] choiceTiles) {

	}

	@Override
	public void takeHabitatTurn(ArrayList<Habitat> choiceHabitat) {

	}
}
