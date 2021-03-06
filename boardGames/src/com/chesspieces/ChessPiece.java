package com.chesspieces;

import java.util.List;

import com.board.Board;
import com.board.Location;
import com.squares.Square;

public abstract class ChessPiece implements Movable {
	protected String name;
	protected PieceColor pieceColor;
	protected Square currentSquare;
	protected Square lastSquare = currentSquare;
	protected boolean moveStatus = false;
	protected boolean castleStatus = false;

	
	public boolean hasMoved() {
		return moveStatus;
	}

	public void setMoveStatus(boolean moveStatus) {
		this.moveStatus = moveStatus;
	}

	public boolean hasCastled() {
		return castleStatus;
	}

	public void setCastleStatus(boolean castleStatus) {
		this.castleStatus = castleStatus;
	}

	public Square getLastSquare() {
		return lastSquare;
	}

	public ChessPiece(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	public Square getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(Square current) {
		this.lastSquare = this.currentSquare;
		this.currentSquare = current;
//		current.set(this);
	}

	public String getName() {
		return name;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}
	
//	public void makeMove(Board board, Square square) {
//		if (this.getValidMoves(board).contains(square.getLocation()) || 
//			this.hasCastled == true) {
//			this.getCurrentSquare().reset();
//			this.setCurrentSquare(square);
//			this.hasMoved = true;
//			this.hasCastled = false;
//		}else {
//			System.out.println("This move is not allowed");
//		}
//	}

	@Override
	public List<Location> getValidMoves(Board board) {
		return null;
	}

	@Override
	public String toString() {
		return "ChessPiece [name=" + name + ", pieceColor=" + pieceColor + ", currentSquare=" + currentSquare + "]";
	}
}
