import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/*这个算法的思路主要是每个id[]直接存触点所对应的分量，id[i]即得到第i个触点的分量，
主要缺点在于，每次union都要遍历一遍所有触点，对于大量数据不适用 */
public class union_find
{
    private int[] id;   //component id
    private int count;  //component number
    public union_find(int N)
    {
        //initialize id arrays
        count = N;
        id = new int[N];
        for(int i = 0;i<N;i++)
            id[i] = i;
    }
    public int count()
    {
        return count;
    }
    public boolean connected(int p, int q)
    {
        return find(p) == find(q);
    }
    public int find(int p) 
    {
        return id[p];
    }
    public void union(int p, int q)
    {
        //将p和q归并到相同的分量中
        int pID = find(p);
        int qID = find(q);

        //若p和q已在相同分量中，则不需采取任何行动
        if (pID == qID) return;

        //将p的分量重命名为q的名称
        for(int i = 0;i<id.length;i++)
        {
            if (id[i] == pID) id[i] = qID;
        }
        count--;
    }
    public static void main(String[] args)
    {
        //解决由StdIn得到的动态连通性问题
        int N = StdIn.readInt();                //读取触点数量
        union_find uf = new union_find(N);      //初始化N个分量
        while(!StdIn.isEmpty())
        {
            int p = StdIn.readInt();
            int q = StdIn.readInt();            //读取整数对
            if(uf.connected(p, q))  continue;   //如果已连通则忽略
            uf.union(p, q);                     //归并分量
            StdOut.println(p + " " + q);        //打印连接
        }
        StdOut.println(uf.count() + "components");
    }
}