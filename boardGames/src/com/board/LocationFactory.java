package com.board;

import com.squares.Square;

public class LocationFactory {
	private static final File[] files = File.values();
	public static Location build(Board board, Square current, Integer fileOffset, Integer rankOffset) {
		Location location = null;
		Integer currentFile = current.getLocation().getFile().ordinal();
		Integer f = currentFile + fileOffset;
		Integer r = current.getLocation().getRank() + rankOffset;

		if (r < 0 || r > 7 || f > 7 || f < 0) {
			return null;
		}else {
			location = new Location(files[f], r);
			return location;
		}
	}
}
