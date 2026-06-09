package src;

abstract class Piece {

    Colour colour;
    private Point point;

    private final String id;
    private final int[][] DIRECTIONS = {};

    Piece(Colour colour) {
        this.colour = colour;
        this.point = new Point(0, 0); // Initialize point
        if (this.colour.isWhite()) {
            this.point.setX(1);
            this.id = "white" + this.getClass().getSimpleName();
        } else {
            this.point.setY(8);
            this.id = "black" + this.getClass().getSimpleName();
        }
    }

    Piece(Colour colour, Point point) {
        this.colour = colour;
        this.point = point;
        this.id = (this.colour.isWhite()) ? "white" + this.getClass().getSimpleName()
                : "black" + this.getClass().getSimpleName();
    }

    // == Getters & Setters ==
    public int[][] getDirections() {
        return this.DIRECTIONS;
    }

    public String getId() {
        return this.id;
    }

    public Point getPoint() {
        return this.point;
    }

    public void setPoint(Point newPoint) {
        this.point = newPoint;
    }

    // == Piece Logic ==
    public void playMove(Board board, Point from, Point to) {
        board.cache = board.getPieceAt(to);
        board.placePiece(this, to);
        board.clearSquare(from);
        this.point = to;
        board.turnToggle();
        board.incrementMoveCount(0.5);
    }

    // Represents unique move pattern for each piece
    abstract boolean correctMovePattern(Board board, Point from, Point to);

    public boolean hasLegalMoves(Board board) {
        for (int[] dir : this.getDirections()) {
            int xDir = dir[0], yDir = dir[1];
            Point target = new Point(this.point.getX() + xDir, this.point.getY() + yDir);
            while (this.moveInBounds(target)) {
                if (this.isStrictlyLegal(board, this.point, target)) {
                    return true;
                }
                if (board.pieceExists(target)) {
                    break;
                }
                target.setX(target.getX() + xDir); // note to self: add point addition / subtraction
                target.setY(target.getY() + yDir);
            }
        }
        return false;
    }

    // Whether a piece can attack a square
    public boolean canAttack(Board board, Point target) {
        if (this.correctMovePattern(board, this.point, target) && this.hasLineOfSight(board, this.point, target)) {
            return true;
        }
        return false;
    }

    // horizontal, vertical and diagonal line of sight (Note: Doesnt check if the
    // type of move is right for the piece)
    boolean hasLineOfSight(Board board, Point from, Point to) {
        Point dir = new Point(Integer.compare(to.getX(), from.getX()), Integer.compare(to.getY(), from.getY()));
        Point curPoint = Point.addPoints(new Point(from.getX(), from.getY()), dir);
        while (curPoint.getX() != to.getX() || curPoint.getY() != to.getY()) {
            if (board.pieceExists(curPoint)) {
                return false;
            }
            curPoint = Point.addPoints(curPoint, dir);
        }
        return true;
    }

    // boolean hasLineOfSight(Board board, int fromX, int fromY, int toX, int toY) {
    // int xDir = Integer.compare(toX, fromX);
    // int yDir = Integer.compare(toY, fromY);
    // int curX = fromX + xDir;
    // int curY = fromY + yDir;
    // while (curX != toX || curY != toY) {
    // if (board.pieceExists(curX, curY)) {
    // return false;
    // }
    // curX += xDir;
    // curY += yDir;
    // }
    // return true;
    // }

    // Checks that apply to all pieces
    boolean isLegalMove(Board board, Point from, Point to) {
        if (!board.pieceExists(from)) {
            System.out.println("no piece there!");
            return false;
        }
        if (!playerColour(board)) {
            return false;
        }
        if (!this.moveInBounds(to)) {
            return false;
        }
        if (this.capturingOwnPiece(board, to)) {
            return false;
        }
        if (!this.correctMovePattern(board, from, to)) {
            return false;
        }

        return true;
    }

    boolean isStrictlyLegal(Board board, Point from, Point to) {
        if (this.moveInBounds(to)
                && correctMovePattern(board, this.point, to)
                && !board.leavesOwnKingExposed(this, this.point, to)
                && !this.capturingOwnPiece(board, to)) {
            return true;
        }
        return false;
    }

    boolean moveInBounds(Point to) {
        if (to.getX() < 1 || to.getX() > 8 || to.getY() < 1 || to.getY() > 8) {
            return false;
        }
        return true;
    }

    boolean playerColour(Board board) {
        return this.colour == board.turnColour;
    }

    boolean isCapture(Board board, Point to) {
        return board.pieceExists(to);
    }

    boolean capturingOwnPiece(Board board, Point to) {
        if (board.pieceExists(to) && board.getPieceAt(to).colour == this.colour) {
            return true;
        }
        return false;
    }

}
