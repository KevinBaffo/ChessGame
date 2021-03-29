//package com.board;
//
//import com.chesspieces.ChessPiece;
//import com.chesspieces.ChessPieceFactory;
//import com.chesspieces.PieceColor;
//import com.squares.Square;
//import com.squares.SquareColor;
//
//import java.awt.Color;
//import java.awt.Graphics;
//import java.awt.Graphics2D;
//import java.awt.Image;
//import java.awt.event.MouseEvent;
//import java.awt.event.MouseListener;
//import java.awt.event.MouseMotionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import javax.imageio.ImageIO;
//import javax.swing.JPanel;
//
//public class Boards extends JPanel implements MouseListener, MouseMotionListener {
//	private static final long serialVersionUID = -4453072494250731877L;
//	private static final Integer BOARD_LENGTH = 8;
//	private static final Integer CELL_SIZE = 64;
//	private static final File[] files = File.values();
//	private final Map<Location, Square> locationsquareMap;
//	private Graphics2D g2 = null;
//	private Color color;
//	
//	private int xOrigin = 0, yOrigin = 0;
//	private ChessPiece piece, king;
//	private boolean turnOfLight = true;
//	Square[][] boardSquares = new Square[BOARD_LENGTH][BOARD_LENGTH];
//	Map<Location, ChessPiece> chessPieces = ChessPieceFactory.getPieces();
//	Map<PieceColor, List<Location>> pieceMoves;
//
//	public Boards() {
//		this.addMouseListener(this);
//		locationsquareMap = new HashMap<>();
//		pieceMoves = new HashMap<>();
//		for(int i = 0; i < boardSquares.length; i++) {
//			int column = 0;
//			SquareColor currentColor = (i % 2 == 0) ? SquareColor.LIGHT : SquareColor.DARK;
//			for(File file : files) { 
//				// build board
//				Square newSquare = new Square(currentColor, new Location(file, BOARD_LENGTH - 1 - i));
//				locationsquareMap.put(newSquare.getLocation(), newSquare);
//				boardSquares[column][i] = newSquare;				
//				// assign pieces
//				if (chessPieces.containsKey(newSquare.getLocation())) {
//					ChessPiece piece = chessPieces.get(newSquare.getLocation());
//					newSquare.setCurrentPiece(piece);
//					newSquare.setTaken(true);
//					piece.setCurrentSquare(newSquare);
//				}
//				currentColor = (currentColor == SquareColor.DARK) ? SquareColor.LIGHT : SquareColor.DARK;
//				column++;
//			}
//		}
//		pieceMoves.put(PieceColor.LIGHT, getPlayerMoves(PieceColor.LIGHT));
//		pieceMoves.put(PieceColor.DARK, getPlayerMoves(PieceColor.DARK));	
//	}
//		
//	public Map<PieceColor, List<Location>> getPieceMoves() {
//		return pieceMoves;
//	}
//
//	public void setChessPieces(Map<Location, ChessPiece> chessPieces) {
//		this.chessPieces = chessPieces;
//	}
//
//	public Map<Location, ChessPiece> getChessPieces() {
//		return chessPieces;
//	}
//
//	public Map<Location, Square> getLocationsquareMap() {
//		return locationsquareMap;
//	}
//
//	public Square[][] getBoardSquares() {
//		return boardSquares;
//	}
//	
//	public boolean isStalemate() {
//		boolean status = false;
//		if (getPlayerMoves(PieceColor.LIGHT).isEmpty() && getPlayerMoves(PieceColor.DARK).isEmpty()) {
//			status = true;
//		}
//		return status;
//	}
//	
//	public List<Location>getPlayerMoves(PieceColor color) {
//		List<Location>  allMoves = new ArrayList<>();
//		for (ChessPiece piece : chessPieces.values()) {
//			if (piece.getPieceColor().equals(color)) {
//				allMoves.addAll(piece.getValidMoves(this));
//			}
//		}
//		return allMoves;
//	}
//	
////	public List<Location>getDarkPlayerMoves() {
////		List<Location>  allMoves = new ArrayList<>();
////		for (ChessPiece piece : chessPieces.values()) {
////			if (piece.getPieceColor().equals(PieceColor.DARK)) {
////				allMoves.addAll(piece.getValidMoves(this));
////			}
////		}
////		return allMoves;
////	}
////	
////	public List<Location> getLightPlayerMoves() {
////		List<Location>  allMoves = new ArrayList<>();
////		for (ChessPiece piece : chessPieces.values()) {
////			if (piece.getPieceColor().equals(PieceColor.LIGHT)) {
////				allMoves.addAll(piece.getValidMoves(this));
////			}
////		}
////		return allMoves;
////	}
//	
//	public boolean isInCheck(PieceColor color) {
//		PieceColor oppositeColor = color == PieceColor.LIGHT ? PieceColor.DARK : PieceColor.LIGHT;
//		// find king
//		for (ChessPiece piece : chessPieces.values()) {
//			if (piece.getPieceColor() == color && piece.getName() == "King") {
//				king = piece;
//			}
//		}
//		if (getPlayerMoves(oppositeColor).contains(king.getCurrentSquare().getLocation())) return true;
//		
//		return false;
//	}
//	
//	public boolean isUnderAttack(Location toCheck, PieceColor color) {
//		if (getPlayerMoves(color).contains(toCheck)) return true;
//		return false;
//	}
//	
////	public Map<ChessPiece , List<Location>>getPieceMoves() {
////		Map<ChessPiece , List<Location>> moves = new HashMap<>();
////		for (ChessPiece p : chessPieces.values()) {
////			moves.put(p, p.getValidMoves(this));
////		}
////		return moves;
////	}
//	
//	@Override
//	protected void paintChildren(Graphics g) {
//		g2 = (Graphics2D)g;
//		drawBoard(g2);
//		drawPieces(g2);
//		if (piece != null) {
//			for (Location location : piece.getValidMoves(this)) {
//				drawOptions(g2, location);
//			}	
//		}
//	}
//	
//	public void drawBoard(Graphics2D g2) {
//		for(Square[] row : boardSquares) {
//			for(Square square : row) {
//				color = (square.getSquarecolor() == SquareColor.DARK) ? new Color(102, 51, 0) : Color.white.darker();
//				g2.setColor(color);
//				Integer xPos = square.getLocation().getFile().ordinal();
//				Integer yPos = square.getLocation().getRank();
//				g2.fillRect(xPos * CELL_SIZE, yPos * CELL_SIZE, CELL_SIZE, CELL_SIZE);
//			}
//		}
//	}
//
//	public void drawPieces(Graphics2D g2) {
//		for (ChessPiece p : chessPieces.values()) {
//			Image pieceImage = null;
//			String url = "img/" + p.getPieceColor().toString() + "_" + p.getName() + ".png";			
//			Integer xPos = p.getCurrentSquare().getLocation().getFile().ordinal();
//			Integer yPos = p.getCurrentSquare().getLocation().getRank();			
//			
//			try {
//				pieceImage = ImageIO.read(new java.io.File(url));
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			g2.drawImage(pieceImage, xPos * CELL_SIZE, yPos * CELL_SIZE, this);
//		}
//	}
//
//	@Override
//	public void mouseDragged(MouseEvent e) {
//		if (piece == null ) return;
//		repaint();
//	}
//
//
//	@Override
//	public void mouseMoved(MouseEvent e) {}
//
//
//	@Override
//	public void mouseClicked(MouseEvent e) {
////		mousePressed(e);
////		repaint();
//	}
//
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		xOrigin = e.getX() / CELL_SIZE;
//		yOrigin = e.getY() / CELL_SIZE;		
//		Location loc = boardSquares[xOrigin][7 - yOrigin].getLocation();
//		piece = chessPieces.get(loc);
//		PieceColor turn = turnOfLight == true ? PieceColor.LIGHT : PieceColor.DARK;
//		if (piece == null ) {
//			return;
//		} else if (piece.getPieceColor() != turn) {
//			piece = null;
//			return;
//		}
//		repaint();
//	}
//
//
//	@Override
//	public void mouseReleased(MouseEvent e) {
//		if (piece == null ) return;
//		int xFinal = e.getX() / CELL_SIZE;
//		int yFinal = e.getY() / CELL_SIZE;
//		Location finalLocation = boardSquares[xFinal][7 - yFinal].getLocation();
//		Square current = piece.getCurrentSquare();
//		try {
//			locationsquareMap.containsKey(finalLocation);
//		} catch (Exception e2) {
//			e2.printStackTrace();
//			return;
//		}
//		if (xOrigin != xFinal || yOrigin != yFinal) {
//			piece.makeMove(this, locationsquareMap.get(finalLocation));
//			if (isInCheck(piece.getPieceColor())) {
//				piece.makeMove(this, boardSquares[xOrigin][7 - yOrigin]);
//				System.out.println("IN CHECK, PLEASE SAVE YOUR KING!");
//			} else {
//				turnOfLight = !turnOfLight;
//				repaint();
//			}
//			
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
//			
//		}
//		pieceMoves.replace(PieceColor.LIGHT, getPlayerMoves(PieceColor.LIGHT));
//		pieceMoves.replace(PieceColor.DARK, getPlayerMoves(PieceColor.DARK));
//		piece = null;
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent e) {}
//
//
//	@Override
//	public void mouseExited(MouseEvent e) {}
//	
//	public void drawOptions(Graphics2D g2, Location loc) {	
//		g2.setColor(Color.blue);
//		g2.fillOval(CELL_SIZE *loc.getFile().ordinal()+ 2*CELL_SIZE/5, CELL_SIZE * loc.getRank() + 2*CELL_SIZE/5, CELL_SIZE /5, CELL_SIZE/5);
//	}
//}
