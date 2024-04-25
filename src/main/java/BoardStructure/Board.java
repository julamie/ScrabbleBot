package BoardStructure;

public class Board {

    private Square[][] board;
    private int size = 15;
    private boolean isEmpty = true;

    public Board() {
        this.board = new Square[this.size][this.size];
        initializeBoardSquares();
    }

    private void addDoubleLetterBonusTiles() {
        this.board[0][3] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[0][11] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[14][3] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[14][11] = new Square(SquareType.LETTER_BONUS_DOUBLE);

        this.board[3][0] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[11][0] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[3][14] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[11][14] = new Square(SquareType.LETTER_BONUS_DOUBLE);

        this.board[2][6] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[2][8] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[12][6] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[12][8] = new Square(SquareType.LETTER_BONUS_DOUBLE);

        this.board[6][2] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[8][2] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[6][12] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[8][12] = new Square(SquareType.LETTER_BONUS_DOUBLE);

        this.board[3][7] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[11][7] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[7][3] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[7][11] = new Square(SquareType.LETTER_BONUS_DOUBLE);

        this.board[6][6] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[6][8] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[8][6] = new Square(SquareType.LETTER_BONUS_DOUBLE);
        this.board[8][8] = new Square(SquareType.LETTER_BONUS_DOUBLE);

    }

    private void addTripleLetterBonusTiles() {
        this.board[1][5] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[1][9] = new Square(SquareType.LETTER_BONUS_TRIPLE);

        this.board[5][1] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[5][5] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[5][9] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[5][13] = new Square(SquareType.LETTER_BONUS_TRIPLE);

        this.board[9][1] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[9][5] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[9][9] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[9][13] = new Square(SquareType.LETTER_BONUS_TRIPLE);

        this.board[13][5] = new Square(SquareType.LETTER_BONUS_TRIPLE);
        this.board[13][9] = new Square(SquareType.LETTER_BONUS_TRIPLE);

    }

    private void addDoubleWordBonusTiles() {
        this.board[1][1] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[2][2] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[3][3] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[4][4] = new Square(SquareType.WORD_BONUS_DOUBLE);

        this.board[1][13] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[2][12] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[3][11] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[4][10] = new Square(SquareType.WORD_BONUS_DOUBLE);

        this.board[13][1] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[12][2] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[11][3] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[10][4] = new Square(SquareType.WORD_BONUS_DOUBLE);

        this.board[10][10] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[11][11] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[12][12] = new Square(SquareType.WORD_BONUS_DOUBLE);
        this.board[13][13] = new Square(SquareType.WORD_BONUS_DOUBLE);

        this.board[7][7] = new Square(SquareType.WORD_BONUS_DOUBLE);
    }

    private void addTripleWordBonusTiles() {
        this.board[0][0] = new Square(SquareType.WORD_BONUS_TRIPLE);
        this.board[0][7] = new Square(SquareType.WORD_BONUS_TRIPLE);
        this.board[0][14] = new Square(SquareType.WORD_BONUS_TRIPLE);

        this.board[7][0] = new Square(SquareType.WORD_BONUS_TRIPLE);
        this.board[7][14] = new Square(SquareType.WORD_BONUS_TRIPLE);

        this.board[14][0] = new Square(SquareType.WORD_BONUS_TRIPLE);
        this.board[14][7] = new Square(SquareType.WORD_BONUS_TRIPLE);
        this.board[14][14] = new Square(SquareType.WORD_BONUS_TRIPLE);
    }

    private void addNormalTiles() {
        for (int row = 0; row < this.size; row++) {
            for (int col = 0; col < this.size; col++) {
                // skip already initialized tiles
                if (this.board[row][col] != null) continue;

                this.board[row][col] = new Square(SquareType.NORMAL);
            }
        }
    }

    private void initializeBoardSquares() {
        addDoubleLetterBonusTiles();
        addTripleLetterBonusTiles();
        addDoubleWordBonusTiles();
        addTripleWordBonusTiles();
        addNormalTiles();
    }

    public void setTileOnBoard(Tile tile, int row, int col) {
        Square currSquare = getSquareAtPos(row, col);

        currSquare.occupySquare(tile);
        this.isEmpty = false;
    }

    public void setTileOnBoard(Tile tile, Coordinates coordinates) {
        setTileOnBoard(tile, coordinates.row(), coordinates.col());
    }

    public boolean isOccupiedAtPos(int row, int col) {
        return getSquareAtPos(row, col).isOccupied();
    }

    public boolean isOccupiedAt(Coordinates coordinates) {
        return isOccupiedAtPos(coordinates.row(), coordinates.col());
    }

    public char getLetterAt(Coordinates coordinates) {
        return getSquareAt(coordinates).getLetter();
    }

    public int getValueAt(Coordinates coordinates) {
        return getSquareAt(coordinates).getValue();
    }

    public Square getSquareAtPos(int row, int col) {
        if (row >= this.size || col >= this.size) throw new IndexOutOfBoundsException();

        return this.board[row][col];
    }

    public Square getSquareAt(Coordinates coordinates) {
        return getSquareAtPos(coordinates.row(), coordinates.col());
    }

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public int getSize() {
        return size;
    }
}
