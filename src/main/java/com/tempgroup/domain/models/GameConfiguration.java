package com.tempgroup.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GameConfiguration {
	private Scanner scanner;

	public int numPlayers;
	public boolean running = true;
	public int HEIGHT = 8*4;
	public int WIDTH = 14*4;
	public ArrayList<String> playerNames;
	ArrayList<Tile> tileBag = new ArrayList<Tile>();

	public String prompt(String q) {
		System.out.println(q);

		String next = scanner.nextLine();

		return next;
	}

	public GameConfiguration() {
		this.scanner = new Scanner(System.in);

		this.setupGame();
		this.setupTileBag();
		this.setupHabitatBag();
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

	private void setupTileBag()  //Generate 100 tiles
	{
		tileBag = new ArrayList<>();

		for(int i = 0; i < 100; i++)
		{
			tileBag.add(new Tile());
		}

	}
	public ArrayList<Tile> getTileBag()
	{
		return this.tileBag;
	}
	public Scanner getScanner() { return this.scanner; }

	public ArrayList<Habitat> setupHabitatBag()
	{
		ArrayList<Habitat> habitatBag = new ArrayList<>();


		for(int i = 0; i < 20; i++)
		{
			habitatBag.add(new Habitat(HabitatToken.BEAR));
		}
		for(int i = 0; i < 20; i++)
		{
			habitatBag.add(new Habitat(HabitatToken.ELK));
		}
		for(int i = 0; i < 20; i++)
		{
			habitatBag.add(new Habitat(HabitatToken.FOX));
		}
		for(int i = 0; i < 20; i++)
		{
			habitatBag.add(new Habitat(HabitatToken.HAWK));
		}
		for(int i = 0; i < 20; i++)
		{
			habitatBag.add(new Habitat(HabitatToken.SALMON));
		}

		Collections.shuffle(habitatBag);

		return habitatBag;
	}

}
