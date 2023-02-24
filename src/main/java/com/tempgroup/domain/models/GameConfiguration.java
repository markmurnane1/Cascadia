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
		try {
			int numPlayers = Integer.parseInt(prompt("How many players are playing? (2-4)"));

			if (numPlayers < 2 || numPlayers > 4) {
				throw new IllegalArgumentException();
			}

			this.numPlayers = numPlayers;
		} catch (Exception e) {
			System.out.println("You must enter an integer value between 2 and 4.\n");
			// return this.setupGame();
		}

		ArrayList<String> playerNames = new ArrayList<String>();

		for (int i = 0; i < this.numPlayers; i++) {
			String playerName = prompt(String.format("\nEnter name of player %d", i + 1));

			if (playerName.length() == 0) {
				System.out.println("Player name must be longer than 0 characters\n");
				// return this.setupGame();
			}

			playerNames.add(playerName);
		}

		this.playerNames = playerNames;
	}
}
