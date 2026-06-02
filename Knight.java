class Knight extends Piece {

    Knight(Colour colour) {
        super(colour);
    }

    Knight(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
    }

    public boolean correctMovePattern(int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return (Math.abs(xDiff) == 2 && Math.abs(yDiff) == 1) || (Math.abs(xDiff) == 1 && Math.abs(yDiff) == 2);
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(fromX, fromY, toX, toY)) {
            System.out.println("Knights move in an L shape");
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♘";
        } else {
            return "♞";
        }
    }
}
