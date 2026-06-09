import java.util.ArrayList;
import java.util.HashMap;

class Board {
    Colour turnColour;
    Piece[] board;
    private double moveCount;
    Piece cache;
    private HashMap<String, Integer> graveyard = new HashMap<>();
    private ArrayList<Move> moveLog = new ArrayList<>();
    King blackKing, whiteKing;

    Board(String s) { // for testing
        this.turnColour = Colour.WHITE;
        this.board = new Piece[64];
        this.moveCount = 1;
        this.graveyard = new HashMap<>();
        this.blackKing = new King(Colour.BLACK);
        this.whiteKing = new King(Colour.WHITE);
        this.board[4] = this.blackKing;
        this.board[60] = this.whiteKing;
    }

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
    public static int getIndex(Point point) {
        return 8 * (8 - point.getY()) + point.getX() - 1;
    }

    public static int getX(int index) {
        return (index % 8) + 1;
    }

    public static int getY(int index) {
        return 8 - (index / 8);
    }


    public Point getKingPoint(Colour colour) {
        if (colour.isWhite()) {
            return this.whiteKing.getPoint();
        } else {
            return this.blackKing.getPoint();
        }
    }

    public Piece getPieceAt(Point point) {
        return this.board[getIndex(point)];
    }

    public int getMoveCount() {
        return (int) this.moveCount;
    }

    public static Move getMove(String move, Board board) {
        Point from = new Point(move.charAt(0) - 96, move.charAt(1) - 48);
        Point to = new Point(move.charAt(3) - 96, move.charAt(4) - 48);
        Piece selectedPiece = board.getPieceAt(from);
        return new Move(from, to, selectedPiece, null);
    }

    public Move getLastMove() {
        return this.moveLog.getLast();
    }

    public void incrementMoveCount(double num) {
        this.moveCount += num;
    }

    public void clearSquare(Point point) {
        this.board[getIndex(point)] = null;
    }

    public void placePiece(Piece piece, Point point) {
        this.board[Board.getIndex(point)] = piece;
        if (piece != null) {
            piece.setPoint(point);
        }
    }

    public boolean pieceExists(Point point) {
        return this.board[Board.getIndex(point)] != null;
    }

    public void turnToggle() {
        this.turnColour = this.turnColour.getOpposite();
    }

    public void toGraveyard(Piece piece) {
        // increment hashmap by 1
    }

    // Checks if a square is attacked by pieces of enemy colour
    public boolean isSquareAttacked(Colour myColour, Point point) {
        for (Piece p : board) {
            if (p != null && p.colour != myColour && p.canAttack(this, point)) {
                return true;
            }
        }
        return false;
    }

    public boolean leavesOwnKingExposed(Piece piece, Point from, Point to) {
        Piece capturedPiece = this.getPieceAt(to);
        this.placePiece(piece, to);
        this.clearSquare(from);
        piece.setPoint(to);

        boolean exposed = this.isSquareAttacked(piece.colour, this.getKingPoint(piece.colour));

        this.placePiece(piece, from);
        this.placePiece(capturedPiece, to);
        piece.setPoint(from);
        return exposed;
    }

    public boolean inCheck(Colour colour) {
        return this.isSquareAttacked(colour, this.getKingPoint(colour));
    }

    public boolean hasLegalMoves() {
        for (Piece p : this.board) {
            if (p != null && p.colour == this.turnColour && p.hasLegalMoves(this)) {
                return true;
            }
        }
        return false;
    }

    public void logMove(Move move) {
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

    // testing
    public void clearBoard() {
        for (Piece p : this.board) {
            p = null;
        }
    }
}
