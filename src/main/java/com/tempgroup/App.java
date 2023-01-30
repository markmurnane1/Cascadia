package com.tempgroup;

import java.io.Console;
import java.io.Reader;

import com.tempgroup.application.services.PrinterService;

public class App {
    public static void main(String[] args) {
        new PrinterService();

        Console cs = System.console();
        Reader reader = cs.reader();

        while (true) {
            try {
                int c = reader.read();

                switch (c) {
                    case 3:
                        System.exit(0);
                        break;
                    case 113:
                        System.exit(0);
                        break;
                    default:
                        break;
                }

            } catch (Exception e) {
                System.out.println("Failed to read from input from console");
                System.exit(1);
            }
        }
    }
}
