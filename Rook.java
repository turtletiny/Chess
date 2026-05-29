class Rook extends Piece {

    Rook(Colour colour) {
        super(colour);
    }

    Rook(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if (xDiff != 0 && yDiff != 0) {
            return false;
        }
        return true;
    }

    public boolean isBlocked(Board board, int fromX, int fromY, int toX, int toY) {
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if (fromX == 0) { // then its a vertical move
            for (int i = 1; i < Math.abs(toY - fromY); i++) {
                if (board.pieceExists(fromX, fromY + i  * yDiff / Math.abs(yDiff))) {
                    return true;
                }
            }
        } else {
            for (int i = 1; i < Math.abs(toX - fromX); i++){
                if (board.pieceExists(fromX + i * xDiff / Math.abs(xDiff), fromY)){
                    return true;
                }
            }
        }
        return false;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♖";
        } else {
            return "♜";
        }
    }
}
