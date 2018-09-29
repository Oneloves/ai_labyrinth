package ia.algorithms.pathFinding;

import java.util.ArrayList;
import java.util.List;

import maze.Cell;
import maze.Maze;

public class AStar {

	public int playerX;
	public int playerY;
	public Maze maze;
	public List<Cell> closedSet = new ArrayList<Cell>();
	public List<Cell> openSet  = new ArrayList<Cell>();


	public AStar(int playerX, int playerY, Maze maze) {
		this.playerX = playerX;
		this.playerY = playerY;
		this.maze = maze;
		openSet.add( maze.cells[playerX][playerY]);
	}


	public int heuristic(Cell a, Cell b) {
		int d = Math.abs(a.row - b.row) + Math.abs(a.column - b.column);
		return d;
	}


	public int bestOption(int winnerIndex) {
		for (int i=1; i<this.openSet.size(); i++) {
            if (this.openSet.get(i).fScore < this.openSet.get(winnerIndex).fScore)
            	winnerIndex = i;
            if (this.openSet.get(i).fScore == this.openSet.get(winnerIndex).fScore)
                if (this.openSet.get(i).gScore > this.openSet.get(winnerIndex).gScore)
                	winnerIndex = i;
		}
		return winnerIndex;
	}


	public void reconstructPath(Cell currentCell) {
		while(currentCell.previous != null) {
			currentCell.state = 3;
			currentCell = currentCell.previous;
		}
		maze.cells[playerX][playerY].state = 3;
	}


	public Boolean search() {
		int winnerIndex = bestOption(0);

		Cell currentCell = this.openSet.get(winnerIndex);

        if (currentCell.type == -1) {
        	reconstructPath(currentCell);
            return true;
        }
        
        this.openSet.remove(currentCell);
    	this.closedSet.add(currentCell);    
    	List<Cell> neighbors = currentCell.neighbors;    	
    	
		for (int index=0; index<neighbors.size(); index++) {
			Cell neighbor = neighbors.get(index);
		
			if (this.closedSet.contains(neighbor) == false) {
				
				int tempG = currentCell.gScore + this.heuristic(neighbor, currentCell);
				
				if (this.openSet.contains(neighbor) == false)
					this.openSet.add(neighbor);
				
				else if (tempG >= neighbor.gScore)
			        continue;
				
			    neighbor.gScore = tempG;
			    neighbor.hScore = this.heuristic(neighbor, maze.cells[5][14]);
			    neighbor.fScore = neighbor.gScore + neighbor.hScore;
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
