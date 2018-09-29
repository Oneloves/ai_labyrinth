package ihm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;

import javax.swing.JPanel;

import main.Main;
import maze.Maze;

public class Grid extends JPanel {
	private static final long serialVersionUID = 1L;
	
	public int GRID_COUNT_W = 1;
	public int GRID_COUNT_H = 1;
	public Maze maze = null;

	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		setBackground(Color.black);
		
		Graphics2D graphics = (Graphics2D) g;
		
		int w = Main.grid.getSize().width;
		int h = Main.grid.getSize().height;
		     
		int sqrWidth = (int)(w/GRID_COUNT_W);
		int sqrHeight = (int)(h/GRID_COUNT_H);
		
		for (int row = 0; row < GRID_COUNT_H; row++) {
			for (int col = 0; col < GRID_COUNT_W; col++) {
				int x = (int) (col * (double)(w/GRID_COUNT_W));
				int y = (int) (row * (double)(h/GRID_COUNT_H));
				
				g.setColor(Color.black);
				
				if(maze != null) {
					if(maze.cells[row][col].type == 1)
						g.setColor(Color.white);
					if(maze.cells[row][col].type == -1)
						g.setColor(Color.yellow);
					if(maze.cells[row][col].state == 2)
						g.setColor(Color.red);
					if(maze.cells[row][col].state == 1)
						g.setColor(Color.green);
					if(maze.cells[row][col].state == 3)
						g.setColor(Color.yellow);
				}
					
				graphics.fillRect(x, y, sqrWidth, sqrHeight);
			}
		}
	}
	
}
