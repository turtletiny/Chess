class King extends Piece {
    private boolean inCheck;
    private final int[][] DIRECTIONS = { { -1, -1 }, { 1, 1 }, { 1, 1 }, { -1, 1 }, { -1, 0 }, { 0, 1 }, { 1, 0 },
            { 0, -1 } };

    King(Colour colour) {
        super(colour);
        this.getPoint().setX(5);
        this.inCheck = false;
    }

    public void move(Board board) {
    }

    @Override
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    public void setInCheck(Boolean bool) {
        this.inCheck = bool;
    }

    @Override
    public boolean correctMovePattern(Board board, Point fromSquare, Point toSquare) {
        Point diff = Point.subtractPoints(toSquare, fromSquare);
        return !(Math.abs(diff.getX()) > 1 || Math.abs(diff.getY()) > 1);
    }

    @Override
    public boolean isLegalMove(Board board, Point fromSquare, Point toSquare) {
        if (!super.isLegalMove(board, fromSquare,toSquare)) {
            return false;
        }
        if (!this.correctMovePattern(board, fromSquare, toSquare)) {
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
