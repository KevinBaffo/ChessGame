package com.chessgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.board.Board;
import com.board.Location;
import com.chesspieces.ChessPiece;
import com.chesspieces.PieceColor;
import com.squares.Square;
import com.squares.SquareColor;

public class DrawChessGame extends JPanel implements MouseListener, MouseMotionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	private Graphics2D g2 = null;
	private Color color;
	private ChessGame game;
	private Integer CELL_SIZE = Board.getCellSize();
	private ChessPiece currentPiece;
	private int xOrigin = 0, yOrigin = 0;
	private boolean turnOfLight = true;
	Map<Location, ChessPiece> allPieces;
	
	public DrawChessGame(ChessGame game ) {
		this.addMouseListener(this);
		this.game = game;
		allPieces = game.getChessPieces();
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		g2 = (Graphics2D)g;
		drawBoard(g2);
		drawPieces(g2);
		if (currentPiece!= null) {
			for (Location location : currentPiece.getValidMoves(game.getChessboard())) {
				drawOptions(g2, location);
			}	
		}
	}
	
	public void drawOptions(Graphics2D g2, Location loc) {	
		g2.setColor(Color.blue);
		g2.fillOval(CELL_SIZE *loc.getFile().ordinal()+ 2*CELL_SIZE/5, CELL_SIZE * loc.getRank() + 2*CELL_SIZE/5, CELL_SIZE /5, CELL_SIZE/5);
	}
	
	public void drawBoard(Graphics2D g2) {
		for(Square[] row : game.getChessboard().getBoardSquares()) {
			for(Square square : row) {
				color = (square.getSquarecolor() == SquareColor.DARK) ? new Color(102, 51, 0) : Color.white.darker();
				g2.setColor(color);
				Integer xPos = square.getLocation().getFile().ordinal();
				Integer yPos = square.getLocation().getRank();
				g2.fillRect(xPos * CELL_SIZE, yPos * CELL_SIZE, CELL_SIZE, CELL_SIZE);
			}
		}
	}

	public void drawPieces(Graphics2D g2) {
		for (ChessPiece p : allPieces.values()) {
			Image pieceImage = null;
			if(p == null) continue;
			String url = "img/" + p.getPieceColor().toString() + "_" + p.getName() + ".png";			
			Integer xPos = p.getCurrentSquare().getLocation().getFile().ordinal();
			Integer yPos = p.getCurrentSquare().getLocation().getRank();			
			
			try {
				pieceImage = ImageIO.read(new java.io.File(url));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g2.drawImage(pieceImage, xPos * CELL_SIZE, yPos * CELL_SIZE, this);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (currentPiece == null ) return;
		repaint();
	}

	@Override
	public void mouseMoved(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		mousePressed(e);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		xOrigin = e.getX() / CELL_SIZE;
		yOrigin = e.getY() / CELL_SIZE;		
		Location loc = game.getChessboard().getBoardSquares()[xOrigin][yOrigin].getLocation();
		System.out.println("Pressed: " + loc);
		currentPiece = allPieces.get(loc);
		PieceColor turn = turnOfLight == true ? PieceColor.LIGHT : PieceColor.DARK;
		if (currentPiece == null ) {
			return;
		} else if (currentPiece.getPieceColor() != turn) {
			currentPiece = null;
			return;
		}
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (currentPiece == null ) return;
		int xFinal = e.getX() / CELL_SIZE;
		int yFinal = e.getY() / CELL_SIZE;
		Location finalLocation = game.getChessboard().getBoardSquares()[xFinal][yFinal].getLocation();
		Square current = currentPiece.getCurrentSquare();
		Square newSquare = game.getChessboard().getLocationsquareMap().get(finalLocation);
		try {
			game.getChessboard().getLocationsquareMap().containsKey(finalLocation);
		} catch (Exception e2) {
			e2.printStackTrace();
			return;
		}
		if (xOrigin != xFinal || yOrigin != yFinal) {
			ChessPiece food = newSquare.getCurrentPiece();
			// remove piece that is about to be eaten so that it doeant affect isInCheck()
			boolean pieceEaten = false;
			try {
				game.getChessPieces().remove(newSquare.getLocation());
				pieceEaten = true;
			} catch(Exception e3) {
				e3.printStackTrace();
				pieceEaten = false;
			}
			//
			currentPiece.makeMove(game.getChessboard(), newSquare);
			if (game.isInCheck(currentPiece.getPieceColor())) {
				currentPiece.makeMove(game.getChessboard(), current);
				if (pieceEaten) game.getChessPieces().put(newSquare.getLocation(), food);
				System.out.println("IN CHECK, PLEASE SAVE YOUR KING!");
			} else {
				game.getChessPieces().remove(current.getLocation());
				game.getChessPieces().put(finalLocation, currentPiece);
				turnOfLight = !turnOfLight;
				repaint();
			}
			
//			Integer num = current.getLocation().getFile().ordinal() - finalLocation.getFile().ordinal() ;
//			if (piece.getName() == "King" && 
//				Math.abs(num) > 1) {
//				if (num > 0 ) {
//					ChessPiece rook = locationsquareMap.get(LocationFactory.build(this, current, piece.getPieceColor(), -4, 0)).getCurrentPiece();
//					Square rookCastlingPosition = locationsquareMap.get(LocationFactory.build(this, current, piece.getPieceColor(), -1, 0));
//					rook.setHasCastled(true);
//					rook.makeMove(this, rookCastlingPosition);
//				} else {
//					ChessPiece rook = locationsquareMap.get(LocationFactory.build(this, current, piece.getPieceColor(), 3, 0)).getCurrentPiece();
//					rook.setHasCastled(true);
//					Square rookCastlingPosition = locationsquareMap.get(LocationFactory.build(this, current, piece.getPieceColor(), 1, 0));
//					rook.makeMove(this, rookCastlingPosition);
//				}
//			}	
		}
		currentPiece = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
