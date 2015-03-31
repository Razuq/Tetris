package tetris;

import javax.swing.*;


public class Score extends JLabel implements BoardListener {
    Board board;
    public Score(Board board) {
        this.board = board;
        this.setText("Score: " + board.getGame().getScore());
    }

    public void boardChanged() {
        this.setText("Score: " + board.getGame().getScore());
        this.revalidate();
    }
}
