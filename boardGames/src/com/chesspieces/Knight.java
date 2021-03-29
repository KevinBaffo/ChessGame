package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.board.Board;
import com.board.Location;
import com.board.LocationFactory;
import com.squares.Square;

public class Knight extends ChessPiece implements Movable {
	public Knight(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Knight";		
	}
	
	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Map<Location, Square> squareMap = board.getLocationsquareMap();
		Square currentSquare = this.getCurrentSquare();
		
		getMoves(board, moveCandidates, squareMap, currentSquare, 2, 1);
		getMoves(board, moveCandidates, squareMap, currentSquare, 2, -1);
		getMoves(board, moveCandidates, squareMap, currentSquare, -2, 1);
		getMoves(board, moveCandidates, squareMap, currentSquare, -2, -1);
		getMoves(board, moveCandidates, squareMap, currentSquare, 1, 2);
		getMoves(board, moveCandidates, squareMap, currentSquare, 1, -2);
		getMoves(board, moveCandidates, squareMap, currentSquare, -1, 2);
		getMoves(board, moveCandidates, squareMap, currentSquare, -1, -2);
		return moveCandidates;
	}
	
	public void getMoves(Board board, List<Location> moveCandidates, Map<Location, Square> squareMap, Square square, Integer fileOffset, Integer rankOffset) {
		Location next = LocationFactory.build(board, square, fileOffset, rankOffset);
		if (squareMap.containsKey(next)) {
			if (squareMap.get(next).isTaken()) {
				if (squareMap.get(next).getCurrentPiece().pieceColor.equals(this.pieceColor)) {
					return;
				}else {
					moveCandidates.add(next);
				}
			}else {
				moveCandidates.add(next);
			}
		}
	}	
}
