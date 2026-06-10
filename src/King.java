package src;

class King extends Piece {

    private boolean hasMoved;
    private final int[][] DIRECTIONS = { { -1, -1 }, { 1, 1 }, { 1, 1 }, { -1, 1 }, { -1, 0 }, { 0, 1 }, { 1, 0 },
            { 0, -1 } };

    King(Colour colour) {
        super(colour);
        this.getPoint().setX(5);
        this.hasMoved = false;
    }

    King(Colour colour, Point point) {
        super(colour, point);
        this.hasMoved = false;
    }

    public void move(Board board) {
    }

    @Override
    public void playMove(Board board, Point fromPoint, Point toPoint) {
        super.playMove(board, fromPoint, toPoint);
        this.hasMoved = true;
    }

    @Override
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    public boolean getHasMoved() {
        return this.hasMoved;
    }

    public void setHasMoved(Boolean bool) {
        this.hasMoved = bool;
    }

    @Override
    public boolean correctMovePattern(Board board, Point fromSquare, Point toSquare) {
        Point diff = Point.subtractPoints(toSquare, fromSquare);
        return !(Math.abs(diff.getX()) > 1 || Math.abs(diff.getY()) > 1);
    }

    @Override
    public boolean isLegalMove(Board board, Point fromSquare, Point toSquare) {
        if (!super.isLegalMove(board, fromSquare, toSquare)) {
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
