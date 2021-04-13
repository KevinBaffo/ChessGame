package com.chesspieces;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.board.Board;
import com.board.File;
import com.board.Location;
import com.squares.Square;
import com.squares.SquareColor;

class ChessPieceModelTest {
	Board board = new Board();
	ChessPiece piece;
	Location loc1;
	Square s1;
	Location loc2 ;
	Square s2;

	@BeforeEach
	void setUp() throws Exception {
		piece = new ChessPiece(PieceColor.LIGHT) {};
		loc1 = new Location(File.C, 2);
		s1 = new Square(SquareColor.DARK, loc1);
		loc2 = new Location(File.C, 2);
		s2 = new Square(SquareColor.DARK, loc2);
		
		piece.setCurrentSquare(s1);
		piece.setCurrentSquare(s2);
	}

	@AfterEach
	void tearDown() throws Exception {}

	@Test
	void testSetCurrentSquare_LastSquare() {
		assertEquals(piece.getLastSquare(), s1);
	}
	
	@Test
	void testSetCurrentSquare() {
		assertEquals(piece.getCurrentSquare(), s2);
	}
	
	@Test
	void testSetCurrentSquare_Piece() {
		assertEquals(s2.getCurrentPiece(), piece);
	}
	
	@Test
	void testSetCurrentSquare_SquareIsTaken() {
		assertEquals(s2.isTaken(), true);
	}

}
