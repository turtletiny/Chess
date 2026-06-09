abstract class Piece {

    Colour colour;
    int x, y;
    private final String id;
    private final int[][] DIRECTIONS = {};

    Piece(Colour colour) {
        this.colour = colour;
        if (this.colour.isWhite()) {
            this.y = 1;
            this.id = "white" + this.getClass().getSimpleName();
        } else {
            this.y = 8;
            this.id = "black" + this.getClass().getSimpleName();
        }
    }

    Piece(Colour colour, int x, int y) {
        this.colour = colour;
        this.x = x;
        this.y = y;
        this.id = (this.colour.isWhite()) ? "white" + this.getClass().getSimpleName()
                : "black" + this.getClass().getSimpleName();
    }

    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    public String getId() {
        return this.id;
    }

    public void playMove(Board board, int fromX, int fromY, int toX, int toY) {
        board.cache = board.getPieceAt(toX, toY);
        board.placePiece(this, toX, toY);
        board.clearSquare(fromX, fromY);
        this.x = toX;
        this.y = toY;
        board.turnToggle();
        board.incrementMoveCount(0.5);
    }

    public void dummyMove(Board board, int fromX, int fromY, int toX, int toY) {
        Piece capturedPiece = board.getPieceAt(toX, toY);
        board.placePiece(this, toX, toY);
        board.clearSquare(fromX, fromY);
        this.x = toX;
        this.y = toY;
    }

    public void revertMove(Board board, int fromX, int fromY, int toX, int toY) {
        board.placePiece(this, fromX, fromY);
        board.placePiece(board.cache, toX, toY);
        this.x = fromX;
        this.y = fromY;
        board.turnToggle();
        board.incrementMoveCount(-0.5);
        board.cache = null;
    }

    // Represents unique move pattern for each piece
    abstract boolean correctMovePattern(Board board, int fromX, int fromY, int toX, int toY);

    public boolean hasLegalMoves(Board board) {
        for (int[] dir : this.getDirections()) {
            int xDir = dir[0], yDir = dir[1];
            int targetX = this.x + xDir, targetY = this.y + yDir;
            while (this.moveInBounds(targetX, targetY)) {
                if (this.isStrictlyLegal(board, this.x, this.y, targetX, targetY)) {
                    return true;
                }
                if (board.pieceExists(targetX, targetY)) {
                    break;
                }
                targetX += xDir;
                targetY += yDir;
            }
        }
        return false;
    }

    // Whether a piece can attack a square
    public boolean canAttack(Board board, int x, int y) {
        if (this.correctMovePattern(board, this.x, this.y, x, y) && this.hasLineOfSight(board, this.x, this.y, x, y)) {
            return true;
        }
        return false;
    }

    // horizontal, vertical and diagonal line of sight (Note: Doesnt check if the
    // type of move is right for the piece)
    boolean hasLineOfSight(Board board, int fromX, int fromY, int toX, int toY) {
        int xDir = Integer.compare(toX, fromX);
        int yDir = Integer.compare(toY, fromY);
        int curX = fromX + xDir;
        int curY = fromY + yDir;
        while (curX != toX || curY != toY) {
            if (board.pieceExists(curX, curY)) {
                return false;
            }
            curX += xDir;
            curY += yDir;
        }
        return true;
    }

    // Checks that apply to all pieces
    boolean isLegalMove(Board board, int fromX, int fromY, int toX, int toY) {
        if (!board.pieceExists(fromX, fromY)) {
            System.out.println("no piece there!");
            return false;
        }
        if (!playerColour(board)) {
            return false;
        }
        if (!this.moveInBounds(toX, toY)) {
            return false;
        }
        if (this.capturingOwnPiece(board, toX, toY)) {
            return false;
        }
        if (!this.correctMovePattern(board, fromX, fromY, toX, toY)) {
            return false;
        }

        return true;
    }

    boolean isStrictlyLegal(Board board, int fromX, int fromY, int toX, int toY) {
        if (this.moveInBounds(toX, toY)
                && correctMovePattern(board, this.x, this.y, toX, toY)
                && !board.leavesOwnKingExposed(this, this.x, this.y, toX, toY)
                && !this.capturingOwnPiece(board, toX, toY)) {
            return true;
        }
        return false;
    }

    boolean moveInBounds(int toX, int toY) {
        if (toX < 1 || toX > 8 || toY < 1 || toY > 8) {
            return false;
        }
        return true;
    }

    boolean playerColour(Board board) {
        return this.colour == board.turnColour;
    }

    boolean isCapture(Board board, int toX, int toY) {
        return board.pieceExists(toX, toY);
    }

    boolean capturingOwnPiece(Board board, int toX, int toY) {
        if (board.pieceExists(toX, toY) && board.getPieceAt(toX, toY).colour == this.colour) {
            return true;
        }
        return false;
    }

}
