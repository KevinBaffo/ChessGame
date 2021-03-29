package com.chesspieces;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.board.Location;

public class DrawPieces extends JPanel {
	private static final Integer CELL_SIZE = 64;
	private Graphics2D g2 = null;
//	private Color color;
	private Map<Location, ChessPiece> pieces;
	
	public DrawPieces(Map<Location, ChessPiece> pieces) {
		this.pieces = pieces;
	}
	
	@Override
	protected void paintChildren(Graphics g) {
		g2 = (Graphics2D)g;
		drawPieces(g2);
	}
	
	public void drawPieces(Graphics2D g2) {
		for (ChessPiece p : pieces.values()) {
			Image pieceImage = null;
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
}
