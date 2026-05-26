public class Main {

    public static void main(String[] args) {
        //Setup
        Board board = new Board();
        boolean running = true;

        //Game Loop
        while (running) {
            board.printBoard();
            System.out.println("");

            System.out.println("Enter coordinate: ");
            String fromCoord = In.nextLine();
            System.out.println("Enter move: ");
            String toCoord = In.nextLine();
            int fromX = fromCoord.charAt(0) - 96;
            int fromY = fromCoord.charAt(1) - 48;
            int toX = toCoord.charAt(0) - 96;
            int toY = toCoord.charAt(1) - 48;
            Piece selectedPiece = board.getPieceAt(fromX, fromY);
            selectedPiece.placePiece(board, toX, toY);
            board.clearAtPos(fromX, fromY);

            In.nextLine();
        }
    }
}
