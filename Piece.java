abstract class Piece {

    Colour colour;
    int x, y;

    Piece(Colour colour) {
        this.colour = colour;
        if (this.colour == Colour.WHITE) {
            this.y = 1;
        } else {
            this.y = 8;
        }
    }

    Piece(Colour colour, int x, int y) {
        this.colour = colour;
        this.x = x;
        this.y = y;
    }

    boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if ((board.whitesTurn && board.getPieceAt(fromX, fromY).colour != Colour.WHITE) // can put into method later
                || (!board.whitesTurn && board.getPieceAt(fromX, fromY).colour != Colour.BLACK))
            return false;
        board.pieceExists(fromX, fromY);
        this.moveInBounds(toX, toY);
        return true;
    }

    boolean moveInBounds(int toX, int toY) {
        if (toX < 1 || toX > 8 || toY < 1 || toY > 8) {
            return false;
        }
        return true;
    }
}
