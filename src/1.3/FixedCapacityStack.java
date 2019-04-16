import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class FixedCapacityStack<Item>
{
    private Item[] a;   //stack entries
    private int N;      //size
    public FixedCapacityStack(int cap)
    {
        a = (Item []) new Object [cap]; 
    }
    public boolean isEmpty()    { return N == 0;}
    public int size()   { return N;}
    private void resize(int max)
    {   //将大小为N <= max的栈移动到一个新的大小为max的数组中
        Item[] temp = (Item[]) new Object[max];
        for (int i = 0; i < N; i++)
            temp[i] = a[i];
        a = temp;
    }
    public void push(Item item)
    {   //将元素压入栈顶
        if ( N == a.length) resize(2 * a.length);
        a[N++] = item;
    }
    public Item pop()
    {   //从栈顶删除元素
        Item item = a[--N];
        a[N] = null; //避免对象游离
        if (N > 0 && N == a.length/4)   resize(a.length/2);
        return item;
    }
}
//java中类和main()看样子不能放在一个.java文件中
public static void main(String[] args)
{
    FixedCapacityStack<String> s;
    s = new FixedCapacityStack<String>(100);
    while(!StdIn.isEmpty())
    {
        String item = StdIn.readstring();
        if (!item.equals("-"))
            s.push(item);
        else if (!s.isEmpty())  StdOut.print(s.pop() + " ");
    }
    StdOut.println("(" + s.size() + " left on stack)");
}