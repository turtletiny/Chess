class Pawn extends Piece {

    Pawn(Colour colour) {
        super(colour);
    }

    Pawn(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {

    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        super.isLegalMove(board, fromX, fromY, toX, toY);
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
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
