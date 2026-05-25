class Board {

    Piece[][] board;

    Board() {
        this.board = new Piece[8][8];
    }

    public void printBoard() {
        System.out.println("  ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
        System.out.println("8 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("7 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("6 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("5 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("4 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("3 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("2 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
        System.out.println("1 │  j  │  j  │  j  │  j  │  j  │  j  │  j  │  j  │");
        System.out.println("  └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
        System.out.println("     A     B     C     D     E     F     G     H");
    }
}
