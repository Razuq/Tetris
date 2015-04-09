package tetris;

/**
 * This class checks for collisions when moving in different directions.
 * The enum Side is used for specifing in which direction to check for a collision.
 * (NONE is used for checking on the current position)
 */

public class CollisionDetector {
    public static enum Side {LEFT, RIGHT, DOWN, NONE}

    public static boolean detectCollision(Side direction, Board board) {
        switch (direction) {
            case LEFT:
                return checkForCollision(board, -1, 0);
            case RIGHT:
                return checkForCollision(board, 1, 0);
            case DOWN:
                return checkForCollision(board, 0, 1);
            case NONE: // "looks" right under the tetro, used when checking if a new tetro can be placed at all
                return checkForCollision(board, 0, 0);
        }
        return false;
    }

    /**
     * Iterates through the tetro array and for each SquareType found (that is not EMPTY),
     * it will check if its potential position is free.
     *
     * @param board the board on which to check for collisions.
     * @param addX  see below.
     * @param addY  addX and addY is used to get the potential position that would be caused by the movement.
     * @return      boolean, true if it will collide or end up outside the board, else false
     */
    private static boolean checkForCollision(Board board, int addX, int addY) {
        for (int x = 0; x < board.getFallingTetro().getColumns(); x++) {
            for (int y = 0; y < board.getFallingTetro().getRows(); y++) {
                if (board.getFallingTetro().getSquareType(y, x) != SquareType.EMPTY) {
                    int potentialX = x + board.getFallingTetroX() + addX;
                    int potentialY = y + board.getFallingTetroY() + addY;

                    // not an actual collision but the potential position would be outside the board
                    if ((potentialX < 0 || potentialX > board.getWidth()-1) || (potentialY < 0 || potentialY > board.getHeight()-1)) {
                        return true;
                    }
                    if (board.getCellType(potentialY, potentialX) != SquareType.EMPTY) {
                        return true;
                    }
                }

            }
        }
        return false;
    }
}
