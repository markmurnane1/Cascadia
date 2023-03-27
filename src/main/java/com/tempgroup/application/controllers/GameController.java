package com.tempgroup.application.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import com.tempgroup.DisplayColour;
import com.tempgroup.application.services.PrintService;
import com.tempgroup.domain.models.*;

public class GameController {
	private ArrayList<Player> players;
	private int currPlayer;
	private ArrayList<Tile> tilesBag;
	private ArrayList<Habitat> habitatBag;
	private Board gameBoard;
	private Board choiceBoard;
	private Scanner scanner;

	public GameController(GameConfiguration config) {
		this.createPlayers(config);
		currPlayer = 0;
		tilesBag = new ArrayList<Tile>();
		tilesBag = config.getTileBag();

		habitatBag = new ArrayList<Habitat>();
		habitatBag = config.setupHabitatBag();

		gameBoard = new Board(config.WIDTH, config.HEIGHT);

		choiceBoard = new Board(16, 4); //Small board to graphically show our choice tiles
		scanner = config.getScanner();

		this.initStarterTiles();

	}

	private void createPlayers(GameConfiguration config) {
		this.players = new ArrayList<Player>();

		for (int i = 0; i < config.numPlayers; i++) {
			Player player = new Player(config.playerNames.get(i));
			this.players.add(player);
		}

		Collections.shuffle(this.players);
	}


	public void endTurn()
	{

		if(currPlayer == players.size() - 1)
		{
			currPlayer = 0;
		}else{
			this.currPlayer++;
		}
	}
	public void clearGameBoard()
	{
		for(int i = 0; i < gameBoard.getHeight(); i++)
		{
			for(int j = 0; j < gameBoard.getWidth(); j++)
			{
				gameBoard.setBoardPos(j,i, "#");
			}
		}
	}
	public void addChoiceTilesToChoiceBoard(ArrayList<Tile> choiceTiles)
	{
		for(int i = 0; i < choiceTiles.size(); i++)
		{
			choiceTiles.get(i).addTileToBoard(choiceBoard);
		}
	}

	public int getCurrPlayer() { return currPlayer; }

	public ArrayList<Player> getPlayers() {
		return this.players;
	}
	public Board getGameBoard() { return this.gameBoard; }
	public Board getChoiceBoard() { return this.choiceBoard; }

	public ArrayList<Tile> getTilesBag() { return this.tilesBag; }

	public Tile getTileFromTileBag() {

		Tile t = tilesBag.get(0);
		tilesBag.remove(0);

		return t;
	}

	public Habitat getHabitatFromBag()
	{
		Habitat h = habitatBag.get(0);
		habitatBag.remove(0);

		return h;
	}
	public void shuffleHabitatBag() { Collections.shuffle(this.habitatBag);}
	public void returnTileToBag(Tile t)
	{
		tilesBag.add(t);
	}
	public void returnHabitatToBag(Habitat h)
	{
		habitatBag.add(h);
	}
	public void initStarterTiles()
	{
		Tile[] starterTile;

		for(int i = 0; i < players.size(); i++)
		{
			starterTile = StarterHabitatTile.StarterTileContainer[i];

			starterTile[0].setX(gameBoard.getWidth() / 2);
			starterTile[0].setY(gameBoard.getHeight() / 2);

			starterTile[1].setX(gameBoard.getWidth() / 2);
			starterTile[1].setY((gameBoard.getHeight() / 2) - 4);

			starterTile[2].setX((gameBoard.getWidth() / 2) + 4);
			starterTile[2].setY(gameBoard.getHeight() / 2);

			for(int j = 0; j < 3; j++)
			{
				players.get(i).addTileToPlayerTiles(starterTile[j]);
			}
		}
	}
	public boolean checkTilePlacementPossible(int x, int y)
	{
		//If the x,y symbol isn't coloured then we can assume position empty
		if(gameBoard.getBoardPos(x*4,y*4).equals("#") && gameBoard.XwithinBounds(x) && gameBoard.YwithinBounds(y))
		{

			//top left
			if(x == 0 && y == 0) return !gameBoard.getBoardPos((x * 4) + 4, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) + 4).equals("#");

			//bottom left
			if(y*4 == gameBoard.getHeight() - 4 && x == 0) return !gameBoard.getBoardPos((x * 4) + 4, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) - 1).equals("#");

			//top right
			if(x*4 == gameBoard.getWidth() - 4 && y == 0) return  !gameBoard.getBoardPos((x * 4) - 1, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) + 4).equals("#");

			//along left side
			if(x == 0) return !gameBoard.getBoardPos((x * 4) + 4, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) + 4).equals("#") ||
									!gameBoard.getBoardPos(x * 4, (y * 4) - 1).equals("#");

			//along top
			if(y == 0) return !gameBoard.getBoardPos((x * 4) + 4, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) + 4).equals("#") ||
						!gameBoard.getBoardPos((x * 4) - 1, y * 4).equals("#");

			//along bottom
			if(y*4 == gameBoard.getHeight() - 4) return !gameBoard.getBoardPos((x * 4) + 4, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) - 1).equals("#") ||
					!gameBoard.getBoardPos((x * 4) - 1, y * 4).equals("#");

			//along right side
			if(x*4 == gameBoard.getWidth() - 4) return !gameBoard.getBoardPos((x * 4) - 1, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) + 4).equals("#") ||
					!gameBoard.getBoardPos(x * 4, (y * 4) - 1).equals("#");

			//check every direction
			return !gameBoard.getBoardPos((x * 4) - 1, y * 4).equals("#") || !gameBoard.getBoardPos(x * 4, (y * 4) - 1).equals("#") || !gameBoard.getBoardPos((x * 4) + 4, y * 4).equals("#") ||
					!gameBoard.getBoardPos(x * 4, (y * 4) + 4).equals("#");
		}

		return false;
	}

	public boolean handleHabitatCulling(ArrayList<Habitat> habitatList)
	{
		ArrayList<Habitat> sameTokens = new ArrayList<>();
		String choice;
		int same;


		for(int i = 0; i < habitatList.size(); i++)
		{
			same = 0;
			sameTokens.clear();

			for(int j = 0; j < habitatList.size(); j++) {

				if (habitatList.get(i).getToken() == (habitatList.get(j).getToken())) {
					same++;
					sameTokens.add(habitatList.get(j));
				}
			}

			if(sameTokens.size() == 4)
			{
				System.out.println("\nculling 4");
				same = 4;
				//return tokens to bag and get 4 more
				for(int l = 0; l < same; l++) returnHabitatToBag(habitatList.get(l));
				shuffleHabitatBag();
				sameTokens.clear();
				for(int o = 0; o < same; o++) sameTokens.add(getHabitatFromBag());

				return true;
			}
			if(sameTokens.size() == 3)
			{
				System.out.println("\nculling 3");
				same = 3;
				System.out.println("Would you like to cull (y/n)");
				choice = scanner.nextLine();

				if(choice.equals("y") || choice.equals("Y"))
				{
					for(int n = 0; n < habitatList.size(); n++){
						returnHabitatToBag(habitatList.get(n));
					}
					habitatList.clear();

					shuffleHabitatBag();

					for(int o = 0; o < 4; o++) habitatList.add(getHabitatFromBag());

					return true;
				}else{
					return false;
				}
			}
		}

		return false;
	}
	public boolean checkGameOver()
	{
		if(this.tilesBag.size() <= 3)
		{
			return true;
		}
		return false;
	}

}
