package tetris;

import java.util.ArrayList;

/**
 * The Poly class is used when creating new tetrominos.
 * It is only called from TetrominoMaker which is the class
 * that creates the SquareType[][] array.
 */

public class Poly {
    private SquareType[][] squares;
    private int rows;
    private int columns;
    private ArrayList<int[]> polyCoordinates; // on the form int[]{x, y}

    public Poly(SquareType[][] squares) {
	this.squares = squares;
        this.rows = squares.length;
        this.columns = squares[0].length;
        createCoordinates();
    }

    /**
     * createCoordinates iterates through the squares array (SquareType[][])
     * and appends each coordinate set to the polyCoordinates array.
     */
    private void createCoordinates() {
        this.polyCoordinates = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                polyCoordinates.add(new int[]{x, y});
            }
        }
    }

    /**
     * Rotates the Poly to the right.
     * @return a new Poly object with the original but rotated Poly.
     */
    public Poly rotateRight() {
        Poly newPoly = new Poly(new SquareType[rows][columns]);

            for (int row = 0; row < rows; row++) {
                for (int column = 0; column < columns; column++) {
                    newPoly.squares[column][rows-1-row] = this.squares[row][column];
                }
            }
        return newPoly;
    }


    public int getRows() {
        return rows;
    }


    public int getColumns() {
        return columns;
    }


    public SquareType getSquareType(int row, int column) {
        return squares[row][column];
    }


    public ArrayList<int[]> getPolyCoordinates() {
        return polyCoordinates;
    }
}
