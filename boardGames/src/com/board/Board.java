package com.board;

import java.util.HashMap;
import java.util.Map;
import com.squares.Square;
import com.squares.SquareColor;

public class Board {
	private static final Integer BOARD_LENGTH = 8;
	private static final Integer CELL_SIZE = 64;
	private static final File[] files = File.values();
	private final Map<Location, Square> locationsquareMap;
	Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH]; // Starts from top left of board 
	public Board() {
		locationsquareMap = new HashMap<>();
		for(int i = 0; i < boardSquares.length; i++) {
			int column = 0;
			SquareColor currentColor = (i % 2 == 0) ? SquareColor.LIGHT : SquareColor.DARK;
			for(File file : files) { 
				// build board
				Square newSquare = new Square(currentColor, new Location(file, BOARD_LENGTH - 1 - i));
				locationsquareMap.put(newSquare.getLocation(), newSquare);
				boardSquares[column][BOARD_LENGTH - 1 - i] = newSquare;				
				currentColor = (currentColor == SquareColor.DARK) ? SquareColor.LIGHT : SquareColor.DARK;
				column++;
			}
		}
	}
	
	public Map<Location, Square> getLocationsquareMap() {
		return locationsquareMap;
	}
	
	public Square[][] getBoardSquares() {
		return boardSquares;
	}

	public static Integer getBoardLength() {
		return BOARD_LENGTH;
	}

	public static Integer getCellSize() {
		return CELL_SIZE;
	}	
}
