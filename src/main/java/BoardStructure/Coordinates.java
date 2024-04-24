package BoardStructure;

public record Coordinates(int row, int col) {
    public Coordinates {
        if (row < 0 || col < 0) throw new IllegalArgumentException("Can't input negative values as coordinate");

    }
}
