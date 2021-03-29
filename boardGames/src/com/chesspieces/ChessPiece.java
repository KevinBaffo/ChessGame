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
	protected boolean hasMoved = false;
	protected boolean hasCastled = false;

	
	public Square getLastSquare() {
		return lastSquare;
	}

	public ChessPiece(PieceColor pieceColor) {
		this.pieceColor = pieceColor;
	}

	public void setHasCastled(boolean hasCastled) {
		this.hasCastled = hasCastled;
	}

	public Square getCurrentSquare() {
		return currentSquare;
	}

	public void setCurrentSquare(Square currentSquare) {
		this.lastSquare = this.currentSquare;
		this.currentSquare = currentSquare;
		currentSquare.setTaken(true);
		currentSquare.setCurrentPiece(this);
	}

	public String getName() {
		return name;
	}

	public PieceColor getPieceColor() {
		return pieceColor;
	}
	
	public void makeMove(Board board, Square square) {
		if (this.getValidMoves(board).contains(square.getLocation()) || 
			this.hasCastled == true) {
//			board.getChessPieces().remove(this.getCurrentSquare().getLocation());
			this.getCurrentSquare().reset();
			this.setCurrentSquare(square);
//			board.getChessPieces().put(square.getLocation(), this);
			this.hasMoved = true;
			this.hasCastled = false;
		}else {
			System.out.println("This move is not allowed");
		}
	}

	@Override
	public List<Location> getValidMoves(Board board) {
		return null;
	}

	@Override
	public String toString() {
		return "ChessPiece [name=" + name + ", pieceColor=" + pieceColor + ", currentSquare=" + currentSquare + "]";
	}
}
