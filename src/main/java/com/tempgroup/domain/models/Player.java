package com.tempgroup.domain.models;

import java.util.ArrayList;

public class Player {
	private String name;
	private int score;

	private ArrayList<Tile> playerTiles;

	public Player(String name) {
		this.name = name;
		this.playerTiles = new ArrayList<Tile>();
	}

	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Tile> getPlayerTiles() { return this.playerTiles; }
}
