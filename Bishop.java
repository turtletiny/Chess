class Bishop extends Piece {

    Bishop(Colour colour) {
        super(colour);
    }

    Bishop(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(int xDiff, int yDiff, int newX, int newY) {
        // Diagonal Movement
        if (Math.abs(xDiff) != Math.abs(yDiff)) {
            return false;
        }
        super.moveInBounds(newX, newY);
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♗";
        }
        return "♝";
    }
}
