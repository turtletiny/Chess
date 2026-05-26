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

    //Move Legality Checks
    boolean pieceExists(Board board, int fromX, int fromY) {
        return board.getPieceAt(fromX, fromY) != null;
    }

    boolean moveInBounds(int newX, int newY) {
        if (newX < 1 || newX > 8 || newY < 1 || newY > 8) {
            return false;
        }
        return true;
    }
}
