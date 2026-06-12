package src;

public record Move(Point from, Point to, Piece movedPiece, Piece capturedPiece) {

    public Point getDir() {
        return new Point(Integer.compare(this.to.getX(), this.from.getX()),
                Integer.compare(this.to.getY(), this.from.getY()));
    }
}
