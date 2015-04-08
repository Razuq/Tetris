package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * This class is used to start the game
 * it also contains the time-loop that drives the game forward
 */
public class BoardTest {
    private static final int BOARD_WIDTH = 16;
    private static final int BOARD_HEIGHT = 20;
    private static final int TIMER_INTERVAL = 300; // In milliseconds


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
