package nl.ru.ai.exercise4;

public class Sudoko 
{
	static final int DIM=9; 
	
	public static void main(String[] args)
	  {
		int [ ] puzzle =new int[DIM*DIM]; 
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
		// TODO Auto-generated method stub
		return false;
	}
	private static void dump(int[] puzzle) {
		// TODO Auto-generated method stub
		
	}
	private static int firstFree(int[] puzzle) {
		// TODO Auto-generated method stub
		return 0;
	}  
}
