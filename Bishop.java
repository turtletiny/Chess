class Bishop extends Piece {

    Bishop(Colour colour) {
        super(colour);
    }

    Bishop(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {

    @Override
    public boolean correctMovePattern(int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return Math.abs(xDiff) == Math.abs(yDiff);
    }

    @Override
    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!correctMovePattern(fromX, fromY, toX, toY)) {
            System.out.println("Bishops move diagonally");
        }
        if (!hasLineOfSight(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (this.capturingOwnPiece(board, toX, toY)) {
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♗";
        }
        return "♝";
    }
}
