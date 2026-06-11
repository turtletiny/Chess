package src;

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
                if (CastleAction.inMap(move)) {
                    // check if rook / king have moved corresponding to getCastleAction
                    // king cannot be in check
                    // move king square by square to see if its valid (not blocked, not castling
                    // through check)
                    // place place rook next to king
                    if (board.inCheck(board.turnColour)) {
                        System.out.println("You can't castle out of check.");
                        continue;
                    } else if (board.canCastle(board.turnColour, CastleAction.getCastleAction(move)) == null) {
                        System.out.println("Invalid castle attempt, try again");
                        continue;
                    }
                    board.castle(CastleAction.getCastleAction(move));
                    break;

                }
                Point from = new Point(move.charAt(0) - 96, move.charAt(1) - 48);
                Point to = new Point(move.charAt(3) - 96, move.charAt(4) - 48);

                Piece selectedPiece = board.getPieceAt(from);
                if (!board.pieceExists(from)
                        || !selectedPiece.isLegalMove(board, from, to)
                        || board.leavesOwnKingExposed(selectedPiece, from, to)) {
                    System.out.println("Try again");
                    continue;
                } else {
                    selectedPiece.playMove(board, from, to);
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
