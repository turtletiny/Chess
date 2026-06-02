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
        if (!this.hasLineOfSight(board, fromX, fromY, toX, toY)){
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
