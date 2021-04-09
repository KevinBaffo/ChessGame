package com.squares;

import com.board.Location;
import com.chesspieces.ChessPiece;

public class Square {
	private final SquareColor squarecolor;
	private final Location location;
	private boolean isTaken;
	private ChessPiece currentPiece;
	
	public Square(SquareColor squarecolor, Location location) {
		this.squarecolor = squarecolor;
		this.location = location;
		reset();
	}
	
	public void reset() {
		this.isTaken = false;
		this.currentPiece = null;
	}

	public ChessPiece getCurrentPiece() {
		return currentPiece;
	}

	public void setCurrentPiece(ChessPiece currentPiece) {
		this.currentPiece = currentPiece;
	}

	public boolean isTaken() {
		return isTaken;
	}

	public void setTaken(boolean isTaken) {
		this.isTaken = isTaken;
	}

	public SquareColor getSquarecolor() {
		return squarecolor;
	}

	public Location getLocation() {
		return location;
	}
	
	@Override
	public String toString() {
		return "Square [squarecolor=" + squarecolor + ", location=" + location + ", isTaken=" + isTaken + "]";
	}	
	
}
