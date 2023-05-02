package com.tempgroup.domain.models.ScoreCard;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.tempgroup.domain.models.HabitatToken;
import com.tempgroup.domain.models.Tile;

public class ScoreCardA extends AScoreCard {
    @Override
    public int SalmonScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles) {
        int score = 0;
        int count;
        int x;
        int y;

        ArrayList<Integer> runCount = new ArrayList<>();

        // Run should only be started on tiles that have not been scored yet
        List<Tile> alreadyCountedTiles = new ArrayList<Tile>();

        for (Tile t : playerTiles) {
            if (t.finalHabitat != null && t.finalHabitat.getToken() == HabitatToken.ELK
                    && !alreadyCountedTiles.contains(t)) {
                alreadyCountedTiles.add(t);
                x = t.getX();
                y = t.getY();
                count = 1;

                // Keep counting in each direction for elk wildlifes
                if (matrix[x + 1][y].finalHabitat != null
                        && matrix[x + 1][y].finalHabitat.getToken() == HabitatToken.SALMON
                        && !alreadyCountedTiles.contains(t)) {
                    while (matrix[x + 1][y].finalHabitat != null
                            && matrix[x + 1][y].finalHabitat.getToken() == HabitatToken.SALMON
                            && !alreadyCountedTiles.contains(t)) {
                        alreadyCountedTiles.add(matrix[x + 1][y]);
                        count++;
                        x += 1;
                    }
                }
                if (matrix[x - 1][y].finalHabitat != null
                        && matrix[x - 1][y].finalHabitat.getToken() == HabitatToken.SALMON
                        && !alreadyCountedTiles.contains(t)) {
                    while (matrix[x - 1][y].finalHabitat != null
                            && matrix[x - 1][y].finalHabitat.getToken() == HabitatToken.SALMON
                            && !alreadyCountedTiles.contains(t)) {
                        alreadyCountedTiles.add(matrix[x - 1][y]);
                        count++;
                        x -= 1;
                    }
                }

                if (matrix[x][y + 1].finalHabitat != null
                        && matrix[x][y + 1].finalHabitat.getToken() == HabitatToken.SALMON
                        && !alreadyCountedTiles.contains(t)) {
                    while (matrix[x][y + 1].finalHabitat != null
                            && matrix[x][y + 1].finalHabitat.getToken() == HabitatToken.SALMON
                            && !alreadyCountedTiles.contains(t)) {
                        alreadyCountedTiles.add(matrix[x][y + 1]);
                        count++;
                        y += 1;
                    }
                }
                if (matrix[x][y - 1].finalHabitat != null
                        && matrix[x][y - 1].finalHabitat.getToken() == HabitatToken.SALMON
                        && !alreadyCountedTiles.contains(t)) {
                    while (matrix[x][y - 1].finalHabitat != null
                            && matrix[x][y - 1].finalHabitat.getToken() == HabitatToken.SALMON
                            && !alreadyCountedTiles.contains(t)) {
                        alreadyCountedTiles.add(matrix[x][y - 1]);
                        count++;
                        y -= 1;
                    }
                }
                runCount.add(count);
            }

        }

        for (Integer run : runCount) {
            if (run == 0)
                score += 0;
            else if (run == 1)
                score += 2;
            else if (run == 2)
                score += 4;
            else if (run == 3)
                score += 7;
            else if (run == 4)
                score += 11;
            else if (run == 5)
                score += 15;
            else if (run == 6)
                score += 20;
            else
                score += 26;
        }
        return score;
    }

    @Override
    public int ElkScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles) {
        int score = 0;
        int count;
        int x, y;
        ArrayList<Integer> lineCount = new ArrayList<>();

        // Since we check every Elk wildlife tile in each direction, we never want to
        // begin the start of a new line
        // on a tile that has already been scored because we can assume we have already
        // counted that line
        List<Tile> alreadyCountedTiles = new ArrayList<Tile>();

        for (Tile t : playerTiles) {
            if (t.finalHabitat != null && t.finalHabitat.getToken() == HabitatToken.ELK
                    && !alreadyCountedTiles.contains(t)) {
                alreadyCountedTiles.add(t);
                x = t.getX();
                y = t.getY();
                count = 1;

                // Keep counting in each direction for elk wildlifes
                if (matrix[x + 1][y].finalHabitat != null
                        && matrix[x + 1][y].finalHabitat.getToken() == HabitatToken.ELK
                        && !alreadyCountedTiles.contains(matrix[x + 1][y])) {
                    while (matrix[x + 1][y].finalHabitat != null
                            && matrix[x + 1][y].finalHabitat.getToken() == HabitatToken.ELK
                            && !alreadyCountedTiles.contains(matrix[x + 1][y])) {
                        alreadyCountedTiles.add(matrix[x + 1][y]);
                        count++;
                        x += 1;
                    }
                }
                if (matrix[x - 1][y].finalHabitat != null
                        && matrix[x - 1][y].finalHabitat.getToken() == HabitatToken.ELK
                        && !alreadyCountedTiles.contains(matrix[x - 1][y])) {
                    while (matrix[x - 1][y].finalHabitat != null
                            && matrix[x - 1][y].finalHabitat.getToken() == HabitatToken.ELK
                            && !alreadyCountedTiles.contains(matrix[x - 1][y])) {
                        alreadyCountedTiles.add(matrix[x - 1][y]);
                        count++;
                        x -= 1;
                    }
                }
                lineCount.add(count);
                count = 0;
                if (matrix[x][y + 1].finalHabitat != null
                        && matrix[x][y + 1].finalHabitat.getToken() == HabitatToken.ELK
                        && !alreadyCountedTiles.contains(matrix[x][y + 1])) {
                    while (matrix[x][y + 1].finalHabitat != null
                            && matrix[x][y + 1].finalHabitat.getToken() == HabitatToken.ELK
                            && !alreadyCountedTiles.contains(matrix[x][y + 1])) {
                        alreadyCountedTiles.add(matrix[x][y + 1]);
                        count++;
                        y += 1;
                    }
                }
                if (matrix[x][y - 1].finalHabitat != null
                        && matrix[x][y - 1].finalHabitat.getToken() == HabitatToken.ELK
                        && !alreadyCountedTiles.contains(matrix[x][y - 1])) {
                    while (matrix[x][y - 1].finalHabitat != null
                            && matrix[x][y - 1].finalHabitat.getToken() == HabitatToken.ELK
                            && !alreadyCountedTiles.contains(matrix[x][y - 1])) {
                        alreadyCountedTiles.add(matrix[x][y - 1]);
                        count++;
                        y -= 1;
                    }
                }
                if (count > 1)
                    lineCount.add(count);
            }
        }

        for (int line : lineCount) {
            if (line == 1)
                score += 2;
            else if (line == 2)
                score += 5;
            else if (line == 3)
                score += 9;
            else
                score += 12;
        }

        return score;
    }

    @Override
    public int BearScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles) { // Unique pairs of bears
        int score = 0;
        int count = 0;
        List<Tile> alreadyCountedTiles = new ArrayList<Tile>();

        for (Tile t : playerTiles) {
            count = 0;
            if (t.finalHabitat != null && t.finalHabitat.getToken() == HabitatToken.BEAR
                    && !alreadyCountedTiles.contains(t)) {
                alreadyCountedTiles.add(t);

                List<Tile> neighbours = getAllNeighbours(matrix, t);

                for (Tile neighbour : neighbours) {
                    if (neighbour.finalHabitat != null && neighbour.finalHabitat.getToken() == HabitatToken.BEAR) {
                        alreadyCountedTiles.add(neighbour);
                        count++;
                    }

                }
                if (count == 1)
                    score += 1;
            }
        }

        if (score == 1)
            return 4;
        if (score == 2)
            return 11;
        if (score == 3)
            return 19;
        if (score == 4)
            return 27;

        return 0;
    }

    @Override
    // Total number of Hawks that are not adjacent to any other hawk
    public int HawkScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles) {
        int score = 0;
        ArrayList<Tile> neighbours = new ArrayList<>();
        ArrayList<Tile> alreadyCountedTiles = new ArrayList<>();

        for (Tile t : playerTiles) {
            if (t.finalHabitat != null) {
                if (t.finalHabitat.getToken() == HabitatToken.HAWK && !alreadyCountedTiles.contains(t)) {
                    alreadyCountedTiles.add(t);
                    neighbours = getAllNeighbours(matrix, t);
                    score++;
                }
                for (Tile t2 : neighbours) {
                    if (t2.finalHabitat != null && t2.finalHabitat.getToken() == HabitatToken.HAWK)
                        alreadyCountedTiles.add(t2);
                }
            }
        }

        if (score == 0)
            return 0;
        else if (score == 1)
            return 2;
        else if (score == 2)
            return 5;
        else if (score == 3)
            return 8;
        else if (score == 4)
            return 11;
        else if (score == 5)
            return 14;
        else if (score == 6)
            return 18;
        else if (score == 7)
            return 22;
        else
            return 26;
    }

    @Override
    public int FoxScoreCard(Tile[][] matrix, ArrayList<Tile> playerTiles) {

        int score = 0;
        // Hashset only stores unique values so we can add all neighbours
        HashSet<HabitatToken> uniqueNeighbours;

        for (Tile t : playerTiles) {
            if (t.finalHabitat != null && t.finalHabitat.getToken() == HabitatToken.FOX) {
                uniqueNeighbours = new HashSet<>(getAllNeighboursWildLifeToken(matrix, t));
                score += uniqueNeighbours.size();
            }
        }

        return score;
    }
}
