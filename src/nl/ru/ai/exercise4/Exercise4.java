package nl.ru.ai.exercise4;

import java.util.ArrayList;

public class Exercise4
{
	/* 
	1b:
	One possible solution: []
	Total number of solutions: 1
	Total number of Fails: 0
	
	Total number of solutions: 0
	Total number of Fails: 8
	
	One possible solution: [20, 10, 10, 2]
	One possible solution: [20, 10, 10, 2]
	One possible solution: [20, 10, 10, 2]
	Total number of solutions: 3
	Total number of Fails: 114
	
	Total number of solutions: 0
	Total number of Fails: 30

	 */
	
	/* 
	1c:
	One possible solution: []
	Total number of solutions: 1
	Total number of Fails: 0
	Total number of Prunes: 0
	
	Total number of solutions: 0
	Total number of Fails: 0
	Total number of Prunes: 1
	
	One possible solution: [20, 10, 10, 2]
	One possible solution: [20, 10, 10, 2]
	One possible solution: [20, 10, 10, 2]
	Total number of solutions: 3
	Total number of Fails: 0
	Total number of Prunes: 5
	
	Total number of solutions: 0
	Total number of Fails: 0
	Total number of Prunes: 14
	 */
	
	/* 
	1d:
	A lot of fails can be prevented by a single prune, like in the example money1.
	In 1b it takes the function 8 fails to know, that there is no solution. 
	But with the test it detects instantly, that the smallest value of untried coins is still bigger than the target, 
	so it only needs one prune and is hence more efficient. Example 3 is even more extreme: Because the paths get pruned,
	the number of fails decreases from 114 to 2.
	So Pruning prevents the function from following "doomed" paths.
	
	The number of prunes versus the number of change mainly depends on the order of the prune test and the 'fail test'.
	Both would return, so which is first in line, will win. If prunes are done correctly, you'll never have fails. This is because
	both prune rules are virtually the same as the 'fail triggering' if statements.
	 */

  public static int nrOfFails;
  public static int nrOfPrunes;
 
  public static void main(String[] args)
  {
	ArrayList<Integer> s = new ArrayList<Integer>( ); 
    int[] money0= {};
    int target0=0;
    System.out.println("Total number of solutions: " + solutions(money0,0,target0,s));
    System.out.println("Total number of Fails: " + nrOfFails);
    System.out.println("Total number of Prunes: " + nrOfPrunes);
    nrOfFails=0;
    nrOfPrunes=0;
    System.out.println("");
    
    int[] money1= { 2, 2, 2, 5, 10, 10, 20 };
    int target1=1;
    System.out.println("Total number of solutions: " + solutions(money1,0,target1,s));
    System.out.println("Total number of Fails: " + nrOfFails);
    System.out.println("Total number of Prunes: " + nrOfPrunes);
    nrOfFails=0;
    nrOfPrunes=0;
    System.out.println("");
    
    int[] money2= { 20, 10, 10, 5, 2, 2, 2 };
    int target2=42;
    System.out.println("Total number of solutions: " + solutions(money2,0,target2,s));
    System.out.println("Total number of Fails: " + nrOfFails);
    System.out.println("Total number of Prunes: " + nrOfPrunes);
    nrOfFails=0;
    nrOfPrunes=0;
    System.out.println("");
    
    int[] money3= { 20, 50, 1000, 1000, 2000 };
    int target3=2021;
    System.out.println("Total number of solutions: " + solutions(money3,0,target3,s));
    System.out.println("Total number of Fails: " + nrOfFails);
    System.out.println("Total number of Prunes: " + nrOfPrunes);
    nrOfFails=0;
    nrOfPrunes=0;
  }
  
  /**
   * Returns the number of ways of creating specified target value as a sum of money starting with c
   * @param money
   * @param c
   * @param target
   * @param s
   * @return number of ways
   */
  private static int solutions(int[] money, int c, int target, ArrayList<Integer> s)
  {
    assert money!=null : "array should be initialized";
    assert c>=0 && c<=money.length;
    int sumOfRest=0;
    boolean pruneTest = true;
    for(int i=c;i<money.length;i++)
    {
    	sumOfRest = sumOfRest+money[i];
    	if(money[i] <= target)
    	{
    		pruneTest = false;
    	}
    }
    if(target==0)
    {
    	showSolution(s);
	    return 1;
    }
    else if(pruneTest||sumOfRest<target)
    {
    	nrOfPrunes++;
    	return 0;
    }
    else if(target<0)
    {
    	nrOfFails++;
    	return 0;
    }
    
    else if(c>=money.length)
    {
    	nrOfFails++;
    	return 0;
    }
    
    s.add(money[c]);
    int with = solutions(money,c+1,target-money[c],s);
    s.remove(s.size()-1);
    int without = solutions(money,c+1,target,s);
    
    return with+without;
  }
  /**
   * Prints the possible solution.
   * @param s
   */
  private static void showSolution( ArrayList<Integer> s) 
  {
	  assert true;
	  System.out.println("One possible solution: " + s);
  }

}
