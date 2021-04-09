package com.chesspieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.board.Board;
import com.board.Location;
import com.board.LocationFactory;

public class King extends ChessPiece  implements Movable {
	PieceColor oppositeColor = pieceColor == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
	Integer des = 0;
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
				Math.abs(candidate.getRank() - this.getCurrentSquare().getLocation().getRank()) == 1) ||
				((candidate.getFile().ordinal()- this.getCurrentSquare().getLocation().getFile().ordinal()) == 2 && 
				(candidate.getRank() == this.getCurrentSquare().getLocation().getRank()) &&
				canCastleRight(board) == true))
				.collect(Collectors.toList());
		
//		moveCandidates = queen.getValidMoves(board).stream()
//				.filter(candidate -> (
//				Math.abs(candidate.getFile().ordinal()- this.getCurrentSquare().getLocation().getFile().ordinal()) == 1 || 
//				Math.abs(candidate.getRank() - this.getCurrentSquare().getLocation().getRank()) == 1))
//				.collect(Collectors.toList());
//		getKingSideCastlingMove(board, moveCandidates);
//		getQueenSideCastlingMove(board, moveCandidates);
		return moveCandidates;
	}
	
	/*
	 *	All squares between the king and the rook must be empty
	 *	king and rook may not have moved from their starting squares		
	 *	The king must not be in check
	 *	The squares that the king passes over must not be under attack, nor the square where it lands on.
	*/
	
	public boolean canCastleRight(Board board) {
		Integer count = 0;
		List<Location> sideLocations = new ArrayList<>();
		sideLocations.add(LocationFactory.build(board, this.currentSquare, 1 , 0));
		sideLocations.add(LocationFactory.build(board, this.currentSquare, 2 , 0));
		Integer i = pieceColor == PieceColor.DARK ? 0 : 7;
		ChessPiece rook = board.getBoardSquares()[7][i].getCurrentPiece();
//		System.out.println("current " + LocationFactory.build(board, lastSquare, pieceColor, 3, 0));
//		ChessPiece rook = board.getLocationsquareMap().get(LocationFactory.build(board, this.currentSquare, this.pieceColor, 3, 0)).getCurrentPiece();
		Set<Location> attackedLocation = board.getLocationsquareMap().keySet().stream().filter(candidate->(candidate.getRank() == 1)).collect(Collectors.toSet());
//		board.getPieceMoves().get(oppositeColor);
		if (attackedLocation == null || rook == null) return false;
		if (!this.hasMoved && !rook.hasMoved) {
			for (Location loc : sideLocations) {
				if (board.getLocationsquareMap().get(loc).isTaken() ||
						attackedLocation.contains(loc)) {
					break;
				} else {
					count ++;
				}
			}
		}		
		boolean check = count == 2 ? true :  false;
		
		return check;
	}
	
	/*
	public void getKingSideCastlingMove(Board board, List<Location> candidates) {
		Integer count = 0;
		List<Location> sideLocations = new ArrayList<>();
		sideLocations.add(LocationFactory.build(board, this.currentSquare, this.pieceColor, 1 , 0));
		sideLocations.add(LocationFactory.build(board, this.currentSquare, this.pieceColor, 2 , 0));
		des ++ ;
		System.out.println("DDDDD " +this.hasMoved + des);
		Integer i = pieceColor == PieceColor.DARK ? 7 : 0;
		ChessPiece rook = board.getBoardSquares()[7][i].getCurrentPiece();
//		System.out.println("current " + LocationFactory.build(board, lastSquare, pieceColor, 3, 0));
//		ChessPiece rook = board.getLocationsquareMap().get(LocationFactory.build(board, this.currentSquare, this.pieceColor, 3, 0)).getCurrentPiece();
		List<Location> attackedLocation = board.getPieceMoves().get(oppositeColor);
		if (attackedLocation == null || rook == null) return;
		if (!this.hasMoved && !rook.hasMoved) {
			for (Location loc : sideLocations) {
				if (board.getLocationsquareMap().get(loc).isTaken() ||
						attackedLocation.contains(loc)) {
					break;
				} else {
					count ++;
				}
			}
		}
		if (count == 2) {
			candidates.add(LocationFactory.build(board, currentSquare, pieceColor, 2, 0)); 
		}
	}
	
	public void getQueenSideCastlingMove(Board board, List<Location> candidates) {
		Integer count = 0;
		List<Location> sideLocations = new ArrayList<>();
		sideLocations.add(LocationFactory.build(board, currentSquare, pieceColor, -1 , 0));
		sideLocations.add(LocationFactory.build(board, currentSquare, pieceColor, -2 , 0));
		sideLocations.add(LocationFactory.build(board, currentSquare, pieceColor, -3 , 0));
//		ChessPiece rook = board.getBoardSquares()[0][0].getCurrentPiece();
		ChessPiece rook = board.getLocationsquareMap().get(LocationFactory.build(board, currentSquare, pieceColor, -4, 0)).getCurrentPiece();
		List<Location> attackedLocation = board.getPieceMoves().get(oppositeColor);
		if (attackedLocation == null || rook == null) return;
		if (!this.hasMoved && !rook.hasMoved) {
			for (Location loc : sideLocations) {
				if (board.getLocationsquareMap().get(loc).isTaken() ||
						attackedLocation.contains(loc)) {
					break;
				} else {
					count ++;
				}
			}
		}
		if (count == 3) {
			candidates.add(LocationFactory.build(board, currentSquare, pieceColor, -2, 0)); 
		}
	}
	*/
}
