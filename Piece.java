abstract class Piece {

    Colour colour;

    Piece(Colour colour) {
        this.colour = colour;
    }

    abstract void move(Board board, int x, int y);
    abstract boolean isLegalMove();
}
