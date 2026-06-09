class Move {
    private final Point from, to;
    private final Piece movedPiece, capturedPiece;

    public Move(Point from, Point to, Piece movedPiece, Piece capturedPiece) {
        this.from = from;
        this.to = to;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    public Point getFrom() {
        return this.from;
    }

    public Point getTo() {
        return this.to;
    }

    public Point getDir() {
        return new Point(Integer.compare(this.to.getX(), this.from.getX()),
                Integer.compare(this.to.getY(), this.from.getY()));
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

}
