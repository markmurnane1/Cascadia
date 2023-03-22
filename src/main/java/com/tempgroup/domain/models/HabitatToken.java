package com.tempgroup.domain.models;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public enum HabitatToken
{
    HAWK,
    BEAR,
    ELK,
    SALMON,
    FOX;

    public static final List<HabitatToken> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
}
