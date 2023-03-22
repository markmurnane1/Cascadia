package com.tempgroup.domain.models;

import java.util.ArrayList;

//The five starters are predetermined
public class StarterHabitatTile {

    public static Tile[] StarterHabitatTileOne = {
            new Tile(new Terrain(TerrainType.WETLAND), new Habitat(HabitatToken.HAWK)),
            new Tile(new Terrain(TerrainType.FOREST), new Terrain(TerrainType.RIVER), new Habitat(HabitatToken.SALMON), new Habitat(HabitatToken.HAWK), new Habitat(HabitatToken.ELK)),
            new Tile(new Terrain(TerrainType.PRAIRIE), new Terrain(TerrainType.MOUNTAIN), new Habitat(HabitatToken.BEAR), new Habitat(HabitatToken.FOX))
    };

    public static Tile[] StarterHabitatTileTwo = {
            new Tile(new Terrain(TerrainType.MOUNTAIN), new Habitat(HabitatToken.BEAR)),
            new Tile(new Terrain(TerrainType.RIVER), new Terrain(TerrainType.PRAIRIE), new Habitat(HabitatToken.SALMON), new Habitat(HabitatToken.BEAR)),
            new Tile(new Terrain(TerrainType.FOREST), new Terrain(TerrainType.WETLAND), new Habitat(HabitatToken.HAWK), new Habitat(HabitatToken.ELK), new Habitat(HabitatToken.FOX))
    };

    public static Tile[] StarterHabitatTileThree = {
            new Tile(new Terrain(TerrainType.FOREST), new Habitat(HabitatToken.ELK)),
            new Tile(new Terrain(TerrainType.RIVER), new Habitat(HabitatToken.HAWK), new Habitat(HabitatToken.ELK), new Habitat(HabitatToken.BEAR)),
            new Tile(new Terrain(TerrainType.PRAIRIE), new Terrain(TerrainType.WETLAND), new Habitat(HabitatToken.FOX), new Habitat(HabitatToken.SALMON))
    };

    public static Tile[] StarterHabitatTileFour = {
            new Tile(new Terrain(TerrainType.RIVER), new Habitat(HabitatToken.SALMON)),
            new Tile(new Terrain(TerrainType.PRAIRIE), new Terrain(TerrainType.FOREST), new Habitat(HabitatToken.SALMON), new Habitat(HabitatToken.BEAR), new Habitat(HabitatToken.ELK)),
            new Tile(new Terrain(TerrainType.MOUNTAIN), new Terrain(TerrainType.WETLAND), new Habitat(HabitatToken.FOX), new Habitat(HabitatToken.HAWK))
    };

    public static Tile[] StarterHabitatTileFive = {
            new Tile(new Terrain(TerrainType.PRAIRIE), new Habitat(HabitatToken.FOX)),
            new Tile(new Terrain(TerrainType.WETLAND), new Terrain(TerrainType.RIVER), new Habitat(HabitatToken.SALMON), new Habitat(HabitatToken.FOX), new Habitat(HabitatToken.HAWK)),
            new Tile(new Terrain(TerrainType.FOREST), new Terrain(TerrainType.MOUNTAIN), new Habitat(HabitatToken.BEAR), new Habitat(HabitatToken.ELK))
    };

    public static Tile[][] StarterTileContainer = {StarterHabitatTileOne, StarterHabitatTileTwo, StarterHabitatTileThree, StarterHabitatTileThree, StarterHabitatTileFour, StarterHabitatTileFive};

}
