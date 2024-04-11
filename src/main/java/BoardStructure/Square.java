package BoardStructure;

public class Square {

    private Tile tile;
    private SquareType type;

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

    public char getLetter() {
        if (!isOccupied()) return ' ';

        return tile.getLetter();
    }

    public int getValue() {
        if (!isOccupied()) return -1;

        return tile.getValue();
    }

    public SquareType getType() {
        return type;
    }
}
