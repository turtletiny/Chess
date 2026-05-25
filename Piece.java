abstract class Piece {

    Colour colour;
    int x, y; //coordinate position of piece

    Piece(Colour colour) {
        this.colour = colour;
    }

    void clearCurrentPos(Board board) {
        board.board[Math.abs(this.y - 8)][this.x - 1] = null; //changes current position to null
    }

    void placePiece(Board board, int x, int y) {
        this.x = x;
        this.y = y;
        board.board[Math.abs(this.y - 8)][this.x - 1] = this;
    }

    abstract boolean isLegalMove();
}
