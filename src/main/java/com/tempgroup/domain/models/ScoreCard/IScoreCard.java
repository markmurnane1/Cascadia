package com.tempgroup.domain.models.ScoreCard;

import com.tempgroup.domain.models.Tile;

public abstract class IScoreCard {

    public abstract int SalmonScoreCard(Tile[][] matrix);
    public abstract int ElkScoreCard(Tile[][] matrix);
    public abstract int BearScoreCard(Tile[][] matrix);
    public abstract int HawkScoreCard(Tile[][] matrix);
    public abstract int FoxScoreCard(Tile[][] matrix);

    public int score(Tile[][] matrix){

        return SalmonScoreCard(matrix) + ElkScoreCard(matrix) + BearScoreCard(matrix) + HawkScoreCard(matrix) + FoxScoreCard(matrix);
    }
}
