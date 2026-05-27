// class Board {

//     Piece[][] board;

//     Board() {
//         this.board = new Piece[8][8];

//         //Place pawns
//         for (int i = 0; i < 8; i++) {
//             this.board[1][i] = new Pawn(Colour.BLACK);
//             this.board[6][i] = new Pawn(Colour.WHITE);
//         }
//         //Place rooks
//         this.board[0][0] = this.board[0][7] = new Rook(Colour.BLACK);
//         this.board[7][0] = this.board[7][7] = new Rook(Colour.WHITE);
//         this.board[0][1] = this.board[0][6] = new Knight(Colour.BLACK);
//         this.board[7][1] = this.board[7][6] = new Knight(Colour.WHITE);
//         this.board[0][2] = this.board[0][5] = new Bishop(Colour.BLACK);
//         this.board[7][2] = this.board[7][5] = new Bishop(Colour.WHITE);
//         this.board[7][3] = new Queen(Colour.WHITE);
//         this.board[7][4] = new King(Colour.WHITE);
//         this.board[0][3] = new Queen(Colour.BLACK);
//         this.board[0][4] = new King(Colour.BLACK);

//     }

//     public Piece getPieceAt(int x, int y) {
//         return this.board[Math.abs(y - 8)][x - 1];
//     }
//     public void clearAtPos(int x, int y){
//         this.board[Math.abs(y - 8)][x - 1] = null;
//     }

//     public void printBoard() {
//         int coord = 8;
//         System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
//         for (Piece[] row : this.board) {
//             String rowString = coord + " │  ";
//             for (Piece p : row) {
//                 if (p == null) {
//                     rowString += " ";
//                 } else {
//                     rowString += p;
//                 }
//                 rowString += "  │  ";
//             }
//             System.out.println(rowString);
//             if (coord != 1) {
//                 System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
//             }

//             coord--;
//         }
//         System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
//         System.out.println("     a     b     c     d     e     f     g     h");
//     }
// }

class Board {
    Piece[] board;
    // index+1 == 8 * abs(8-y) + x

    Board() {
        this.board = new Piece[64];
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

    public void clearAtPos(int x, int y) {
        this.board[getIndex(x, y)] = null;
    }

    public void printBoard() {
        int count = 1;
        int rowNum = 1;
        System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
        for (Piece p : this.board) {
            String row = rowNum + " │  ";
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

    public static void main(String[] args) {
        Board board = new Board();
        board.printBoard();
    }

}
