package src;
class Queen extends Piece {

    private final int[][] DIRECTIONS = { { -1, -1 }, { 1, 1 }, { 1, 1 }, { -1, 1 }, { -1, 0 }, { 0, 1 }, { 1, 0 },
            { 0, -1 } };

    Queen(Colour colour) {
        super(colour);
    }

    Queen(Colour colour, Point point) {
        super(colour, point);
    }

    public void move(Board board) {
    }

    @Override
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    @Override
    public boolean correctMovePattern(Board board, Point from, Point to) {
        Point diff = Point.subtractPoints(to, from);
        int xDiff = diff.getX();
        int yDiff = diff.getY();
        return (Math.abs(xDiff) == Math.abs(yDiff)) || ((xDiff == 0 && yDiff != 0) || (xDiff != 0 && yDiff == 0));
    }

    @Override
    public boolean isLegalMove(Board board, Point from, Point to) {
        if (!super.isLegalMove(board, from, to)) {
            return false;
        }
        if (!this.correctMovePattern(board, from, to)) {
            System.out.println("Queens can only move diagonally or perpendicularly");
            return false;
        }
        if (!this.hasLineOfSight(board, from, to)) {
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♕";
        } else {
            return "♛";
        }
    }
}
