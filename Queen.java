class Queen extends Piece {

    Queen(Colour colour) {
        super(colour);
        this.x = 4;
    }

    public void move(Board board) {
    }

    public boolean correctMovePattern(int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return (Math.abs(xDiff) == Math.abs(yDiff)) || ((xDiff == 0 && yDiff != 0) || (xDiff != 0 && yDiff == 0));
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(fromX, fromY, toX, toY)){
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
