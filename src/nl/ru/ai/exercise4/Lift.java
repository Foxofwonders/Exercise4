package nl.ru.ai.exercise4;

import java.util.ArrayList;

public class Lift 
{
	 public static int nrOfFails;
	 public static int nrOfPrunes;
	 

	public static void main(String[] args)
	  {
		ArrayList<Integer> s = new ArrayList<Integer>( ); 
	    int[] weights= { 30, 40, 41, 80, 90, 50, 55, 92, 66, 82, 62, 70};
	    int targetWeight=500;
	    int targetPeople=6;
	    int[] bestSolution = new int[targetPeople];
	    System.out.println("Total number of solutions: " + solutions(weights,0,targetWeight,targetPeople,s, bestSolution));
	    int bestSum=0;
		 for(int i =0;i<bestSolution.length;i++)
		 {
			 bestSum=bestSum+bestSolution[i];
		 }
		 System.out.println("Best Solution: ");
		 for (int i=0; i<bestSolution.length; i++)
		 {
			 System.out.print(bestSolution[i] + " ");
		 }
		 System.out.println(" = "+bestSum);
	    
	    System.out.println("Total number of Prunes: " + nrOfPrunes);
	    nrOfFails=0;
	    nrOfPrunes=0;
	    System.out.println("");
	  }

	/**
	 * Function returns the number of ways of creating the maximum possible target value as a group of people, 
	 * who don't exceed the max load and max number of 6 starting with current Person.
	 * @param weights
	 * @param currentPerson
	 * @param targetWeight
	 * @param targetPeople
	 * @param s
	 * @param bestSolution
	 * @return number of ways 
	 */
	 private static int solutions(int[] weights, int currentPerson, int targetWeight, int targetPeople, ArrayList<Integer> s, int[] bestSolution)
	  {
	    assert weights!=null : "array should be initialized";
	    assert currentPerson>=0 && currentPerson<=weights.length;
	    assert bestSolution!=null : "should be initialized";
	    assert s!=null : "should be initialized";
	    boolean pruneTest = true;
	    for(int i=currentPerson;i<weights.length;i++)
	    {
	    	if(weights[i] <= targetWeight)
	    	{
	    		pruneTest = false;
	    	}
	    }
	    if(targetWeight>=0 && targetPeople==0)
	    {
	    	showSolution(s, bestSolution);
		    return 1;
	    }
	    else if(pruneTest)
	    {
	    	nrOfPrunes++;
	    	return 0;
	    }
	    s.add(weights[currentPerson]);
	    int with = solutions(weights,currentPerson+1,targetWeight-weights[currentPerson],targetPeople-1, s, bestSolution);
	    s.remove(s.size()-1);
	    int without = solutions(weights,currentPerson+1,targetWeight,targetPeople, s, bestSolution);
	    
	    return with+without;
	  }
	 
	 /**
	  * Function displays all solutions(when not commented out) and finds the best Solution and saves it in an array
	  * @param s
	  * @param bestSolution
	  */
	 private static void showSolution( ArrayList<Integer> s, int[] bestSolution) 
	  {
		 assert s!=null: "Error while passing solution";
		 assert bestSolution!=null : "should be initialized";
		 int sum=0;
		 int bestSum=0;
		 for(int i =0;i<s.size();i++)
		 {
			 bestSum=bestSum+bestSolution[i];
			 sum=sum+s.get(i);
		 }
		 if(bestSum<sum)
		 {
			 for(int i = 0;i<s.size();i++)
			 {
				 bestSolution[i]=s.get(i);
				 
			 }
		 }
		 
		 // System.out.println("One possible solution: " + s + " = " + sum);
	  }

}

