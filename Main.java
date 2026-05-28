public class Main {
    public static void main(String[] args) {
        // Setup
        Board board = new Board();
        double moveCount = 1;
        boolean whitesTurn = true;
        boolean running = true;
        boolean whiteCastlingRights = true;
        boolean blackCastlingRights = false;

        // Game Loop
        while (running) {
            board.printBoard();
            System.out.println("\nTurn Number: " + (int) moveCount + ".");
            if (whitesTurn) {
                System.out.println("♚ WHITE's turn\n");
            } else {
                System.out.println("♔ BLACK's turn\n");
            }
            System.out.println("Enter move ");
            String move = In.nextLine();
            int fromX = move.charAt(0) - 96;
            int fromY = move.charAt(1) - 48;
            int toX = move.charAt(3) - 96;
            int toY = move.charAt(4) - 48;
            Piece selectedPiece = board.getPieceAt(fromX, fromY);
            whitesTurn = !whitesTurn;
        }
    }
}
