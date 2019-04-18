import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;

/*这个算法的思路主要是每个触点所对应的id[]元素都是同一个分量中另一个触点的名称（也可能是它自己），我们将这种联系称为连接。要注意有一个不同，
其实每一个分量就是一棵树，加权的意思就是我们总是把较小的树连接到较大的树上，这需要添加一个数组和一些代码来记录树中的节点树，
在实现find()时，我们从给定触点开始，一直找到根触点(即指向它自己的触点)，这样一个触点必定存在，当且仅当分别由2个触点开始的这个过程到达
同一个根触点时它们存在于同一个连通分量中。union()则是将2个触点的根触点变为1个 */
public class WeightedQuickUnionUF
{
    private int[] id;   //component id
    private int[] sz;   //(由触点索引的)各个根节点所对应的分量大小
    private int count;  //component number
    public WeightedQuickUnionUF(int N)
    {
        //initialize id arrays
        count = N;
        id = new int[N];
        for(int i = 0;i<N;i++)
            id[i] = i;
        sz = new int[N];
        for (int i = 0; i <N ;i++)
            sz[i] = 1;
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
        //跟随连接找到根节点
        while(p != id[p])   p = id[p];
        return p;
    }
    public void union(int p, int q)
    {
        //将p和q的根节点统一
        int i = find(p);
        int j = find(q);

        //若p和q已在相同分量中，则不需采取任何行动
        if (i == j) return;

        //将小树的节点连接到大树的节点
        if (sz[i] < sz[j]) {id[i] = j;sz[j] += sz[i];}
        else               {id[j] = i;sz[i] += sz[j];}
        count--;
    }
    public static void main(String[] args)
    {
        //解决由StdIn得到的动态连通性问题
        int N = StdIn.readInt();                //读取触点数量
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);      //初始化N个分量
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