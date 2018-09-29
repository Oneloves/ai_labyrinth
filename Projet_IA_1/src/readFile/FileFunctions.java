package readFile;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;



public class FileFunctions {

	String[] strRows;
	String filePath;
	
	public int[][] maze;
	public int initialColumn;
	public int initialRow;
	public int nbColumn;
	public int nbRow;


	public FileFunctions(){}


	public void txtMaze(String filePath) {
		this.filePath = filePath;
		readFile();
		extractMaze();
	}


	private void readFile(){
		try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
			initialColumn = Integer.parseInt(br.readLine());
			initialRow = Integer.parseInt(br.readLine());
			nbColumn = Integer.parseInt(br.readLine());
			nbRow = Integer.parseInt(br.readLine());
			String curentLine;
			strRows = new String[nbRow];
			int index = 0;
			while ((curentLine = br.readLine()) != null){
				strRows[index] = curentLine;
				index++;
			}
		}catch(FileNotFoundException e){
			System.out.println("******  ERROR : File not found  ******");
		}catch(IOException e){
			System.out.println("******  ERROR : Unknow  ******");			
		}
		
	}


	private void extractMaze(){
		maze = new int[nbRow][nbColumn];
		String[] strSplit;
		String str;
		for(int rowIndex=0; rowIndex<strRows.length; rowIndex++){
			str = strRows[rowIndex].replaceAll("-1", "2");
			strSplit = str.split("");
			for(int charX=0; charX<strSplit.length; charX++){
				int number = Integer.parseInt(strSplit[charX]);
				if(number == 2)
					number = -1;
				maze[rowIndex][charX] = number;
			}
		}
	}
	
}














