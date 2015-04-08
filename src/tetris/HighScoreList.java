package tetris;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * HighScoreList is used to keep a record of all high scores recorded during the current program instance
 */
public class HighScoreList {
    private static final HighScoreList INSTANCE = new HighScoreList();
    private List<HighScore> highScores;

    private HighScoreList() {
        highScores = new ArrayList<>();
        addRandomHighScores();

    }

    public static HighScoreList getIntance() {
        return INSTANCE;
    }

    /**
     * Adds a new high score to the HighScoreList and sorts it (highest to lowest).
     * @param highScore
     */
    public void addHighScore(HighScore highScore) {
        this.highScores.add(highScore);
        Collections.sort(highScores, new ScoreComparator());
    }

    public List<HighScore> getHighScores() {
        return highScores;
    }

    // Adds some high scores to fill up the list
    private void addRandomHighScores() {
        int highScoresToAdd = 10;
        for (int i = 1; i <= highScoresToAdd; i++) {
            this.highScores.add(new HighScore("Player " + i, 1000*i));
        }
    }
}
