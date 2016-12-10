package nl.ru.ai.exercise4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Sudoko 
{
	static final int DIM=9; 
	static final int[] boxes = {0,0,0,1,1,1,2,2,2,0,0,0,1,1,1,2,2,2,0,0,0,1,1,1,2,2,2,3,3,3,4,4,4,5,5,5,3,3,3,4,4,4,5,5,5,3,3,3,4,4,4,5,5,5,6,6,6,7,7,7,8,8,8,6,6,6,7,7,7,8,8,8,6,6,6,7,7,7,8,8,8};
	public static int nrOfPrunes;
	
	public static void main(String[] args)
	{
		int [ ] puzzle =new int[DIM*DIM];
		readFile("Sudoko.txt",puzzle);
		printPuzzle(puzzle);
		solve(puzzle);
		
		
	}
	
	private static void readFile(String filename, int[] puzzle) 
	{
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream(filename);
		    Scanner scanner=new Scanner(inputStream);
		    int i = 0;
		    while(scanner.hasNext())
		    {
		    	puzzle[i]=scanner.nextInt();
		    	i++;
		    }
		    scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error while reading file.");
			e.printStackTrace();
		}

	}

	static int toPostion(int row, int col)
	{
		return row*DIM+col; 
	}
	static int posToRow(int pos)
	{
		return pos/DIM; 
	}
	static int posToCol(int pos)
	{
		return pos%DIM; 
	}
	static void solve(int [ ] puzzle) 
	{
		int free=firstFree(puzzle); 
	    if(free==puzzle.length) 
	    {
			System.out.println("Solved: ");
	    	dump(puzzle); 
	    	System.out.println("Needed " + nrOfPrunes + " Prunes.");
	    	nrOfPrunes=0;
	    }
	    else 
	    {
		    for(int digit=1;digit<=9;digit++) 
		    { 
		    	puzzle[free]=digit; // prepare 
		    	if(valid(puzzle))
		    	{
		    		solve(puzzle); // recurse
		    	}
		    	puzzle[free]=0; // repair 
		    } 
	    }
	    
	}
	private static boolean valid(int[] puzzle) {
		
		for(int i=0;i<puzzle.length;i++)
		{
			for(int j=0;j<puzzle.length;j++)
			{
				if(!(j==i))
				{
					if(posToRow(i)==posToRow(j)||posToCol(i)==posToCol(j))
						if(puzzle[i]==puzzle[j]&&puzzle[i]!=0&&puzzle[j]!=0)
						{
							nrOfPrunes++;
							return false;
						}
					if(isInBox(i,j)&&puzzle[i]==puzzle[j]&&puzzle[i]!=0&&puzzle[j]!=0)
					{
						nrOfPrunes++;
						return false;
					}
							
				}
			}
		}
		return true;
	}
	
	private static void dump(int[] puzzle) {
		printPuzzle(puzzle);
	}
	
	private static boolean isInBox(int i, int j) 
	{
		if(boxes[i]==boxes[j])
			return true;
		else
			return false;
	}
	
	private static void printPuzzle(int[] puzzle) {
		for(int i=0;i<puzzle.length;i++)
		{
			if((i/DIM)>((i-1)/DIM))
			{
				System.out.println("");
				System.out.print((puzzle[i]) + " ");
			}
			else
			{
				System.out.print(puzzle[i]+" ");
			}
		}
		System.out.println("");
		System.out.println("");
		
	}
	
	private static int firstFree(int[] puzzle) {
		for(int i=0;i<puzzle.length;i++)
		{
			if(puzzle[i]==0)
				return i;
		}
		return puzzle.length;
	}  
}
