package src;

import java.util.HashSet;

class MoveNotation {
    //could be hashmap char : piece 
    HashSet<Character> validNotations = new HashSet<>();

    MoveNotation() {
        this.validNotations.add('n');
        this.validNotations.add('k');
        this.validNotations.add('q');
        this.validNotations.add('r');
        this.validNotations.add('b');
    }
    void convertMove(String input) {
        String safeInput = input.trim().toLowerCase();

        
        // Is a pawn move
        if (safeInput.length() == 2 &&
                safeInput.charAt(0) >= 'a' && safeInput.charAt(0) <= 'z'
                && safeInput.charAt(1) >= '1' && safeInput.charAt(1)<= '8') {
                //dest = safeInput
                //from = (if pawn exists that can move to dest)
        } else if (safeInput.length() == 3 && 
            this.isValidPieceNotation(safeInput.charAt(0))
        ) {
            //dest = charat1, charat2
            //from = piece that can move to dest
        }

    }


    public boolean isValidPieceNotation(char c) {
            return this.validNotations.contains(c);
        }
}

// ne4
// e2
// pe2
// Rad1
// r4c8
