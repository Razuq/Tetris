package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

/**
 * Draws the board, falling poly and high scores
 */
public class TetrisComponent extends JComponent implements BoardListener {
    private final static int SQUARE_SIZE = 25; // the size of a square in a tetris block
    private final static int HIGH_SCORES_TO_DISPLAY = 10;

    private Dimension boardSize;
    private Board board;
    private EnumMap<SquareType, Color> colorMap;


    public TetrisComponent(Board board) {
        this.board = board;
        this.colorMap = SquareType.colorMap();
        this.boardSize = new Dimension(board.getWidth() * (SQUARE_SIZE + 1), board.getHeight() * (SQUARE_SIZE + 1));
    }


    @Override
    public Dimension getPreferredSize() {
        return new Dimension(this.boardSize);
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
     * @param g2d
     */
    private void drawGameBoard(Graphics2D g2d) {
        g2d.setColor(Color.decode("#222222"));
        g2d.fillRect(0, 0, board.getWidth() * (SQUARE_SIZE + 1), board.getHeight() * (SQUARE_SIZE + 1));

        for (int y = 0; y < board.getHeight(); y++) {
            for (int x = 0; x < board.getWidth(); x++) {
                if (board.getCellType(y, x) != SquareType.EMPTY) {
                    g2d.setColor(colorMap.get(board.getCellType(y, x)));
                    g2d.fillRect(x + SQUARE_SIZE * x, y + SQUARE_SIZE * y, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        // draws the falling tetro if there is one
        if (board.getFallingTetro() != null) {
            for (int[] polyCoordinate : board.getFallingTetro().getPolyCoordinates()) {
                int x = polyCoordinate[0];
                int y = polyCoordinate[1];
                if (board.getFallingTetro().getSquareType(y, x) != SquareType.EMPTY) {

                    int boardX = polyCoordinate[0] + board.getFallingTetroX();
                    int boardY = polyCoordinate[1] + board.getFallingTetroY();
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
     * @param g2d
     */
    private void drawCurrentScore(Graphics2D g2d) {
        int scoreXPos = 10;
        int scoreYPos = SQUARE_SIZE / 2 + 5;
        Font font = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(font);

        // "text shadow"
        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + board.getGame().getScore(), scoreXPos + 1, scoreYPos + 1);

        // score text
        g2d.setColor(Color.decode("#999999"));
        g2d.drawString("Score: " + board.getGame().getScore(), scoreXPos, scoreYPos);
    }

    /**
     * Draws the high score list that is displayed after loosing a game
     * @param g2d
     */
    private void drawHighScore(Graphics2D g2d) {
        HighScoreList hsl = HighScoreList.getIntance();
        int textXPos = 40;
        int textYPos = 60;
        int verticalSpacing = 20;

        Font font = new Font("Arial", Font.BOLD, 12);
        g2d.setColor(Color.decode("#999999"));
        g2d.setFont(font);

        g2d.drawString("High Scores:", textXPos, textYPos - verticalSpacing);
        int numberOfHighScores = Math.min(hsl.getHighScores().size(), HIGH_SCORES_TO_DISPLAY);
        for (int i = 0; i < numberOfHighScores; i++) {
            HighScore highScore = hsl.getHighScores().get(i);
            int rank = i + 1;

            g2d.drawString(rank + ".  " + highScore.getName() + "  -  " + highScore.getScore(), textXPos, textYPos + (verticalSpacing*i));
        }

        g2d.drawString("Press <ENTER> to continue..", textXPos, boardSize.height - textYPos);

    }


    public void boardChanged() {
        repaint();
    }

    public void boardChanged(Rectangle repaintArea) {
        repaint(repaintArea);
    }

}

