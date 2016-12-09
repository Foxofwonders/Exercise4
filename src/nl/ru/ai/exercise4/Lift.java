package nl.ru.ai.exercise4;

import java.util.ArrayList;

public class Lift 
{
	 public static int nrOfFails;
	 public static int nrOfPrunes;

	public static void main(String[] args)
	  {
		ArrayList<Integer> s = new ArrayList<Integer>( ); 
	    int[] money0= { 30, 40, 41, 80, 90, 50, 55, 92, 66, 82, 62, 70};
	    int target0=0;
	    System.out.println("Total number of solutions: " + solutions(money0,0,target0,s));
	    System.out.println("Total number of Fails: " + nrOfFails);
	    System.out.println("Total number of Prunes: " + nrOfPrunes);
	    nrOfFails=0;
	    nrOfPrunes=0;
	    System.out.println("");
	  }

	private static String solutions(int[] money0, int i, int target0, ArrayList<Integer> s) {
		// TODO Auto-generated method stub
		return null;
	}

}
