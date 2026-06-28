//CLI Chess Game

package src;

public class Main {
    public static void main(String[] args) {
        // Setup
        Board board = new Board();
        boolean playing = true;
        Colour winner = null;

        System.out.println("\nChess.");
        System.out.println("Enter any key to start game");
        In.nextLine();

        // Game Loop
        while (playing) {
            board.printBoard();
            board.drawBox();
            while (true) {
                try {
                    System.out.println(" Enter move ");
                    String move = In.nextLine();
                    // can put these into a function later
                    if (CastleAction.inMap(move)) {
                        if (board.inCheck(board.turnColour)) {
                            System.out.println("You can't castle out of check.");
                            continue;
                        } else if (!board.canCastle(board.turnColour,
                                CastleAction.getCastleAction(move, board.turnColour))) {
                            System.out.println("Invalid castle attempt, try again");
                            continue;
                        }
                        board.castle(CastleAction.getCastleAction(move, board.turnColour));
                        // board.logMove(new Move(board.turnColour,
                        // CastleAction.getCastleAction(move)));
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
                        board.logMove(board.getMove(move));
                        selectedPiece.playMove(board, from, to);

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
                } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException e) {
                    System.out.println("Invalid Input!");
                    In.nextLine();
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
