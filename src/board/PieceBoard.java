package board;

import java.util.ArrayList;

import chess.Chess;
import chess.Chess.BlockedException;
import chess.Chess.CheckException;
import chess.Chess.WrongColorException;
import pieces.Bishop;
import pieces.Knight;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;
import pieces.Piece.Color;
import pieces.Piece.PieceType;

/**
 * Class dealing with piece-board interactions
 */
public class PieceBoard {
	static ArrayList<String> white_moves = new ArrayList<String>();
	static ArrayList<String> black_moves = new ArrayList<String>();
	
	static int lastPawnMoveOrCapture = 0;
	static boolean check = false;
	static boolean end = false;
	
	/**
	 * Gets the amount of moves since the last pawn move or capture
	 * @return
	 */
	public static int getLastPawnMoveOrCapture() {
		return lastPawnMoveOrCapture;
	}
	
	/**
	 * Gets value of the check variable
	 * @return
	 */
	public static boolean getCheckStatus() {
		return check;
	}
	
	/**
	 * Gets the value of the end variable
	 * @return
	 */
	public static boolean isEnd() {
		return end;
	}
	
	/**
	 * Sets the value of end to true
	 */
	public static void setEnd() {
		end = true;
	}
	
	/**
	 * Checks to see if a move has a clear path and if it is the correct piece. Throws a BlockedException if there is a piece in the way
	 * 
	 * @param board Chess board
	 * @param firstRank corresponding index of the rank of the starting position
	 * @param firstFile corresponding index of the file of the starting position
	 * @param secondRank corresponding index of the rank of the ending position
	 * @param secondFile corresponding index of the file of the ending position
	 * 
	 */
	private static void checkPath(Board board, int firstRank, int firstFile, int secondRank, int secondFile) throws BlockedException {
		Color color = board.getSquare(firstRank, firstFile).getPiece().getColor();
		Square s1 = board.getSquare(firstRank, firstFile);
		Square s2 = board.getSquare(secondRank, secondFile);
		Piece p = s1.getPiece();
		//check path
		Square pathSquare;
		switch(p.getPieceType()) {
			case PAWN: 
				if (!p.hasMoved()) {
					if (p.getColor() == Color.BLACK) {
						pathSquare = board.getSquare(firstRank + 1, firstFile);
					}
					else {
						pathSquare = board.getSquare(firstRank - 1, firstFile);
					}
					if (!pathSquare.isEmpty()) {
						throw new BlockedException();
					}
				}
				break;
			
			case BISHOP:
				//going in the upper direction of the board
				int i, j;
				if (firstRank > secondRank) {
					//going left
					i = firstRank - 1;
					if (firstFile > secondFile) {
						j = firstFile - 1;
						while (i > secondRank && j > secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i--;
							j--;
						}
					}
					//going right
					else {
						j = firstFile + 1;
						while (i > secondRank && j < secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i--;
							j++;
						}
					}
				}
				//going down
				else {
					i = firstRank + 1;
					//going left
					if (firstFile > secondFile) {
						j = firstFile - 1;
						while (i < secondRank && j > secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i++;
							j--;
						}
					}
					//going right
					else {
						j = firstFile + 1;
						while (i < secondRank && j < secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i++;
							j++;
						}
					}
				}
				break;
				
			case ROOK:
				int a;
				//if files are the same
				if (firstFile == secondFile) {
					if (firstRank < secondRank) {
						for (a = firstRank + 1; a < secondRank; a++) {
							pathSquare = board.getSquare(a, firstFile);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
					else {
						for (a = firstRank - 1; a > secondRank; a--) {
							pathSquare = board.getSquare(a, firstFile);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
				}
				//otherwise ranks are the same
				else if (firstRank == secondRank){
					if (firstFile < secondFile) {
						for (a = firstFile + 1; a < secondFile; a++) {
							pathSquare = board.getSquare(firstRank, a);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
					else {
						for (a = firstFile - 1; a > secondFile; a--) {
							pathSquare = board.getSquare(firstRank, a);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
				}
				break;
			case QUEEN:
				int b;
				//if files are the same
				if (firstFile == secondFile) {
					if (firstRank < secondRank) {
						for (b = firstRank + 1; b < secondRank; b++) {
							pathSquare = board.getSquare(b, firstFile);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
					else {
						for (b = firstRank - 1; b > secondRank; b--) {
							pathSquare = board.getSquare(b, firstFile);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
				}
				//otherwise ranks are the same
				else if (firstRank == secondRank){
					if (firstFile < secondFile) {
						for (b = firstFile + 1; b < secondFile; b++) {
							pathSquare = board.getSquare(firstRank, b);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
					else {
						for (b = firstFile - 1; b > secondFile; b--) {
							pathSquare = board.getSquare(firstRank, b);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
						}
					}
				}
				//going up
				if (firstRank > secondRank) {
					//going left
					i = firstRank - 1;
					if (firstFile > secondFile) {
						j = firstFile - 1;
						while (i > secondRank && j > secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i--;
							j--;
						}
					}
					//going right
					else {
						j = firstFile + 1;
						while (i > secondRank && j < secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i--;
							j++;
						}
					}
				}
				//going down
				else {
					i = firstRank + 1;
					//going left
					if (firstFile > secondFile) {
						j = firstFile - 1;
						while (i < secondRank && j > secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i++;
							j--;
						}
					}
					//going right
					else {
						j = firstFile + 1;
						while (i < secondRank && j < secondFile) {
							pathSquare = board.getSquare(i, j);
							if (!pathSquare.isEmpty()) {
								throw new BlockedException();
							}
							i++;
							j++;
						}
					}
				}
				break;
			default:
				break;
		}
		if(s2.getPiece() != null){
			if(color == s2.getPiece().getColor()){
				
				if (s1.getPiece().getPieceType() != PieceType.PAWN) {
					if (color == Color.WHITE) {
						board.getCoveredWhite()[secondRank][secondFile] = 1;
					}
					else {
						board.getCoveredBlack()[secondRank][secondFile] = 1;
					}
				}
				throw new BlockedException();
			}
		}
	}
	
	/**
	 * Moves a piece using a string representation of a move. Throws an IllegalArgumentException if the move is invalid.
	 * and throws a WrongColorException if the piece is of the wrong color.
	 * 
	 * @param move String representation of a move
	 * @param board Chess board
	 * @param color color of the player
	 * @throws Exception 
	 * 
	 */
	public static void movePiece(String move, Board board, Color color) throws Exception {
		Color opposite;
		if (color == Color.WHITE) {
			opposite = Color.BLACK;
		}
		else {
			opposite = Color.WHITE;
		}
		int[] ranksAndFiles = Chess.parseMove(move);
		if(color != board.getSquare(ranksAndFiles[0], ranksAndFiles[1]).getPiece().getColor()){
			throw new WrongColorException();
		}
		checkPath(board, ranksAndFiles[0], ranksAndFiles[1], ranksAndFiles[2], ranksAndFiles[3]);
		Square s1 = board.getSquare(ranksAndFiles[0], ranksAndFiles[1]);
		Square s2 = board.getSquare(ranksAndFiles[2], ranksAndFiles[3]);
		Piece p = s1.getPiece();
		boolean hasMovedTemp = p.hasMoved();
		Piece p2Temp = s2.getPiece();
		boolean hasMovedTemp2 = false;
		if(p2Temp != null){
			hasMovedTemp2 = p2Temp.hasMoved();
		}
		//castling for white
		if (p.getPieceType() == PieceType.KING) {
			Piece rook_castle;
			if (p.getColor() == Color.WHITE) {
				if (s1.toString().equals("[7,4]") && (s2.toString().equals("[7,6]") || s2.toString().equals("[7,2]"))) {
					if (s2.toString().equals("[7,6]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() - 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() + 1).getPiece();
						if (canCastle(p, rook_castle, board)) {
							p.move(s2.getRank(), s2.getIntFile());
							board.getSquare(s2.getRank(), s2.getIntFile()).occupy(p);
							s1.removePiece();
							board.getSquare(s2.getRank(), s1.getIntFile() + 1).occupy(new Rook(s2.getRank(), s1.getIntFile() + 1, p.getColor()));
							board.getSquare(s2.getRank(), s2.getIntFile() + 1).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}
						else {
							throw new IllegalArgumentException();
						}
					}
					else if (s2.toString().equals("[7,2]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() + 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() - 2).getPiece();
						if (canCastle(p, rook_castle, board)) {
							p.move(s2.getRank(), s2.getIntFile());
							board.getSquare(s2.getRank(), s2.getIntFile()).occupy(p);
							s1.removePiece();
							board.getSquare(s2.getRank(), s1.getIntFile() - 1).occupy(new Rook(s2.getRank(), s1.getIntFile() - 1, p.getColor()));
							board.getSquare(s2.getRank(), s2.getIntFile() - 2).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}	
						else {
							throw new IllegalArgumentException();
						}
					}
				}
			}
			//castling for black
			else {
				if (s1.toString().equals("[0,4]") && (s2.toString().equals("[0,6]") || s2.toString().equals("[0,2]"))) {
					if (s2.toString().equals("[0,6]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() - 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() + 1).getPiece();
						if (canCastle(p, rook_castle, board)) {
							p.move(s2.getRank(), s2.getIntFile());
							p.setMoved();
							board.getSquare(s2.getRank(), s2.getIntFile()).occupy(p);
							s1.removePiece();
							board.getSquare(s2.getRank(), s1.getIntFile() + 1).occupy(new Rook(s2.getRank(), s1.getIntFile() + 1, p.getColor()));
							board.getSquare(s2.getRank(), s1.getIntFile() + 1).getPiece().setMoved();
							board.getSquare(s2.getRank(), s2.getIntFile() + 1).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}
						else {
							throw new IllegalArgumentException();
						}
					}
					if (s2.toString().equals("[0,2]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() + 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() - 2).getPiece();
						if (canCastle(p, rook_castle, board)) {
							p.move(s2.getRank(), s2.getIntFile());
							p.setMoved();
							board.getSquare(s2.getRank(), s2.getIntFile()).occupy(p);
							s1.removePiece();
							board.getSquare(s2.getRank(), s1.getIntFile() - 1).occupy(new Rook(s2.getRank(), s1.getIntFile() - 1, p.getColor()));
							board.getSquare(s2.getRank(), s1.getIntFile() - 1).getPiece().setMoved();
							board.getSquare(s2.getRank(), s2.getIntFile() - 2).removePiece();
							lastPawnMoveOrCapture++;
							return;
						}		
						else {
							throw new IllegalArgumentException();
						}
					}	
				}
			}
		}
		
		//pawn can only move diagonally if it's capturing
		if (p.getPieceType() == PieceType.PAWN) {
			//check for en passant
			if (canEnPassant(p, board, s2)) {
				Square adj_square;
				if (p.getColor() == Color.WHITE) {
					adj_square = board.getSquare(s2.getRank() + 1, s2.getIntFile());
					board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
				}
				else {
					adj_square = board.getSquare(s2.getRank() - 1, s2.getIntFile());
					board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
				}
				adj_square.removePiece();
			}
			else {
				if (p.getColor() == Color.WHITE) {
					if (s2 != board.getSquare(s1.getRank() - 1, s1.getIntFile())) {
						if (p.hasMoved()){ 
							if ((!canEnPassant(p, board, s2) && s2.isEmpty()) || s2.isEmpty() || s2.getPiece().getColor() == Color.WHITE) {
								throw new IllegalArgumentException();
							}
						}
						else {
							if (s2 != board.getSquare(s1.getRank() - 2, s1.getIntFile()) && (s2.isEmpty() || s2.getPiece().getColor() == Color.WHITE)) {
								throw new IllegalArgumentException();
							}
						}
					}
					if (s2 == board.getSquare(s1.getRank() - 1, s1.getIntFile())  || s2 == board.getSquare(s1.getRank() - 2, s2.getIntFile())) {
						Piece blocked = s2.getPiece();
						if (blocked != null) {
							throw new BlockedException();
						}
					}
				}
				else {
					if (s2 != board.getSquare(s1.getRank() + 1, s1.getIntFile()) && p.hasMoved()) {
						if (p.hasMoved()) {
							if ((!canEnPassant(p, board, s2) && s2.isEmpty()) || s2.isEmpty() || s2.getPiece().getColor() == Color.BLACK) {
								throw new IllegalArgumentException();
							}
						}
						else {
							if (s2 != board.getSquare(s1.getRank() + 2, s1.getIntFile()) && (s2.isEmpty() || s2.getPiece().getColor() == Color.BLACK)) {
								throw new IllegalArgumentException();
							}
						}
					}
					if (s2 == board.getSquare(s1.getRank() + 1, s1.getIntFile())  || s2 == board.getSquare(s1.getRank() + 2, s2.getIntFile())) {
						Piece blocked = s2.getPiece();
						if (blocked != null) {
							throw new BlockedException();
						}
					}
				}
			}
		}
		
		Piece captured = s2.getPiece();
		if (captured != null || p.getPieceType() == PieceType.PAWN) {
			if (captured != null) {
				if (captured.getColor() == Color.WHITE) {
					board.getWhiteCounts().put(captured.getPieceType(), board.getWhiteCounts().get(captured.getPieceType()) - 1);
				}
				else {
					board.getBlackCounts().put(captured.getPieceType(), board.getBlackCounts().get(captured.getPieceType()) - 1);
				}
				s2.removePiece();
			}
			lastPawnMoveOrCapture = 0;
		}
		else {
			lastPawnMoveOrCapture++;
		}
		p.move(s2.getRank(), s2.getIntFile());
		updateProtectedSquares(board);
		p.setMoved();
		s2.occupy(p);
		s1.removePiece();
		if (p.getColor() == Color.WHITE) {
			white_moves.add(move);
		}
		else {
			black_moves.add(move);
		}
		
		//promotes it to Queen by default; additional promotions specified in promotePawn method
		if (s2.getPiece().getPieceType() == PieceType.PAWN) {
			if (s2.getPiece().getColor() == Color.WHITE) {
				if (s2.getRank() == 0) {
					s2.removePiece();
					s2.occupy(new Queen(s2.getRank(), s2.getIntFile(), color));
					board.getWhiteCounts().put(PieceType.QUEEN, board.getWhiteCounts().get(PieceType.QUEEN) + 1);
					board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
				}
			}
			else {
				if (s2.getRank() == 7) {
					s2.removePiece();
					s2.occupy(new Queen(s2.getRank(), s2.getIntFile(), color));
					board.getBlackCounts().put(PieceType.QUEEN, board.getBlackCounts().get(PieceType.QUEEN) + 1);
					board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
				}
			}
		}
		updateProtectedSquares(board);
		inCheck(board, opposite);
		updateProtectedSquares(board);
		if(color == Color.WHITE){
			inCheck(board, Color.BLACK);
		} else {
			inCheck(board, Color.WHITE);
		}
		if(check){	
			p.absoluteMove(ranksAndFiles[0], ranksAndFiles[1], hasMovedTemp);
			s1.occupy(p);
			s2.removePiece();
			if(p2Temp != null){
				p2Temp.absoluteMove(ranksAndFiles[2], ranksAndFiles[3], hasMovedTemp2);
				s2.occupy(p2Temp);
			}
			throw new CheckException();
		}
		if(!hasLegalMoves(board, color)){
			updateProtectedSquares(board);
			inCheck(board, color);
			System.out.println();
			board.printBoard();
			System.out.println();
			if(check){
				String col = color == Color.BLACK ? "Black" : "White";
				System.out.println(col + " wins");
			} else {
				System.out.println("Stalemate");
			}
			System.exit(0);
		}
	}
	
	/**
	 * Promotes a pawn using a string representation of a move. Throws an IllegalArgumentException if the move is invalid
	 * and throws a WrongColorException if the piece is of the wrong color.
	 * 
	 * @param move String representation of a move
	 * @param board Chess board
	 * @param color color of the player
	 * @throws Exception 
	 * 
	 */
	public static void promotePawn(String move, Board board, Color color) throws Exception {
		if(move.charAt(5) != ' '){
			throw new IllegalArgumentException();
		}
		String promoteTo = ("" + move.charAt(6)).toLowerCase(); //character representation of the piece to promote to
		int[] ranksAndFiles = Chess.parseMove(move.substring(0, 5));
		Square start = board.getSquare(ranksAndFiles[0], ranksAndFiles[1]), end = board.getSquare(ranksAndFiles[2], ranksAndFiles[3]);
		if(color != start.getPiece().getColor()){
			throw new WrongColorException();
		}
		checkPath(board, ranksAndFiles[0], ranksAndFiles[1], ranksAndFiles[2], ranksAndFiles[3]);
		//move piece accordingly
		System.out.println(move);
		movePiece(move.substring(0, 5), board, color);
		Piece p;
		end.removePiece();
		
		switch(promoteTo) {
			case "q":
				p = new Queen(ranksAndFiles[2], ranksAndFiles[3], color);
				break;
			
			case "n":
				p = new Knight(ranksAndFiles[2], ranksAndFiles[3], color);
				break;
			
			case "r":
				p = new Rook(ranksAndFiles[2], ranksAndFiles[3], color);
				break;
				
			case "b":
				p = new Bishop(ranksAndFiles[2], ranksAndFiles[3], color);
				break;
				
			default:
				throw new IllegalArgumentException();
		}
		
		end.occupy(p);
		lastPawnMoveOrCapture = 0;
		if (color == Color.WHITE) {
			board.getWhiteCounts().put(p.getPieceType(), board.getWhiteCounts().get(p.getPieceType()) + 1);
			board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
		}
		else {
			board.getBlackCounts().put(p.getPieceType(), board.getBlackCounts().get(p.getPieceType()) + 1);
			board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
		}
		updateProtectedSquares(board);
	}
	
	/**
	 * Updates the board's corresponding protected squares array for a particular piece
	 * @param p Piece of any type
	 * @param b An instance of a Board
	 * @throws BlockedException 
	 */
	public static void updateProtectedSquares(Board b) {
		b.resetCoveredSquares();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Piece p = b.getSquare(i, j).getPiece();
				//only execute if the first square has a piece
				if (p != null) {
					//different cases for non-pawns
					if (p.getPieceType() != PieceType.PAWN) {
						for (int x = 0; x < 8; x++) {
							for (int y = 0; y < 8; y++) {
								if (x != i || y != j) {
									try {
										p.checkMove(x, y);
										checkPath(b, i, j, x, y);
									} catch (Exception e) {
										continue;
									}
									if (p.getColor() == Color.WHITE) {
										b.getCoveredWhite()[x][y] = 1;
									}
									else {
										b.getCoveredBlack()[x][y] = 1;
									}
								}
							}
						}
					}
					else {
						
						int file = p.getCol();
						int rank = p.getRow();
						if (rank != 0 && rank != 7) {
							if (file != 0 && file != 7) {
								if (p.getColor() == Color.WHITE) {
									b.getCoveredWhite()[rank-1][file+1] = 1;
									b.getCoveredWhite()[rank-1][file-1] = 1;
								}
								else {
									b.getCoveredBlack()[rank+1][file+1] = 1;
									b.getCoveredBlack()[rank+1][file-1] = 1;
								}
							}
							else {
								if (file == 0) {
									if (p.getColor() == Color.WHITE) {
										b.getCoveredWhite()[rank-1][file+1] = 1;
									}
									else {
										b.getCoveredBlack()[rank+1][file+1] = 1;
									}
								}
								else if (file == 7) {
									if (p.getColor() == Color.WHITE) {
										b.getCoveredWhite()[rank-1][file-1] = 1;
									}
									else {
										b.getCoveredBlack()[rank+1][file-1] = 1;
									}
								}
							}
						}
						
					}
				}
			}
		}
	}
	
	/**
	 * Checks the protected squares and determines check status depending on color
	 * @param b Instance of a Board
	 * @param c Color of the opposite player
	 * @return Whether the opposite color is in check
	 */
	public static void inCheck(Board b, Color c) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square s = b.getSquare(i, j);
				if (s.getPiece() != null) {
					if (s.getPiece().getPieceType() == PieceType.KING && s.getPiece().getColor() != c) {
						if (c == Color.BLACK) {
							if (b.getCoveredBlack()[i][j] == 1) {
								check = true;
								return;
							}
						}
						else {
							if (b.getCoveredWhite()[i][j] == 1) {
								check = true;
								return;
							}
						}
					}
				}
			}
		}
		check = false;
	}
	
	/**
	 * Checks if a given piece is a pawn, and if it is, if it can use the en passant move
	 * @param p A chess piece, only relevant if it's a pawn
	 * @param s Square that it can en passant to (diagonal to pawn)
	 * @return Whether the opportunity for en passant exists
	 * @throws Exception 
	 */
	private static boolean canEnPassant(Piece p, Board b, Square s) throws Exception {
		if (p.getPieceType() != PieceType.PAWN) {
			return false;
		}
		else {
			Square pawnPos = b.getSquare(p.getRow(), p.getCol());
			Square adj_square;
			Piece adj_pawn;
			//white's move
			//System.out.println(p.getColor() + " rank: " + pawnPos.getRank());
			if (p.getColor() == Color.WHITE) {
				if (pawnPos.getRank() != 3) {
					return false;
				}
				adj_square = b.getSquare(s.getRank() + 1, s.getIntFile());
				if (adj_square.isEmpty()) {
					return false;
				}
				adj_pawn = adj_square.getPiece();
				if (adj_pawn.getPieceType() != PieceType.PAWN) {
					return false;
				}
				if (black_moves.isEmpty()) {
					return false;
				}
				else {
					int[] black_last_move = Chess.parseMove(black_moves.get(black_moves.size() - 1)); //retrieve last move black made
					//last move black made has to be a 2-square pawn move in an adjacent file
					if (black_last_move[0] != 1 || black_last_move[1] != s.getIntFile() ||
							black_last_move[2] != s.getRank() + 1 || black_last_move[3] != s.getIntFile()) {
						return false;
					}
				}		
			}
			//black's move
			else {
				if (pawnPos.getRank() != 4) {
					return false;
				}
				adj_square = b.getSquare(s.getRank() - 1, s.getIntFile());
				if (adj_square.isEmpty()) {
					return false;
				}
				adj_pawn = adj_square.getPiece();
				if (adj_pawn.getPieceType() != PieceType.PAWN) {
					return false;
				}
				if (white_moves.isEmpty()) {
					return false;
				}
				else {
					int[] white_last_move = Chess.parseMove(white_moves.get(white_moves.size() - 1)); //retrieve last move white made
					//last move white made has to be a 2-square pawn move in an adjacent file
					if (white_last_move[0] != 6 || white_last_move[1] != s.getIntFile() ||
							white_last_move[2] != s.getRank() - 1 || white_last_move[3] != s.getIntFile()) {
						return false;
					}
				}			
			}
		}
		return true;
	}
	
	/**
	 * Moves a piece using a string representation of a move, but reverts it afterwards. Throws an IllegalArgumentException if the move is invalid.
	 * 
	 * @param move String representation of a move
	 * @param board Chess board
	 * @param color color of the player
	 * @throws Exception 
	 * 
	 */
	private static boolean phantomMove(int row1, int col1, int row2, int col2, Board board, Color color) throws Exception{
		Color opposite;
		if (color == Color.WHITE) {
			opposite = Color.BLACK;
		}
		else {
			opposite = Color.WHITE;
		}
		checkPath(board, row1, col1, row2, col2);
		Square s1 = board.getSquare(row1, col1);
		Square s2 = board.getSquare(row2, col2);
		Piece p = s1.getPiece();
		boolean hasMovedTemp = p.hasMoved();
		Piece p2Temp = s2.getPiece();
		boolean hasMovedTemp2 = false;
		if(p2Temp != null){
			hasMovedTemp2 = p2Temp.hasMoved();
		}
		//castling for white
		if (p.getPieceType() == PieceType.KING) {
			Piece rook_castle;
			if (p.getColor() == Color.WHITE) {
				if (s1.toString().equals("[7,4]") && (s2.toString().equals("[7,6]") || s2.toString().equals("[7,2]"))) {
					if (s2.toString().equals("[7,6]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() - 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() + 1).getPiece();
						if (canCastle(p, rook_castle, board)) {
							return true;
						}
						else {
							throw new IllegalArgumentException();
						}
					}
					else if (s2.toString().equals("[7,2]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() + 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() - 2).getPiece();
						if (canCastle(p, rook_castle, board)) {
							return true;
						}	
						else {
							throw new IllegalArgumentException();
						}
					}
				}
			}
			//castling for black
			else {
				if (s1.toString().equals("[0,4]") && (s2.toString().equals("[0,6]") || s2.toString().equals("[0,2]"))) {
					if (s2.toString().equals("[0,6]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() - 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() + 1).getPiece();
						if (canCastle(p, rook_castle, board)) {
							return true;
						}
						else {
							throw new IllegalArgumentException();
						}
					}
					if (s2.toString().equals("[0,2]")) {
						if (!board.getSquare(s2.getRank(), s2.getIntFile() + 1).isEmpty()) {
							throw new BlockedException();
						}
						rook_castle = board.getSquare(s2.getRank(), s2.getIntFile() - 2).getPiece();
						if (canCastle(p, rook_castle, board)) {
							return true;
						}		
						else {
							throw new IllegalArgumentException();
						}
					}	
				}
			}
		}
		//pawn can only move diagonally if it's capturing
		if (p.getPieceType() == PieceType.PAWN) {
			//check for en passant
			if (canEnPassant(p, board, s2)) {
				Square adj_square;
				if (p.getColor() == Color.WHITE) {
					adj_square = board.getSquare(s2.getRank() + 1, s2.getIntFile());
					board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
				}
				else {
					adj_square = board.getSquare(s2.getRank() - 1, s2.getIntFile());
					board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
				}
				adj_square.removePiece();
			}
			else {
				if (p.getColor() == Color.WHITE) {
					if (s2 != board.getSquare(s1.getRank() - 1, s1.getIntFile())) {
						if (p.hasMoved()){ 
							if ((!canEnPassant(p, board, s2) && s2.isEmpty()) || s2.isEmpty() || s2.getPiece().getColor() == Color.WHITE) {
								throw new IllegalArgumentException();
							}
						}
						else {
							if (s2 != board.getSquare(s1.getRank() - 2, s1.getIntFile()) && (s2.isEmpty() || s2.getPiece().getColor() == Color.WHITE)) {
								throw new IllegalArgumentException();
							}
						}
					}
					if (s2 == board.getSquare(s1.getRank() - 1, s1.getIntFile())  || s2 == board.getSquare(s1.getRank() - 2, s2.getIntFile())) {
						Piece blocked = s2.getPiece();
						if (blocked != null) {
							throw new BlockedException();
						}
					}
				}
				else {
					if (s2 != board.getSquare(s1.getRank() + 1, s1.getIntFile()) && p.hasMoved()) {
						if (p.hasMoved()) {
							if ((!canEnPassant(p, board, s2) && s2.isEmpty()) || s2.isEmpty() || s2.getPiece().getColor() == Color.BLACK) {
								throw new IllegalArgumentException();
							}
						}
						else {
							if (s2 != board.getSquare(s1.getRank() + 2, s1.getIntFile()) && (s2.isEmpty() || s2.getPiece().getColor() == Color.BLACK)) {
								throw new IllegalArgumentException();
							}
						}
					}
					if (s2 == board.getSquare(s1.getRank() + 1, s1.getIntFile())  || s2 == board.getSquare(s1.getRank() + 2, s2.getIntFile())) {
						Piece blocked = s2.getPiece();
						if (blocked != null) {
							throw new BlockedException();
						}
					}
				}
			}
		}
		p.move(s2.getRank(), s2.getIntFile());
		updateProtectedSquares(board);
		p.setMoved();
		s2.occupy(p);
		s1.removePiece();
		//promotes it to Queen by default; additional promotions specified in promotePawn method
		if (s2.getPiece().getPieceType() == PieceType.PAWN) {
			if (s2.getPiece().getColor() == Color.WHITE) {
				if (s2.getRank() == 0) {
					s2.removePiece();
					s2.occupy(new Queen(s2.getRank(), s2.getIntFile(), color));
					board.getWhiteCounts().put(PieceType.QUEEN, board.getWhiteCounts().get(PieceType.QUEEN) + 1);
					board.getWhiteCounts().put(PieceType.PAWN, board.getWhiteCounts().get(PieceType.PAWN) - 1);
				}
			}
			else {
				if (s2.getRank() == 7) {
					s2.removePiece();
					s2.occupy(new Queen(s2.getRank(), s2.getIntFile(), color));
					board.getBlackCounts().put(PieceType.QUEEN, board.getBlackCounts().get(PieceType.QUEEN) + 1);
					board.getBlackCounts().put(PieceType.PAWN, board.getBlackCounts().get(PieceType.PAWN) - 1);
				}
			}
		}
		updateProtectedSquares(board);
		inCheck(board, opposite);
		p.absoluteMove(row1, col1, hasMovedTemp);
		s1.occupy(p);
		s2.removePiece();
		if(p2Temp != null){
			p2Temp.absoluteMove(row2, col2, hasMovedTemp2);
			s2.occupy(p2Temp);		
		}
		if(!check){
			return true;
		}
		return false;
	}
	
	/**
	 * Determines whether there are legal moves to be made
	 * @param b Board instance
	 * @param c Color color
	 * @return True if legal moves exist, false otherwise
	 */
	public static boolean hasLegalMoves(Board b, Color c) {
		Color opposite;
		if (c == Color.WHITE) {
			opposite = Color.BLACK;
		}
		else {
			opposite = Color.WHITE;
		}
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				Square s1 = b.getSquare(i, j);
				Piece p = s1.getPiece();
				//only execute if the first square has a piece
				if (p != null && p.getColor() == opposite) {
					for (int x = 0; x < 8; x++) {
						for (int y = 0; y < 8; y++) {
							if (x != i || y != j) {
								try {
									if(phantomMove(i, j, x, y, b, opposite)){
										return true;
									}
								} catch (Exception e) {
									continue;
								}
							}
						}
					}
				}
			}
		}
		end = true;
		return false;
	}
	
	/**
	 * Checks if castling is allowed
	 * @param p1 First piece (king)
	 * @param p2 Second piece (rook)
	 * @param b Current chessboard
	 * @return Whether the player can castle
	 * @throws BlockedException 
	 */
	private static boolean canCastle(Piece p1, Piece p2, Board b) throws BlockedException {
		if (p1.getPieceType() != PieceType.KING || (p2 == null || p2.getPieceType() != PieceType.ROOK)) {
			return false;
		}
		else if (p1.getColor() != p2.getColor()) {
			return false;
		}
		else if (p1.hasMoved() || p2.hasMoved()) {
			return false;
		}
		else if (check) {
			return false;
		}
		else {
			Square right_side = b.getSquare(p1.getRow(), p1.getCol() + 2);
			Square left_side = b.getSquare(p1.getRow(), p1.getCol() - 2);
			if (!(right_side.isEmpty() && b.getSquare(right_side.getRank(), right_side.getIntFile() - 1).isEmpty()) &&
					!(left_side.isEmpty() && b.getSquare(left_side.getRank(), left_side.getIntFile() + 1).isEmpty())) {
				return false;
			}
			//can't castle through or into check
			else {
				boolean right_side_safe = true;
				boolean left_side_safe = true;
				if (p1.getColor() == Color.WHITE) {
					if (b.getCoveredBlack()[right_side.getRank()][right_side.getIntFile()] == 1 || b.getCoveredBlack()[right_side.getRank()][right_side.getIntFile() - 1] == 1) {
						right_side_safe = false;
					}
					if (b.getCoveredBlack()[left_side.getRank()][left_side.getIntFile()] == 1 || b.getCoveredBlack()[left_side.getRank()][left_side.getIntFile() + 1] == 1) {
						left_side_safe = false;
					}
				}
				else {
					if (b.getCoveredWhite()[right_side.getRank()][right_side.getIntFile()] == 1 || b.getCoveredWhite()[right_side.getRank()][right_side.getIntFile() - 1] == 1) {
						right_side_safe = false;
					}
					if (b.getCoveredWhite()[left_side.getRank()][left_side.getIntFile()] == 1 || b.getCoveredWhite()[left_side.getRank()][left_side.getIntFile() + 1] == 1) {
						left_side_safe = false;
					}
				}
				if (!right_side_safe && !left_side_safe) {
					return false;
				}
			}
			return true;
		}
	}
}
