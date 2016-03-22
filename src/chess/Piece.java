package chess;

import java.util.function.BiFunction;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public abstract class Piece {
		
	private int row;
	private int col;
	private Color color;
	private PieceType piece;
	private boolean moved;
	enum Color { WHITE, BLACK };
	enum PieceType { KING , QUEEN , ROOK, BISHOP, KNIGHT, PAWN };

	/**
	 * Constructor for Piece.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param piece type of piece
	 * @param color color of the piece
	 */
	public Piece(int row, int col, PieceType piece, Color color){
		this.piece = piece;
		this.row = row;
		this.col = col;
		this.color = color;
		this.moved = false;
	}
	

	/**
	 * Moves a piece. Throws an IllegalArgumentException if the move is invalid.
	 * 
	 * @param rank rank to move the piece to
	 * @param file file to move the piece to
	 * 
	 */
	public void movePiece(int rank, int file, BiFunction<Integer, Integer, Boolean> canMove) throws IllegalArgumentException {
		if(row < 0 || row > 7 || col < 0 || col > 7 || !canMove.apply(rank, file)){
			throw new IllegalArgumentException();
		}
		if(this.row == rank && this.col == file){
			throw new IllegalArgumentException();
		}
		this.row = rank;
		this.col = file;
		this.moved = true;
	}
	
	/**
	 * Checks if a move is legal, throw an exception otherwise
	 * @param row destination row
	 * @param col destination column
	 * @throws IllegalArgumentException
	 */
	public void checkMove(int row, int col) throws IllegalArgumentException {
		if(row < 0 || row > 7 || col < 0 || col > 7){
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Moves a piece and sets hasMoved
	 * @param rank destination row
	 * @param file destination column
	 * @boolean hasMoved has moved
	 * @throws IllegalArgumentException
	 */
	public void absoluteMove(int rank, int file, boolean hasMoved) throws IllegalArgumentException {
		if(row < 0 || row > 7 || col < 0 || col > 7){
			throw new IllegalArgumentException();
		}
		this.row = rank;
		this.col = file;
		this.moved = hasMoved;
	}
	
	
	/**
	 * Moves a piece by calling movePiece and passing in corresponding BiFunction.Throws an IllegalArgumentException if the move is invalid.
	 * 
	 * @param row row to move the piece to
	 * @param col column to move the piece to
	 * 
	 */
	public abstract void move(int row, int col) throws IllegalArgumentException;
	
	/**
	 * Returns the piece's file/row
	 * @return row of a piece
	 */
	public int getRow(){
		return this.row;
	}
	
	/**
	 * Returns the piece's rank/column
	 * @return column of a piece
	 */
	public int getCol(){
		return this.col;
	}
	
	/**
	 * Returns the piece's color
	 * @return color of a piece
	 */
	public Color getColor(){
		return this.color;
	}
	
	/**
	 * Returns the piece's type
	 * @return type of a piece
	 */
	public PieceType getPieceType() {
		return piece;
	}
	
	/**
	 * Returns whether the piece has moved
	 * @return true if the piece has been moved, false otherwise
	 */
	public boolean hasMoved(){
		return this.moved;
	}
	
	/**
	 * Sets the status of moved to true (to be called after moving a piece)
	 */
	public void setMoved() {
		moved = true;
	}
}
