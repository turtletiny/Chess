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

        // Pawn move. FORMAT: "e4"
        if (input.length() == 2
                && input.charAt(0) >= 'a' && input.charAt(0) <= 'h'
                && input.charAt(1) >= '1' && input.charAt(1) <= '8') {
            Point toSquare = new Point(input.charAt(0) - 96, input.charAt(1) - 48);
            Point initialPoint = MoveNotation.pieceExists(toSquare, 'p', board, false);
            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);

            // Piece move. FORMAT: "ne4"
        } else if (input.length() == 3
                && MoveNotation.isValidPieceNotation(input.charAt(0))
                && input.charAt(1) >= 'a' && input.charAt(1) <= 'h'
                && input.charAt(2) >= '1' && input.charAt(2) <= '8') {
            Point toSquare = new Point(input.charAt(1) - 96, input.charAt(2) - 48);
            Point initialPoint = MoveNotation.pieceExists(toSquare, input.charAt(0), board, false);
            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);

            // Piece move. FORMAT: "nxe4"
        } else if (input.length() == 4
                && MoveNotation.isValidPieceNotation(input.charAt(0))
                && input.charAt(1) == 'x'
                && input.charAt(2) >= 'a' && input.charAt(2) <= 'h'
                && input.charAt(3) >= '1' && input.charAt(3) <= '8') {
            Point toSquare = new Point(input.charAt(2) - 96, input.charAt(3) - 48);
            Point initialPoint = MoveNotation.pieceExists(toSquare, input.charAt(0), board, true);
            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);

            // Piece move. FORMAT: "nae4" / "n1e4"
        } else if (input.length() == 4
                && MoveNotation.isValidPieceNotation(input.charAt(0))
                && ((input.charAt(1) >= 'a' && input.charAt(1) <= 'h')
                        || (input.charAt(1) >= '1' && input.charAt(1) <= '8'))
                && input.charAt(2) >= 'a' && input.charAt(2) <= 'h'
                && input.charAt(3) >= '1' && input.charAt(3) <= '8') {
            Point toSquare = new Point(input.charAt(2) - 96, input.charAt(3) - 48);
            Point initialPoint = MoveNotation.searchByAxis(toSquare, input.charAt(0), board, false, input.charAt(1));
            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);

            // Piece move. FORMAT: "n1xe4"
        } else if (input.length() == 5
                && MoveNotation.isValidPieceNotation(input.charAt(0))
                && ((input.charAt(1) >= 'a' && input.charAt(1) <= 'h')
                        || (input.charAt(1) >= '1' && input.charAt(1) <= '8'))
                && input.charAt(2) == 'x'
                && input.charAt(3) >= 'a' && input.charAt(3) <= 'h'
                && input.charAt(4) >= '1' && input.charAt(4) <= '8') {
            Point toSquare = new Point(input.charAt(3) - 96, input.charAt(4) - 48);
            Point initialPoint = MoveNotation.searchByAxis(toSquare, input.charAt(0), board, true, input.charAt(1));
            return (initialPoint == null) ? null : new Move(initialPoint, toSquare);
        } else {
            return null;
        }
    }

    public static boolean isValidPieceNotation(char c) {
        return MoveNotation.NOTATION_MAP.containsKey(c);
    }

    public static Point pieceExists(Point toSquare, char notation, Board board, boolean isCapture) {
        if (!NOTATION_MAP.containsKey(notation)) {
            return null;
        }
        Class<? extends Piece> targetClass = NOTATION_MAP.get(notation);
        int validCount = 0;
        Point validPoint = null;
        for (Piece p : board.board) {
            if (p != null
                    && targetClass.isInstance(p)
                    && p.isLegalMove(board, p.getPoint(), toSquare)
                    && ((!isCapture) || (isCapture && p.isCapture(board, toSquare)))) {
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

    public static Point searchByAxis(Point toSquare, char notation, Board board, boolean isCapture, char axis) {
        if (!NOTATION_MAP.containsKey(notation)) {
            return null;
        }
        Class<? extends Piece> targetClass = NOTATION_MAP.get(notation);
        int validCount = 0;
        Point validPoint = null;

        // char validity (a-h or 1-8) is already checked in convertMove()
        if (axis >= 97) {
            int i = MoveNotation.letterToRow(axis);
            for (; i < board.board.length; i += 8) {
                Piece p = board.board[i];
                if (p != null
                        && targetClass.isInstance(p)
                        && p.isLegalMove(board, p.getPoint(), toSquare)
                        && ((!isCapture) || (isCapture && p.isCapture(board, toSquare)))
            ) {
                    validCount++;
                    validPoint = p.getPoint();
                }
            }
        } else {
            int i = MoveNotation.charToColumn(axis);
            int j = i + 8;
            for (; i < j; i++) {
                Piece p = board.board[i];
                if (p != null
                        && targetClass.isInstance(p)
                        && p.isLegalMove(board, p.getPoint(), toSquare)
                        && ((!isCapture) || (isCapture && p.isCapture(board, toSquare)))) {
                    validCount++;
                    validPoint = p.getPoint();
                }
            }
        }
        if (validCount == 1) {
            return validPoint;
        } else {
            return null;
        }
    }

    private static int letterToRow(char letter) {
        return letter - 97; // 97 is the ascii value of 'a' -> converts a to 0, b to 1, etc, representing
                            // the index number to start from in the board array
    }

    private static int charToColumn(char num) {
        return 64 - 8 * (num - '0');
    }
}
