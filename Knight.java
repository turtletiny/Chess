class Knight extends Piece {
    private final int[][] DIRECTIONS = { { -2, -1 }, { -2, 1 }, { 2, -1 }, { 2, 1 }, { 1, -2 }, { 1, 2 }, { -1, -2 },
            { -1, 2 } };

    Knight(Colour colour) {
        super(colour);
    }

    Knight(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
    }

    @Override
    boolean hasLineOfSight(Board board, int fromX, int fromY, int toX, int toY) {
        return true;
    }

    @Override
    public boolean correctMovePattern(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return (Math.abs(xDiff) == 2 && Math.abs(yDiff) == 1) || (Math.abs(xDiff) == 1 && Math.abs(yDiff) == 2);
    }

    @Override
    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(board, fromX, fromY, toX, toY)) {
            System.out.println("Knights move in an L shape");
            return false;
        }
        return true;
    }

    @Override
    public boolean hasLegalMoves(Board board) {
        for (int[] dir : this.DIRECTIONS) {
            if (this.isStrictlyLegal(board, this.x, this.y, this.x + dir[0], this.y + dir[1])) {
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
