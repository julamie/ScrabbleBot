package BoardStructure;

public class Square {

    private Tile tile;
    private final SquareType type;

    public Square(SquareType type) {
        this.type = type;
    }

    public void occupySquare(Tile tile) {
        if (isOccupied()) throw new IllegalArgumentException("Can't occupy already occupied square");

        this.tile = tile;
    }

    public boolean isOccupied() {
        return tile != null;
    }

    public Character getLetter() {
        if (!isOccupied()) return null;

        return tile.getLetter();
    }

    public Integer getValue() {
        if (!isOccupied()) return null;

        return tile.getValue();
    }

    public SquareType getType() {
        return type;
    }
}
