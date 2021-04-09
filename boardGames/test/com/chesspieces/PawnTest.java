package com.chesspieces;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.board.Board;
import com.board.File;
import com.board.Location;
import com.board.LocationFactory;
import com.chesspieces.ChessPiece;
import com.chesspieces.Pawn;
import com.chesspieces.PieceColor;
import com.squares.Square;

class PawnTest {
	
	@Test
	void testGetValidFirstMove_LightPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.LIGHT);
		pawn.setCurrentSquare(board.getBoardSquares()[1][1]);
		Location nextLoc = new Location(File.B, 3);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getFirstMove(board, moveCandidates);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetValidForwardMove_LightPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.LIGHT);
		pawn.setCurrentSquare(board.getBoardSquares()[1][1]);
		Location nextLoc = new Location(File.B, 2);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getForwardMove(board, moveCandidates);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetValid_ForwardEastMove_LightPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.LIGHT);
		ChessPiece enemy = new ChessPiece(PieceColor.DARK) {};
		pawn.setCurrentSquare(board.getBoardSquares()[1][1]);
		Location east = LocationFactory.buildFromPiece(board, pawn, 1, 1);
		Square northEastSquare = board.getBoardSquares()[2][2];
		northEastSquare.setTaken(true);
		northEastSquare.setCurrentPiece(enemy);
		Location nextLoc = new Location(File.C, 2);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getAttackingMove(board, moveCandidates, east);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetValidForwardWest_LightPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.LIGHT);
		ChessPiece enemy = new ChessPiece(PieceColor.DARK) {};
		pawn.setCurrentSquare(board.getBoardSquares()[1][1]);
		Location west = LocationFactory.buildFromPiece(board, pawn, -1, 1);
		Square northEastSquare = board.getBoardSquares()[0][2];
		northEastSquare.setTaken(true);
		northEastSquare.setCurrentPiece(enemy);
		Location nextLoc = new Location(File.A, 2);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getAttackingMove(board, moveCandidates, west);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetUpgradeStatus_LightPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.LIGHT);
		pawn.setCurrentSquare(board.getBoardSquares()[3][7]);
		assertTrue(pawn.getUpgradeStatus());
	}
	
	@Test
	void testGetValidFirstMoveExtra_DarkPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.DARK);
		pawn.setCurrentSquare(board.getBoardSquares()[3][6]);
		Location nextLoc = new Location(File.D, 4);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getFirstMove(board, moveCandidates);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetValidForwardMove_DarkPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.DARK);
		pawn.setCurrentSquare(board.getBoardSquares()[3][6]);
		Location nextLoc = new Location(File.D, 5);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getForwardMove(board, moveCandidates);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetValid_ForwardEastMove_DarkPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.DARK);
		ChessPiece enemy = new ChessPiece(PieceColor.LIGHT) {};
		pawn.setCurrentSquare(board.getBoardSquares()[3][6]);
		Location east = LocationFactory.buildFromPiece(board, pawn, 1, 1);
		Square northEastSquare = board.getBoardSquares()[4][5];
		northEastSquare.setTaken(true);
		northEastSquare.setCurrentPiece(enemy);
		Location nextLoc = new Location(File.E, 5);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		pawn.getAttackingMove(board, moveCandidates, east);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetValid_ForwardWestMove_DarkPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.DARK);
		ChessPiece enemy = new ChessPiece(PieceColor.LIGHT) {};
		pawn.setCurrentSquare(board.getBoardSquares()[3][6]);
		Square forwardWestSquare = board.getBoardSquares()[2][5];
		forwardWestSquare.setTaken(true);
		forwardWestSquare.setCurrentPiece(enemy);
		Location nextLoc = new Location(File.C, 5);
		List<Location> moveCandidates = new ArrayList<>(), test = new ArrayList<>();
		Location west = LocationFactory.buildFromPiece(board, pawn, -1, 1);
		pawn.getAttackingMove(board, moveCandidates, west);
		test.add(nextLoc);
		assertEquals(moveCandidates, test);
	}
	
	@Test
	void testGetUpgradeStatus_DarkPiece() {
		Board board = new Board();
		Pawn pawn = new Pawn(PieceColor.DARK);
		pawn.setCurrentSquare(board.getBoardSquares()[3][0]);
		assertTrue(pawn.getUpgradeStatus());
	}
	
}
