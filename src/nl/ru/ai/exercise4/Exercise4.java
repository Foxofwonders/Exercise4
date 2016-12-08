package nl.ru.ai.exercise4;

public class Exercise4
{

  public static void main(String[] args)
  {
    int[] money0= {};
    int target0=0;
    System.out.println(solutions(money0,0,target0));
    
    int[] money1= { 2, 2, 2, 5, 10, 10, 20 };
    int target1=1;
    System.out.println(solutions(money1,0,target1));
    
    int[] money2= { 20, 10, 10, 5, 2, 2, 2 };
    int target2=42;
    System.out.println(solutions(money2,0,target2));
    
    int[] money3= { 20, 50, 1000, 1000, 2000 };
    int target3=2021;
    System.out.println(solutions(money3,0,target3));
  }
  /**
   * Returns the number of ways of creating specified target value as a sum of money starting with c
   * @param money
   * @param c
   * @param target
   * @return number of ways
   */
  private static int solutions(int[] money, int c, int target)
  {
    assert money!=null : "array should be initialized";
    assert c>=0&&c<=money.length;
    if(target==0)
      return 1;
    if(target<0)
      return 0;
    if(c>=money.length)
      return 0;
    int with=solutions(money,c+1,target-money[c]);
    int without=solutions(money,c+1,target);
    return with+without;
  }

}
