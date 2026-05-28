class King extends Piece {

    King(Colour colour) {
        super(colour);
        this.x = 5;
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        super.isLegalMove(board, fromX, fromY, toX, toY);
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if (Math.abs(xDiff) != 1 || Math.abs(yDiff) != 1) {
            return false;
        }
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
