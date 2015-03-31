package tetris;

import javax.swing.*;
import java.awt.*;
import java.util.EnumMap;

public class TetrisComponent extends JComponent implements BoardListener
{
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


    @Override public Dimension getPreferredSize() {
	return new Dimension(this.boardSize);
    }


    @Override protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	final Graphics2D g2d = (Graphics2D) g;

	g2d.setColor(Color.decode("#222222"));
	g2d.fillRect(0, 0, board.getWidth() * (SQUARE_SIZE + 1), board.getHeight() * (SQUARE_SIZE + 1));

	switch (board.getGame().getState()) {
	    case RUN:
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
		break;
	    case GAME_OVER:

	}
    }


    public void boardChanged() {
	removeAll();
	repaint();
    }

    public void boardChanged(Rectangle repaintArea) {
	repaint(repaintArea);
    }

}

