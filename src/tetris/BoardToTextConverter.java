package tetris;

/**
 * This class was used as an early step, to be able to visulize the game as development progressed
 */
public class BoardToTextConverter {
    public static String convertToText(Board board) {
	StringBuilder stringBuilder = new StringBuilder();

	boolean isFalling = board.getFallingTetro() != null; // Check if there is a falling Poly

	for (int y = 0; y < board.getHeight(); y++) {
	    for (int x = 0; x < board.getWidth(); x++) {
		    switch (board.getCellType(y, x)) {
			case OUTSIDE:
			    if (x == board.getWidth() - 1) { // If we are at the right edge of the grid
				stringBuilder.append("%\n");
			    } else {
				stringBuilder.append("%");
			    }
			    break;
			case EMPTY:
			    stringBuilder.append(" ");
			    break;
			case I:
			case O:
			case T:
			case S:
			case Z:
			case J:
			case L:
			    stringBuilder.append("#");
			    break;


		}
	    }
	}

	// Adds the poly to the string if there is one
	if (isFalling) {
	    for (int[] polyCoordinate : board.getFallingTetro().getPolyCoordinates()) {
		int x = polyCoordinate[0];
		int y = polyCoordinate[1];

		int boardCoordinateX = x + board.getFallingTetroX();
		int boardCoordinateY = y + board.getFallingTetroY();
		int stringLocation = boardCoordinateX + ((1+board.getWidth()) * (boardCoordinateY)); // 1+board.getWidth() since each row contains width+1 signs

		if (board.getFallingTetro().getSquareType(y, x) != SquareType.EMPTY) {

		    System.out.println(
			    "X: " + boardCoordinateX + " Y: " + boardCoordinateY + " String location: " + stringLocation);
		    stringBuilder.replace(stringLocation, stringLocation + 1, "#");
		}
	    }
	}
	return stringBuilder.toString();
    }
}
