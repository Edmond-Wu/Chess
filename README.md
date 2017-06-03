# Java Chess
Chess game coded in Java meant to run in the terminal. All standard rules implemented.

## Move inputs
General moves follow the form: "starting-square ending-square" (ex: "e2 e4"). 

### Castling
To castle, the input would be the same as moving the king 2 squares king-side or queen-side.

### Promotions
Promotions follow the input form: "starting-square ending-square promotion-letter" (ex: "e7 e8 r" promotes to a rook). Promotions only happen if the pawn lands on either the 1st/8th rank depending on color otherwise one can't promote. Pawns cannot be selected as a promotion piece target. If no input is specified for the promotion, then it promotes to queen by default.
