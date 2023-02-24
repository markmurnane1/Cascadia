package com.tempgroup.application.controller;

import java.util.ArrayList;
import java.util.Collections;

import com.tempgroup.domain.models.GameConfiguration;
import com.tempgroup.domain.models.Player;

public class GameController {
	private ArrayList<Player> players;

	public GameController(GameConfiguration config) {
		this.createPlayers(config);
	}

	private void createPlayers(GameConfiguration config) {
		this.players = new ArrayList<Player>();

		for (int i = 0; i < config.numPlayers; i++) {
			Player player = new Player(config.playerNames.get(i));
			this.players.add(player);
		}

		Collections.shuffle(this.players);
	}

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
}
