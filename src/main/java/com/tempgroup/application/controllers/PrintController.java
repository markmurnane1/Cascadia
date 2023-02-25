package com.tempgroup.application.controllers;

import com.tempgroup.application.services.PrintService;

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
}
