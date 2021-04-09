package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import com.board.Board;
import com.board.Location;


public class Queen extends ChessPiece implements Movable {
	
	public Queen(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Queen";
	}
		
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
