package com.chessgame;

import java.awt.BorderLayout;
import java.awt.Paint;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import com.board.Board;
import com.board.File;
import com.board.Location;
import com.chesspieces.ChessPiece;
import com.chesspieces.ChessPieceFactory;
import com.chesspieces.DrawPieces;
import com.chesspieces.PieceColor;
import com.squares.Square;
import com.squares.SquareColor;

public class ChessGame {
	private static final Board chessBoard = new Board();
	private Map<Location, ChessPiece> chessPieces;
	private Map<PieceColor, List<Location>> pieceMoves;
	private ChessPiece king;
	
	public ChessGame(Map<Location, ChessPiece> chessPieces) {
		this.chessPieces = chessPieces;
	}
	
	public Board getChessboard() {
		return chessBoard;
	}

	public Map<Location, ChessPiece> getChessPieces() {
		return chessPieces;
	}

	public void draw() {
		JFrame frame;
		DrawChessGame drawGame;
		frame = new JFrame("Chess Game");
		frame.setSize(530, 550);
		frame.setLayout(new BorderLayout());
		drawGame = new DrawChessGame(this);
		frame.add(drawGame, BorderLayout.CENTER);
		frame.setVisible(true);
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	public void assignPieces() {
		for(Square current : chessBoard.getLocationsquareMap().values()) {
			if (chessPieces.containsKey(current.getLocation())) {
				ChessPiece p = chessPieces.get(current.getLocation());
				current.setCurrentPiece(p);
				current.setTaken(true);
				p.setCurrentSquare(current);
			}
		}
	}
	
	public List<Location>getPlayerMoves(PieceColor color) {
		List<Location>  allMoves = new ArrayList<>();
		for (ChessPiece piece : chessPieces.values()) {
			if(piece == null) continue;
			if (piece.getPieceColor().equals(color)) {
				allMoves.addAll(piece.getValidMoves(chessBoard));
			}
		}
		return allMoves;
	}
		
	public boolean isInCheck(PieceColor color) {
		PieceColor oppositeColor = color == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
		// find king
		for (ChessPiece piece : chessPieces.values()) {
			if(piece == null) continue;
			if (piece.getPieceColor() == color && piece.getName() == "King") {
				king = piece;
			}
		}
		if (getPlayerMoves(oppositeColor).contains(king.getCurrentSquare().getLocation())) return true;
		
		return false;
	}
	
	public boolean isUnderAttack(Location toCheck, PieceColor color) {
		if (getPlayerMoves(color).contains(toCheck)) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Map<Location, ChessPiece> pieces = ChessPieceFactory.getPieces();
		ChessGame game = new ChessGame(pieces);
		game.assignPieces();
		game.draw();
	}
}
