# Priority

- en passant logic 
- out of index checks
- set king to hasmoved in castle()
- optimise Board.castle()
- optimise Board.canCastle()

# later

- input / exception handling
- promotion optimisation + theming
- consistency with functions and organisation
- error message order
- storing and displaying captured pieces
- displaying points and point differences
- castling - keep track of: whether king has moved and which rooks have moved
  - cant castle through check (lineOfSight)
  - cant castle into check 
- getting out of check (capture the piece in lineOfSight, block lineOfSight with another piece, move into legal square)
- Stalemate (!inCheck && noLegalMoves)
- Checkmate (inCheck && noLegalMoves)
- refactor to using Move class to represent moves
- use Square class to store x/y coord
