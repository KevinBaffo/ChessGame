package com.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.squares.Square;

class BoardTest {

	@Test
	void testBoardLength() {
		Board board = new Board();
		Integer count = 0;
		for(Square s : board.getLocationsquareMap().values()) {
			count++;
		}
		assertEquals(count, 64);
	}
}
