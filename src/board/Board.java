package board;

import java.util.*;

import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Queen;
import pieces.Rook;
import pieces.Piece.Color;
import pieces.Piece.PieceType;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Board {
	
	public static final char[] files = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
	private Square[][] board;
	private HashMap<PieceType, Integer> white_piece_counts;
	private HashMap<PieceType, Integer> black_piece_counts;
	private int[][] protected_white;
	private int[][] protected_black;
	
	/**
	 * Constructor for a chessboard.
	 */
	public Board() {
		white_piece_counts = new HashMap<PieceType, Integer>();
		black_piece_counts = new HashMap<PieceType, Integer>();
		protected_white = new int[8][8];
		protected_black = new int[8][8];
		board = new Square[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				int color;
				if (i % 2 == 0) {
					if (j % 2 == 0) {
						color = 1;
					}
					else {
						color = 0;
					}
				}
				else {
					if (j % 2 == 0) {
						color = 0;
					}
					else {
						color = 1;
					}
				}
				board[i][j] = new Square(files[j], i, j, color);
			}
		}
	}
	
	/**
	 * Returns a square given a rank and file number
	 * @param rank rank number
	 * @param file file number
	 * @return a Square with corresponding rank and file
	 */
	public Square getSquare(int rank, int file) {
		return board[rank][file];
	}
	
	/**
	 * Gets the list of the amount of each white piece is left
	 * @return HashMap containing pieces and their counts on the board
	 */
	public HashMap<PieceType, Integer> getWhiteCounts() {
		return white_piece_counts;
	}
	
	/**
	 * Gets the list of the amount of each black piece is left
	 * @return HashMap containing pieces and their counts on the board
	 */
	public HashMap<PieceType, Integer> getBlackCounts() {
		return black_piece_counts;
	}
	
	/**
	 * Get all covered/protected squares by white
	 * @return 2D boolean array containing squares that are covered by white pieces
	 */
	public int[][] getCoveredWhite() {
		return protected_white;
	}
	
	/**
	 * Get all covered/protected squares by black
	 * @return 2D boolean array containing squares that are covered by black pieces
	 */
	public int[][] getCoveredBlack() {
		return protected_black;
	}
	
	/**
	 * Prints out each piece and their counts on the board
	 * @param map A Piece-Count hashmap (either white or black)
	 */
	public void printHashMap(HashMap<PieceType, Integer> map) {
		System.out.println("Pawn counts: " + map.get(PieceType.PAWN));
		System.out.println("Queen counts: " + map.get(PieceType.QUEEN));
		System.out.println("Rook counts: " + map.get(PieceType.ROOK));
		System.out.println("Bishop counts: " + map.get(PieceType.BISHOP));
		System.out.println("Knight counts: " + map.get(PieceType.KNIGHT));
	}
	
	/**
	 * Initializes the board with its starting setup position
	 */
	public void initialize() {
		for (int i = 0; i < 8; i++) {
			board[1][i].occupy(new Pawn(1, i, Color.BLACK));
			board[6][i].occupy(new Pawn(6, i, Color.WHITE));
			if (i == 0 || i == 7) {
				board[0][i].occupy(new Rook(0, i, Color.BLACK));
				board[7][i].occupy(new Rook(7, i, Color.WHITE));
			}
			else if (i == 1 || i == 6) {
				board[0][i].occupy(new Knight(0, i, Color.BLACK));
				board[7][i].occupy(new Knight(7, i, Color.WHITE));
			}
			else if (i == 2 || i == 5) {
				board[0][i].occupy(new Bishop(0, i, Color.BLACK));
				board[7][i].occupy(new Bishop(7, i, Color.WHITE));
			}
			else if (i == 3) {
				board[0][i].occupy(new Queen(0, i, Color.BLACK));
				board[7][i].occupy(new Queen(7, i, Color.WHITE));
			}
			else {
				board[0][i].occupy(new King(0, i, Color.BLACK));
				board[7][i].occupy(new King(7, i, Color.WHITE));
			}
		}
	}
	
	/**
	 * Resets the protection arrays
	 */
	public void resetCoveredSquares() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				protected_white[i][j] = 0;
				protected_black[i][j] = 0;
			}
		}
	}
	
	/**
	 * Determines a draw condition depending on pieces on the board
	 * @return whether the game is a draw
	 */
	public boolean draw() {
		//checkmate is possible if there are queens and/or rooks and/or pawns on the board
		if (white_piece_counts.get(PieceType.QUEEN) > 0 || black_piece_counts.get(PieceType.QUEEN) > 0 || 
				white_piece_counts.get(PieceType.ROOK) > 0 || black_piece_counts.get(PieceType.ROOK) > 0 ||
				white_piece_counts.get(PieceType.PAWN) > 0 || black_piece_counts.get(PieceType.PAWN) > 0) {
			return false;
		}
		else {
			//can checkmate with 2+ bishops
			if (white_piece_counts.get(PieceType.BISHOP) >= 2 || black_piece_counts.get(PieceType.BISHOP) >= 2) {
				return false;
			}
			return true;
		}
	}
	
	/**
	 * Initializes the piece count hashmaps to starting values
	 */
	public void initializePieceCounts() {
		white_piece_counts.put(PieceType.PAWN, 8);
		white_piece_counts.put(PieceType.QUEEN, 1);
		white_piece_counts.put(PieceType.ROOK, 2);
		white_piece_counts.put(PieceType.KNIGHT, 2);
		white_piece_counts.put(PieceType.BISHOP, 2);
		black_piece_counts.put(PieceType.PAWN, 8);
		black_piece_counts.put(PieceType.QUEEN, 1);
		black_piece_counts.put(PieceType.ROOK, 2);
		black_piece_counts.put(PieceType.KNIGHT, 2);
		black_piece_counts.put(PieceType.BISHOP, 2);
	}
	
	/**
	 * Prints out visual representation of a chessboard
	 */
	public void printBoard() {
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++) {
				System.out.print(board[x][y].display() + " ");
			}
			System.out.println(8 - x);
		}
		for (int z = 0; z < 8; z++) {
			System.out.print(" " + files[z] + " ");
		}
		System.out.println();
	}
	
	/**
	 * Prints out all the board's chess notation coordinates
	 */
	public void printCoordinates() {
		for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {
				System.out.print(board[a][b].toString() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Prints out the notated form of the board
	 */
	public void printNotation() {
		for (int a = 0; a < 8; a++) {
			for (int b = 0; b < 8; b++) {
				System.out.print(board[a][b].getNotation() + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	/**
	 * Prints out the squares that black and white are protecting
	 */
	public void printProtectedSquares() {
		System.out.println("Squares covered by black:");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(protected_black[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("Squares covered by white:");
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				System.out.print(protected_white[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}
