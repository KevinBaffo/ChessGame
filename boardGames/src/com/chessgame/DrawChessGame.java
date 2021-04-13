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
import com.board.LocationFactory;
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
				drawOptions(g2, location, Color.blue);
			}
			if (currentPiece.getName() == "King") {
				for (Location loc : game.getCastlingMoves(currentPiece.getPieceColor())) {
					drawOptions(g2, loc, Color.red);
				}
			}
		}
		
	}
	
	public void drawOptions(Graphics2D g2, Location loc, Color color) {	
		g2.setColor(color);
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
	
	public void drawCastling(Location location) {
		Integer rankOfKing = currentPiece.getPieceColor() == PieceColor.DARK ? 0 : 7;
		Integer rookFileOffset = 1;
		ChessPiece king = null, rook = null;
		for(ChessPiece piece : game.getChessPieces().values()) {
			if (piece.getName() == "King" && piece.getPieceColor().equals(currentPiece.getPieceColor())) {
				king = piece;
			}
		}
		
		if (location.getFile().ordinal() > king.getCurrentSquare().getLocation().getFile().ordinal()) {
			rook = game.getChessboard().getBoardSquares()[7][rankOfKing].getCurrentPiece(); 
			rookFileOffset *= -1;
		} else {
			rook = game.getChessboard().getBoardSquares()[0][rankOfKing].getCurrentPiece();
			
		}
		game.updateChessPieces(location, king);
		game.updateChessPieces(LocationFactory.buildFromPiece(game.getChessboard(), king, rookFileOffset, 0), rook);
		turnOfLight = !turnOfLight;
		repaint();
	}
	
	public void movePieceTo(Location location) {
		Square originalSquare = currentPiece.getCurrentSquare();
		Square finalSquare = game.getChessboard().getLocationsquareMap().get(location);
		ChessPiece foodPiece = finalSquare.getCurrentPiece();
		// Remove piece that is about to be eaten so that it doesn't effect isInCheck()
		boolean takingEnemyPiece = game.getChessPieces().containsKey(location)  && !foodPiece.getPieceColor().equals(currentPiece.getPieceColor());
		if (takingEnemyPiece) game.removeChessPiece(foodPiece);

		game.updateChessPieces(location, currentPiece);
		if (game.isInCheck(currentPiece.getPieceColor())) {
			System.out.println("IN CHECK, PLEASE SAVE YOUR KING!");
			game.updateChessPieces(originalSquare.getLocation(), currentPiece);
			if (takingEnemyPiece) game.addChessPieces(location, foodPiece);
		} else {
			currentPiece.setMoveStatus(true);
			turnOfLight = !turnOfLight;
			repaint();
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
		if (!game.getChessboard().getLocationsquareMap().containsKey(finalLocation)) return; 
		if (xOrigin != xFinal || yOrigin != yFinal) {
			if (currentPiece.getValidMoves(game.getChessboard()).contains(finalLocation)) movePieceTo(finalLocation);
			
			if (game.getCastlingMoves(currentPiece.getPieceColor()).contains(finalLocation)) drawCastling(finalLocation);
			
		}
		currentPiece = null;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
}
