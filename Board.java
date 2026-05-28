class Board {
    boolean whitesTurn;
    Piece[] board;

    Board() {
        this.whitesTurn = true;
        this.board = new Piece[64];
        for (int i = 0; i < 8; i++) {
            // Initialise Pawns
            this.board[i + 8] = new Pawn(Colour.BLACK, Board.getX(i + 8), 7);
            this.board[i + 48] = new Pawn(Colour.WHITE, Board.getX(i + 48), 2);

            // Initialise Pieces
            switch (i) {
                case 0, 7:
                    this.board[i] = new Rook(Colour.BLACK, Board.getX(i), 8);
                    this.board[i + 56] = new Rook(Colour.WHITE, Board.getX(i + 56), 1);
                    break;
                case 1, 6:
                    this.board[i] = new Knight(Colour.BLACK, Board.getX(i), 8);
                    this.board[i + 56] = new Knight(Colour.WHITE, Board.getX(i + 56), 1);
                    break;
                case 2, 5:
                    this.board[i] = new Bishop(Colour.BLACK, Board.getX(i), 8);
                    this.board[i + 56] = new Bishop(Colour.WHITE, Board.getX(i + 56), 1);
                    break;
            }
        }
        // Intialise Kings + Queens
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

    public boolean pieceExists(int x, int y) {
        return this.board[Board.getIndex(x, y)] != null;
    }

    public void printBoard() {
        int count = 1;
        int rowNum = 8;
        System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
        String row = rowNum + " │  ";
        for (Piece p : this.board) {
            if (p == null) {
                row += "   │  ";
            } else {
                row += p + "  │  ";
            }
            if (count % 8 == 0) {
                rowNum--;
                System.out.println(row);
                row = rowNum + " │  ";
                if (rowNum != 0) {
                    System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
                }

            }
            count++;
        }
        System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
        System.out.println("     a     b     c     d     e     f     g     h");
    }
}
