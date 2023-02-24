package com.tempgroup.domain.models;

import java.util.ArrayList;

public class GameConfiguration {
	private int numPlayers;
	private ArrayList<String> playerNames;

	public void setNumPlayers(int n) {
		this.numPlayers = n;
	}

	public int getNumPlayers() {
		return this.numPlayers;
	}

	public void setPlayerNames(ArrayList<String> playerNames) {
		this.playerNames = playerNames;
	}

	public ArrayList<String> getPlayerNames() {
		return this.playerNames;
	}
}
