import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

//选择排序算法，大约需要N^2次比较和N次交换，复杂度为O(N^2)
public class Selection implements Comparable
{
    public static void sort(Comparable[] a)
    {
        //将a[]按升序排列
        int N = a.length;
        for(int i = 0; i < N ;i++)
        {
            int min = i;
            for(int j = i+1; j < N;j++)
            {
                if (less(a[j], a[min])) min = j;
            }
            exch(a, i, min);
        }
    }
    public static boolean less(Comparable v, Comparable w)
    {
        return v.compareTo(w) < 0;
    }
    public static void exch(Comparable[] a, int i, int j)
    {
        Comparable t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
    private static void show(Comparable[] a)
    {
        //在单行中打印数组
        for(int i = 0; i < a.length; i++)
        {
            StdOut.print(a[i] + " ");
        }
        StdOut.println();
    }
    private static boolean isSorted(Comparable[] a)
    {
        //测试数组元素是否有序
        for(int i = 1; i < a.length; i++)
            if(less(a[i], a[i-1]))  return false;
        return true;
    }
    public static void main(String[] args)
    {
        //从标准输入读取字符串，将它们排序并输出
        String[] a = In.readStrings();
        sort(a);
        assert isSorted(a);
        show(a);
    }
}