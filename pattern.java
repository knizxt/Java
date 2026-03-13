import java.util.*;
public class pattern
{
   
    public static void print_pattern()
    {
         /* Function to print the pattern */
         
        for (int i = 5; i >= 1; i--) // loop for rows
{
    for (int j = 1; j <= i; j++) // loop for columns
    {
        System.out.print("*");
    }
    System.out.println(); // move to new line after each row is printed
}
       
        /* Do not change the code beyond this point*/
    }
     public static void main(String[]args)
    {
        Scanner sc=new Scanner(System.in);
        int n = sc.nextInt();
        print_pattern();
    }
}