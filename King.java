class King extends Piece {
    private boolean inCheck;
    private final int[][] DIRECTIONS = { { -1, -1 }, { 1, 1 }, { 1, 1 }, { -1, 1 }, { -1, 0 }, { 0, 1 }, { 1, 0 },
            { 0, -1 } };

    King(Colour colour) {
        super(colour);
        this.x = 5;
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
    public boolean correctMovePattern(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return !(Math.abs(xDiff) > 1 || Math.abs(yDiff) > 1);
    }

    @Override
    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(board, fromX, fromY, toX, toY)) {
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
