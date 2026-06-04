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

    public void playMove(Board board, int fromX, int fromY, int toX, int toY) {
        board.placePiece(this, toX, toY);
        board.clearSquare(fromX, fromY);
        this.x = toX;
        this.y = toY;
        board.turnToggle();
    }

    // Represents unique move pattern for each piece
    public boolean correctMovePattern(int fromX, int fromY, int toX, int toY) {
        return true;
    }

    // Whether a piece is attacking a square
    public boolean isAttacking(int x, int y) {
        return true;
    }

    // horizontal, vertical and diagonal line of sight (Note: Doesnt check if the
    // type of move is right for the piece)
    boolean hasLineOfSight(Board board, int fromX, int fromY, int toX, int toY) {
        int xDir = Integer.compare(toX, fromX);
        int yDir = Integer.compare(toY, fromY);
        int curX = fromX + xDir;
        int curY = fromY + yDir;
        while (curX != toX || curY != toY) {
            if (board.pieceExists(curX, curY)) {
                return false;
            }
            curX += xDir;
            curY += yDir;
        }
        return true;
    }

    // Checks that apply to all pieces
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
        if (board.pieceExists(toX, toY) && board.getPieceAt(toX, toY).colour == this.colour) {
            System.out.println("You cant capture your own piece!");
            return true;
        }
        return false;
    }

}
