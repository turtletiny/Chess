import java.util.ArrayList;
import java.util.HashMap;

class Board {
    Colour turnColour;
    Piece[] board;
    private double moveCount;
    private HashMap<String, Integer> graveyard; // maps piece (black or white) to count
    Piece cache;
    private ArrayList<String> moveLog;
    King blackKing, whiteKing; // allows for instant global calling

    Board() {
        this.turnColour = Colour.WHITE;
        this.board = new Piece[64];
        this.moveCount = 1;
        this.graveyard = new HashMap<>();
        this.moveLog = new ArrayList<>();

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
        this.board[59] = new Queen(Colour.WHITE);
        this.blackKing = new King(Colour.BLACK);
        this.whiteKing = new King(Colour.WHITE);
        this.board[4] = this.blackKing;
        this.board[60] = this.whiteKing;
    }

    // Getters
    public static int getIndex(int x, int y) {
        return 8 * (8 - y) + x - 1;
    }

    public static int getX(int index) {
        return (index % 8) + 1;
    }

    public static int getY(int index) {
        return 8 - (index / 8);
    }

    public int getKingX(Colour colour) {
        if (colour.isWhite()) {
            return this.whiteKing.x;
        }
        return this.blackKing.x;
    }

    public int getKingY(Colour colour) {
        if (colour.isWhite()) {
            return this.whiteKing.y;
        }
        return this.blackKing.y;
    }

    public Piece getPieceAt(int x, int y) {
        return this.board[getIndex(x, y)];
    }

    public int getMoveCount() {
        return (int) this.moveCount;
    }

    public static Move getMove(String move, Board board) {
        int fromX = move.charAt(0) - 96, fromY = move.charAt(1) - 48;
        int toX = move.charAt(3) - 96, toY = move.charAt(4) - 48;
        Piece selectedPiece = board.getPieceAt(fromX, fromY);
        return new Move(fromX, fromY, toX, toY, selectedPiece, null);
    }

    public String getLastMove() {
        return this.moveLog.getLast();
    }

    public void incrementMoveCount(double num) {
        this.moveCount += num;
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

    public void turnToggle() {
        this.turnColour = this.turnColour.getOpposite();
    }

    public void toGraveyard(Piece piece) {
        //increment hashmap by 1
    }

    // Checks if a square is attacked by pieces of enemy colour
    public boolean isSquareAttacked(Colour myColour, int x, int y) {
        for (Piece p : board) {
            if (p != null && p.colour != myColour && p.canAttack(this, x, y)) {
                return true;
            }
        }
        return false;
    }

    public boolean leavesOwnKingExposed(Piece piece, int fromX, int fromY, int toX, int toY) {
        Piece capturedPiece = this.getPieceAt(toX, toY);
        this.placePiece(piece, toX, toY);
        this.clearSquare(fromX, fromY);
        piece.x = toX;
        piece.y = toY;

        boolean exposed = this.isSquareAttacked(piece.colour, this.getKingX(piece.colour), this.getKingY(piece.colour));

        this.placePiece(piece, fromX, fromY);
        this.placePiece(capturedPiece, toX, toY);
        piece.x = fromX;
        piece.y = fromY;

        return exposed;
    }

    public boolean inCheck(Colour colour) {
        return this.isSquareAttacked(colour, this.getKingX(colour), this.getKingY(colour));
    }

    // public boolean hasLegalMoves(){
    // for (Piece p : this.board){

    // }
    // }

    public void logMove(String move) {
        this.moveLog.add(move);
    }

    public void printLog() {
        System.out.println("Move Log");
        for (int i = 0; i < this.moveLog.size(); i += 2) {
            System.out.println((i + 1) + ". " + this.moveLog.get(i) + "| " + this.moveLog.get(i + 1));
        }
    }

    public void printBoard() {
        int count = 1;
        int rowNum = 8;
        System.out.println("   ┌─────┬─────┬─────┬─────┬─────┬─────┬─────┬─────┐");
        String row = rowNum + " │  ";
        for (Piece p : this.board) {
            if (p == null) {
                row += "   │  ";
            } else {
                row += p + "  │  ";
            }
            if (count % 8 == 0) {
                rowNum--;
                System.out.println(" " + row);
                row = rowNum + " │  ";
                if (rowNum != 0) {
                    System.out.println("   ├─────┼─────┼─────┼─────┼─────┼─────┼─────┼─────┤");
                }
            }
            count++;
        }
        System.out.println("   └─────┴─────┴─────┴─────┴─────┴─────┴─────┴─────┘");
        System.out.println("      a     b     c     d     e     f     g     h");
    }
}
