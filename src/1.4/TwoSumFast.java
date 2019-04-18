import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

//用归并排序+二分查找实现，速度更快
public class TwoSumFast
{
    public static int count(int[] a)
    {
        //计算和为0的整数对的数目
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i =0; i < N ;i++)
        {
            if(BinarySearch.rank(-a[i], a) > i)
                cnt++;
        }
        return cnt;
    }
    public static void main(String[] args)
    {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}