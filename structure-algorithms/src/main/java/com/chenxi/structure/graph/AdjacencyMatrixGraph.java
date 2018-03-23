package com.chenxi.structure.graph;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxi on 2017/8/9.
 * <p>
 * 邻接矩阵
 *
 * @author chenxi
 */
public class AdjacencyMatrixGraph {
    private List<Object> mVexs;//存储点的链表
    private int[][] mMatrix;//邻接矩阵，用来存储边
    private int numOfEdges;//边的数目
    private static final int INF = Integer.MAX_VALUE;   // 最大值

    public AdjacencyMatrixGraph(int n) {
        //初始化矩阵，一维数组，和边的数目
        mMatrix = new int[n][n];
        mVexs = new ArrayList<>(n);
        numOfEdges = 0;
    }

    /**
     * 得到结点的个数
     *
     * @return
     */
    public int getNumOfVertex() {
        return mVexs.size();
    }

    /**
     * 得到边的数目
     *
     * @return
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回结点i的数据
     *
     * @param i
     * @return
     */
    public Object getValueByIndex(int i) {
        return mVexs.get(i);
    }

    /**
     * 返回v1,v2的权值
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getWeight(int v1, int v2) {
        return mMatrix[v1][v2];
    }

    /**
     * 插入结点
     *
     * @param vertex
     */
    public void insertVertex(Object vertex) {
        mVexs.add(mVexs.size(), vertex);
    }

    /**
     * 插入边
     *
     * @param v1
     * @param v2
     * @param weight
     */
    public void insertEdge(int v1, int v2, int weight) {
        mMatrix[v1][v2] = weight;
        mMatrix[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 删除结点
     *
     * @param v1
     * @param v2
     */
    public void deleteEdge(int v1, int v2) {
        mMatrix[v1][v2] = 0;
        numOfEdges--;
    }

    /**
     * 得到第一个邻接结点的下标
     *
     * @param index
     * @return
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < mVexs.size(); j++) {
            if (mMatrix[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接结点的下标来取得下一个邻接结点
     *
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < mVexs.size(); j++) {
            if (mMatrix[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /*
     * 深度优先搜索遍历图
     */
    public void DFS() {
        boolean[] visited = new boolean[mVexs.size()];       // 顶点访问标记

        // 初始化所有顶点都没有被访问
        for (int i = 0; i < mVexs.size(); i++)
            visited[i] = false;

        System.out.printf("DFS: ");
        for (int i = 0; i < mVexs.size(); i++) {
            if (!visited[i])
                DFS(i, visited);
        }
        System.out.printf("\n");
    }

    /*
     * 深度优先搜索遍历图的递归实现
     */
    private void DFS(int i, boolean[] visited) {

        visited[i] = true;
        System.out.printf("%s ", mVexs.get(i).toString());
        // 遍历该顶点的所有邻接顶点。若是没有访问过，那么继续往下走
        for (int w = getFirstNeighbor(i); w >= 0; w = getNextNeighbor(i, w)) {
            if (!visited[w])
                DFS(w, visited);
        }
    }

    /*
     * 广度优先搜索（类似于树的层次遍历）
     */
    public void BFS() {
        int head = 0;
        int rear = 0;
        int[] queue = new int[mVexs.size()];            // 辅组队列
        boolean[] visited = new boolean[mVexs.size()];  // 顶点访问标记

        for (int i = 0; i < mVexs.size(); i++)
            visited[i] = false;

        System.out.printf("BFS: ");
        for (int i = 0; i < mVexs.size(); i++) {

            // 遍历每一部分连通图的第一个节点并加入队列
            if (!visited[i]) {
                visited[i] = true;
                System.out.printf("%s ", mVexs.get(i).toString());
                queue[rear++] = i;  // 入队列
            }

            while (head != rear) {
                int j = queue[head++];  // 出队列
                for (int k = getFirstNeighbor(j); k >= 0; k = getNextNeighbor(j, k)) { //k是为访问的邻接顶点
                    if (!visited[k]) {
                        visited[k] = true;
                        System.out.printf("%s ", mVexs.get(k).toString());
                        queue[rear++] = k;
                    }
                }
            }
        }
        System.out.printf("\n");
    }

    /*
     * prim最小生成树
     *
     * 参数说明：
     *   start -- 从图中的第start个元素开始，生成最小树
     */
    public void prim(int start) {
        int num = mVexs.size();         // 顶点个数
        int index = 0;                    // prim最小树的索引，即prims数组的索引
        Object[] prims = new Object[num];  // prim最小树的结果数组
        int[] weights = new int[num];   // 顶点间边的权值


        // 初始化"顶点的权值数组"，
        // 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
        for (int i = 0; i < num; i++) {
            weights[i] = mMatrix[start][i];
            prims[i] = mVexs.get(0);
        }

        // 起始节点标记为已使用:-1代表已使用，后续不再判断
        weights[start] = -1;

        int sum = 0;
        for (int i = 1; i < num; i++) {

            int k = 0;
            int min = INF;
            // 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
            for (int j = 0; j < num; j++) {
                if (weights[j] > 0 && weights[j] < min) {
                    min = weights[j];
                    k = j;
                }

            }
            // 标志节点为已使用
            weights[k] = -1;

            System.out.println(MessageFormat.format("找到边({0},{1})权为：{2}", prims[k], mVexs.get(k), min));

            sum += min;

            // 如果集合U中有多个顶点与集合V中某一顶点存在边
            // 则选取最小权值边加入lowcost集合中
            for (int j = 0; j < num; j++) {
                // 将k节点相关节点的边权值与已有的边权值比较，如果小于就覆盖
                if (mMatrix[k][j] != 0 && (weights[j] == 0 || mMatrix[k][j] < weights[j])) {
                    weights[j] = mMatrix[k][j];
                    // 同步权值集合中某节点较小边权值为节点k
                    prims[j] = mVexs.get(k);
                }
            }
        }
    }


    private class Edge {
        private Object start;
        private Object end;
        private int weight;

        public Edge(Object start, Object end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    /**
     * 求最小树的Kruskal算法
     * 算法思想：克鲁斯卡尔算法从另一个途径求网中的最小生成树。假设联通网N=(V,{E})，则令
     * 最小生成树的厨师状态为只有n个顶点而无边的非连通图T=(V,{})，途中每个顶点自称一个连通分量。
     * 在E中选择代价最小的边，若该边衣服的顶点落在T中不同的连通分量上，则将此边加入到T中，否则舍去此边
     * 而选择下一条最小的边。以此类推，直至T中所有的顶点都在同一连通分量上为止。
     *
     */


    public void kruskal() {
        int mEdgNum = mVexs.size();
        int index = 0;                      // rets数组的索引
        int[] vends = new int[mEdgNum];     // 用于保存"已有最小生成树"中每个顶点在该最小树中的终点。
        Edge[] rets = new Edge[mEdgNum];  // 结果数组，保存kruskal最小生成树的边
        Edge[] edges;                      // 图对应的所有边

        // 获取"图中所有的边"
        edges = getEdges();
        // 将边按照"权"的大小进行排序(从小到大)
        sortEdges(edges, mEdgNum);

        for (int i = 0; i < mEdgNum; i++) {
            int p1 = getPosition(edges[i].start);      // 获取第i条边的"起点"的序号
            int p2 = getPosition(edges[i].end);        // 获取第i条边的"终点"的序号

            int m = getEnd(vends, p1);                 // 获取p1在"已有的最小生成树"中的终点
            int n = getEnd(vends, p2);                 // 获取p2在"已有的最小生成树"中的终点
            // 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
            if (m != n) {
                vends[m] = n;                       // 设置m在"已有的最小生成树"中的终点为n
                rets[index++] = edges[i];           // 保存结果
            }
        }


        // 统计并打印"kruskal最小生成树"的信息
        int length = 0;
        for (int i = 0; i < index; i++)
            length += rets[i].weight;
        System.out.printf("Kruskal=%d: ", length);
        for (int i = 0; i < index; i++)
            System.out.printf("(%c,%c) ", rets[i].start, rets[i].end);
        System.out.printf("\n");

    }

    /*
     * 获取i的终点
     */
    private int getEnd(int[] vends, int i) {
        while (vends[i] != 0)
            i = vends[i];
        return i;
    }

    /*
     * 对边按照权值大小进行排序(由小到大)
     */
    private void sortEdges(Edge[] edges, int elen) {

        for (int i = 0; i < elen; i++) {
            for (int j = i + 1; j < elen; j++) {
                if (edges[i].weight > edges[j].weight) {
                    // 交换"边i"和"边j"
                    Edge tmp = edges[i];
                    edges[i] = edges[j];
                    edges[j] = tmp;
                }
            }
        }
    }

    /*
     * 获取图中的边
     */
    private Edge[] getEdges() {
        int index = 0;
        Edge[] edges;

        edges = new Edge[mVexs.size()];
        for (int i = 0; i < mVexs.size(); i++) {
            for (int j = i + 1; j < mVexs.size(); j++) {
                if (mMatrix[i][j] != INF) {
                    edges[index++] = new Edge(mVexs.get(i), mVexs.get(i), mMatrix[i][j]);
                }
            }
        }

        return edges;
    }

    /*
     * 返回ch位置
     */
    private int getPosition(Object object) {
        for (int i = 0; i < mVexs.size(); i++)
            if (mVexs.get(i) == object)
                return i;
        return -1;
    }

    /*
     * 打印矩阵队列图
     */
    public void print() {
        System.out.printf("Martix Graph:\n");
        for (int i = 0; i < mVexs.size(); i++) {
            for (int j = 0; j < mVexs.size(); j++)
                System.out.printf("%10d ", mMatrix[i][j]);
            System.out.printf("\n");
        }
    }


    public static void main(String args[]) {
        int n = 8;//分别代表结点个数和边的数目
        String labels[] = {"V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8"};//结点的标识
        AdjacencyMatrixGraph graph = new AdjacencyMatrixGraph(n);
        for (String label : labels) {
            graph.insertVertex(label);//插入结点
        }
        graph.insertEdge(0, 1, 2);
        graph.insertEdge(0, 2, 5);
        graph.insertEdge(1, 3, 8);
        graph.insertEdge(1, 4, 7);
        graph.insertEdge(2, 5, 9);
        graph.insertEdge(2, 6, 4);
        graph.insertEdge(3, 7, 2);
        graph.insertEdge(4, 7, 4);
        graph.insertEdge(5, 7, 6);
        graph.insertEdge(6, 7, 15);

        graph.DFS();
        graph.BFS();

        graph.print();

        graph.prim(0);
    }
}
