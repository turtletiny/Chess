class King extends Piece {

    King(Colour colour) {
        super(colour);
        this.x = 5;
    }

    public void move(Board board) {
    }

    public boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!super.isLegalMove(board, fromX, fromY, toX, toY)){
            return false;
        }
        int xDiff = toX - fromX;
        int yDiff = toY - fromY;
        if (Math.abs(xDiff) > 1 || Math.abs(yDiff) > 1) {
            System.out.println("Kings move 1 square at a time");
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
