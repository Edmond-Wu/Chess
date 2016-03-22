package chess;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Square {
	private char file;
	private int rank;
	private int int_file;
	private int color; //0 for black, 1 for white
	private boolean is_empty;
	private Piece piece;
	
	/**
	 * Constructor for a Square
	 * @param r Rank of a square
	 * @param f File of a square
	 * @param c Color of a square
	 */
	public Square(char f, int r, int i_f, int c) {
		this.rank = r;
		this.file = f;
		this.int_file = i_f;
		this.color = c;
		this.is_empty = true;
		this.piece = null;
	}
	
	/**
	 * Returns the square's rank
	 * @return rank of a square
	 */
	public int getRank() {
		return rank;
	}
	
	/**
	 * Returns the integer representation of the square's file
	 * @return file of a square, integer form
	 */
	public int getIntFile() {
		return int_file;
	}
	
	/**
	 * Returns the square's file
	 * @return file of a square
	 */
	public char getFile() {
		return file;
	}
	
	/**
	 * Returns the square's color
	 * @return color of a square
	 */
	public int getColor() {
		return color;
	}
	
	/**
	 * Returns the square's occupation status
	 * @return the true/false value of is_empty
	 */
	public boolean isEmpty() {
		return is_empty;
	}
	
	/**
	 * Returns the piece that is occupying the square
	 * @return the square's occupying piece
	 */
	public Piece getPiece() {
		return piece;
	}
	
	/**
	 * Places a piece on the square
	 * @param p any piece that will move onto the square
	 */
	public void occupy(Piece p) {
		piece = p;
		is_empty = false;
	}
	
	/**
	 * Removes a piece from the square
	 */
	public void removePiece() {
		piece = null;
		is_empty = true;
	}
	
	/**
	 * Returns a terminal representation of a square
	 * @return "##" if the square is black, 2 spaces if white
	 */
	public String display() {
		if (is_empty) {
			if (color == 0) {
				return "##";
			}
			return "  ";
		}
		return getPiece().toString();
	}
	
	/**
	 * Returns the coordinate form of the square
	 */
	public String toString() {
		return "[" + rank + "," + int_file + "]";
	}
	
	/**
	 * Returns the chess notation form of the square
	 */
	public String getNotation() {
		return file + "" + (8 - rank);
	}
}
