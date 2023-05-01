package com.tempgroup.application.controllers;

import java.util.*;
import java.util.stream.Collectors;

import com.tempgroup.domain.models.*;
import com.tempgroup.domain.models.Move.MaxScoreStrategy;
import com.tempgroup.domain.models.Move.RandomMoveStrategy;
import com.tempgroup.domain.models.Player.APlayer;
import com.tempgroup.domain.models.Player.AiPlayer;
import com.tempgroup.domain.models.Utility.Constants;
import com.tempgroup.domain.models.Utility.Point;

public class GameController {
	private ArrayList<APlayer> players;
	private int currPlayer;
	private ArrayList<Tile> tilesBag;
	private ArrayList<Habitat> habitatBag;
	private Board gameBoard;
	private Board choiceBoard;

	public GameController(GameConfiguration config) {
		this.createPlayers(config);
		currPlayer = 0;
		tilesBag = config.getTileBag();
		habitatBag = config.setupHabitatBag();

		gameBoard = new Board(Constants.WIDTH * Constants.TILESIZE, Constants.HEIGHT * Constants.TILESIZE);

		choiceBoard = new Board(16, 4); //Small board to graphically show our choice tiles

		this.initStarterTiles();

	}

	private void createPlayers(GameConfiguration config) {
		this.players = new ArrayList<APlayer>();


		APlayer bot1 = new AiPlayer(config.playerNames.get(0), new MaxScoreStrategy(), this);
		APlayer bot2 = new AiPlayer(config.playerNames.get(1), new MaxScoreStrategy(), this);

		this.players.add(bot1);
		this.players.add(bot2);
	}

	public void endTurn(ArrayList<Tile> tilesChoice, ArrayList<Habitat> habitatChoice)
	{
		if(currPlayer == players.size() - 1)
		{
			currPlayer = 0;
		}else{
			this.currPlayer++;
		}
		for(Habitat h : habitatChoice)
		{
			returnHabitatToBag(h);
		}
		habitatChoice.clear();
		for(Tile t : tilesChoice)
		{
			returnTileToBag(t);
		}
		tilesChoice.clear();
	}
	public void addChoiceTilesToChoiceBoard(ArrayList<Tile> choiceTiles)
	{
		for(int i = 0; i < choiceTiles.size(); i++)
		{
			choiceBoard.addTileToBoard(choiceTiles.get(i));
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
		LinkedHashSet<HabitatToken> tokens = new LinkedHashSet<>();

		for(Habitat habitat : habitatList)
		{
			tokens.add(habitat.getToken());
		}
		//All 4 are the same
		if(tokens.size() == 1 || tokens.size() == 2)
		{
			for(int i = 0; i < 4; i++) {
				returnHabitatToBag(habitatList.get(i));
			}
			habitatList.clear();
			shuffleHabitatBag();
			for(int j = 0; j < 4; j++)
				habitatList.add(getHabitatFromBag());

			return true;
		}
		return false;
	}
	public List<Point> validMoves(Tile[][] tileMatrix, ArrayList<Tile> playerTiles)
	{
		List<Point> possibleMoves = new ArrayList<>();

		for(Tile t : playerTiles)
		{
			if(!t.emptyTile()) {
				int x = t.getX();
				int y = t.getY();

				if (tileMatrix[x + 1][y].emptyTile() && x+1 < Constants.WIDTH - 1) {
					possibleMoves.add(new Point(x + 1, y));
				}
				if (tileMatrix[x - 1][y].emptyTile() && x - 1 > 0) {
					possibleMoves.add(new Point(x - 1, y));
				}
				if (tileMatrix[x][y + 1].emptyTile() && y + 1 < Constants.HEIGHT - 1) {
					possibleMoves.add(new Point(x, y + 1));
				}
				if (tileMatrix[x][y - 1].emptyTile() && y - 1 > 0) {
					possibleMoves.add(new Point(x, y - 1));
				}

			}
		}

		return possibleMoves.stream().distinct().collect(Collectors.toList());
	}
	public ArrayList<Tile> getValidHabitatMoves(ArrayList<Tile> playerTiles, Habitat h) {
		ArrayList<Tile> possibleTiles = new ArrayList<>();

		for(Tile t : playerTiles)
		{
			if(t.finalHabitat == null)
			{
				for(Habitat habitat : t.habitats)
				{
					if(habitat.getToken() == h.getToken())
						possibleTiles.add(t);
				}
			}
		}
		return possibleTiles;
	}
	public void updateDeck(ArrayList<Tile> tilesChoice, ArrayList<Habitat> habitatChoice)
	{
		for (int i = 0; i < 4; i++) {
			habitatChoice.add(getHabitatFromBag());
			tilesChoice.add(getTileFromTileBag());
			tilesChoice.get(i).setX(i);
			tilesChoice.get(i).setY(0);
		}

		//Returns true if culling
		while(handleHabitatCulling(habitatChoice));
	}
	public void updateView(ArrayList<Tile> tilesChoice, Board gameBoard, Tile[][] playerTileMatrix)
	{
			addChoiceTilesToChoiceBoard(tilesChoice);
			gameBoard.matrixToBoard(playerTileMatrix);
	}
}
