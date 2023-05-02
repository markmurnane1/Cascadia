package com.tempgroup.domain.models;

import com.tempgroup.domain.models.Utility.DisplayColour;

public class Habitat {
    private String colour;
    private String symbol;
    private HabitatToken token;

    public Habitat(HabitatToken h) {
        switch (h) {
            case FOX:
                colour = DisplayColour.ORANGE;
                symbol = "F";
                break;
            case HAWK:
                colour = DisplayColour.PURPLE;
                symbol = "H";
                break;
            case BEAR:
                colour = DisplayColour.BLUE; // ADD brown
                symbol = "B";
                break;
            case ELK:
                colour = DisplayColour.BLACK;
                symbol = "E";
                break;
            case SALMON:
                colour = DisplayColour.RED;
                symbol = "S";
                break;
            default:
                colour = DisplayColour.RED;
                symbol = "0";
                break;
        }

        token = h;
    }

    public String getColour() {
        return this.colour;
    }

    public String getSymbol() {
        return this.symbol;
    }

    public HabitatToken getToken() {
        return this.token;
    }

}
