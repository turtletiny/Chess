

class Queen extends Piece {

    Queen(Colour colour) {
        super(colour);
    }

    public void move(Board board) {}

    public boolean isLegalMove(int xDiff, int yDiff) {
        if ((Math.abs(xDiff) != Math.abs(yDiff)) && //diagonal movement
            !((xDiff != 0 && yDiff == 0) || xDiff == 0 && yDiff != 0)) { //perpendicular movement
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
