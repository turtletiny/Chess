# Priority
- pawn logic is still incomplete (logic reversed for 1 or 2 movess


# later
- input / exception handling
- consistency with functions and organisation
- store each piece logic into its own function (e.g diagonal movement for bishop)
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
