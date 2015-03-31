package tetris;


import java.awt.Rectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class is used to create the tetris game board
 * and also to handle the currently fa tetromino
 */
public class Board {
    private SquareType[][] grid;
    private int width;
    private int height;

    private Poly fallingTetro; // Should not be in the constructor, the poly is added as the game progresses
    private int fallingTetroX;
    private int fallingTetroY;

    private TetrisGame game;



    private List<BoardListener> boardListeners = new ArrayList<>();

    public Board(int width, int height) {
	this.width = width;
	this.height = height;
	this.grid = new SquareType[height][width];
	this.game = new TetrisGame(this);

	// Fills the grid with SquareType.EMPTY
	for (int column = 0; column < width; column++) {
	    for (int row = 0; row < height; row++) {
		if (column == 0 || column == width - 1 || row == 0 || row == height - 1) {
		    grid[row][column] = SquareType.OUTSIDE;
		} else {
		    grid[row][column] = SquareType.EMPTY;
		}
	    }
	}
    }

    // randomizes the board inside of the frame
    public void randomizeBoard() {
	Random rnd = new Random();
	int numberOfTypes = SquareType.values().length;

	for (int column = 1; column < this.width-1; column++) {
	    for (int row = 1; row < this.height-1; row++) {
		int randomType = rnd.nextInt(numberOfTypes);
		grid[row][column] = SquareType.values()[randomType];
	    }
	}

	notifyListeners();
    }

    public void addBoardListener(BoardListener bl) {
	this.boardListeners.add(bl);
    }

    public void removeBoardListener(BoardListener bl) {
	this.boardListeners.remove(bl);
    }

    public void notifyListeners() {
	for (BoardListener bl : boardListeners) {
	    bl.boardChanged();
	}
    }

    public TetrisGame getGame() {
	return game;
    }

    public int getWidth() {
	return width;
    }

    public int getHeight() {
	return height;
    }

    public Poly getFallingTetro() {
	return fallingTetro;
    }

    public int getFallingTetroX() {
	return fallingTetroX;
    }

    public int getFallingTetroY() {
	return fallingTetroY;
    }

    public SquareType[][] getGrid() {
	return grid;
    }

    public void setFallingTetroX(int x) {
	    this.fallingTetroX = x;
    }
    public void setFallingTetroY(int y) {
	    this.fallingTetroY = y;
    }

    public void setFallingTetro(Poly p) {
	this.fallingTetro = p;
    }

    public void setSquare(int row, int column, SquareType value) {
	this.grid[row][column] = value;
    }

    public SquareType getCellType(int row, int column) {
	return this.grid[row][column];
    }


}
