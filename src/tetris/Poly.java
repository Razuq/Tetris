package tetris;

import java.util.ArrayList;

/**
 *
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

    private void createCoordinates() {
        this.polyCoordinates = new ArrayList<>();
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < columns; x++) {
                polyCoordinates.add(new int[]{x, y});
            }
        }
    }

    public Poly rotate(boolean direction) { // true= right, false=left
        int numberOfRotations = 0;
        if (direction) {
            numberOfRotations = 1;
        } else {
            numberOfRotations = 3;
        }

        Poly newPoly = new Poly(new SquareType[rows][columns]);

            for (int r = 0; r < rows; r++) {
                for (int c = 0; c < columns; c++) {
                    newPoly.squares[c][rows-1-r] = this.squares[r][c];
                }
            }

        return newPoly;
    }
    public int getRows() { // might be used later on TODO
        return rows;
    }

    public int getColumns() { // TODO nesseccary?
        return columns;
    }

    public int getLowestPoint() {
        int lowestY = 0;
        for (int y = 0; y < this.rows; y++) {
            for (int x = 0; x < this.columns; x++) {
                if ((squares[y][x] != SquareType.EMPTY) && y > lowestY) {
                    lowestY = y;
                }
            }
        }
        return lowestY;
    }

    public SquareType getSquareType(int row, int column) {
        return squares[row][column];
    }

    public ArrayList<int[]> getPolyCoordinates() { // maybe
        return polyCoordinates;
    }
}
