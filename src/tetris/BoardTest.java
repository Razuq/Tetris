package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * BoardTest is the class used for running the game.
 * It creates a new tetris frame and then starts a game loop,
 * which in turn calls on the tick method in the TetrisGame class.
 */
final public class BoardTest {
    private static final int BOARD_WIDTH = 16;
    private static final int BOARD_HEIGHT = 20;
    private static final int TIMER_INTERVAL = 300; // In milliseconds

    private BoardTest() {}

    public static void main(String[] args) {
        TetrisFrame tFrame = new TetrisFrame(new Board(BOARD_WIDTH, BOARD_HEIGHT));

        final Action progressGame = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               tFrame.getBoard().getGame().tick();

            }};

            final Timer clockTimer = new Timer(TIMER_INTERVAL, progressGame);
            clockTimer.setCoalesce(true);
            clockTimer.start();
    }
}
