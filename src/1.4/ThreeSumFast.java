import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.BinarySearch;

import java.util.Arrays;

//用归并排序+二分查找实现，速度更快,即使在100万个数中进行查找，时间也在可接受范围内
public class TwoSumFast
{
    public static int count(int[] a)
    {
        //计算和为0的三元组的数目
        Arrays.sort(a);
        int N = a.length;
        int cnt = 0;
        for (int i =0; i < N ;i++)
        {
            for(int j = i+1; j<N;j++)
            {
                if(BinarySearch.rank(-a[i]-a[j], a) > j)
                cnt++;
            }
        }
        return cnt;
    }
    public static void main(String[] args)
    {
        int[] a = In.readInts(args[0]);
        StdOut.println(count(a));
    }
}