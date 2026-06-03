# notes
- pawn.isCorrectMovePattern() uses different signature to piece.isCorrectMovePattern

# Priority
- en passant logic 
- refactor to using Move class to represent moves

# later

- input / exception handling
- consistency with functions and organisation
- error message order
- have a class called 'coordinate'
- have a class called 'move'
- storing and displaying captured pieces
- displaying points and point differences
- pawn capture logic
- en passant
- castling - keep track of: whether king has moved and which rooks have moved
  - cant castle through check (lineOfSight)
  - cant castle into check 
- log of moves: stack or arraylist
- getting out of check (capture the piece in lineOfSight, block lineOfSight with another piece, move into legal square)
- Stalemate (!inCheck && noLegalMoves)
- Checkmate (inCheck && noLegalMoves)
