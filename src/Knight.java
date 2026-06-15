package src;

class Knight extends Piece {
    private static final String NAME = "KNIGHT";
    private final int[][] DIRECTIONS = { { -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 }, { 1, -2 }, { 1, 2 }, { -1, -2 },
            { -1, 2 } };

    Knight(Colour colour) {
        super(colour);
    }

    Knight(Colour colour, Point point) {
        super(colour, point);
    }

    public void move(Board board) {
    }

    @Override
    public int getValue() {
        return 3;
    }

    @Override
    boolean hasLineOfSight(Board board, Point fromPoint, Point toPoint) {
        return true;
    }

    @Override
    public boolean correctMovePattern(Board board, Point fromPoint, Point toPoint) {
        Point diff = Point.subtractPoints(toPoint, fromPoint);
        return (Math.abs(diff.getX()) == 2 && Math.abs(diff.getY()) == 1)
                || (Math.abs(diff.getX()) == 1 && Math.abs(diff.getY()) == 2);
    }

    @Override
    public boolean isLegalMove(Board board, Point fromPoint, Point toPoint) {
        if (!super.isLegalMove(board, fromPoint, toPoint)) {
            return false;
        }
        if (!this.correctMovePattern(board, fromPoint, toPoint)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean hasLegalMoves(Board board) {
        for (int[] dir : this.DIRECTIONS) {
            Point toPoint = Point.addPoints(this.getPoint(), new Point(dir[0], dir[1]));
            if (this.isStrictlyLegal(board, this.getPoint(), toPoint)) {
                return true;
            }
        }
        return false;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♘";
        } else {
            return "♞";
        }
    }
}
