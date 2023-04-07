package com.tempgroup.application.controllers;

import com.tempgroup.DisplayColour;
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

		this.println("\nYour tiles: ");
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
		this.println("Your Wildlife Tokens: ");
		for(int i = 0; i < habitats.size(); i++)
		{
			this.print(habitats.get(i).getColour() + habitats.get(i).getSymbol() + DisplayColour.RESET);
			this.print(" ");
		}
	}

}
