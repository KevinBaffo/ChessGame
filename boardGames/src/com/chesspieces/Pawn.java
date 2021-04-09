package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import com.board.Board;
import com.board.Location;
import com.board.LocationFactory;
import com.squares.Square;

public class Pawn extends ChessPiece implements Movable {
	boolean upgradeStatus = false;
	
	public Pawn(PieceColor pieceColor) {
		super(pieceColor);
		this.name = "Pawn";
	}
	
	@Override
	public List<Location> getValidMoves(Board board) {
		List<Location> moveCandidates = new ArrayList<>();
		Location east = LocationFactory.buildFromPiece(board, this, 1, 1);
		Location west = LocationFactory.buildFromPiece(board, this, -1, 1);
		
		getFirstMove(board, moveCandidates);
		getForwardMove(board, moveCandidates);
		getAttackingMove(board, moveCandidates, west);
		getAttackingMove(board, moveCandidates, east);
		
		return moveCandidates;
	}
	
	public void getFirstMove(Board board, List<Location> moveCandidates) {
		Location doubleMove = LocationFactory.buildFromPiece(board, this, 0, 2);
		Square square = board.getLocationsquareMap().get(doubleMove);
		if (this.hasMoved == false && !square.isTaken()) {
			moveCandidates.add(doubleMove);
		}
	}
	
	public void getForwardMove(Board board, List<Location> moveCandidates) {
		Integer currentRank = currentSquare.getLocation().getRank();
		if (currentRank != 0) {
			Location frontSquare = LocationFactory.buildFromPiece(board, this, 0, 1);
			if (board.getLocationsquareMap().get(frontSquare).getCurrentPiece() == null) {
				moveCandidates.add(frontSquare);
			}
		}
	}
	
	public void getAttackingMove(Board board, List<Location> moveCandidates, Location loc) {
		if (currentSquare.getLocation().getRank() != 0 &&  currentSquare.getLocation().getFile().ordinal() != 7) {
			Square square = board.getLocationsquareMap().get(loc);
			if (square.isTaken() == true && square.getCurrentPiece().getPieceColor() != this.pieceColor ) {
				moveCandidates.add(loc);
			}
		}
	}
	
	public boolean getUpgradeStatus() {
		if(this.pieceColor == PieceColor.LIGHT && this.currentSquare.getLocation().getRank() == 7 ||
			this.pieceColor == PieceColor.DARK && this.currentSquare.getLocation().getRank() == 0) {
			this.upgradeStatus = true;
		}
		return this.upgradeStatus;
	}

	public void setUpgradeStatus(boolean upgradeStatus) {
		this.upgradeStatus = upgradeStatus;
	}
	
}
