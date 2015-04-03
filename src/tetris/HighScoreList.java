package tetris;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class HighScoreList {
    private static final HighScoreList INSTANCE = new HighScoreList();
    private List<HighScore> highScores;

    private HighScoreList() {
        highScores = new ArrayList<>();
    }

    public static HighScoreList getIntance() {
        return INSTANCE;
    }

    public void addHighScore(HighScore highScore) {
        this.highScores.add(highScore);
        Collections.sort(highScores, new ScoreComparator());
    }


    public List<HighScore> getHighScores() {
        return highScores;
    }
}
