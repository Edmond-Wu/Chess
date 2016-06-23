package pieces;


/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Pawn extends Piece {

	/**
	 * Constructor for Pawn.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param color color of the piece
	 */
	public Pawn(int row, int col, Color color){
		super(row, col, PieceType.PAWN, color);
	}
	
	
	/**
	 * Moves a pawn. Assumes it can make the diagonal move. Throws an IllegalArgumentException if the move is invalid.
	 * 
	 * @param rank rank to move the piece to
	 * @param file file to move the piece to
	 * 
	 */
	public void move(int rank, int file) throws IllegalArgumentException {
		super.movePiece(rank, file, (r, c) -> {
			if(getColor() == Color.BLACK){
				if(r < getRow()){
					return false;
				}
				if(r == getRow() + 1 && Math.abs(c - getCol()) <= 1){
					return true;
				}
				if(!hasMoved() && r == getRow() + 2 && c == getCol()){
					return true;
				}
				return false;
			} else {
				if(r > getRow()){
					return false;
				}
				if(r == getRow() - 1 && Math.abs(c - getCol()) <= 1){
					return true;
				}
				if(!hasMoved() && r == getRow() - 2 && c == getCol()){
					return true;
				}
				return false;
			}
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
		return color_letter + "p";
	}
}
