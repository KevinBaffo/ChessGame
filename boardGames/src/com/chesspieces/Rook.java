package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.board.Board;
import com.board.Location;
import com.board.LocationFactory;
import com.squares.Square;

public class Rook extends ChessPiece implements Movable {
	public Rook(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Rook";
	}

	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		
		// Pieces with same Rank
		moveCandidates.addAll(getCandidates(board, 0, 1));
		moveCandidates.addAll(getCandidates(board, 0, -1));
		
		// Pieces with Same File
		moveCandidates.addAll(getCandidates(board, 1, 0));
		moveCandidates.addAll(getCandidates(board, -1, 0));
		
		return moveCandidates;
	}
	
	public List<Location> getCandidates(Board board, Integer fileOffset, Integer rankOffset) {
		List<Location> moveCandidates = new ArrayList<>();
		Map<Location, Square> squareMap = board.getLocationsquareMap();
		Location next = LocationFactory.build(board, currentSquare, fileOffset, rankOffset);
		while (squareMap.containsKey(next)) {
			if (squareMap.get(next).isTaken()) {
				if (squareMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) {
					break;
				}else {
					moveCandidates.add(next);
					break;
				}
			}else {
				moveCandidates.add(next);
				next = LocationFactory.build(board, squareMap.get(next), fileOffset, rankOffset);
			}
		}
		return moveCandidates;
	}	
}

