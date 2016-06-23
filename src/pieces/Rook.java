package pieces;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Rook extends Piece {
	
	/**
	 * Constructor for Rook.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param color color of the piece
	 */
	public Rook(int row, int col, Color color){
		super(row, col, PieceType.ROOK, color);
	}
	
	/**
	 * Checks if the move specified by row and col is illegal
	 */
	public void checkMove(int row, int col) throws IllegalArgumentException {
		super.checkMove(row, col);
		if(row != getRow() && col != getCol()){
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
			if(r != getRow() && c != getCol()){
				return false;
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
		return color_letter + "R";
	}
}
