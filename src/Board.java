package src;

import java.util.HashMap;
import java.util.ArrayList;

class Board {
    private double moveCount;
    Colour turnColour;
    King blackKing, whiteKing;
    Rook whiteRookKingSide, whiteRookQueenSide, blackRookKingSide, blackRookQueenSide;
    Piece[] board;
    private HashMap<Piece, Integer> whiteGraveyard = new HashMap<>();
    private HashMap<Piece, Integer> blackGraveyard = new HashMap<>();
    private int pointDiff; // how many points white is up by
    private ArrayList<Move> moveLog = new ArrayList<>();

    Board() {
        this.turnColour = Colour.WHITE;
        this.board = new Piece[64];
        this.moveCount = 1;
        this.pointDiff = 0;

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
        this.blackRookKingSide = new Rook(Colour.BLACK, new Point(8, 8));
        this.blackRookQueenSide = new Rook(Colour.BLACK, new Point(1, 8));
        this.placePiece(this.whiteRookKingSide, new Point(8, 1));
        this.placePiece(this.whiteRookQueenSide, new Point(1, 1));
        this.placePiece(this.blackRookKingSide, new Point(8, 8));
        this.placePiece(this.blackRookQueenSide, new Point(1, 8));

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

    public Rook getRook(CastleAction castleSide) {
        return switch (castleSide) {
            case WHITESHORT -> this.whiteRookKingSide;
            case WHITELONG -> this.whiteRookQueenSide;
            case BLACKSHORT -> this.blackRookKingSide;
            case BLACKLONG -> this.blackRookQueenSide;
            default -> null;
        };
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

    public void updatePointDiff(Piece deadPiece) {
        if (deadPiece == null) {
            return;
        }
        if (deadPiece.colour.isWhite()) {
            this.pointDiff -= deadPiece.getValue();
        } else {
            this.pointDiff += deadPiece.getValue();
        }
    }

    public void logMove(Move move) {
        this.moveLog.add(move);
    }

    public boolean canCastle(Colour colour, CastleAction castleSide) {
        Rook rook = this.getRook(castleSide);
        if (this.getKing(colour).getHasMoved() || rook.getHasMoved()) {
            return false;
        }
        if (this.getPieceAt(rook.getPoint()) != rook) {
            return false;
        }
        if (this.getKing(colour).hasLineOfSight(this, this.getKingPoint(colour), rook.getPoint())) {
            int xDir = (castleSide == CastleAction.BLACKLONG || castleSide == CastleAction.WHITELONG) ? -1 : 1;
            while (Math.abs(xDir) < 3) {
                if (this.leavesOwnKingExposed(this.getKing(colour), this.getKingPoint(colour),
                        this.getKingPoint(colour).addValues(xDir, 0))) {
                    return false;
                }
                xDir += xDir / Math.abs(xDir);
            }
        } else {
            return false;
        }
        return true;
    }

    public void castle(CastleAction actionType) {
        switch (actionType) {
            case CastleAction.WHITESHORT -> {
                this.placePiece(this.getKing(Colour.WHITE), new Point(7, 1));
                this.clearSquare(new Point(5, 1));
                this.placePiece(this.whiteRookKingSide, new Point(6, 1));
                this.clearSquare(new Point(8, 1));
                this.whiteKing.setHasMoved(true);
                this.whiteRookKingSide.setHasMoved();
            }
            case CastleAction.WHITELONG -> {
                this.placePiece(this.getKing(Colour.WHITE), new Point(3, 1));
                this.clearSquare(new Point(5, 1));
                this.placePiece(this.whiteRookQueenSide, new Point(4, 1));
                this.clearSquare(new Point(1, 1));
                this.whiteKing.setHasMoved(true);
                this.whiteRookQueenSide.setHasMoved();
            }
            case CastleAction.BLACKSHORT -> {
                this.placePiece(this.getKing(Colour.BLACK), new Point(7, 8));
                this.clearSquare(new Point(5, 8));
                this.placePiece(this.blackRookKingSide, new Point(6, 8));
                this.clearSquare(new Point(8, 8));
                this.blackKing.setHasMoved(true);
                this.blackRookKingSide.setHasMoved();
            }
            case CastleAction.BLACKLONG -> {
                this.placePiece(this.getKing(Colour.BLACK), new Point(3, 8));
                this.clearSquare(new Point(5, 8));
                this.placePiece(this.blackRookQueenSide, new Point(4, 8));
                this.clearSquare(new Point(1, 8));
                this.blackKing.setHasMoved(true);
                this.blackRookQueenSide.setHasMoved();
            }
            default -> {
                System.out.println("Error");
            }
        }
        this.turnToggle();
        this.incrementMoveCount(0.5);
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
        if (piece == null) {
            return;
        }
        if (piece.colour.isWhite()) {
            this.whiteGraveyard.put(piece, this.whiteGraveyard.getOrDefault(piece, 0) + 1);
        } else {
            this.blackGraveyard.put(piece, this.blackGraveyard.getOrDefault(piece, 0) + 1);
        }
    }

    public void printGraveyard() {
        int diffTotal = 0;
        String str = "";
        for (Piece key : this.whiteGraveyard.keySet()) {
            int diff = this.whiteGraveyard.get(key) - this.blackGraveyard.getOrDefault(key, 0);
            diffTotal += diff * key.getValue();
        }
        System.out.println(str + "+" + diffTotal);
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
        System.out.println("");
        if (this.pointDiff < 0) {
            System.out.println("  Black: +" + Math.abs(this.pointDiff));
        } else {
            System.out.println("  Black  ");
        }
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
        System.out.println("      a     b     c     d     e     f     g     h\n");

        if (this.pointDiff > 0) {
            System.out.println("  White: +" + this.pointDiff);
        } else {
            System.out.println("  White");
        }
    }

    // public void drawBox() {
    // String s = (this.turnColour.isWhite()) ? "♚ WHITE's turn" : "♔ BLACK's turn";
    // String j = "Turn: " + (int) this.moveCount;
    // // j = "Turn: " + 13; //test line for double digit
    // int diff = j.length() - 7;
    // int horizontalBarCount = 16;
    // int frontPadding = 15;
    // int midPadding = 3;
    // String finalString = " ".repeat(frontPadding) + "╭" +
    // "─".repeat(horizontalBarCount) + "╮\n"
    // + " ".repeat(frontPadding) + "│ " + " ".repeat(midPadding) + j + "
    // ".repeat(midPadding)
    // + " ".repeat(diff) + "│\n"
    // + " ".repeat(frontPadding) + "│ " + s + " ".repeat(diff) + "│\n"
    // + " ".repeat(frontPadding) + "╰" + "─".repeat(horizontalBarCount) + "╯";

    // System.out.println(finalString);
    // }
    //
    public void drawBox() {
        String j = "Turn: " + (int) this.moveCount;
        // j = "Turn: " + 13; //test line for double digit
        String s = (this.turnColour.isWhite()) ? "♚ WHITE's turn" : "♔ BLACK's turn";
        int diff = j.length() - 7;
        System.out.println("               ╭" + "─".repeat(diff + 16) + "╮");
        System.out.println("               │    " + j + " ".repeat(diff + 4) + "│");
        System.out.println("               │" + s + " ".repeat(diff + 4) + "│");
        System.out.println("               " + "─".repeat(diff + 16));
    }

    // == Testing==
    Board(String s) { // for testing
        this.turnColour = Colour.WHITE;
        this.board = new Piece[64];
        this.moveCount = 1;
        this.blackKing = new King(Colour.BLACK);
        this.whiteKing = new King(Colour.WHITE);
        this.board[4] = this.blackKing;
        this.board[60] = this.whiteKing;
    }
}
