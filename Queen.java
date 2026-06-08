class Queen extends Piece {

    private final int[][] DIRECTIONS = { { -1, -1 }, { 1, 1 }, { 1, 1 }, { -1, 1 }, { -1, 0 }, { 0, 1 }, { 1, 0 },
            { 0, -1 } };

    Queen(Colour colour) {
        super(colour);
        this.x = 4;
    }

    public void move(Board board) {
    }

    @Override
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    @Override
    public boolean correctMovePattern(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return (Math.abs(xDiff) == Math.abs(yDiff)) || ((xDiff == 0 && yDiff != 0) || (xDiff != 0 && yDiff == 0));
    }

    @Override
    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(board, fromX, fromY, toX, toY)) {
            System.out.println("Queens can only move diagonally or perpendicularly");
            return false;
        }
        if (!this.hasLineOfSight(board, fromX, fromY, toX, toY)) {
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
