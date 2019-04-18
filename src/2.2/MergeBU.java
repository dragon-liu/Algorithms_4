import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.In;

/*自底向上的归并排序，与自顶向下正相反，即先归并那些微型数组，然后再成对归并得到得到的子数组，以此类推。具体来说，就是先两两归并，再四四归并，
八八归并，一直下去，可能有时归并的第二个数组比第一个小，但对merge()方法不是问题，算法复杂度在O(NlgN),一般来说需要1/2NlgN~NlgN次比较，
最多需要访问数组6NlgN次*/
public class MergeBU implements Comparable
{
    public static Comparable[] aux; //归并所需的辅助数组
    
    public static void sort(Comparable[] a)
    {   //进行lgN次两两归并
       int N = a.length;
       aux = new Comparable[N];
       for(int sz = 1; sz < N; sz = sz+sz)                                      //sz子数组大小
       {    
           for(int lo = 0; lo < N - sz; lo += sz + sz)                          //lo:子数组索引
           {
               merge(a, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
           }
       }
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