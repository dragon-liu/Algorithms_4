import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;

public class BinarySearchDp
{
    public static int i =0;
    public static int rank(int key, int [] a)
    {
        return rank(key, a, 0, a.length - 1);
    }
    public static int rank(int key, int [] a, int lo, int hi)
    {
        if (lo > hi)    return -1;
        int mid =lo +(hi - lo) / 2;
        if (key < a[mid])
        {
            hi = mid - 1;
            for(int j = 0; j < i; j++)
                StdOut.print(' ');
            StdOut.println((i++) + ' ' + lo + ' ' + hi);
            return rank(key, a, lo, hi);
        }   
        else if(key > a[mid])
        {
            lo = mid + 1;
            for(int j = 0; j < i; j++)
                StdOut.print(' ');
            StdOut.println((i++) + ' ' + lo + ' ' + hi);
            return rank(key, a, lo, hi);
        }   
        else return mid;
    }
    public static void main(String[] args) {

        // read the integers from a file
        In in = new In(args[0]);
        int[] whitelist = in.readAllInts();

        // sort the array
        Arrays.sort(whitelist);

        // read integer key from standard input; print if not in whitelist
        while (!StdIn.isEmpty()) {
            int key = StdIn.readInt();
            if (BinarySearchDp.rank(key, whitelist) == -1)
                StdOut.println(key);
        }
    }
}