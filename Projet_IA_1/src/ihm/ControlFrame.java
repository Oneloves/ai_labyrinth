package ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import ia.algorithms.pathFinding.AStar;
import ia.algorithms.pathFinding.BestFirst;
import ia.algorithms.pathFinding.UniformCost;
import main.Main;
import maze.Maze;
import readFile.FileFunctions;

public class ControlFrame implements ActionListener {
	
	JPanel panel;
	JPanel panelLoadFile;
	JPanel panelAlgorithmsChoice;
	JPanel panelRunChoise;
	
	JButton importFileButton;
	JButton loadButton;
	JButton goButton;
	
	ButtonGroup groupRunChoice;
	ButtonGroup groupAlgorithmChoice;
	
	JRadioButton normalChoice;
	JRadioButton stepByStepChoice;
	JRadioButton automaticChoice;
	JRadioButton aStarChoice;
	JRadioButton bestFirstChoice;
	JRadioButton uniformCostChoice;
	
	AStar astar = null;
	BestFirst bestFirst = null;
	UniformCost uniformCost = null;
	
	
	public ControlFrame() {
	    JFrame controlFrame = new JFrame();
	    controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
		panel = new JPanel();
		panelLoadFile = new JPanel();
		panelAlgorithmsChoice = new JPanel();
		panelRunChoise = new JPanel();
		
		importFileButton = new JButton("Open file");
		loadButton = new JButton("Load");
		goButton = new JButton("Go");
		
		groupRunChoice = new ButtonGroup ();
		normalChoice = new JRadioButton("Normal");
		stepByStepChoice = new JRadioButton("Step by step");
		automaticChoice = new JRadioButton("Automatic");
		
		groupAlgorithmChoice = new ButtonGroup();
		aStarChoice = new JRadioButton("A*");
		bestFirstChoice = new JRadioButton("Best first");
		uniformCostChoice = new JRadioButton("Uniform cost");
		
		aStarChoice.setSelected(true);
		normalChoice.setSelected(true);

		loadButton.addActionListener(this);
		goButton.addActionListener(this);
		
		goButton.setEnabled(false);
		normalChoice.setEnabled(false);
		stepByStepChoice.setEnabled(false);
		automaticChoice.setEnabled(false);
		normalChoice.setEnabled(false);
		aStarChoice.setEnabled(false);
		bestFirstChoice.setEnabled(false);
		uniformCostChoice.setEnabled(false);
		
	    setUpControlFrame(controlFrame);
	    controlFrame.pack();
	    controlFrame.setLocationRelativeTo(null);
	    controlFrame.setVisible(true);
	}
	
	
	public void setUpControlFrame(JFrame controlFrame) {		
		groupRunChoice.add(normalChoice);
		groupRunChoice.add(stepByStepChoice);
		groupRunChoice.add(automaticChoice);
		
		groupAlgorithmChoice.add(aStarChoice);
		groupAlgorithmChoice.add(bestFirstChoice);
		groupAlgorithmChoice.add(uniformCostChoice);
		
		panelLoadFile.add(importFileButton);
		panelLoadFile.add(loadButton);
		
		panelAlgorithmsChoice.add(normalChoice);
		panelAlgorithmsChoice.add(stepByStepChoice);
		panelAlgorithmsChoice.add(automaticChoice);
		
		panelRunChoise.add(aStarChoice);
		panelRunChoise.add(bestFirstChoice);
		panelRunChoise.add(uniformCostChoice);
		
		panel.add(panelLoadFile);
		panel.add(panelAlgorithmsChoice);
		panel.add(panelRunChoise);
		panel.add(goButton);
		
		controlFrame.add(panel);
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		
		int result = 0;
		
		//-------------------------------------/
		if(e.getSource() == goButton) {
			
			if(normalChoice.isSelected()) {
				if(aStarChoice.isSelected())
					result = astar.searchComplete();
				else if(bestFirstChoice.isSelected())
					result = bestFirst.searchComplete();
				else if(uniformCostChoice.isSelected())
					result = uniformCost.searchComplete();
			}
			
			else if(stepByStepChoice.isSelected()) {
				if(aStarChoice.isSelected())
					result = astar.searchStepByStep();
				else if(bestFirstChoice.isSelected())
					result = bestFirst.searchStepByStep();
				else if(uniformCostChoice.isSelected())
					result = uniformCost.searchStepByStep();
			}
			
			if(result == -1)
				goButton.setEnabled(false);
			else if(result == 0)
				goButton.setEnabled(false);
			
			Main.grid.repaint();
		}

		//-------------------------------------/
		else if(e.getSource() == loadButton) {
			goButton.setEnabled(true);
			normalChoice.setEnabled(true);
			stepByStepChoice.setEnabled(true);
			automaticChoice.setEnabled(true);
			normalChoice.setEnabled(true);
			aStarChoice.setEnabled(true);
			bestFirstChoice.setEnabled(true);
			uniformCostChoice.setEnabled(true);
			
			String filePath = "C:\\Users\\jeanf\\Desktop\\ex1.txt";
			FileFunctions fileFunctions = new FileFunctions();
			fileFunctions.txtMaze(filePath);
			
			Maze maze = new Maze(fileFunctions.maze);
			int playerX = fileFunctions.initialColumn;
			int playerY = fileFunctions.initialRow;
			
			Main.grid.GRID_COUNT_W = maze.cols;
			Main.grid.GRID_COUNT_H = maze.rows;
			Main.grid.maze = maze;
			maze.resetStateCells();
			
			uniformCost = new UniformCost(playerX, playerY, maze);
			astar = new AStar(playerX, playerY, maze);
			bestFirst = new BestFirst(playerX, playerY, maze);
			Main.grid.repaint();
		}
	}

	
}
