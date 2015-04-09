package tetris;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The board class is used for creating the two-dimensional game board that the game is played upon.
 * Board is also used when adding a falling tetromino.
 */
public class Board {
    private SquareType[][] grid;
    private int width;
    private int height;

    private Poly fallingTetro; // Should not be in the constructor, the poly is added as the game progresses
    private int fallingTetroX; // The coordinate of the left-top corner of the tetro
    private int fallingTetroY; //

    private TetrisGame game;
    private List<BoardListener> boardListeners = new ArrayList<>();

    /**
     * Creates a board with the specified width and height.
     * The outermost squares on the board are set to the enum SquareType.OUTSIDE,
     * whilst the rest are set to SquareType.EMPTY
     * @param width the width of the board
     * @param height he height of the board
     */
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


    /**
     * Randomizes the board on the inside of its frame.
     * Even though it is not used, I left it because it was in the assignment.
     */
    public void randomizeBoard() {
        Random rnd = new Random();
        int numberOfTypes = SquareType.values().length;

        for (int column = 1; column < this.width - 1; column++) {
            for (int row = 1; row < this.height - 1; row++) {
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


    public void setFallingTetroX(int x) {
        this.fallingTetroX = x;
    }

    public void setFallingTetroY(int y) {
        this.fallingTetroY = y;
    }

    public void setFallingTetro(Poly p) {
        this.fallingTetro = p;
    }

    // Sets a specific SquareType to the desired square
    public void setSquare(int row, int column, SquareType value) {
        this.grid[row][column] = value;
    }

    // Returns the SquareType for the specified square
    public SquareType getCellType(int row, int column) {
        return this.grid[row][column];
    }


}
