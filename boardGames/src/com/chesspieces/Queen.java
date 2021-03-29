package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import com.board.Board;
import com.board.Location;


public class Queen extends ChessPiece implements Movable {
//	private ChessPiece bishop; 
//	private ChessPiece rook;
	
	public Queen(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Queen";
	}
	
//	public Queen(PieceColor pieceColor, ChessPiece bishop, ChessPiece rook) {
//		super(pieceColor);
//		bishop.setCurrentSquare(this.currentSquare);
//		this.name = "Queen";
//		this.bishop = bishop;
//		this.rook = rook;
//		//
////		this.bishop = new Bishop(pieceColor);
////		this.rook = new Rook(pieceColor);
//		
//		//
//	}
		
	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Bishop bishop = new Bishop(pieceColor);
		Rook rook = new Rook(pieceColor);
		bishop.setCurrentSquare(currentSquare);
		rook.setCurrentSquare(currentSquare);
		
		moveCandidates.addAll(bishop.getValidMoves(board));
		moveCandidates.addAll(rook.getValidMoves(board));
		return moveCandidates;		
	}

}
