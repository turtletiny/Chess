abstract class Piece {

    Colour colour;
    int x, y; //coordinate position of piece

    Piece(Colour colour) {
        this.colour = colour;
    }

    public void clearCurrentPos(Board board) {
        board.board[Math.abs(this.y - 8)][this.x - 1] = null; //changes current position to null
    }

    public void placePiece(Board board, int x, int y) {
        this.x = x;
        this.y = y;
        board.board[Math.abs(this.y - 8)][this.x - 1] = this;
    }
    public Piece getPieceAt(Board board, int x, int y){
        return board.board[Math.abs(this.y - 8)][this.x - 1];
    }

    abstract boolean isLegalMove();
}
