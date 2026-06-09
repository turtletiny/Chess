package src;

class Bishop extends Piece {
    int[][] DIRECTIONS = { { -1, -1 }, { 1, 1 }, { 1, -1 }, { -1, 1 } };

    Bishop(Colour colour) {
        super(colour);
    }

    Bishop(Colour colour, Point point) {
        super(colour, point);
    }

    public void move(Board board) {
    }

    @Override
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    @Override
    public boolean correctMovePattern(Board board, Point fromSquare, Point toSquare) {
        Point diff = Point.subtractPoints(toSquare, fromSquare);
        return Math.abs(diff.getX()) == Math.abs(diff.getY());
    }

    @Override
    public boolean isLegalMove(Board board, Point fromSquare, Point toSquare) {
        if (!super.isLegalMove(board, fromSquare, toSquare)) {
            return false;
        }
        if (!correctMovePattern(board, fromSquare, toSquare)) {
            return false;
        }
        if (!hasLineOfSight(board, fromSquare, toSquare)) {
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
