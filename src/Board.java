package src;

import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Board {
    private double moveCount;
    Colour turnColour;
    Piece cache;
    King blackKing, whiteKing;
    Rook whiteRookKingSide, whiteRookQueenSide, blackRookKingSide, blackRookQueenSide;
    Piece[] board;
    private HashMap<String, Integer> graveyard = new HashMap<>();
    private ArrayList<Move> moveLog = new ArrayList<>();

    Board() {
        this.turnColour = Colour.WHITE;
        this.board = new Piece[64];
        this.moveCount = 1;

        // == Initialise Pieces ==
        for (int i = 0; i < 8; i++) {
            // Pawns
            this.board[i + 8] = new Pawn(Colour.BLACK, new Point(Board.getX(i + 8), 7));
            this.board[i + 48] = new Pawn(Colour.WHITE, new Point(Board.getX(i + 48), 2));

            // Knights & Bishops
            if (i == 1 || i == 6) {
                this.board[i] = new Knight(Colour.BLACK, Board.getPoint(i));
                this.board[i + 56] = new Knight(Colour.WHITE, Board.getPoint(i + 56));
            } else if (i == 2 || i == 5) {
                this.board[i] = new Bishop(Colour.BLACK, Board.getPoint(i));
                this.board[i + 56] = new Bishop(Colour.WHITE, Board.getPoint(i + 56));
            }
        }
        // Rooks
        this.whiteRookKingSide = new Rook(Colour.WHITE, new Point(8, 1));
        this.whiteRookQueenSide = new Rook(Colour.WHITE, new Point(1, 1));
        this.blackRookKingSide = new Rook(Colour.BLACK, new Point(1, 8));
        this.blackRookQueenSide = new Rook(Colour.BLACK, new Point(8, 8));
        this.placePiece(this.whiteRookKingSide, new Point(8, 1));
        this.placePiece(this.whiteRookQueenSide, new Point(1, 1));
        this.placePiece(this.blackRookKingSide, new Point(1, 8));
        this.placePiece(this.blackRookQueenSide, new Point(8, 8));

        // Queens
        this.board[3] = new Queen(Colour.BLACK, new Point(4, 8));
        this.board[59] = new Queen(Colour.WHITE, new Point(4, 1));

        // Kings
        this.blackKing = new King(Colour.BLACK, new Point(5, 8));
        this.whiteKing = new King(Colour.WHITE, new Point(5, 1));
        this.board[4] = this.blackKing;
        this.board[60] = this.whiteKing;
    }

    // == Getters & Setters ==

    public King getKing(Colour colour) {
        if (colour.isWhite()) {
            return this.whiteKing;
        }
        return this.blackKing;
    }

    public static int getIndex(Point point) {
        return 8 * (8 - point.getY()) + point.getX() - 1;
    }

    public static int getX(int index) {
        return (index % 8) + 1;
    }

    public static int getY(int index) {
        return 8 - (index / 8);
    }

    public static Point getPoint(int index) {
        return new Point(getX(index), getY(index));
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

    public boolean pieceExists(Point point) {
        return this.board[Board.getIndex(point)] != null;
    }

    public int getMoveCount() {
        return (int) this.moveCount;
    }

    public Move getMove(String move) {
        Point from = new Point(move.charAt(0) - 96, move.charAt(1) - 48);
        Point to = new Point(move.charAt(3) - 96, move.charAt(4) - 48);
        Piece selectedPiece = this.getPieceAt(from);
        return new Move(from, to, selectedPiece, null, selectedPiece.colour, null);
    }

    public Move getLastMove() {
        if (this.moveLog.size() > 0) {
            return this.moveLog.getLast();
        }
        return null;

    }

    public void logMove(Move move) {
        this.moveLog.add(move);
    }

    public CastleAction canCastle(Colour colour, CastleAction castleSide) {
        CastleAction castleType = null;

        // White Short
        if (castleSide == CastleAction.SHORT && colour.isWhite()) {
            if (this.whiteRookKingSide.getHasMoved() || this.getKing(Colour.WHITE).getHasMoved()) {
                return castleType;
            } else if (this.getKing(colour).hasLineOfSight(this, new Point(5, 1), new Point(7, 1))) {
                for (int xDir = 1; xDir < 3; xDir++) {
                    if (this.leavesOwnKingExposed(this.getKing(colour), new Point(5, 1), new Point(5 + xDir, 1))) {
                        return castleType;
                    }
                }
                return CastleAction.WHITESHORT;

            }

            // White Long
        } else if (castleSide == CastleAction.LONG && colour.isWhite()) {
            if (this.whiteRookQueenSide.getHasMoved() || this.getKing(Colour.WHITE).getHasMoved()) {
                return castleType;
            } else if (this.getKing(colour).hasLineOfSight(this, new Point(5, 1), new Point(2, 1))) {
                for (int xDir = 1; xDir < 4; xDir++) {
                    if (this.leavesOwnKingExposed(this.getKing(colour), new Point(5, 1), new Point(5 - xDir, 1))) {
                        return castleType;
                    }
                }
                return CastleAction.WHITELONG;
            }
            // Black Short
        } else if (castleSide == CastleAction.SHORT && !colour.isWhite()) {
            if (this.blackRookKingSide.getHasMoved() || this.getKing(Colour.BLACK).getHasMoved()) {
                return castleType;
            } else if (this.getKing(colour).hasLineOfSight(this, new Point(5, 8), new Point(7, 8))) {
                for (int xDir = 1; xDir < 3; xDir++) {
                    if (this.leavesOwnKingExposed(this.getKing(colour), new Point(5, 8), new Point(5 + xDir, 8))) {
                        return castleType;
                    }
                }
                return CastleAction.BLACKSHORT;
            }

            // Black Long
        } else if (castleSide == CastleAction.LONG && !colour.isWhite()) {
            if (this.blackRookKingSide.getHasMoved() || this.getKing(Colour.BLACK).getHasMoved()) {
                return castleType;
            } else if (this.getKing(colour).hasLineOfSight(this, new Point(5, 8), new Point(2, 8))) {
                for (int xDir = 1; xDir < 4; xDir++) {
                    if (this.leavesOwnKingExposed(this.getKing(colour), new Point(5, 8), new Point(5 - xDir, 8))) {
                        return castleType;
                    }
                }
                return CastleAction.BLACKLONG;
            }
        }
        return castleType;
    }

    public void castle(CastleAction actionType) {
        switch (actionType) {
            case CastleAction.WHITESHORT -> {
                this.placePiece(this.getKing(Colour.WHITE), new Point(7, 1));
                this.clearSquare(new Point(5, 1));
                this.placePiece(this.whiteRookKingSide, new Point(6, 1));
                this.clearSquare(new Point(8, 1));
                this.whiteKing.setPoint(new Point(7, 1));
                this.whiteRookKingSide.setPoint(new Point(6, 1));
            }
            case CastleAction.WHITELONG -> {
                this.placePiece(this.getKing(Colour.WHITE), new Point(2, 1));
                this.clearSquare(new Point(5, 1));
                this.placePiece(this.whiteRookKingSide, new Point(3, 1));
                this.clearSquare(new Point(1, 1));
                this.whiteKing.setPoint(new Point(2, 1));
                this.whiteRookQueenSide.setPoint(new Point(3, 1));
            }
            case CastleAction.BLACKSHORT -> {
                this.placePiece(this.getKing(Colour.BLACK), new Point(7, 8));
                this.clearSquare(new Point(5, 8));
                this.placePiece(this.blackRookKingSide, new Point(6, 8));
                this.clearSquare(new Point(8, 8));
                this.blackKing.setPoint(new Point(7, 8));
                this.blackRookKingSide.setPoint(new Point(6, 8));
            }
            case CastleAction.BLACKLONG -> {
                this.placePiece(this.getKing(Colour.BLACK), new Point(2, 8));
                this.clearSquare(new Point(5, 8));
                this.placePiece(this.blackRookQueenSide, new Point(3, 8));
                this.clearSquare(new Point(1, 8));
                this.blackKing.setPoint(new Point(2, 8));
                this.blackRookQueenSide.setPoint(new Point(3, 8));
            }
            default -> {
                System.out.println("Error");
            }
        }
        this.turnToggle();
        this.incrementMoveCount(0.5);
        // set king to hasMoved

    }

    public boolean inCheck(Colour colour) {
        return this.isSquareAttacked(colour, this.getKingPoint(colour));
    }

    // == Board Actions ==
    public void clearSquare(Point point) {
        this.board[getIndex(point)] = null;
    }

    public void placePiece(Piece piece, Point point) {
        this.board[Board.getIndex(point)] = piece;
        if (piece != null) {
            piece.setPoint(point);
        }
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

    public boolean hasLegalMoves() {
        for (Piece p : this.board) {
            if (p != null && p.colour == this.turnColour && p.hasLegalMoves(this)) {
                return true;
            }
        }
        return false;
    }

    // == Board conditions ==

    public void turnToggle() {
        this.turnColour = this.turnColour.getOpposite();
    }

    public void toGraveyard(Piece piece) {
        // increment hashmap by 1
    }

    public void incrementMoveCount(double num) {
        this.moveCount += num;
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

    // == Testing==
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
}
