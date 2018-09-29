package main;


import java.awt.Dimension;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;

import ia.algorithms.pathFinding.AStar;
import ia.algorithms.pathFinding.BestFirst;
import ia.algorithms.pathFinding.UniformCost;
import ihm.ControlFrame;
import ihm.Grid;
import maze.Maze;
import readFile.FileFunctions;

public class Main {
	
	public static Grid grid;
    

	public static void main(String[] args){
		
		grid = new Grid();
	    grid.setPreferredSize(new Dimension(400, 400));
	    JFrame mazeFrame = new JFrame("Grid");
	    mazeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    mazeFrame.add(grid);
	    mazeFrame.pack();
	    mazeFrame.setLocationRelativeTo(null);
		mazeFrame.setVisible(true);
		
		new ControlFrame();
	}

	
}
