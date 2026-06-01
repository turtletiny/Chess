class Queen extends Piece {

    Queen(Colour colour) {
        super(colour);
        this.x = 4;
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if ((Math.abs(xDiff) != Math.abs(yDiff)) && // diagonal movement
                !((xDiff != 0 && yDiff == 0) || xDiff == 0 && yDiff != 0)) { // perpendicular movement
            System.out.println("Queens move diagonally or perpendicularly");
            return false;
        }
        if (this.isBlocked(board, fromX, fromY, toX, toY)){
            return false;
        }
        return true;
    }

    public boolean isBlocked(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX, yDiff = toY - fromY;
        if (Math.abs(xDiff) == Math.abs(yDiff)) { // diagonal move
            int xDir = xDiff / Math.abs(xDiff), yDir = yDiff / Math.abs(yDiff);
            for (int i = 1; i < Math.abs(toY - fromY); i++) {
                if (board.pieceExists(fromX + i * xDir, fromY + i * yDir)) {
                    System.out.println("Bishop blocked bum");
                    return true;
                }
            }
        } else { // perpendicular move
            if (xDiff == 0) { // vertical move
                int yDir = yDiff / Math.abs(yDiff);
                for (int i = 1; i < Math.abs(toY - fromY); i++) {
                    if (board.pieceExists(fromX, fromY + i * yDir)) {
                        return true;
                    }
                }
            } else { // horizontal move
                int xDir = xDiff / Math.abs(xDiff);
                for (int i = 1; i < Math.abs(toX - fromX); i++) {
                    if (board.pieceExists(fromX + i * xDir, fromY)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♕";
        } else {
            return "♛";
        }
    }
}
