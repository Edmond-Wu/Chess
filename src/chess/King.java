package chess;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class King extends Piece {
	
	/**
	 * Constructor for King.
	 * 
	 * @param row row of the piece
	 * @param col column of the piece
	 * @param color color of the piece
	 */
	public King(int row, int col, Color color){
		super(row, col, PieceType.KING, color);
	}
	
	/**
	 * Checks if the move specified by row and col is illegal
	 */
	public void checkMove(int row, int col) throws IllegalArgumentException {
		super.checkMove(row, col);
		if(row < getRow() - 1 || row > getRow() + 1 || col < getCol() - 1 || col > getCol() + 1){
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
			if (r == getRow() && (c == getCol() + 2 || c == getCol() - 2)) {
				if (hasMoved()) {
					return false;
				}
				return true;
			}
			else if(r < getRow() - 1 || r > getRow() + 1 || c < getCol() - 1 || c > getCol() + 1){
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
		return color_letter + "K";
	}
}
