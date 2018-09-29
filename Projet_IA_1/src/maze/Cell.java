package maze;

import java.util.ArrayList;
import java.util.List;

public class Cell {

	public int row;
	public int column;
	public int type;					// 1 wall, -1 exit, 0 path
	public int fScore;					// For each node, the total cost of getting from the start node to the goal by passing by that node. That value is partly known, partly heuristic.	
	public int gScore;					// For each node, the cost of getting from the start node to that node.
	public int hScore;
	public Boolean visited;
	public List<Cell> neighbors;
	public Cell previous;    			// For each node, which node it can most efficiently be reached from. If a node can be reached from many nodes, cameFrom will eventually contain the most efficient previous step.
	public int state;					// State 0 nothing, 1 openSet, 2 in the closet, 3 is the path.


	public Cell(int row, int column, int type){
		this.row = row;
		this.column = column;
		this.type = type;
		this.fScore = 0;
		this.gScore = 0;
		this.hScore = 0;
		this.state = 0;
		this.visited = false;
		neighbors = new ArrayList<Cell>();
	}


	public void addNeighbors(Maze maze) {

		if(this.type != 1) {
			if(this.row < maze.rows - 1 && maze.cells[this.row + 1][this.column].type != 1)
				this.neighbors.add(maze.cells[this.row + 1][this.column]);
			
			if(this.row > 0 && maze.cells[this.row - 1][this.column].type != 1)
				this.neighbors.add(maze.cells[this.row - 1][this.column]);
			
			if(this.column < maze.cols - 1 && maze.cells[this.row][this.column + 1].type != 1)
				this.neighbors.add(maze.cells[this.row][this.column + 1]);
			
			if(this.column > 0 && maze.cells[this.row][this.column-1].type != 1)
				this.neighbors.add(maze.cells[this.row][this.column - 1]);
		}
	}
}
