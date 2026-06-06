abstract class Piece {

    Colour colour;
    int x, y;
    private final int[][] DIRECTIONS = {};

    Piece(Colour colour) {
        this.colour = colour;
        if (this.colour == Colour.WHITE) {
            this.y = 1;
        } else {
            this.y = 8;
        }
    }

    Piece(Colour colour, int x, int y) {
        this.colour = colour;
        this.x = x;
        this.y = y;
    }

    public int[][] getDirections() {
        return this.DIRECTIONS;
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

    public void dummyMove(Board board, int fromX, int fromY, int toX, int toY){
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
        for (int[] dir : this.DIRECTIONS) {
            int curX = this.x, curY = this.y;
            int xDir = dir[0], yDir = dir[1];
            while (!board.pieceExists(curX + xDir, curY + yDir) && this.moveInBounds(curX + xDir, curY + yDir)) {
                if (this.isLegalMove(board, curX, curY, curX + xDir, curY + yDir)) {
                    return true;
                }
                curX += xDir;
                curY += yDir;
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
        if (correctMovePattern(board, fromX, fromY, toX, toY) && !board.leavesOwnKingExposed(this, fromX, fromY, toX, toY)){
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
            System.out.println("You cant capture your own piece!");
            return true;
        }
        return false;
    }

}
