abstract class Piece {

    Colour colour;
    int x,y; //coordinate position of piece

    Piece(Colour colour) {
        this.colour = colour;
    }

    void clearCurrentPos(Board board){
        board.board[Math.abs(this.y-8)][this.x-1] = null; //changes current position to null

    }


    abstract boolean isLegalMove();

    }
