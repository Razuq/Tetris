package tetris;

import java.io.Serializable;

/**
 * HighScore is a class used for adding new high scores.
 */
public class HighScore {
    private String name;
    private int score;

    /**
     * Creates the HighScore object
     * @param name  the name of the player
     * @param score the final score the player managed to get
     */
    public HighScore(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
}
