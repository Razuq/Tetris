package tetris;

public class TetrominoMaker
{

    public int getNumberOfTypes() {
	return SquareType.values().length - 2; // remove the types OUTSIDE and EMPTY
    }

    public Poly getPoly(int n) {
	switch (n) {
	    case 0:
		return new Poly(createI());
	    case 1:
		return new Poly(createO());
	    case 2:
		return new Poly(createT());
	    case 3:
		return new Poly(createS());
	    case 4:
		return new Poly(createZ());
	    case 5:
		return new Poly(createJ());
	    case 6:
		return new Poly(createL());
	    default:
		throw new IllegalArgumentException(n + " is not a valid number");
	}
    }

    private SquareType[][] createI() {
	return new SquareType[][]{
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY},
		{SquareType.I, SquareType.I, SquareType.I, SquareType.I},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}
	};
    }

    private SquareType[][] createO() {
	return new SquareType[][]{
		{SquareType.O, SquareType.O},
		{SquareType.O, SquareType.O}
	};
    }

    private SquareType[][] createT() {
	return new SquareType[][]{
		{SquareType.EMPTY, SquareType.T, SquareType.EMPTY},
		{SquareType.T, SquareType.T, SquareType.T},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}
	};
    }

    private SquareType[][] createS() {
	return new SquareType[][]{
		{SquareType.EMPTY, SquareType.S, SquareType.S},
		{SquareType.S, SquareType.S, SquareType.EMPTY},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}
	};
    }

    private SquareType[][] createZ() {
	return new SquareType[][]{
		{SquareType.Z, SquareType.Z, SquareType.EMPTY},
		{SquareType.EMPTY, SquareType.Z, SquareType.Z},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}
	};
    }

    private SquareType[][] createJ() {
	return new SquareType[][]{
		{SquareType.J, SquareType.EMPTY, SquareType.EMPTY},
		{SquareType.J, SquareType.J, SquareType.J},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}
	};
    }

    private SquareType[][] createL() {
	return new SquareType[][]{
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.L},
		{SquareType.L, SquareType.L, SquareType.L},
		{SquareType.EMPTY, SquareType.EMPTY, SquareType.EMPTY}
	};
    }
}
