package com.tempgroup;

public class Habitat {
    private String colour;
    private String symbol;
    private String habit;

    public Habitat(String habitat) {
        switch (habitat) {
            case "FOX":
                colour = DisplayColour.ORANGE;
                symbol = "F";
                break;
            case "HAWK":
                colour = DisplayColour.PURPLE;
                symbol = "H";
                break;
            default:
                colour = DisplayColour.RED;
                symbol = "0";
                break;
        }
    }

    public String getColour() {
        return this.colour;
    }

    public String getSymbol() {
        return this.symbol;
    }
}
