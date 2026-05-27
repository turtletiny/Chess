abstract class Piece {

    Colour colour;
    int x, y; // coordinate position of piece

    Piece(Colour colour) {
        this.colour = colour;
        //initialise x, y values based on position in board
        // or we could just loop through board and initialise
    }

    // public void clearCurrentPos(Board board) {
    //     board.board[get] = null;
    // }

    // Move Legality Checks
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
