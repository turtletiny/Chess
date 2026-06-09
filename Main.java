public class Main {
    public static void main(String[] args) {
        // Setup
        Board board = new Board();
        boolean playing = true;
        Colour winner = null;

        // Game Loop
        while (playing) {
            board.printBoard();
            System.out.println("\nTurn Number: " + board.getMoveCount() + ".");
            if (board.turnColour == Colour.WHITE) {
                System.out.println("♚ WHITE's turn\n");
            } else {
                System.out.println("♔ BLACK's turn\n");
            }
            while (true) {

                System.out.println("Enter move ");
                String move = In.nextLine();
                // can put these into a function later
                    int fromX = move.charAt(0) - 96;
                    int fromY = move.charAt(1) - 48;
                    int toX = move.charAt(3) - 96;
                    int toY = move.charAt(4) - 48;

                Piece selectedPiece = board.getPieceAt(fromX, fromY);
                if (!board.pieceExists(fromX, fromY)
                        || !selectedPiece.isLegalMove(board, fromX, fromY, toX, toY)
                        || board.leavesOwnKingExposed(selectedPiece, fromX, fromY, toX, toY)) {
                    System.out.println("Try again");
                    continue;
                } else {
                    selectedPiece.playMove(board, fromX, fromY, toX, toY);
                    // board.logMove(move);
                    if (board.inCheck(board.turnColour) && board.hasLegalMoves()) {
                        System.out.println(board.turnColour + " in check");
                    } else if (board.inCheck(board.turnColour) && !board.hasLegalMoves()) {
                        winner = board.turnColour.getOpposite();
                        playing = false;
                    } else if (!board.inCheck(board.turnColour) && !board.hasLegalMoves()) {
                        playing = false;
                    }
                    break;
                }
            }
        }
        if (winner == null) {
            System.out.println("Stalemate in " + board.getMoveCount() + " moves");
        } else {
            System.out.println(winner + " wins by checkmate in " + board.getMoveCount() + " moves");
        }
    }
}
