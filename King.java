class King extends Piece {

    King(Colour colour) {
        super(colour);
    }

    public void move(Board board) {}

    public boolean isLegalMove(int xDiff, int yDiff) {
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♔";
        } else {
            return "♚";
        }
    }
}
