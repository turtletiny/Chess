package src;

class Pawn extends Piece {
    boolean hasMoved;
    int yDir;

    Pawn(Colour colour) {
        super(colour);
        this.hasMoved = false;
        this.yDir = (this.colour.isWhite()) ? 1 : -1;

    }

    Pawn(Colour colour, Point point) {
        super(colour, point);
        this.hasMoved = false;
        this.yDir = (this.colour.isWhite()) ? 1 : -1;
    }

    public void move(Board board) {
        this.hasMoved = true;
    }

    @Override
    public boolean hasLegalMoves(Board board) {
        if (this.isStrictlyLegal(board, this.getPoint(), this.getPoint().addValues(0, this.yDir))
                || this.isStrictlyLegal(board, this.getPoint(), this.getPoint().addValues(0, 2 * this.yDir))
                || this.isStrictlyLegal(board, this.getPoint(), this.getPoint().addValues(1, this.yDir))
                || this.isStrictlyLegal(board, this.getPoint(), this.getPoint().addValues(-1, this.yDir))) {
            return true;
        }
        return false;
    }

    public boolean canAttack(Board board, Point point) {
        if (this.colour.isWhite()) {
            if (point.getY() - this.getPoint().getY() == 1 && Math.abs(point.getX() - this.getPoint().getX()) == 1) {
                return true;
            }
        } else {
            if (point.getY() - this.getPoint().getY() == -1 && Math.abs(point.getX() - this.getPoint().getX()) == 1) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void playMove(Board board, Point fromPoint, Point toPoint) {
        super.playMove(board, fromPoint, toPoint);
        this.hasMoved = true;
    }

    public boolean correctMovePattern(Board board, Point fromPoint, Point toPoint) {
        Point diff = Point.subtractPoints(toPoint, fromPoint);
        if (!(diff.getY() == this.yDir || (diff.getY() == 2 * this.yDir && !this.hasMoved))) {
            return false;
        }
        if (!this.isCapture(board, toPoint) && diff.getX() != 0) {
            return false;
        }
        if (this.isCapture(board, toPoint) && Math.abs(diff.getX()) != 1) {
            return false;
        }

        // if (board.getLastMove() == pawn && math.abs(yDiff) == 2) ... then en passant
        // possible
        return true;
    }

    @Override
    public boolean isLegalMove(Board board, Point fromPoint, Point toPoint) {
        if (!super.isLegalMove(board, fromPoint, toPoint)) {
            return false;
        }
        if (!correctMovePattern(board, fromPoint, toPoint)) {
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
