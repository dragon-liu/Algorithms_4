import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

/*自顶向下的归并排序，即运用递归，要排序一列树，先排序左半部分，再排序右半部分，再归并起来。也就是把一个大的排序不断拆分，拆到最小就是1个数
自己排序，一个数自己排序我们可以不看，其实也就是变为2个数排序，排好的2组2个数再归并排序，以此类推，算法复杂度在O(NlgN),一般来说需要
1/2NlgN~NlgN次比较，最多需要访问数组6NlgN次*/
public class Merge implements Comparable
{
    public static Comparable[] aux; //归并所需的辅助数组
    
    public static void sort(Comparable[] a)
    {
       aux = new Comparable[a.lenghth];
       sort(a, 0, a.length - 1);
    }

    public static void sort(Comparable[] a, int lo, int hi)
    {
        //将数组a[lo...hi]排序
        if(hi <= lo)    return;
        int mid = lo + (hi - lo) / 2;
        sort(a, lo, mid);           //将左半边排序
        sort(a, mid+1, hi);         //将右半边排序
        merge(a, lo, mid, hi);      //归并结果
    }

    public static void merge(Comparable[] a, int lo, int mid, int hi)
    {   //将a[lo...mid]和a[mid+1...hi]归并
        int i = lo, j = mid + 1;

        for(int k = lo; k <= hi; k++)   //将a[lo...hi]复制到aux[lo...hi]
            aux[k] = a[k];
        
        for(int k =lo; k <= hi; k++)    //归并回到a[lo...hi]
        {
            if (i > mid)                    a[k] = aux[j++];
            else if(j > hi)                 a[k] = aux[i++];
            else if(less(aux[j], aux[i]))   a[k] = aux[j++];
            else                            a[k] = aux[i++];
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