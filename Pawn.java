class Pawn extends Piece {

    Pawn(Colour colour) {
        super(colour);
    }

    Pawn(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {

    }

    public boolean isLegalMove(Board board, int xDiff, int yDiff) {
        // for white pieces: board[6]
        // for black pieces: board[1]
        return true;

    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♙";

        } else {
            return "♟";

        }

    }

}
