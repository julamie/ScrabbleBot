package boardStructure;

public record Coordinates(int row, int col) {
    public Coordinates {
        if (row < 0 || col < 0) throw new IllegalArgumentException("Can't input negative values as coordinate");
    }

    public Coordinates getLeft() {
        if (col == 0) return null;

        return new Coordinates(row, col - 1);
    }

    public Coordinates getRight() {
        return new Coordinates(row, col + 1);
    }

    public Coordinates getUpper() {
        if (row == 0) return null;

        return new Coordinates(row - 1, col);
    }

    public Coordinates getLower() {
        return new Coordinates(row + 1, col);
    }
}
