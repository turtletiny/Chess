class Bishop extends Piece {

    Bishop(Colour colour) {
        super(colour);
    }

    Bishop(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        int xDiff = toX - fromX, yDiff = toY - fromY;
        if (Math.abs(xDiff) != Math.abs(yDiff)) {
            System.out.println("Bishops move diagonally");
            return false;
        }
        if (this.isBlocked(board, fromX, fromY, toX, toY)) {
            return false;
        }
        if (this.capturingOwnPiece(board, toX, toY)){
            return false;
        }
        return true;
    }

    public boolean isBlocked(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX, yDiff = toY - fromY, xDir, yDir;
        if (xDiff > 0) {
            xDir = 1;
        } else {
            xDir = -1;
        }
        if (yDiff > 0) {
            yDir = 1;
        } else {
            yDir = -1;
        }

        for (int i = 1; i < Math.abs(toY - fromY); i++) {
            if (board.pieceExists(fromX + i * xDir, fromY + i * yDir)) {
                System.out.println("Bishop blocked bum");
                return true;
            }
        }
        return false;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♗";
        }
        return "♝";
    }
}
