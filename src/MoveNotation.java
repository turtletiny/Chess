package src;

import java.util.HashMap;

class MoveNotation {
    public static HashMap<Character, Class<? extends Piece>> NOTATION_MAP = new HashMap<>();

    static {
        MoveNotation.NOTATION_MAP.put('n', Knight.class);
        MoveNotation.NOTATION_MAP.put('q', Queen.class);
        MoveNotation.NOTATION_MAP.put('k', King.class);
        MoveNotation.NOTATION_MAP.put('r', Rook.class);
        MoveNotation.NOTATION_MAP.put('b', Bishop.class);
        MoveNotation.NOTATION_MAP.put('p', Pawn.class);
    }

    public static Move convertMove(String input, Board board) {
        String safeInput = input.trim().toLowerCase();

        // Pawn move. FORMAT: "e4"
        if (safeInput.length() == 2 &&
                safeInput.charAt(0) >= 'a' && safeInput.charAt(0) <= 'h'
                && safeInput.charAt(1) >= '1' && safeInput.charAt(1) <= '8') {
            Point toSquare = new Point(safeInput.charAt(0) - 96, safeInput.charAt(1) - 48);
            Point initialPoint = MoveNotation.pieceExists(toSquare, 'p', board);

            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);

            // Piece move. FORMAT: "ne4"
        } else if (safeInput.length() == 3 &&
                MoveNotation.isValidPieceNotation(safeInput.charAt(0)) &&
                safeInput.charAt(1) >= 'a' && safeInput.charAt(1) <= 'h'
                && safeInput.charAt(2) >= '1' && safeInput.charAt(1) <= '8') {
            Point toSquare = new Point(safeInput.charAt(1) - 96, safeInput.charAt(2) - 48);
            Point initialPoint = MoveNotation.pieceExists(toSquare, safeInput.charAt(0), board);

            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);
        }
        return null;
    }

    public static boolean isValidPieceNotation(char c) {
        return MoveNotation.NOTATION_MAP.containsKey(c);
    }

    public static Point pieceExists(Point toSquare, char notation, Board board) {
        if (!NOTATION_MAP.containsKey(notation)) {
            return null;
        }
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
