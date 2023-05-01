package com.tempgroup.application.controllers;

import com.tempgroup.domain.models.Player.APlayer;
import com.tempgroup.domain.models.ScoreCard.AScoreCard;
import com.tempgroup.domain.models.Tile;
import com.tempgroup.domain.models.Utility.DisplayColour;
import com.tempgroup.application.services.PrintService;
import com.tempgroup.domain.models.Board;
import com.tempgroup.domain.models.Habitat;

import java.util.ArrayList;

public class PrintController {
	PrintService printService;

	public PrintController() {
		this.printService = new PrintService();
	}

	public void println(Object o) {
		this.printService.println(o);
	}

	public void print(Object o) {
		this.printService.print(o);
	}

	public void displayGameBoard(Board gameBoard)
	{
		int colCoord = 0;
		int rowIndex = 0;
		this.print(" ");
		for(int columnIndex = 0; columnIndex < gameBoard.getWidth(); columnIndex++)
		{
			if(columnIndex % 4 == 0)
			{
				this.print(colCoord);
				colCoord++;
			}else{
				this.print(" ");
			}
		}
		this.println("");

		for(int i = 0; i < gameBoard.getHeight(); i++)
		{
			if(i % 4 == 0)
			{
				this.print(rowIndex);
				rowIndex++;
			}else{
				this.print(" ");
			}
			for(int j = 0; j < gameBoard.getWidth(); j++)
			{

				this.print(gameBoard.getBoardPos(j,i));

			}
			this.println("");

		}
	}
	public void displayChoiceTiles(Board choiceBoard)
	{
		for(int i = 0; i < choiceBoard.getHeight(); i++)
		{
			for(int j = 0; j < choiceBoard.getWidth(); j++)
			{

				this.print(choiceBoard.getBoardPos(j,i));

			}
			this.println("");
		}

	}
	public void displayHabitatChoices(ArrayList<Habitat> habitats)
	{
		for(int i = 0; i < habitats.size(); i++)
		{
			this.print(habitats.get(i).getColour() + habitats.get(i).getSymbol() + DisplayColour.RESET);
			this.print(" ");
		}
	}
	public void displayScoring(AScoreCard scoreCard, APlayer player)
	{
		ArrayList<Tile> playerTiles = player.getPlayerTiles();
		Tile[][] tileMatrix = player.getPlayerTileMatrix();

		int salmon = scoreCard.SalmonScoreCard(tileMatrix, playerTiles);
		int bear = scoreCard.BearScoreCard(tileMatrix, playerTiles);
		int elk = scoreCard.ElkScoreCard(tileMatrix, playerTiles);
		int fox = scoreCard.FoxScoreCard(tileMatrix, playerTiles);
		int hawk = scoreCard.HawkScoreCard(tileMatrix, playerTiles);
		int corridorScore = scoreCard.countLargestConnectedHabitat(tileMatrix, playerTiles);

		System.out.println("\n\n------"+player.getName()+"'s Scorecard----------------");
		System.out.println("--------------------------------------");
		System.out.println("ELK: " + elk + "---------------------------------");
		System.out.println("SALMON: " + salmon + "-----------------------------");
		System.out.println("BEAR: " + bear + "-------------------------------");
		System.out.println("HAWK: " + hawk + "-------------------------------");
		System.out.println("FOX: " + fox + "--------------------------------");
		System.out.println("HABITAT CORRIDOR: " + corridorScore + "-------------------");
		System.out.println("--------------------------------------\n");
		System.out.println("TOTAL: " + (salmon+bear+elk+fox+hawk+corridorScore) + "--------------------------");

	}
	public void displayView(Board gameBoard, Board choiceBoard, ArrayList<Habitat> habitatChoice, String name)
	{
		displayGameBoard(gameBoard);
		print(name + "'s Choice Tiles: \n");
		displayChoiceTiles(choiceBoard);
		print(name + "'s Wildlife Tokens: \n");
		displayHabitatChoices(habitatChoice);
		print("\n");
	}


}
