package tetris;

import java.util.Comparator;

public class ScoreComparator implements Comparator<HighScore> {

    @Override
    public int compare(HighScore hs1, HighScore hs2) {
        return hs2.getScore() - hs1.getScore();
    }
}
