package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import com.board.Board;
import com.board.Location;
import com.board.LocationFactory;
import com.squares.Square;

public class Pawn extends ChessPiece implements Movable {
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Pawn";
	}
	
	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Location doubleMove = LocationFactory.build(board, this.currentSquare, 0, 2);
		Integer currentFile = currentSquare.getLocation().getFile().ordinal();
		Integer currentRank = currentSquare.getLocation().getRank();
		if (this.hasMoved == false && board.getLocationsquareMap().get(doubleMove).getCurrentPiece() == null) {
			moveCandidates.add(doubleMove);
		}
		if (currentRank != 0) {
			Integer nextRank = this.pieceColor == PieceColor.DARK ? currentRank + 1 : currentRank -1;
			Location frontSquare = LocationFactory.build(board, this.currentSquare, 0, 1);
			if (board.getLocationsquareMap().get(frontSquare).getCurrentPiece() == null) {
				moveCandidates.add(frontSquare);
			}
			if (currentFile != 7) {
				Integer rightFile = currentFile + 1;
				Square northEastSquare = board.getBoardSquares()[rightFile][7 - nextRank];
				if (northEastSquare.isTaken() == true && northEastSquare.getCurrentPiece().getPieceColor() != this.pieceColor ) {
					moveCandidates.add(northEastSquare.getLocation());
				}
			} 
			if (currentFile != 0) {
				Integer leftFile = currentFile - 1;
				Square northWestSquare = board.getBoardSquares()[leftFile][7 - nextRank];
				if (northWestSquare.isTaken() == true && northWestSquare.getCurrentPiece().getPieceColor() != this.pieceColor ) {
					moveCandidates.add(northWestSquare.getLocation());
				}
			}
		}		
		return moveCandidates;
	}
}
