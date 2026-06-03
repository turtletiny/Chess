class Pawn extends Piece {
    boolean hasMoved;

    Pawn(Colour colour) {
        super(colour);
        this.hasMoved = false;
    }

    Pawn(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
        this.hasMoved = true;
    }

    @Override
    public void playMove(Board board, int fromX, int fromY, int toX, int toY) {
        board.placePiece(this, toX, toY);
        board.clearSquare(fromX, fromY);
        this.x = toX;
        this.y = toY;
        this.hasMoved = true;
        board.turnToggle();
    }

    public boolean correctMovePattern(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX, yDiff = toY - fromY, yDir = 1;
        if (this.colour == Colour.BLACK) {
            yDir = -1;
        }
        if (!(yDiff == 1 * yDir || (yDiff == 2 * yDir && !this.hasMoved))) {
            return false;
        }
        if (!this.isCapture(board, toX, toY) && xDiff != 0) {
            return false;
        }

        // if (board.getLastMove() == pawn && math.abs(yDiff) == 2) ... then en passant possible
        return true;
    }

    @Override
    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!correctMovePattern(board, fromX, fromY, toX, toY)) {
            return false;
        }
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
