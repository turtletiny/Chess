package src;

enum Colour {
    WHITE(true, "#eeeed2"),
    BLACK(false, "#769656");

    private final boolean isWhite;
    private final String colourCode;

    Colour(boolean isWhite,String colourCode ) {
        this.isWhite = isWhite;
        this.colourCode = colourCode;
    }

    public boolean isWhite() {
        return this.isWhite;
    }

    public Colour getOpposite() {
        return this == WHITE ? BLACK : WHITE;
    }

    public String getColourCode() {
        return this.colourCode;
    }
}
