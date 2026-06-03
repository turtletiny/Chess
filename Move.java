class Move {
    private final int fromX, fromY, toX, toY;
    private final Piece movedPiece, capturedPiece;

    public Move(int fromX, int fromY, int toX, int toY, Piece movedPiece, Piece capturedPiece) {
        this.fromX = fromX;
        this.fromY = fromY;
        this.toX = toX;
        this.toY = toY;
        this.movedPiece = movedPiece;
        this.capturedPiece = capturedPiece;
    }

    public int getFromX() {
        return this.fromX;
    }

    public int getFromY() {
        return this.fromY;
    }

    public int getToX() {
        return this.toX;
    }

    public int getToY() {
        return this.toY;
    }

    public Piece getMovedPiece() {
        return this.movedPiece;
    }

    public Piece getCapturedPiece() {
        return this.capturedPiece;
    }

}
