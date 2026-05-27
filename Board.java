class Board {
    Piece[] board;

    Board() {
        this.board = new Piece[64];
        for (int i = 0; i < 8; i++) {
            this.board[i + 8] = new Pawn(Colour.BLACK);
            this.board[i + 48] = new Pawn(Colour.WHITE);
        }
        this.board[0] = this.board[7] = new Rook(Colour.BLACK);
        this.board[56] = this.board[63] = new Rook(Colour.WHITE);
        this.board[1] = this.board[6] = new Knight(Colour.BLACK);
        this.board[57] = this.board[62] = new Knight(Colour.WHITE);
        this.board[2] = this.board[5] = new Bishop(Colour.BLACK);
        this.board[58] = this.board[61] = new Bishop(Colour.WHITE);
        this.board[3] = new Queen(Colour.BLACK);
        this.board[4] = new King(Colour.BLACK);
        this.board[59] = new Queen(Colour.WHITE);
        this.board[60] = new King(Colour.WHITE);
    }

    public static int getIndex(int x, int y) {
        return 8 * (8 - y) + x - 1;
    }

    public static int getX(int index) {
        return (index % 8) + 1;
    }

    public static int getY(int index) {
        return 8 - (index / 8);
    }

    public Piece getPieceAt(int x, int y) {
        return this.board[getIndex(x, y)];
    }

    public void clearSquare(int x, int y) {
        this.board[getIndex(x, y)] = null;
    }

    public void placePiece(Piece piece, int x, int y) {
        this.board[Board.getIndex(x, y)] = piece;
    }

    public void printBoard() {
        int count = 1;
        int rowNum = 1;
        System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
        String row = rowNum + " │  ";
        for (Piece p : this.board) {

            if (p == null) {
                row += "   │  ";
            } else {
                row += p + "  │  ";
            }
            if (count % 8 == 0) {
                System.out.println(row);
                row = rowNum + " │  ";
                if (rowNum != 8) {
                    System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
                }
                rowNum++;
            }
            count++;
        }
        System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
        System.out.println("     a     b     c     d     e     f     g     h");
    }
}
