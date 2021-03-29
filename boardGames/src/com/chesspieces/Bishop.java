package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.board.Board;
import com.board.Location;
import com.board.LocationFactory;
import com.squares.Square;

public class Bishop extends ChessPiece implements Movable {
	public Bishop(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Bishop";
	}

	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		getDiagonalMoves(moveCandidates, board, 1, 1);
		getDiagonalMoves(moveCandidates, board, 1, -1);
		getDiagonalMoves(moveCandidates, board, -1, 1);
		getDiagonalMoves(moveCandidates, board, -1, -1);
		return moveCandidates;
	}
	
	public void getDiagonalMoves(List<Location> moveCandidates, Board board, Integer fileOffset, Integer rankOffset) {
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
	}	
}

