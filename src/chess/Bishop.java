package chess;


/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Bishop extends Piece {
	
	/**
	 * Constructor for Bishop.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param color color of the piece
	 */
	public Bishop(int row, int col, Color color){
		super(row, col, PieceType.BISHOP, color);
	}
	
	/**
	 * Checks if the move specified by row and col is illegal
	 */
	public void checkMove(int row, int col) throws IllegalArgumentException {
		super.checkMove(row, col);
		int dr = row - getRow();
		if (!(col == getCol() + dr || col == getCol() - dr)) {
			throw new IllegalArgumentException();
		}
	}
	
	/**
	 * Moves a piece. Throws an IllegalArgumentException if the move is invalid.
	 * 
	 * @param rank rank to move the piece to
	 * @param file file to move the piece to
	 * 
	 */
	public void move(int rank, int file) throws IllegalArgumentException {
		super.movePiece(rank, file, (r, c) -> {
			int dr = rank - getRow();
			return (file == getCol() + dr || file == getCol() - dr);
		});
	}
	
	/**
	 * Returns a text representation of the piece for display purposes
	 */
	public String toString() {
		char color_letter;
		if (getColor() == Color.WHITE) {
			color_letter = 'w';
		}
		else {
			color_letter = 'b';
		}
		return color_letter + "B";
	}
}
