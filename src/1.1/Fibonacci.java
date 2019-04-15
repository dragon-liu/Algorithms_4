import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Fibonacci
{
    public static long F(int N)
    {
        if(N == 0)  return 0;
        if(N == 1)  return 1;
        return F(N-1) +F (N-2); 
    }
    public static void main(String[] args)
    {
        long [] a = new long [10];
        for(int N = 0; N < 10; N++)
        {
            a[N] = F(N); //一旦先存进数组，速度就会快很多，为什么
        }
        for(int N = 0; N < 10; N++)
        {
            StdOut.println(N +" "+F(N));
        }
    }
}