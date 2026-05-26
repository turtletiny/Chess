abstract class Piece {

    Colour colour;
    int x, y; //coordinate position of piece

    Piece(Colour colour) {
        this.colour = colour;
    }

    public void clearCurrentPos(Board board) {
        board.board[Math.abs(this.y - 8)][this.x - 1] = null;
    }

    public void placePiece(Board board, int x, int y) {
        this.x = x;
        this.y = y;
        board.board[Math.abs(this.y - 8)][this.x - 1] = this;
    }

    abstract boolean isLegalMove(int xDiff, int yDiff);
}
