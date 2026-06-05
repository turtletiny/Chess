enum Colour {
    WHITE(true),
    BLACK(false);

    private final boolean isWhite;

    Colour(Boolean isWhite) {
        this.isWhite = isWhite;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public Colour getOpposite() {
        return this == WHITE ? BLACK : WHITE;
    }
}
