class King extends Piece {
    boolean inCheck;
    King(Colour colour) {
        super(colour);
        this.x = 5;
        this.inCheck = false;
    }

    public void move(Board board) {
    }

    public boolean correctMovePattern(int fromX, int fromY, int toX, int toY){
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        return !(Math.abs(xDiff) > 1 || Math.abs(yDiff) > 1);
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
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
