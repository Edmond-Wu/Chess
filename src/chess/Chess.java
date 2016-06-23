package chess;

import java.util.*;
import java.util.regex.*;

import board.Board;
import board.PieceBoard;
import pieces.Piece.Color;

/** 
 * @author Vincent Xie and Edmond Wu 
 */
public class Chess {
	
	static boolean draw_offer = false;
	
	@SuppressWarnings("serial")
	public static class BlockedException extends Exception {
		public BlockedException() {}
	}
	 
	@SuppressWarnings("serial")
	public static class WrongColorException extends Exception {
		public WrongColorException() {}
	}
	
	@SuppressWarnings("serial")
	public static class CheckException extends Exception {
		public CheckException() {}
	}
	
	/**
	 * Parses a string representation of a move into it's respective ranks and files.
	 * 
	 * @param move String representation of a move
	 * @param board Chess board
	 * @return array of ranks and files in the order [rank1, file1, rank2, file2]
	 * @throws Exception 
	 */
	public static int[] parseMove(String move) throws Exception{
		int[] result = new int[4];
		if (draw_offer && move.equals("draw")) {
			PieceBoard.setEnd();
			System.out.println("Draw.");
			throw new Exception();
		}
		if (move.length() == 11) {
			String draw = move.substring(6);
			if (draw.equals("draw?")) {
				draw_offer = true;
			}
		}
		else if(move.length() != 5){
			throw new IllegalArgumentException();
		}
		for(String s: move.split(" ")){
			if(s.length() != 2 && !s.equals("draw?")){
				throw new IllegalArgumentException();
			}
		}
		String regex = "[a-h][1-8]";
		Pattern r = Pattern.compile(regex);
		Matcher m = r.matcher(move);
		for(int i = 0; i <= 2; i+=2) {
			if(m.find()) {
				String output = m.group(0);
				result[i] = 8 - Integer.parseInt(output.charAt(1) + "");
				result[i + 1] = output.charAt(0) - 'a';
			} else {
				throw new IllegalArgumentException();
			}
		}
		return result;
	}
	
	/**
	 * Application main method.
	 * @param args
	 */
	public static void main(String[] args) {
		Board board = new Board();
		board.initialize();
		board.initializePieceCounts();
		board.printBoard();
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);
		Color color;
		for (int count = 0; !PieceBoard.isEnd() && !board.draw() && PieceBoard.getLastPawnMoveOrCapture() < 50; count++) {
			if (count % 2 == 0) {
				System.out.print("\nWhite's move: ");
				color = Color.WHITE;
			}
			else {
				System.out.print("\nBlack's move: ");
				color = Color.BLACK;
			}
			try{
				PieceBoard.updateProtectedSquares(board);
				//board.printProtectedSquares();
				String move = scan.nextLine();
				if (move.toLowerCase().equals("resign")) {
					if (color == Color.WHITE) {
						System.out.println("Black wins");
					}
					else {
						System.out.println("White wins");
					}
					break;
				}
				move = move.trim();
				if(move.length() == 7){
					PieceBoard.promotePawn(move, board, color);
				} else {
					PieceBoard.movePiece(move, board, color);
				}
			} catch (IllegalArgumentException e) {
				System.out.print("\nIllegal move, try again");
				count--;
				continue;
			} catch (BlockedException e){
				System.out.print("\nIllegal move, try again");
				count--;
				continue;
			} catch (NullPointerException e){
				System.out.print("\nIllegal move, try again");
				count--;
				continue;
			} catch (WrongColorException e){
				System.out.print("\nIllegal move, try again");
				count--;
				continue;
			} catch (CheckException e){
				System.out.print("\nIllegal move, try again");
				count--;
				continue;
			} catch (Exception e){
				System.out.print("\nDraw");
			}
			System.out.println();
			board.printBoard();
			PieceBoard.updateProtectedSquares(board);
			PieceBoard.inCheck(board, color);
			if(PieceBoard.getCheckStatus()){
				System.out.println("Check");
			}
		}
	}
}
