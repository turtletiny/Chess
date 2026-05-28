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

    boolean moveInBounds(int newX, int newY) {
        if (newX < 1 || newX > 8 || newY < 1 || newY > 8) {
            return false;
        }
        return true;
    }
}
