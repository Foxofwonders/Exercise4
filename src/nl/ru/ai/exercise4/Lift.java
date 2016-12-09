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
	    int people = weights.length;
	    int targetWeight=500;
	    int targetPeople=6;
	    System.out.println("Total number of solutions: " + solutions(weights,people,0,targetWeight,targetPeople,s));
	    System.out.println("Total number of Fails: " + nrOfFails);
	    System.out.println("Total number of Prunes: " + nrOfPrunes);
	    nrOfFails=0;
	    nrOfPrunes=0;
	    System.out.println("");
	  }

	 private static int solutions(int[] weights,int people, int currentPerson, int targetWeight, int targetPeople, ArrayList<Integer> s)
	  {
	    assert weights!=null : "array should be initialized";
	    assert currentPerson>=0 && currentPerson<=weights.length;
	    int sumOfRest=0;
	    boolean pruneTest = true;
	    for(int i=currentPerson;i<weights.length;i++)
	    {
	    	sumOfRest = sumOfRest+weights[i];
	    	if(weights[i] <= targetWeight)
	    	{
	    		pruneTest = false;
	    	}
	    }
	    if(targetWeight>=0 && targetPeople==0)
	    {
	    	showSolution(s);
		    return 1;
	    }
	    else if(pruneTest)
	    {
	    	nrOfPrunes++;
	    	return 0;
	    }
	    
	    
	    
	    s.add(weights[currentPerson]);
	    int with = solutions(weights,people-1,currentPerson+1,targetWeight-weights[currentPerson],targetPeople-1, s);
	    s.remove(s.size()-1);
	    int without = solutions(weights,people,currentPerson+1,targetWeight,targetPeople, s);
	    
	    return with+without;
	  }
	 private static void showSolution( ArrayList<Integer> s) 
	  {
		 int sum=0;
		 for(int i =0;i<s.size();i++)
		 {
			 sum=sum+s.get(i);
		 }
		  System.out.println("One possible solution: " + s + " = " + sum);
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
