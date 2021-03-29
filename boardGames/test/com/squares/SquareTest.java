package com.squares;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.Test;

import com.board.File;
import com.board.Location;

public class SquareTest {	
	
	@Test
	public void testSquare() {
		Square square = new Square(SquareColor.LIGHT, new Location(File.C, 5));
		assertFalse(square.isTaken());	
	}

	@Test
	void testResetIsTaken() {
		Square square = new Square(SquareColor.LIGHT, new Location(File.C, 5));
		square.setTaken(true);
		square.reset();
		assertFalse(square.isTaken());
	}
	
	@Test
	void testResetCurrentPiece() {
		Square square = new Square(SquareColor.LIGHT, new Location(File.C, 5));
		square.setTaken(true);
		square.reset();
		assertNull(square.getCurrentPiece());
	}
	
}
