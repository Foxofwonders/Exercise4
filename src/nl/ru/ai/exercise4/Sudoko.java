package nl.ru.ai.exercise4;

public class Sudoko 
{
	static final int DIM=9; 
	
	public static void main(String[] args)
	{
		int [ ] puzzle =new int[DIM*DIM];
		printPuzzle(puzzle);
		solve(puzzle);
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
	    dump(puzzle); 
	    else 
	    for(int digit=1;digit<=9;digit++) 
	    { 
	    	puzzle[free]=digit;
	    	// prepare 
	    	if(valid(puzzle)) 
	        solve(puzzle);
	    	// recurse
	    	puzzle[free]=0;
	    	// repair 
	    } 
	}
	private static boolean valid(int[] puzzle) {
		for(int i=0;i<puzzle.length;i++)
		{
			int row = posToRow(i);
			int col = posToCol(i);
			int value = puzzle[i];
			for(int j=0;j<puzzle.length;j++)
			{
				if(!(j==i))
				{
					if(row==posToRow(j)||col==posToCol(j))
						if(value==puzzle[j])
							return false;
				}
			}
		}
		return true;
	}
	
	private static void dump(int[] puzzle) {
		printPuzzle(puzzle);
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
