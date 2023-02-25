package com.tempgroup.domain.models;

import java.util.ArrayList;
import java.util.Scanner;

public class GameConfiguration {
	private Scanner scanner;

	public int numPlayers;
	public ArrayList<String> playerNames;

	private String prompt(String q) {
		System.out.println(q);

		String next = scanner.nextLine();

		return next;
	}

	public GameConfiguration() {
		this.scanner = new Scanner(System.in);

		this.setupGame();
	}

	private void setupGame() {
		int numPlayers = 0;

		while (numPlayers < 2 || numPlayers > 4) {
			try {
				numPlayers = Integer.parseInt(prompt("How many players are playing? (2-4)"));
			} catch (Exception e) {
				System.out.println("You must enter an integer value between 2 and 4.\n");
			}
		}

		this.numPlayers = numPlayers;

		ArrayList<String> playerNames = new ArrayList<String>();

		for (int i = 0; i < this.numPlayers; i++) {
			String playerName = "";

			while (playerName.isEmpty()) {
				playerName = prompt(String.format("\nEnter name of player %d", i + 1));
			}

			playerNames.add(playerName);
		}

		this.playerNames = playerNames;
	}
}
