package ia.algorithms.pathFinding;

import java.util.ArrayList;
import java.util.List;

import maze.Cell;
import maze.Maze;

public class BestFirst {

	int playerX;
	int playerY;
	Maze maze;
	List<Cell> closedSet = new ArrayList<Cell>();
	List<Cell> openSet  = new ArrayList<Cell>();
	
	
	public BestFirst(int playerX, int playerY, Maze maze) {
		this.playerX = playerX;
		this.playerY = playerY;
		this.maze = maze;
		openSet.add(maze.cells[playerX][playerY]);
	}
	
	
	public int heuristic(Cell a, Cell b) {
		int d = Math.abs(a.row - b.row) + Math.abs(a.column - b.column);
		return d;
	}


	public void reconstructPath(Cell currentCell) {
		while(currentCell.previous != null) {
			currentCell.state = 3;
			currentCell = currentCell.previous;
		}
		maze.cells[playerX][playerY].state = 3;
	}


	public int bestOption(int winnerIndex) {
		for (int i=1; i<this.openSet.size(); i++) {
            if (this.openSet.get(i).hScore < this.openSet.get(winnerIndex).hScore)
            	winnerIndex = i;
		}
		return winnerIndex;
	}


	public Boolean search() {
		int winnerIndex = bestOption(0);
		Cell currentCell = this.openSet.get(winnerIndex);

        this.openSet.remove(currentCell);
    	this.closedSet.add(currentCell);   
    	
        if (currentCell.type == -1) {
        	reconstructPath(currentCell);
            return true;
        }
        
    	List<Cell> neighbors = currentCell.neighbors;
	
		for (int index=0; index<neighbors.size(); index++) {
			Cell neighbor = neighbors.get(index);
			
			if (this.closedSet.contains(neighbor) == false) {
				
				int tmpH = this.heuristic(neighbor, maze.cells[5][14]);
				
				if (this.openSet.contains(neighbor) == false)
					openSet.add(neighbor);
				
				else if (tmpH >= neighbor.hScore)
			        continue;
				
			    neighbor.hScore = tmpH;
			    neighbor.previous = currentCell;
			}
			
		}
			
		for(int i=0; i<closedSet.size(); i++)
			closedSet.get(i).state = 2;
		
		for(int i=0; i<openSet.size(); i++)
			openSet.get(i).state = 1;
		
		return false;
	}


	public int searchComplete() {
		boolean bool = false;
		while(openSet.isEmpty() == false && bool == false)
			bool = search();
		if(openSet.isEmpty())
			return -1;
		return 0;
	}
	
	
	public int searchStepByStep() {
		boolean bool = false;
		if(openSet.isEmpty() == false) {
			bool = search();
			if(bool)
				return 0;
			return 1;
		}
		else
			return -1;
	}
	
	
}
