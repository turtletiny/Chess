package src;

class Rook extends Piece {
    private boolean hasMoved;
    private final int[][] DIRECTIONS = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    Rook(Colour colour) {
        super(colour);
        this.hasMoved = false;
    }

    Rook(Colour colour, Point point) {
        super(colour, point);
        this.hasMoved = false;
    }

    // Everything to be done when a move is actually made
    public void move(Board board) {
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    @Override
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    @Override
    public void playMove(Board board, Point fromPoint, Point toPoint) {
        super.playMove(board, fromPoint, toPoint);
        this.hasMoved = true;
    }

    @Override
    public boolean correctMovePattern(Board board, Point from, Point to) {
        Point diff = Point.subtractPoints(to, from);
        return (diff.getX() == 0 && diff.getY() != 0) || (diff.getX() != 0 && diff.getY() == 0);
    }

    @Override
    public boolean isLegalMove(Board board, Point from, Point to) {
        if (!super.isLegalMove(board, from, to)) {
            return false;
        }
        if (!this.correctMovePattern(board, from, to)) {
            System.out.println("Rooks can only move perpendicularly");
            return false;
        }
        if (!this.hasLineOfSight(board, from, to)) {
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
