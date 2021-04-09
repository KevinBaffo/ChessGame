package com.chesspieces;

import java.util.HashMap;
import java.util.Map;
import com.board.File;
import com.board.Location;

public final class ChessPieceFactory {

	public ChessPieceFactory() {};
	
	public static Map<Location, ChessPiece> getPieces() {
		Map<Location, ChessPiece> pieces = new HashMap<>();
		
		// rooks
		pieces.put(new Location(File.A, 7), new Rook(PieceColor.LIGHT));
		pieces.put(new Location(File.H, 7), new Rook(PieceColor.LIGHT));
		
		pieces.put(new Location(File.A, 0), new Rook(PieceColor.DARK));
		pieces.put(new Location(File.H, 0), new Rook(PieceColor.DARK));
	
		//  knights
		pieces.put(new Location(File.B, 7), new Knight(PieceColor.LIGHT));
		pieces.put(new Location(File.G, 7), new Knight(PieceColor.LIGHT));
		
		pieces.put(new Location(File.B, 0), new Knight(PieceColor.DARK));
		pieces.put(new Location(File.G, 0), new Knight(PieceColor.DARK));

		// bishops
		pieces.put(new Location(File.C, 7), new Bishop(PieceColor.LIGHT));
		pieces.put(new Location(File.F, 7), new Bishop(PieceColor.LIGHT));
		
		pieces.put(new Location(File.C, 0), new Bishop(PieceColor.DARK));
		pieces.put(new Location(File.F, 0), new Bishop(PieceColor.DARK));
		
		// queens
		pieces.put(new Location(File.D, 7), new Queen(PieceColor.LIGHT));
		pieces.put(new Location(File.D, 0), new Queen(PieceColor.DARK));
		
		
		// kings 
		pieces.put(new Location(File.E, 7), new King(PieceColor.LIGHT));
		pieces.put(new Location(File.E, 0), new King(PieceColor.DARK));
		
		// pawns 
		for (File file : File.values()) {
			pieces.put(new Location(file, 6), new Pawn(PieceColor.LIGHT));
			pieces.put(new Location(file, 1), new Pawn(PieceColor.DARK));
		}

		return pieces;		
	}
}
