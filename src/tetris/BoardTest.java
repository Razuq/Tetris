package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Class is used to run various tests as the development goes along
 */
public class BoardTest {
    private static final int BOARD_WIDTH = 16;
    private static final int BOARD_HEIGHT = 20;

    public static void main(String[] args) {
        Board board = new Board(BOARD_WIDTH, BOARD_HEIGHT);


        TetrisFrame tFrame = new TetrisFrame(board);

        final Action progressGame = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
               tFrame.getBoard().getGame().tick();

            }};

            final Timer clockTimer = new Timer(300, progressGame);
            clockTimer.setCoalesce(true);
            clockTimer.start();


    }
}
