package com.tempgroup.application.controllers;

import java.io.Console;
import java.io.Reader;
import java.util.Observable;

public class InputController extends Observable {
	private PrintController printer;
	private Boolean shouldPrintUserInputs = true;

	public InputController() {
		this.printer = new PrintController();
	}

	public void listenForCommands() {
		Console cs = System.console();
		Reader reader = cs.reader();

		while (true) {
			try {
				int c = reader.read();

				// this switch must run constantly regardless of whether we are printing user
				// inputs as it allows the user to kill the application
				switch (c) {
					// ctrl+c
					case 3:
						this.setChanged();
						InputEvent quitEvent = new InputEvent(InputEvent.EventId.QUIT_GAME, null);
						notifyObservers(quitEvent);
						break;
					default:
						break;
				}

				if (shouldPrintUserInputs) {
					this.printer.print(String.valueOf(c));
					continue;
				}

			} catch (Exception e) {
				this.printer.println("Failed to read from input from console");
				System.exit(1);
			}
		}
	}

	public static class InputEvent {
		private EventId eventId;
		private Object value;

		public enum EventId {
			QUIT_GAME
		}

		public InputEvent(EventId event, Object value) {
			this.eventId = event;
			this.value = value;
		}

		public EventId getEventId() {
			return this.eventId;
		}

		public Object getValue() {
			return this.value;
		}
	}
}
