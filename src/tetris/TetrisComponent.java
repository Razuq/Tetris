package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * TetrisComponent is used to draw each component of the game.
 * It decides what to draw based on the current game state.
 */
public class TetrisComponent extends JComponent implements BoardListener {
    private final static int SQUARE_SIZE = 25; // the size of a square in a tetris block
    private final static int HIGH_SCORES_TO_DISPLAY = 10; // Maximum number of high scores to display.
    private final static int FONT_SIZE = 12;

    private final static int CURRENT_SCORE_X_POS = 10;
    private final static int CURRENT_SCORE_Y_POS = SQUARE_SIZE / 2 + 5;

    private final static int HIGH_SCORE_X_POS = 40;
    private final static int HIGH_SCORE_Y_POS = 60;
    private final static int HIGH_SCORE_VERTICAL_SPACING = 20;

    private Dimension boardSize;
    private Board board;
    private EnumMap<SquareType, Color> colorMap; // according to the assignment a EnumMap was fine to use


    public TetrisComponent(Board board) {
        this.board = board;
        this.colorMap = SquareType.colorMap();
        this.boardSize = new Dimension(board.getWidth() * (SQUARE_SIZE + 1), board.getHeight() * (SQUARE_SIZE + 1));
    }


    @Override
    public Dimension getPreferredSize() { // should return the Dimension created in the constructor
        return boardSize;
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        final Graphics2D g2d = (Graphics2D) g;


        switch (board.getGame().getState()) {
            case RUN:
                drawGameBoard(g2d);
                drawCurrentScore(g2d);
                break;
            case GAME_OVER:
                drawHighScore(g2d);
        }
    }

    /**
     * Draws every SquareType to its chosen color (except EMPTY)
     * @param g2d Graphics2D-object from paintComponent
     */
    private void drawGameBoard(Graphics2D g2d) {
        // draws the board background
        g2d.setColor(Color.decode("#222222"));
        g2d.fillRect(0, 0, board.getWidth() * (SQUARE_SIZE + 1), board.getHeight() * (SQUARE_SIZE + 1));

        // draws each square on the board that does not have the value SquareType.EMPTY
        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board.getCellType(y, x) != SquareType.EMPTY) {
                    g2d.setColor(colorMap.get(board.getCellType(y, x)));
                    g2d.fillRect(x + SQUARE_SIZE * x, y + SQUARE_SIZE * y, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        // draws the falling tetromino if there is one
        if (board.getFallingTetro() != null) {
            for (int[] polyCoordinate : board.getFallingTetro().getPolyCoordinates()) {
                int x = polyCoordinate[0];
                int y = polyCoordinate[1];

                // only draw square where the value is not SquareType.EMPTY
                if (board.getFallingTetro().getSquareType(y, x) != SquareType.EMPTY) {
                    int boardX = x + board.getFallingTetroX();
                    int boardY = y + board.getFallingTetroY();
                    int drawPosX = boardX + (boardX * SQUARE_SIZE);
                    int drawPosY = boardY + (boardY * SQUARE_SIZE);
                    g2d.setColor(colorMap.get(board.getFallingTetro().getSquareType(y, x)));
                    g2d.fillRect(drawPosX, drawPosY, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }
    }

    /**
     * Draws the current score whilst in game.
     * @param g2d Graphics2D-object from paintComponent
     */
    private void drawCurrentScore(Graphics2D g2d) {
        Font font = new Font("Arial", Font.BOLD, FONT_SIZE);
        g2d.setFont(font);

        // score "text shadow"
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + board.getGame().getScore(), CURRENT_SCORE_X_POS + 1, CURRENT_SCORE_Y_POS + 1);

        // score text
        g2d.setColor(Color.decode("#999999"));
        g2d.drawString("Score: " + board.getGame().getScore(), CURRENT_SCORE_X_POS, CURRENT_SCORE_Y_POS);
    }

    /**
     * Draws the high score list that is displayed after loosing a game
     * @param g2d Graphics2D-object from paintComponent
     */
    private void drawHighScore(Graphics2D g2d) {
        HighScoreList hsl = HighScoreList.getIntance();

        Font font = new Font("Arial", Font.BOLD, FONT_SIZE);
        g2d.setColor(Color.decode("#999999"));
        g2d.setFont(font);

        g2d.drawString("High Scores:", HIGH_SCORE_X_POS, HIGH_SCORE_Y_POS - HIGH_SCORE_VERTICAL_SPACING);
        int numberOfHighScores = Math.min(hsl.getHighScores().size(), HIGH_SCORES_TO_DISPLAY);
        for (int i = 0; i < numberOfHighScores; i++) {
            HighScore highScore = hsl.getHighScores().get(i);
            int rank = i + 1;

            g2d.drawString(rank + ".  " + highScore.getName() + "  -  " + highScore.getScore(), HIGH_SCORE_X_POS, HIGH_SCORE_Y_POS + (HIGH_SCORE_VERTICAL_SPACING*i));
        }

        g2d.drawString("Press <ENTER> to continue..", HIGH_SCORE_X_POS, boardSize.height - HIGH_SCORE_Y_POS);

    }


    public void boardChanged() {
        repaint();
    }
    }

