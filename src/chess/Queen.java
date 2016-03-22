package chess;


/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Queen extends Piece {	

	/**
	 * Constructor for Queen.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param color color of the piece
	 */
	public Queen(int row, int col, Color color){
		super(row, col, PieceType.QUEEN, color);
	}

	/**
	 * Checks if the move specified by row and col is illegal
	 */
	public void checkMove(int row, int col) throws IllegalArgumentException {
		super.checkMove(row, col);
		if(row != getRow() && col != getCol()){
			int dr = row - getRow();
			if (!(col == getCol() + dr || col == getCol() - dr)) {
				throw new IllegalArgumentException();
			}
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
			if(r != getRow() && c != getCol()){
				int dr = rank - getRow();
				return (file == getCol() + dr || file == getCol() - dr);
			}
			return true;
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
		return color_letter + "Q";
	}
}
