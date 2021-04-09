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
import com.chesspieces.ChessPiece;
import com.chesspieces.PieceColor;
import com.chesspieces.Rook;
import com.squares.Square;
import com.squares.SquareColor;

class RookTest {

	@BeforeEach
	void setUp() throws Exception {}

	@AfterEach
	void tearDown() throws Exception {
	}
 
	/////////////////////////////////// Light Pieces ////////////////////////////
	@Test
	void testSameFileCandidatesLight_EmptyColumn() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.LIGHT);
		rookie.setCurrentSquare(board.getBoardSquares()[7][0]);
		List<Location> expected = rookie.getCandidates(board, 0, 1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 8; i++) {
			moveCandidates.add(new Location(File.H, i));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameFileCandidatesLight_Friendly() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.LIGHT);
		ChessPiece friendly = new ChessPiece(PieceColor.LIGHT) {};
		rookie.setCurrentSquare(board.getBoardSquares()[7][0]);
		friendly.setCurrentSquare(board.getBoardSquares()[7][5]);
		List<Location> expected = rookie.getCandidates(board, 0, 1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 5; i++) {
			moveCandidates.add(new Location(File.H, i));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	final void testSameFileCandidatesLight_Enemy() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.LIGHT);
		ChessPiece enemy = new ChessPiece(PieceColor.DARK) {};
		rookie.setCurrentSquare(board.getBoardSquares()[0][0]);
		enemy.setCurrentSquare(board.getBoardSquares()[0][6]);
		List<Location> expected = rookie.getCandidates(board, 0, 1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 1; i < 7; i++) {
			moveCandidates.add(new Location(File.A, i));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameRankCandidatesLight_EmptyRow() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.LIGHT);
		File[] file = File.values();
		rookie.setCurrentSquare(board.getBoardSquares()[7][0]);
		List<Location> expected = rookie.getCandidates(board, -1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 0; i--) {
			moveCandidates.add(new Location(file[i], 0));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameRankCandidatesLight_Friendly() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.LIGHT);
		ChessPiece friendly = new ChessPiece(PieceColor.LIGHT) {};
		File[] file = File.values();
		rookie.setCurrentSquare(board.getBoardSquares()[7][0]);
		friendly.setCurrentSquare(board.getBoardSquares()[3][0]);
		List<Location> expected = rookie.getCandidates(board, -1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 4; i--) {
			moveCandidates.add(new Location(file[i], 0));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameRankCandidatesLight_Enemy() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.LIGHT);
		ChessPiece enemy = new ChessPiece(PieceColor.DARK) {};
		File[] file = File.values();
		rookie.setCurrentSquare(board.getBoardSquares()[7][0]);
		enemy.setCurrentSquare(board.getBoardSquares()[3][0]);
		List<Location> expected = rookie.getCandidates(board, -1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 3; i--) {
			moveCandidates.add(new Location(file[i], 0));
		}
		assertEquals(expected, moveCandidates);
	}
	
	/////////////////////////////////Dark Pieces //////////////////////////////////////////
	
	@Test
	void testSameFileCandidatesDark_EmptyColumn() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.DARK);
		rookie.setCurrentSquare(board.getBoardSquares()[7][6]);
		List<Location> expected = rookie.getCandidates(board, 0, -1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 5; i >= 0; i--) {
			moveCandidates.add(new Location(File.H, i)); 
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameFileCandidatesDark_Friendly() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.DARK);
		ChessPiece friendly = new ChessPiece(PieceColor.DARK) {};
		rookie.setCurrentSquare(board.getBoardSquares()[0][7]);
		friendly.setCurrentSquare(board.getBoardSquares()[0][3]);
		List<Location> expected = rookie.getCandidates(board, 0, -1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 4 ; i--) {
			moveCandidates.add(new Location(File.A, i));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	final void testSameFileCandidatesDark_Enemy() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.DARK);
		ChessPiece enemy = new ChessPiece(PieceColor.LIGHT) {};
		rookie.setCurrentSquare(board.getBoardSquares()[0][7]);
		enemy.setCurrentSquare(board.getBoardSquares()[0][3]);
		List<Location> expected = rookie.getCandidates(board, 0, -1);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 3 ; i--) {
			moveCandidates.add(new Location(File.A, i));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameRankCandidatesDark_EmptyColumn() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.DARK);
		File[] file = File.values();
		rookie.setCurrentSquare(board.getBoardSquares()[7][7]);
		List<Location> expected = rookie.getCandidates(board, -1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 0; i--) {
			moveCandidates.add(new Location(file[i], 7));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameRankCandidatesDark_Friendly() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.DARK);
		ChessPiece friendly = new ChessPiece(PieceColor.DARK) {};
		File[] file = File.values();
		rookie.setCurrentSquare(board.getBoardSquares()[7][7]);
		friendly.setCurrentSquare(board.getBoardSquares()[3][7]);
		List<Location> expected = rookie.getCandidates(board, -1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 4; i--) {
			moveCandidates.add(new Location(file[i], 7));
		}
		assertEquals(expected, moveCandidates);
	}
	
	@Test
	void testSameRankCandidatesDark_Enemy() {
		Board board = new Board();
		Rook rookie = new Rook(PieceColor.DARK);
		ChessPiece enemy = new ChessPiece(PieceColor.LIGHT) {};
		File[] file = File.values();
		rookie.setCurrentSquare(board.getBoardSquares()[7][5]);
		enemy.setCurrentSquare(board.getBoardSquares()[3][5]);
		List<Location> expected = rookie.getCandidates(board, -1, 0);
		List<Location> moveCandidates = new ArrayList<>();
		for(int i = 6; i >= 3; i--) {
			moveCandidates.add(new Location(file[i], 5));
		}
		assertEquals(expected, moveCandidates);
	}
	
	//	@Test
//	void testMakeMove() {
//		fail("Not yet implemented");
//	}
}
