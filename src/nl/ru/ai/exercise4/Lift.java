package nl.ru.ai.exercise4;

import java.util.ArrayList;

public class Lift 
{
	 public static int nrOfFails;
	 public static int nrOfPrunes;

	public static void main(String[] args)
	  {
		ArrayList<Integer> s = new ArrayList<Integer>( ); 
		ArrayList<Integer> bestSolution = new ArrayList<Integer>( );
		for(int i=0;i<6;i++)
		{
			bestSolution.add(0);
		}
	    int[] weights= { 30, 40, 41, 80, 90, 50, 55, 92, 66, 82, 62, 70};
	    int people = weights.length;
	    int targetWeight=500;
	    int targetPeople=6;
	    System.out.println("Total number of solutions: " + solutions(weights,people,0,targetWeight,targetPeople,s,bestSolution));
	    System.out.println("Best Solution: " + bestSolution);
	    System.out.println("Total number of Fails: " + nrOfFails);
	    System.out.println("Total number of Prunes: " + nrOfPrunes);
	    nrOfFails=0;
	    nrOfPrunes=0;
	    System.out.println("");
	  }

	 private static int solutions(int[] weights,int people, int currentPerson, int targetWeight, int targetPeople, ArrayList<Integer> s, ArrayList<Integer> highestValue)
	  {
	    assert weights!=null : "array should be initialized";
	    assert currentPerson>=0 && currentPerson<=weights.length;
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
	    	compareSolution(s,highestValue);
		    return 1;
	    }
	    else if(pruneTest)
	    {
	    	nrOfPrunes++;
	    	return 0;
	    }
	    
	    
	    
	    s.add(weights[currentPerson]);
	    int with = solutions(weights,people-1,currentPerson+1,targetWeight-weights[currentPerson],targetPeople-1, s,highestValue);
	    s.remove(s.size()-1);
	    int without = solutions(weights,people,currentPerson+1,targetWeight,targetPeople, s,highestValue);
	    
	    return with+without;
	  }
	 private static void compareSolution( ArrayList<Integer> s,ArrayList<Integer> bestSolution) 
	  {
		 int sumS=0;
		 int sumBestSolution=0;
		 for(int i =0;i<s.size();i++)
		 {
			 sumS=sumS+s.get(i);
			 sumBestSolution=sumBestSolution+bestSolution.get(i);
		 }
		  if(sumBestSolution<sumS)
		  {
			  bestSolution.clear();
			  bestSolution=s;
		  }
	  }

}


//else if(targetWeight<0)
//{
//	nrOfFails++;
//	return 0;
//}
//else if(targetPeople<0)
//{
//	nrOfFails++;
//	return 0;
//}
//else if(currentPerson>=weights.length)
//{
//	nrOfFails++;
//	return 0;
//}
