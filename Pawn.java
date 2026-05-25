class Pawn extends Piece {

    Pawn(Colour colour) {
        super(colour);
    }

    public void move(Board board) {
        board.board[1][0] = null;
        board.board[2][0] = this;
    }
}
