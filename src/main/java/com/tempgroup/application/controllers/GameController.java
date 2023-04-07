package com.tempgroup.application.controllers;

import java.util.*;

import com.tempgroup.domain.models.*;
import com.tempgroup.domain.models.Move.RandomMoveStrategy;
import com.tempgroup.domain.models.Player.APlayer;
import com.tempgroup.domain.models.Player.AiPlayer;

public class GameController {
	private ArrayList<APlayer> players;
	private int currPlayer;
	private ArrayList<Tile> tilesBag;
	private ArrayList<Habitat> habitatBag;
	private Board gameBoard;
	private Board choiceBoard;
	private Scanner scanner;

	public GameController(GameConfiguration config) {
		this.createPlayers(config);
		currPlayer = 0;
		tilesBag = config.getTileBag();
		habitatBag = config.setupHabitatBag();

		gameBoard = new Board(Constants.WIDTH * Constants.TILESIZE, Constants.HEIGHT * Constants.TILESIZE);

		choiceBoard = new Board(16, 4); //Small board to graphically show our choice tiles
		scanner = config.getScanner();

		this.initStarterTiles();

	}

	private void createPlayers(GameConfiguration config) {
		this.players = new ArrayList<APlayer>();

		/*for (int i = 0; i < config.numPlayers; i++) {
			Player player = new Player(config.playerNames.get(i));
			this.players.add(player);
		}*/
		//Collections.shuffle(this.players);
		APlayer bot1 = new AiPlayer("Bot1", new RandomMoveStrategy(), this);
		APlayer bot2 = new AiPlayer("Bot2", new RandomMoveStrategy(), this);

		this.players.add(bot1);
		this.players.add(bot2);
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

	public void addChoiceTilesToChoiceBoard(Tile[] choiceTiles)
	{
		for(int i = 0; i < choiceTiles.length; i++)
		{
			choiceBoard.addTileToBoard(choiceTiles[i]);
		}
	}

	public int getCurrPlayer() { return currPlayer; }

	public ArrayList<APlayer> getPlayers() {
		return this.players;
	}
	public Board getGameBoard() { return this.gameBoard; }
	public Board getChoiceBoard() { return this.choiceBoard; }


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

			starterTile[0].setX(Constants.WIDTH / 2);
			starterTile[0].setY(Constants.HEIGHT / 2);

			starterTile[1].setX(Constants.WIDTH / 2);
			starterTile[1].setY((Constants.HEIGHT / 2) - 1);

			starterTile[2].setX((Constants.WIDTH / 2) + 1);
			starterTile[2].setY(Constants.HEIGHT / 2);

			for(int j = 0; j < 3; j++)
			{
				players.get(i).addTileToPlayerTiles(starterTile[j]);
			}
		}
	}
	public boolean handleHabitatCulling(ArrayList<Habitat> habitatList)
	{
		ArrayList<Habitat> sameTokens = new ArrayList<>();

		String choice;
		int same;



		for(int i = 0; i < habitatList.size(); i++) {
			same = 0;

			for (int j = 0; j < habitatList.size(); j++) {

				if (habitatList.get(i).getToken() == (habitatList.get(j).getToken())) {
					same++;
					sameTokens.add(habitatList.get(j));
				}
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
	public List<Point> validMoves(Tile[][] tileMatrix){
		List<Point> possibleMoves = new ArrayList<>();

		for(int row = 1; row < Constants.WIDTH - 1; row++){

			for(int column = 1; column < Constants.HEIGHT - 1; column++){

				if(tileMatrix[row][column].emptyTile()){

					if(!tileMatrix[row+1][column].emptyTile()){
						Point p = new Point(row, column);
						possibleMoves.add(p);

					}else if(!tileMatrix[row-1][column].emptyTile()){
						Point p = new Point(row, column);
						possibleMoves.add(p);

					}else if(!tileMatrix[row][column+1].emptyTile()){
						Point p = new Point(row, column);
						possibleMoves.add(p);

					}else if(!tileMatrix[row+1][column-1].emptyTile()){
						Point p = new Point(row, column);
						possibleMoves.add(p);

					}

				}
			}
		}

		return possibleMoves;
	}


	public List<Tile> getValidHabitatMoves(ArrayList<Tile> playerTiles, Habitat h) {
		List<Tile> possibleTiles = new ArrayList<>();

		for(int i = 0; i < playerTiles.size(); i++){

			for(int j = 0; j < playerTiles.get(i).habitats.size(); j++){

				if(playerTiles.get(i).habitats.get(j).getToken().equals(h.getToken())){

					if(playerTiles.get(i).finalHabitat == null){

						possibleTiles.add(playerTiles.get(i));
					}
				}
			}
		}

		return possibleTiles;
	}
}
