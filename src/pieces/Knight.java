package pieces;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Knight extends Piece {

	/**
	 * Constructor for Knight.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param color color of the piece
	 */
	public Knight(int row, int col, Color color){
		super(row, col, PieceType.KNIGHT, color);
	}
	
	/**
	 * Checks if the move specified by row and col is illegal
	 */
	public void checkMove(int row, int col) throws IllegalArgumentException {
		super.checkMove(row, col);
		if(!((Math.abs(row - getRow()) == 2 && Math.abs(col - getCol()) == 1) || (Math.abs(row - getRow()) == 1 && Math.abs(col - getCol()) == 2))){
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
			if((Math.abs(r - getRow()) == 2 && Math.abs(c - getCol()) == 1) || (Math.abs(r - getRow()) == 1 && Math.abs(c - getCol()) == 2)){
				return true;
			}
			return false;
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
		return color_letter + "N";
	}
}
