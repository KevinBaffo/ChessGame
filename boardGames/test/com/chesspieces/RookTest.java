package com.chesspieces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.board.Board;
import com.board.File;
import com.board.Location;
import com.chessgame.ChessGame;
import com.squares.Square;
import com.squares.SquareColor;

class RookTest {
	ChessPiece piece = new Rook(PieceColor.DARK);
	Location loc1 = new Location(File.C, 2);
	Square s1 = new Square(SquareColor.DARK, loc1);
	Location loc2 = new Location(File.C, 2);
	Square s2 = new Square(SquareColor.DARK, loc2);
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testRook() {assertEquals(piece.getPieceColor(), PieceColor.DARK);}
	
	@Test
	void testSetCurrentSquare() {
		piece.setCurrentSquare(s1);
		piece.setCurrentSquare(s2);
		assertEquals(piece.getLastSquare(), s1);
		assertEquals(piece.getCurrentSquare(), s2);
		assertEquals(s2.getCurrentPiece(), piece);
		assertEquals(s2.isTaken(), true);
	}

	@Test
	void testSameFileCandidates_EmptyColumn() {
		Rook rookie = new Rook(PieceColor.LIGHT);
		Map<Location, ChessPiece> pieces = new HashMap<>();
		pieces.put(new Location(File.A, 0), rookie);
		ChessGame game1 = new ChessGame(pieces);
		game1.assignPieces();
		List<Location> test = rookie.getCandidates(game1.getChessboard(), 0, 1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 8; i++) {
			moveCandidates.add(new Location(File.A, i));
		}
		assertEquals(test.size(), 7);
//		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testSameFileCandidates_FriendlyPiece() {
		Rook rookie = new Rook(PieceColor.LIGHT);
		Rook friendly = new Rook(PieceColor.LIGHT);
		Map<Location, ChessPiece> pieces = new HashMap<>();
		pieces.put(new Location(File.A, 0), rookie);
		pieces.put(new Location(File.A, 5), friendly);
		ChessGame game = new ChessGame(pieces);
		game.assignPieces();
		List<Location> test = rookie.getCandidates(game.getChessboard(), 0, 1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 5; i++) {
			moveCandidates.add(new Location(File.A, i));
		}
		assertEquals(moveCandidates, test);
	}
	@Test
	void testSameFileCandidates_EnemyPiece() {
		Rook rookie = new Rook(PieceColor.LIGHT);
		Rook enemy = new Rook(PieceColor.DARK);
		Map<Location, ChessPiece> pieces = new HashMap<>();
		pieces.put(new Location(File.A, 0), rookie);
		pieces.put(new Location(File.A, 5), enemy);
		ChessGame game = new ChessGame(pieces);
		game.assignPieces();
		List<Location> test = rookie.getCandidates(game.getChessboard(), 0, 1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 6; i++) {
			moveCandidates.add(new Location(File.A, i));
		}
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testSameRankCandidates_EmptyRow() {
		Rook rookie = new Rook(PieceColor.LIGHT);
		File[] files = File.values();
		Map<Location, ChessPiece> pieces = new HashMap<>();
		pieces.put(new Location(File.A, 0), rookie);
		ChessGame game = new ChessGame(pieces);
		game.assignPieces();
		List<Location> test = rookie.getCandidates(game.getChessboard(), 1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 8; i++) {
			moveCandidates.add(new Location(files[i], 0));
		}
		assertEquals(moveCandidates, test);
	}

//	@Test
//	void testMakeMove() {
//		fail("Not yet implemented");
//	}
}
