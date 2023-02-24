package com.tempgroup.domain.models;

public class Player {
	private String name;
	private int score;

	public Player(String name) {
		this.name = name;
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
}
