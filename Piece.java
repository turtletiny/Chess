abstract class Piece {

    Colour colour;

    Piece(Colour colour) {
        this.colour = colour;
    }

    abstract void move();
}
