package com.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.squares.Square;
import com.squares.SquareColor;

class LocationFactoryTest {
	Location origin = new Location(File.A, 0);
	Location bottomLeft = new Location(File.A, 7);
	Location topRight = new Location(File.H, 0);
	Location bottomRight = new Location(File.H, 7);

	@Test
	void testBuildSameLocation() {
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, origin), 0, 0);
		assertEquals(origin, newLocation);
	}
	
	@Test
	void testBuild_InvalidFileOffset_BottomLeftCorner() {
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, bottomLeft), -1, 0);
		assertNull(newLocation);
	}
	
	@Test
	void testBuild_InvalidFileOffset_BottomRightCorner() {
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, bottomRight), 1, 0);
		assertNull(newLocation);
	}
	
	@Test
	void testBuild_InvalidFileOffset_TopLeftCorner() {
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, origin), -1, 0);
		assertNull(newLocation);
	}
	
	@Test
	void testBuild_InvalidFileOffset_TopRightCorner() {
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, topRight), 1, 0);
		assertNull(newLocation);
	}
	
	@Test
	void testBuildPositiveOffset() {
		Location location = new Location(File.B, 3);
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, location), 2, 2);
		assertEquals(new Location(File.D, 5), newLocation);
	}
	

	@Test
	void testBuildNegativeOffset() {
		Location location = new Location(File.B, 3);
		Location newLocation = LocationFactory.build(new Board(), new Square(SquareColor.LIGHT, location), -1, -1);
		assertEquals(new Location(File.A, 2), newLocation);
	}
}
