package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.board.Board;
import com.board.Location;

public class King extends ChessPiece  implements Movable {
	PieceColor oppositeColor = pieceColor == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
	public King(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "King";
	}

	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Queen queen = new Queen(pieceColor);
		queen.setCurrentSquare(currentSquare);
		moveCandidates = queen.getValidMoves(board).stream()
				.filter(candidate -> (
				Math.abs(candidate.getFile().ordinal()- this.getCurrentSquare().getLocation().getFile().ordinal()) == 1 || 
				Math.abs(candidate.getRank() - this.getCurrentSquare().getLocation().getRank()) == 1))
				.collect(Collectors.toList());
		return moveCandidates;
	}	
}
