package src;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

class MoveNotation {
    public static HashMap<Character, Class<? extends Piece>> NOTATION_MAP = new HashMap<>();

    MoveNotation() {
        MoveNotation.NOTATION_MAP.put('n', Knight.class);
        MoveNotation.NOTATION_MAP.put('q', Queen.class);
        MoveNotation.NOTATION_MAP.put('k', King.class);
        MoveNotation.NOTATION_MAP.put('r', Rook.class);
        MoveNotation.NOTATION_MAP.put('b', Bishop.class);
        MoveNotation.NOTATION_MAP.put(' ', Pawn.class);
    }

    Move convertMove(String input, Board board) {
        String safeInput = input.trim().toLowerCase();

        // Is a pawn move
        if (safeInput.length() == 2 &&
                safeInput.charAt(0) >= 'a' && safeInput.charAt(0) <= 'h'
                && safeInput.charAt(1) >= '1' && safeInput.charAt(1) <= '8') {
            Point toSquare = new Point(safeInput.charAt(0) - 96, safeInput.charAt(1) - 48);
            Point initialPoint = MoveNotation.pieceExists(toSquare, ' ', board); //blank char == pawn notation
            if (initialPoint == null) {
                return null;
            } else {
                return new Move(initialPoint, toSquare);
            }





        } else if (safeInput.length() == 3 &&
                this.isValidPieceNotation(safeInput.charAt(0))) {
            // dest = charat1, charat2
            // from = piece that can move to dest
        }

    }

    public boolean isValidPieceNotation(char c) {
        return MoveNotation.NOTATION_MAP.containsKey(c);
    }

    public static Point pieceExists(Point toSquare, char notation, Board board) {
        Class<? extends Piece> targetClass = NOTATION_MAP.get(notation);
        int validCount = 0;
        Point validPoint = null;
        for (Piece p : board.board) {
            if (p != null &&
                    targetClass.isInstance(p) &&
                    p.isLegalMove(board, p.getPoint(), toSquare)) {
                validCount++;
                validPoint = p.getPoint();
            }
        }
        if (validCount == 1) {
            return validPoint;
        } else {
            return null;
        }
    }
}

// ne4
// e2
// pe2
// Rad1
// r4c8
