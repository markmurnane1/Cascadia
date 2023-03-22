package com.tempgroup.domain.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum TerrainType
{
    FOREST,
    WETLAND,
    RIVER,
    MOUNTAIN,
    PRAIRIE;

    public static final List<TerrainType> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
}
