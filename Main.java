public class Main {
    public static void main(String[] args) {
        // Setup
        Board board = new Board();
        double moveCount = 1;
        boolean running = true;

        // Game Loop
        while (running) {
            board.printBoard();
            System.out.println("\nTurn Number: " + (int) moveCount + ".");
            if (board.turnColour == Colour.WHITE) {
                System.out.println("♚ WHITE's turn\n");
            } else {
                System.out.println("♔ BLACK's turn\n");
            }
            while (true) {

                System.out.println("Enter move ");
                String move = In.nextLine();
                int fromX = move.charAt(0) - 96;
                int fromY = move.charAt(1) - 48;
                int toX = move.charAt(3) - 96;
                int toY = move.charAt(4) - 48;
                Piece selectedPiece = board.getPieceAt(fromX, fromY);
                if (!selectedPiece.isLegalMove(board, fromX, fromY, toX, toY)) {
                    System.out.println("Try again");
                    continue;
                } else {
                    selectedPiece.playMove(board, fromX, fromY, toX, toY);
                    if (board.isSquareAttacked(board.whiteKing.x, board.whiteKing.y)
                            || board.isSquareAttacked(board.blackKing.x, board.blackKing.y)) {
                    }
                    break;
                }
            }
        }
    }
}
