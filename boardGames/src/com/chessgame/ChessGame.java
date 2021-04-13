package com.chessgame;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import com.board.Board;
import com.board.Location;
import com.chesspieces.ChessPiece;
import com.chesspieces.ChessPieceFactory;
import com.chesspieces.PieceColor;
import com.squares.Square;

public class ChessGame {
	private static final Board chessBoard = new Board();
	private Map<Location, ChessPiece> chessPieces;
	
	public ChessGame(Map<Location, ChessPiece> chessPieces) {
		this.chessPieces = chessPieces;
		this.assignPieces(chessPieces);
	}
	
	public Board getChessboard() {
		return chessBoard;
	}

	public Map<Location, ChessPiece> getChessPieces() {
		return chessPieces;
	}
	
	public void removeChessPiece(ChessPiece piece) {
		Location location = piece.getCurrentSquare().getLocation();
		chessBoard.getLocationsquareMap().get(location).reset();
		chessPieces.remove(piece.getCurrentSquare().getLocation());
		piece.setCurrentSquare(null);
	}
	
	public void addChessPieces(Location location, ChessPiece piece) {
		chessPieces.put(location, piece);
		chessBoard.getLocationsquareMap().get(location).set(piece);
		piece.setCurrentSquare(chessBoard.getLocationsquareMap().get(location));
	}
	
	public void updateChessPieces(Location location, ChessPiece piece) {
		removeChessPiece(piece);
		addChessPieces(location, piece);
	}
	
	public void draw() {
		JFrame frame;
		DrawChessGame drawGame;
		frame = new JFrame("Chess Game");
		frame.setSize(530, 550);
		frame.setLayout(new BorderLayout());
		drawGame = new DrawChessGame(this);
		frame.add(drawGame, BorderLayout.CENTER);
		frame.setVisible(true);
//		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
	}
	
	public void assignPieces(Map<Location, ChessPiece> pieces) {
		for(Square current : chessBoard.getLocationsquareMap().values()) {
			if (this.chessPieces.containsKey(current.getLocation())) {
				ChessPiece p = this.chessPieces.get(current.getLocation());
				current.setCurrentPiece(p);
				current.setTaken(true);
				p.setCurrentSquare(current);
			}
		}
	}
	
	public List<Location> getPlayerMoves(PieceColor color) {
		List<Location>  allMoves = new ArrayList<>();
		for (ChessPiece piece : chessPieces.values()) {
			if(piece == null) continue;
			if (piece.getPieceColor().equals(color)) {
				allMoves.addAll(piece.getValidMoves(chessBoard));
			}
		}
		return allMoves;
	}
	
	public List<Location> getCastlingMoves(PieceColor color) {
		List<Location>  castleMoves = new ArrayList<>();
		canCastleRight(castleMoves, color);
		canCastleLeft(castleMoves, color);
		return castleMoves;
	}
	
	public void canCastleRight(List<Location> moveCandidates, PieceColor color) {
		Integer count = 0;
		List<Location> sideLocations = new ArrayList<>();
		Integer i = color == PieceColor.DARK ? 0 : 7;
		PieceColor oppositeColor = color == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
		sideLocations.add(chessBoard.getBoardSquares()[5][i].getLocation());
		sideLocations.add(chessBoard.getBoardSquares()[6][i].getLocation());
		ChessPiece rook = chessBoard.getBoardSquares()[7][i].getCurrentPiece();
		ChessPiece king = chessBoard.getBoardSquares()[4][i].getCurrentPiece();
		List<Location> attackedLocation = getPlayerMoves(oppositeColor);
		if (attackedLocation == null || rook == null || king == null) return;
		if (!king.hasMoved() && !rook.hasMoved()) {
			for (Location loc : sideLocations) {
				if (chessBoard.getLocationsquareMap().get(loc).isTaken() ||
					attackedLocation.contains(loc)) {
					break;
				} else {
					count ++;
				}
			}
		}
		if (count == 2) moveCandidates.add(sideLocations.get(sideLocations.size() - 1));
	}
	
	public void canCastleLeft(List<Location> moveCandidates, PieceColor color) {
		Integer count = 0;
		List<Location> sideLocations = new ArrayList<>();
		Integer i = color == PieceColor.DARK ? 0 : 7;
		PieceColor oppositeColor = color == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
		sideLocations.add(chessBoard.getBoardSquares()[1][i].getLocation());
		sideLocations.add(chessBoard.getBoardSquares()[2][i].getLocation());
		sideLocations.add(chessBoard.getBoardSquares()[3][i].getLocation());
		ChessPiece rook = chessBoard.getBoardSquares()[0][i].getCurrentPiece();
		ChessPiece king = chessBoard.getBoardSquares()[4][i].getCurrentPiece();
		List<Location> attackedLocation = getPlayerMoves(oppositeColor);
		if (attackedLocation == null || rook == null || king == null) return;
		if (!king.hasMoved() && !rook.hasMoved()) {
			for (Location loc : sideLocations) {
				if (chessBoard.getLocationsquareMap().get(loc).isTaken() ||
					attackedLocation.contains(loc)) {
					break;
				} else {
					count ++;
				}
			}
		}
		if (count == 3) moveCandidates.add(sideLocations.get(0));
	}
	
	public boolean isInCheck(PieceColor color) {
		PieceColor oppositeColor = color == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
		ChessPiece king = null;
		// find king
		for (ChessPiece piece : chessPieces.values()) {
			if(piece == null) continue;
			if (piece.getPieceColor() == color && piece.getName() == "King") {
				king = piece;
			}
		}
		if (getPlayerMoves(oppositeColor).contains(king.getCurrentSquare().getLocation())) return true;
		
		return false;
	}
	
	public boolean isUnderAttack(Location toCheck, PieceColor color) {
		if (getPlayerMoves(color).contains(toCheck)) return true;
		return false;
	}
	
	public static void main(String[] args) {
		Map<Location, ChessPiece> pieces = ChessPieceFactory.getPieces();
		ChessGame game = new ChessGame(pieces);
		game.draw();
	}
}
