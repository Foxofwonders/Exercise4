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
		System.out.println("Initial Sudoko:");
		printPuzzle(puzzle);
		solve(puzzle);
		
		
	}
	/**
	 * reads Sudoko from file and saves it in an array
	 */
	private static void readFile(String filename, int[] puzzle) 
	{
		assert filename!=null : "Filename missing";
		assert puzzle!=null : "Puzzle should be initialized";
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
	/**
	 * Converts position in array to the row in the Sudoko
	 * @param pos
	 * @return row
	 */
	static int posToRow(int pos)
	{
		assert true;
		return pos/DIM; 
	}
	
	/**
	 * Converts position in array to the column in the Sudoko
	 * @param pos
	 * @return column
	 */
	static int posToCol(int pos)
	{
		assert true;
		return pos%DIM; 
	}
	
	/**
	 * Solves the Sudoko
	 * @param puzzle
	 */
	static void solve(int [ ] puzzle) 
	{
		assert puzzle!=null : "Puzzle should be initialized"; 
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
	
	/**
	 * Checks whether the Sudoko is valid according to the rules.
	 * @param puzzle
	 * @return validity
	 */
	private static boolean valid(int[] puzzle) 
	{
		assert puzzle!=null : "Puzzle should be initialized";
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
	
	/**
	 * Prints out a possible solution
	 * @param puzzle
	 */
	private static void dump(int[] puzzle) 
	{
		assert puzzle!=null : "Puzzle should be initialized";
		printPuzzle(puzzle);
	}
	
	/**
	 * Checks whether the elements of the array are in the same 3x3 box
	 * @param i
	 * @param j
	 * @return isInBox
	 */
	private static boolean isInBox(int i, int j) 
	{
		assert true;
		if(boxes[i]==boxes[j])
			return true;
		else
			return false;
	}
	
	/**
	 * Prints out Sudoko
	 * @param puzzle
	 */
	private static void printPuzzle(int[] puzzle) 
	{
		assert puzzle!=null : "Puzzle should be initialized";
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
	
	/**
	 * Finds the first "free"(=0) element of the array
	 * @param puzzle
	 * @return first free element
	 */
	private static int firstFree(int[] puzzle) 
	{
		assert puzzle!=null : "Puzzle should be initialized";
		for(int i=0;i<puzzle.length;i++)
		{
			if(puzzle[i]==0)
				return i;
		}
		return puzzle.length;
	}  
}
