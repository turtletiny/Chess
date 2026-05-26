class Bishop extends Piece {

    Bishop(Colour colour) {
        super(colour);
    }

    public void move(Board board) {}

    @Override
    public boolean isLegalMove(int xDiff, int yDiff) {
        if (Math.abs(xDiff) != Math.abs(yDiff)) {
            return false;
        }
        if (Math.abs(xDiff) > 7){
            return false;
        }
        return true;
    }

    public String toString() {
        if (this.colour == Colour.BLACK) {
            return "♗";
        } else {
            return "♝";
        }
    }
}
//Legal moves:
// this.x && this.y has to change by abs(n)
//
