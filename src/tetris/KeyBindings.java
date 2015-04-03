package tetris;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class KeyBindings {

    private TetrisComponent tc;
    private TetrisGame tg;
    private TetrisFrame tf;

    public KeyBindings(TetrisFrame tf, TetrisComponent tc) {
        this.tc = tf.tetrisComponent;
        this.tg = tf.getBoard().getGame();
        this.tf = tf;
        bindKeys();
    }

    public void bindKeys() {
        leftArrow();
        rightArrow();
        upArrow();
        space();
        enter();
    }

    private void leftArrow() {
        Action moveLeft = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tg.movePolyLeft();
            }
        };

        tc.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "moveLeft");
        tc.getActionMap().put("moveLeft", moveLeft);
    }

    private void rightArrow() {
        System.out.println("Adding rightarrow keybind");
        Action moveRight = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tg.movePolyRight();
            }
        };

        tc.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "moveRight");
        tc.getActionMap().put("moveRight", moveRight);
    }

    private void upArrow() {
        Action rotate = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                tg.rotatePoly();
            }
        };

        tc.getInputMap().put(KeyStroke.getKeyStroke("UP"), "rotate");
        tc.getActionMap().put("rotate", rotate);
    }

    private void space() {
        Action floorPoly = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                tg.hardDrop();
            }
        };

        tc.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "floor");
        tc.getActionMap().put("floor", floorPoly);
    }

    private void enter() {
        Action newGame = new AbstractAction() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                if (tg.getState() == TetrisGame.States.GAME_OVER) {
                    tf.newGame();
                    System.out.println("New Game!");
                }
            }
        };

        tc.getInputMap().put(KeyStroke.getKeyStroke("ENTER"), "newGame");
        tc.getActionMap().put("newGame", newGame);

    }

    public void clearBindings() {
        tc.getInputMap().clear();
        tc.getActionMap().clear();
    }
}
