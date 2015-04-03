package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener {
    private final static int SQUARE_SIZE = 25; // the size of a square in a tetris block
    private final static int COMPONENT_WIDTH = 600;
    private final static int COMPONENT_HEIGHT = 600;

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

        // draw the poly if there is one
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

    private void drawCurrentScore(Graphics2D g2d) {
        Font scoreFont = new Font("Arial", Font.BOLD, 12);


        g2d.setFont(scoreFont);

        g2d.setColor(Color.BLACK);
        g2d.drawString("Score: " + board.getGame().getScore(), 11, SQUARE_SIZE / 2 + 6);
        g2d.setColor(Color.decode("#999999"));
        g2d.drawString("Score: " + board.getGame().getScore(), 10, SQUARE_SIZE / 2 + 5);
    }

    private void drawHighScore(Graphics2D g2d) {
        HighScoreList hsl = HighScoreList.getIntance();
        int startX = 40;
        int startY = 60;
        int verticalSpacing = 20;

        Font scoreFont = new Font("Arial", Font.BOLD, 12);
        g2d.setColor(Color.decode("#999999"));
        g2d.setFont(scoreFont);

        g2d.drawString("High Scores:", startX, startY-verticalSpacing);
        int scoresToDraw = hsl.getHighScores().size();
        for (int i = 0; i < scoresToDraw; i++) {
            HighScore highScore = hsl.getHighScores().get(i);
            g2d.drawString((i+1) + ".  " + highScore.getName() + " " + highScore.getScore(), startX, startY + (verticalSpacing*i));
            System.out.println(highScore.getName() + " " + highScore.getScore());
        }

    }


    public void boardChanged() {
        repaint();
    }

    public void boardChanged(Rectangle repaintArea) {
        repaint(repaintArea);
    }

}

