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

    public boolean playMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (isLegalMove(board, fromX, fromY, toX, toY)) {
            board.placePiece(this, toX, toY);
            board.clearSquare(fromX, fromY);
            board.turnToggle();
            return true;
        }
        return false;
    }

    boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!board.pieceExists(fromX, fromY)) {
            System.out.println("There's no piece on that square! ");
            return false;
        }
        if (!playerColour(board)) {
            System.out.println("That's not your piece! ");
            return false;
        }
        if (!this.moveInBounds(toX, toY)) {
            System.out.println("That move is off the board! ");
            return false;
        }
        if (this.capturingOwnPiece(board, toX, toY)) {
            return false;
        }
        return true;
    }

    boolean moveInBounds(int toX, int toY) {
        if (toX < 1 || toX > 8 || toY < 1 || toY > 8) {
            return false;
        }
        return true;
    }

    boolean playerColour(Board board) {
        return this.colour == board.turnColour;
    }

    boolean isCapture(Board board, int toX, int toY) {
        return board.pieceExists(toX, toY);
    }

    boolean capturingOwnPiece(Board board, int toX, int toY) {
        if (board.getPieceAt(toX, toY).colour == this.colour) {
            System.out.println("You cant capture your own piece!");
            return true;
        }
        return false;
    }

}
