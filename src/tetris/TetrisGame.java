package tetris;

import java.util.Random;

import tetris.CollisionDetector.Side;

import javax.swing.*;

/**
 * This class contains the game mechanics for the game such as moving the blocks and collision checks
 */

public class TetrisGame {
    public static enum States {
        GAME_OVER, RUN
    }

    private States state = States.RUN;
    public Board board;

    private int score = 0;

    public TetrisGame(Board b) {
        this.board = b;
    }

    /**
     * The tick method is used to make the game progress forward.
     * It is supposed to be called from a game loop.
     * It will check if the board has a falling poly, if it has one
     * it will try to move it one step down or write it to the board.
     * If there is none it will try to add a new one.
     */
    public void tick() {
        switch (state) {
            case RUN:
                boolean hasFalling = (this.board.getFallingTetro() != null);
                if (hasFalling) {
                    if (!CollisionDetector.detectCollision(Side.DOWN, board)) {
                        movePolyDown();
                    } else {
                        writeTetroToBoard();
                        removeRow();
                        addRandomPoly();
                    }
                } else {
                    addRandomPoly();
                }
                board.notifyListeners();
                break;
            case GAME_OVER:
                return;
        }
    }


    /**
     * Creates a new random Poly and adds it as "falling" to the board.
     * Should there not be enough space left, it changes the game state to GAME_OVER.
     */
    private void addRandomPoly() {
        Random randomPoly = new Random();
        TetrominoMaker tm = new TetrominoMaker();

        // generates a new random Tetro and adds it to the falling field
        this.board.setFallingTetro(tm.getPoly(randomPoly.nextInt(tm.getNumberOfTypes())));
        // sets the x-coordinate to the middle of the board
        this.board.setFallingTetroX((this.board.getWidth() - this.board.getFallingTetro().getColumns()) / 2);
        // always start under the board frame
        this.board.setFallingTetroY(1);

        // If the newly added Tetro is added over a tetro block on the board
        if (CollisionDetector.detectCollision(Side.NONE, board)) {
            state = States.GAME_OVER;
            setHighScore();
        }
    }

    /**
     * Moves the falling tetro to the board grid
     */
    private void writeTetroToBoard() {
        for (int y = 0; y < board.getFallingTetro().getRows(); y++) {
            for (int x = 0; x < board.getFallingTetro().getColumns(); x++) {
                SquareType square = board.getFallingTetro().getSquareType(y, x);
                if (square != SquareType.EMPTY) {
                    board.setSquare(y + board.getFallingTetroY(), x + board.getFallingTetroX(), square);
                }
            }
        }
    }


    /**
     * Moves the falling tetro to the left if there is room
     */
    public void movePolyLeft() {
        if (!CollisionDetector.detectCollision(Side.LEFT, board)) {
            board.setFallingTetroX(board.getFallingTetroX() - 1);
            board.notifyListeners();
        }
    }

    /**
     * Moves the falling tetro to the right if there is room
     */
    public void movePolyRight() {
        if (!CollisionDetector.detectCollision(Side.RIGHT, board)) {
            board.setFallingTetroX(board.getFallingTetroX() + 1);
            board.notifyListeners();
        }
    }

    private void movePolyDown() {
        board.setFallingTetroY(board.getFallingTetroY() + 1);
    }

    /**
    * Moves the falling tetro as far down as possible
     */
    public void hardDrop() {
        while (!CollisionDetector.detectCollision(Side.DOWN, board)) {
            movePolyDown();
        }
        board.notifyListeners();
    }

    /**
     * Rotates the falling poly if there is enough room.
     */
    public void rotatePoly() {
        Poly oldPoly = board.getFallingTetro();
        board.setFallingTetro(board.getFallingTetro().rotateRight());
        if (CollisionDetector.detectCollision(Side.NONE, board)) {
            board.setFallingTetro(oldPoly);
        }
        board.notifyListeners();
    }

    /**
    * Iterates through the board row by row, looking for rows that does not contain any SquareType.EMPTY.
    * When one is found, the values on that row is set to EMPTY and the rows above it are moved down one step.
     */
    private void removeRow() {
        int rowsRemoved = 0;
        for (int y = 1; y < board.getHeight() - 1; y++) {
            int fullRowCounter = 1;
            for (int x = 1; x < board.getWidth() - 1; x++) {
                if (board.getCellType(y, x) != SquareType.EMPTY) {
                    fullRowCounter++; // increments with one for each square found that is not empty
                }
            }
            if (fullRowCounter == board.getWidth() - 1) {
                for (int column = 1; column < fullRowCounter; column++) {
                    board.setSquare(y, column, SquareType.EMPTY);
                }
                rowsRemoved++;

                for (int aboveRemovedRow = y; aboveRemovedRow > 1; aboveRemovedRow--) {
                    for (int xx = 0; xx < board.getWidth(); xx++) {
                        board.setSquare(aboveRemovedRow, xx, board.getCellType(aboveRemovedRow - 1, xx));
                    }
                }
            }
        }
        if (rowsRemoved > 0) {
            addScore(rowsRemoved);
        }
    }


    /**
     * Increments the score variable with a value based on how many rows
     * that was removed.
     *
     * @param rowsRemoved   number of rows removed with the removeRow method.
     */
    private void addScore(int rowsRemoved) {
        if (rowsRemoved == 1) {
            this.score += 100;
        } else if (rowsRemoved == 2) {
            this.score += 300;
        } else if (rowsRemoved == 3) {
            this.score += 500;
        } else if (rowsRemoved == 4) {
            this.score += 800;
        }
        board.notifyListeners();
    }

    /**
     * Creates a JOptionPane where the player can enter his/hers name.
     * The name combined with the score is then used to create a HighScore object
     * that is added to the HighScoreList.
     */
    private void setHighScore() {
        String name = JOptionPane.showInputDialog("Enter your name");
        HighScoreList hsl = HighScoreList.getIntance();
        hsl.addHighScore(new HighScore(name, score));
        board.notifyListeners();
    }

    public int getScore() {
        return score;
    }

    public States getState() {
        return state;
    }

    public void setState(States state) {
        this.state = state;
    }
}
