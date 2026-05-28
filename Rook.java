class Rook extends Piece {

    Rook(Colour colour) {
        super(colour);
    }

    Rook(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {}

    public boolean isLegalMove(int xDiff, int yDiff) {
        if ((xDiff != 0 && yDiff == 0) ||
            (xDiff == 0 && yDiff != 0)){
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♖";
        } else {
            return "♜";
        }
    }
}
