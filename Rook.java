class Rook extends Piece {

    Rook(Colour colour) {
        super(colour);
    }

    Rook(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    // Everything to be done when a move is actually made
    public void move(Board board) {
    }

    @Override
    public boolean correctMovePattern(int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return (xDiff == 0 && yDiff != 0) || (xDiff != 0 && yDiff == 0);
    }

    @Override
    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(fromX, fromY, toX, toY)) {
            System.out.println("Rooks can only move perpendicularly");
            return false;
        }
        if (!this.hasLineOfSight(board, fromX, fromY, toX, toY)) {
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
