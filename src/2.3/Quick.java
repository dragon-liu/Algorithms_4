import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;

/*快速排序算法，主要2特点是原地排序(只需要一个很小的辅助栈),且将长度为N的数组排序所需要的时间和NlgN成正比 */

public class Quick implements Comparable
{
    public static void sort(Comparable[] a)
    {
        StdRandom.shuffle(a);       //消除对输入的依赖
        sort(a, 0 , a.length - 1);
    } 

    public static void sort(Comparable[] a, int lo, int hi)
    {
        if(hi <= lo)    return;
        int j = partition(a, lo, hi);   //切分
        sort(a, lo, j - 1);             //将左半部分a[lo...j-1]排序
        sort(a, j + 1, hi);             //将右半部分a[j+1...hi]排序
    }

    public static int partition(Comparable[] a, int lo, int hi)
    {   //将数组切分为a[lo...hi], a[i], a[i+1...hi]
        int i = lo, j =hi + 1;      //左右扫描指针
        Comparable v = a[lo];       //切分元素
        while(true)
        {   //扫描左右，检查扫描是否结束并交换元素
            while(less(a[++i], v))  if(i == hi) break;
            while(less(v, a[--j]))  if(j == lo) break;
            if(i > j)   break;
            exch(a, i, j);
        }
        exch(a, lo, j);     //将v=a[j]放入正确的位置
        return j;           //a[lo...j-1] <= a[j] <=a[j+1...hi]达成
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