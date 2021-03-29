package com.chesspieces;

import com.board.Board;
import com.board.Location;
import java.util.List;

public interface Movable {
	List<Location> getValidMoves(Board board);
}
