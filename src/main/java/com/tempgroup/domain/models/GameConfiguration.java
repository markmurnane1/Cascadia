package com.tempgroup.domain.models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class GameConfiguration {
	private Scanner scanner;

	public int numPlayers;
	public boolean running = true;

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

		this.numPlayers = 2;

		ArrayList<String> playerNames = new ArrayList<String>();

		for (int i = 0; i < this.numPlayers; i++) {
			String playerName = "";

			while (playerName.isEmpty()) {
				playerName = prompt(String.format("\nEnter name of Bot %d", i + 1));
			}

			playerNames.add(playerName);
		}

		this.playerNames = playerNames;
	}
	private void initKeyStoneTile(ArrayList<Tile> tileBag)
	{
		for(Tile t : tileBag)
		{
			if(t.terrains.size() == 1 && t.habitats.size() == 1)
			{
				t.makeKeyStoneTile();
			}
		}
	}

	private void setupTileBag()  //Generate 100 tiles
	{
		tileBag = new ArrayList<>();

		for(int i = 0; i < 200; i++)
		{
			Tile tile = new Tile();

			int numOfTerrains = new Random().nextInt(2) + 1;
			int numOfHabitats = new Random().nextInt(2) + 1;

			tile.habitats = new ArrayList<Habitat>();
			for(int j = 0; j < numOfHabitats; j++)
			{
				int pickHabitat = new Random().nextInt(HabitatToken.values().length);
				Habitat h = new Habitat(HabitatToken.VALUES.get(pickHabitat));
				tile.habitats.add(h);
			}

			tile.terrains = new ArrayList<Terrain>();
			for(int j = 0; j < numOfTerrains; j++)
			{
				int pickTerrain = new Random().nextInt(TerrainType.values().length);
				Terrain t = new Terrain(TerrainType.VALUES.get(pickTerrain));
				tile.terrains.add(t);
			}

			tileBag.add(tile);
		}

		initKeyStoneTile(tileBag);
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
