class King extends Piece {

    King(Colour colour) {
        super(colour);
        this.x = 5;
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(int xDiff, int yDiff, int newX, int newY) {
        // if king moves into line of sight, return false
        if (Math.abs(xDiff) != 1 || Math.abs(yDiff) != 1) {
            return false;
        }
        super.moveInBounds(newX, newY);
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
