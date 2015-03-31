package tetris;

import java.util.EnumMap;
import java.awt.Color;
public enum SquareType {
    OUTSIDE, EMPTY, I, O, T, S, Z, J, L;

    public static EnumMap<SquareType, Color> colorMap() {
        EnumMap<SquareType, Color> squareTypeColors = new EnumMap<>(SquareType.class);
        Color[] colors = new Color[]{Color.DARK_GRAY, Color.BLACK, Color.CYAN, Color.YELLOW, Color.MAGENTA, // the order corresponds to the types
                Color.GREEN, Color.RED, Color.BLUE, Color.ORANGE};

        for (int i = 0; i < colors.length; i++) {
            squareTypeColors.put(SquareType.values()[i], colors[i]);
        }

        return squareTypeColors;
    }
}
