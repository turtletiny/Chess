

class Pawn extends Piece {

    Pawn(Colour colour) {
        super(colour);
    }

    public void move(Board board) {

    }

    public boolean isLegalMove(){
        return true;

    }

    public String toString(){
        if (this.colour == Colour.BLACK){
            return "♙";


        }
        else{
            return "♟";

        }

    }

}
