package tetris;

import java.io.Serializable;

/**
 * The HighScore class is used to create a new high score when the player looses.
 */
public class HighScore {
    private String name;
    private int score;

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
