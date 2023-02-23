package com.tempgroup.application.services;

/*
 * PrinterService assumes the user is using some form of xterm (https://en.wikipedia.org/wiki/Xterm) 
 * based terminal. You can verify this from the shell: `echo $TERM` example output: "xterm-256color".
 */
public class PrinterService {
	public PrinterService() {
		try {
			this.enterRawMode();
			this.printHelpBar();
		} catch (Exception e) {
			System.out.println("Unable to move terminal to raw mode.");
			System.exit(1);
		}
	}

	/*
	 * enterRawMode switches the terminal to raw mode (i.e. not canonical mode)
	 * We also switch the terminal to use the alternate buffer for printing.
	 * "https://www.gnu.org/software/libc/manual/html_node/Canonical-or-Not.html"
	 * "https://invisible-island.net/xterm/ctlseqs/ctlseqs.html#h2-The-Alternate-Screen-Buffer"
	 */
	private void enterRawMode() throws Exception {
		System.out.print("\033[?1049h");

		try {
			String[] cmd = { "/bin/sh", "-c", "stty raw </dev/tty" };
			Runtime.getRuntime().exec(cmd).waitFor();
		} catch (Exception e) {
			throw e;
		}

		// register a shutdown hook to make sure we return the user to primary buffer
		// and canonical mode before the program ends.
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			System.out.print("\033[?1049l");

			try {
				String[] exitCmd = { "/bin/sh", "-c", "stty cooked </dev/tty" };
				Runtime.getRuntime().exec(exitCmd).waitFor();
			} catch (Exception e) {
			}
		}));
	}

	private void moveCursor(int x, int y) {
		System.out.print(String.format("\033[%d;%dH", y, x));
	}

	private void printHelpBar() {
		this.moveCursor(0, 9999); // bottom left
		System.out.print("Hit q to quit, h for help");
	}
}
