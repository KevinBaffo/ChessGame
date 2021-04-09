package com.board;

import com.chesspieces.ChessPiece;
import com.chesspieces.PieceColor;
import com.squares.Square;

public class LocationFactory {
	private static final File[] files = File.values();
	public static Location build(Board board, Square current, Integer fileOffset, Integer rankOffset) {
		Integer currentFile = current.getLocation().getFile().ordinal();
		Integer f = currentFile + fileOffset;
		Integer r = current.getLocation().getRank() + rankOffset;

		if (r < 0 || r > 7 || f > 7 || f < 0) {
			return null;
		}else {
			return  new Location(files[f], r);
		}
	}
	
	public static Location buildFromPiece(Board board, ChessPiece piece, Integer fileOffset, Integer rankOffset) {
		Square current = piece.getCurrentSquare();
		rankOffset *= piece.getPieceColor() == PieceColor.DARK ? 1 : -1;
		Integer currentFile = current.getLocation().getFile().ordinal();
		Integer f = currentFile + fileOffset;
		Integer r = current.getLocation().getRank() + rankOffset;

		if (r < 0 || r > 7 || f > 7 || f < 0) {
			return null;
		}else {
			return  new Location(files[f], r);
		}
	}
}
