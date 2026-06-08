class Pawn extends Piece {
    boolean hasMoved;
    int yDir;

    Pawn(Colour colour) {
        super(colour);
        this.hasMoved = false;
        this.yDir = (this.colour.isWhite()) ? 1 : -1;

    }

    Pawn(Colour colour, int x, int y) {
        super(colour, x, y);
        this.hasMoved = false;
        this.yDir = (this.colour.isWhite()) ? 1 : -1;
    }

    public void move(Board board) {
        this.hasMoved = true;
    }

    @Override
    public boolean hasLegalMoves(Board board) {
        if (this.isStrictlyLegal(board, this.x, this.y, this.x, this.y + this.yDir)
                || this.isStrictlyLegal(board, this.x, this.y, this.x, this.y + 2 * this.yDir)
                || this.isStrictlyLegal(board, this.x, this.y, this.x + 1, this.y + this.yDir)
                || this.isStrictlyLegal(board, this.x, this.y, this.x - 1, this.y + this.yDir)) {
            return true;
        }
        return false;
    }

    public boolean canAttack(Board board, int x, int y) {
        if (this.colour.isWhite()) {
            if (y - this.y == 1 && Math.abs(x - this.x) == 1) {
                return true;
            }
        } else {
            if (y - this.y == -1 && Math.abs(x - this.x) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void playMove(Board board, int fromX, int fromY, int toX, int toY) {
        super.playMove(board, fromX, fromY, toX, toY);
        this.hasMoved = true;
    }

    public void revertMove(Board board, int fromX, int fromY, int toX, int toY) {
        super.revertMove(board, fromX, fromY, toX, toY);
        // logic to revert hasmoved
    }

    public boolean correctMovePattern(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX, yDiff = toY - fromY;
        if (!(yDiff == this.yDir || (yDiff == 2 * this.yDir && !this.hasMoved))) {
            return false;
        }
        if (!this.isCapture(board, toX, toY) && xDiff != 0) {
            return false;
        }
        if (this.isCapture(board, toX, toY) && Math.abs(xDiff) != 1) {
            return false;
        }

        // if (board.getLastMove() == pawn && math.abs(yDiff) == 2) ... then en passant
        // possible
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
