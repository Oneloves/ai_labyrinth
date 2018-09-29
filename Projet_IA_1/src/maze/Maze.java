package maze;



public class Maze {

	public Cell[][] cells;
	public int rows;
	public int cols;
	
	
	public Maze(int[][] maze){
		this.rows = maze.length;
		this.cols = maze[0].length;
		this.cells = new Cell[rows][cols];
		for(int x=0; x<rows; x++)
			for(int y=0; y<cols; y++){
				Cell cell = new Cell(x, y, maze[x][y]);
				this.cells[x][y] = cell;
			}
		for(int x=0; x<rows; x++)
			for(int y=0; y<this.cols; y++){
				this.cells[x][y].addNeighbors(this);
			}
	}
	
	

	public void resetStateCells(){
		for(int x=0; x<rows; x++){
			for(int y=0; y<cols; y++){
				if(cells[x][y].state != 0)
				cells[x][y].state = 0;
			}
		}
	}
	
}
