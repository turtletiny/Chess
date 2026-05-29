class Pawn extends Piece {
    boolean hasMoved;

    Pawn(Colour colour) {
        super(colour);
        this.hasMoved = false;
    }

    Pawn(Colour colour, int x, int y) {
        super(colour, x, y);
    }

    public void move(Board board) {
        this.hasMoved = true;
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)) {
            return false;
        }
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;

        if (xDiff != 0 && !this.isCapture(board, toX, toY)) {
            System.out.println("Pawns can only move diagonally on capture");
            return false;
        }
        if (this.colour == Colour.WHITE && yDiff < 0) {
            System.out.println("Pawns can only move forwards");
            return false;
        }
        if (this.colour == Colour.BLACK && yDiff > 0) {
            System.out.println("Pawns can only move forwards");
            return false;
        }
        if (this.hasMoved && !(Math.abs(yDiff) == 1 || Math.abs(yDiff) == 2)) {
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♙";

        } else {
            return "♟";

        }

    }

}
