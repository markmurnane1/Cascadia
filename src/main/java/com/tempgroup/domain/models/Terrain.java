package com.tempgroup.domain.models;

import com.tempgroup.domain.models.Utility.DisplayColour;

public class Terrain {
    private String colour;
    private TerrainType type;

    public Terrain(TerrainType t) {
        switch (t) {
            case FOREST:
                colour = DisplayColour.GREEN;
                break;
            case WETLAND:
                colour = DisplayColour.PURPLE;
                break;
            case MOUNTAIN:
                colour = DisplayColour.CYAN;
                break;
            case PRAIRIE:
                colour = DisplayColour.YELLOW;
                break;
            case RIVER:
                colour = DisplayColour.BLUE;
                break;
            default:
                colour = DisplayColour.RED;
                break;
        }

        type = t;
    }

    public String getColour() {
        return this.colour;
    }

    public TerrainType getTerrain() {
        return this.type;
    }

}
