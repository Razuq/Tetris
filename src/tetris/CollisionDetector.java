package tetris;

/**
 * This class checks for collisions
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
            case NONE:
                return checkForCollision(board, 0, 0);
        }
        return false;
    }

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
