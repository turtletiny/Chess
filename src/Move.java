//Representation of a move for the movelog

package src;

public record Move(Point from, Point to, Piece movedPiece, Piece capturedPiece, Colour colour, CastleAction castleMove) {

    public Move(Colour colour, CastleAction castleMove){
        this(null, null, null, null, colour, castleMove);
    }

    public Move(Point from, Point to) {
        this(from, to, null, null, null,null);
    }


    public Point getDir() {
        return new Point(Integer.compare(this.to.getX(), this.from.getX()),
                Integer.compare(this.to.getY(), this.from.getY()));
    }

    public Point getDiff() {
        return Point.subtractPoints(to, from);
    }

    public String toString() {
        return this.from + "->" + this.to;
    }
}
