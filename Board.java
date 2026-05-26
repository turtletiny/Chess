class Board {

    Piece[][] board;

    Board() {
        this.board = new Piece[8][8];
        for (int i = 0; i < 8; i++) {
            this.board[1][i] = new Pawn(Colour.BLACK);
            this.board[6][i] = new Pawn(Colour.WHITE);
        }
    }

    public Piece getPieceAt(int x, int y) {
        return this.board[Math.abs(y - 8)][x - 1];
    }
    public void clearAtPos(int x, int y){
        this.board[Math.abs(y - 8)][x - 1] = null;
    }

    public void printBoard() {
        int coord = 8;
        System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
        for (Piece[] row : this.board) {
            String rowString = coord + " │  ";
            for (Piece p : row) {
                if (p == null) {
                    rowString += "X";
                } else {
                    rowString += p;
                }
                rowString += "  │  ";
            }
            System.out.println(rowString);
            if (coord != 1) {
                System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
            }

            coord--;
        }
        System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
        System.out.println("     A     B     C     D     E     F     G     H");
    }
}
// public void printBoard() {
//     System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
//     System.out.println("8 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("7 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("6 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("5 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("4 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("3 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("2 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//     System.out.println("1 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
//     System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
//     System.out.println("     A     B     C     D     E     F     G     H");
// }
