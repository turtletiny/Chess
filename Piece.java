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
        if (!board.pieceExists(fromX, fromY)){
            System.out.println("There's no piece on that square! ");
            return false;
        }
        if (!playerColour(board)){
            System.out.println("That's not your piece! ");
            return false;
        }
        if (!this.moveInBounds(toX, toY)){
            System.out.println("That move is off the board! ");
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
}
