package com.chenxi.structure.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * Created by chenxi on 2017/8/9.
 * 图
 * <p>
 * 1. 如果图中任意两个顶点之间的边都是无向边（简而言之就是没有方向的边），则称该图为无向图（Undirected graphs）。
 * <p>
 * 2. 如果图中任意两个顶点之间的边都是有向边（简而言之就是有方向的边），则称该图为有向图（Directed graphs）。
 * <p>
 * 3-1. 在无向图中，如果任意两个顶点之间都存在边，则称该图为无向完全图。
 * <p>
 * 3-2. 在有向图中，如果任意两个顶点之间都存在方向互为相反的两条弧，则称该图为有向完全图。
 * <p>
 * 4. 当一个图接近完全图时，则称它为稠密图（Dense Graph），而当一个图含有较少的边时，则称它为稀疏图（Spare Graph）。
 * <p>
 * 5. 顶点vi的度（Degree）是指在图中与Vi相关联的边的条数。
 * <p>
 * 5-1. 对于有向图来说，有入度（In-degree）和出度（Out-degree）之分，有向图顶点的度等于该顶点的入度和出度之和。
 * <p>
 * 6. ①若无向图中的两个顶点V1和V2存在一条边(V1,V2)，则称顶点V1和V2邻接（Adjacent）；
 * <p>
 * 6. ②若有向图中存在一条边<V3,V2>，则称顶点V3与顶点V2邻接，且是V3邻接到V2或V2邻接直V3；
 * <p>
 * 7. 在无向图中，若从顶点Vi出发有一组边可到达顶点Vj，则称顶点Vi到顶点Vj的顶点序列为从顶点Vi到顶点Vj的路径（Path）。
 * <p>
 * 8. 有些图的边或弧具有与它相关的数字，这种与图的边或弧相关的数叫做权（Weight）。
 * <p>
 * 9. 若从Vi到Vj有路径可通，则称顶点Vi和顶点Vj是连通（Connected）的。
 *
 * @author chenxi
 */
public class AdjacencyListGraph<T> {

    /**
     * 边链表节点
     */
    private class EdgeNode {
        VertexNode<T> adjVertex; // 该边所指向的顶点的位置
        int weight; // 权值
        EdgeNode nextEdge; // 指向下一条弧

        public EdgeNode() {
        }

        public EdgeNode(VertexNode<T> adjVertex) {
            this.adjVertex = adjVertex;
        }

        public EdgeNode(VertexNode<T> adjVertex, int weight) {
            this.adjVertex = adjVertex;
            this.weight = weight;
        }
    }

    /**
     * 定点
     *
     * @param <K>
     */
    private class VertexNode<K> {
        K data; // 顶点信息
        EdgeNode firstEdge; // 指向第一条弧
        boolean isVisited = false;  // 访问标志：遍历时使用

        public VertexNode() {
        }

        public VertexNode(K data) {
            this.data = data;
        }
    }

    // 顶点数组
    private List<VertexNode<T>> mVexs = new ArrayList<>();


    /**
     * 查找元素
     *
     * @param item
     * @return
     */
    private VertexNode<T> find(T item) {
        for (VertexNode<T> mVex : this.mVexs) {
            if (mVex.data.equals(item)) {
                return mVex;
            }
        }
        return null;
    }

    /**
     * 是否包含节点
     *
     * @param item
     * @return
     */
    private boolean contains(T item) {
        for (VertexNode<T> mVex : this.mVexs) {
            if (mVex.data.equals(item)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 初始化访问状态
     */
    private void initVisited() {
        for (VertexNode<T> mVex : this.mVexs) {
            mVex.isVisited = false;
        }
    }

    /**
     * 添加节点
     *
     * @param item
     * @throws Exception
     */
    public void addVertex(T item) throws Exception {
        if (contains(item)) {
            throw new Exception("添加了重复的顶点！");
        }

        VertexNode<T> newVertex = new VertexNode<>(item);
        this.mVexs.add(newVertex);

    }

    /**
     * 添加边
     *
     * @param from
     * @param to
     * @throws Exception
     */
    public void addEdge(T from, T to, int weight) throws Exception {
        VertexNode<T> fromVertex = find(from);
        if (fromVertex == null) {
            throw new Exception("头顶点不存在！");
        }

        VertexNode<T> toVertex = find(to);
        if (toVertex == null) {
            throw new Exception("尾顶点不存在！");
        }

        // 无向图的两个顶点都需要记录边的信息
        addDirectedEdge(fromVertex, toVertex, weight);
        addDirectedEdge(toVertex, fromVertex, weight);
    }

    public void addEdge(T from, T to) throws Exception {
        addEdge(from, to, 1);
    }

    /**
     * 添加单向边
     *
     * @param fromVertex
     * @param toVertex
     * @throws Exception
     */
    private void addDirectedEdge(VertexNode<T> fromVertex, VertexNode<T> toVertex, int weight) throws Exception {
        if (fromVertex.firstEdge == null) {
            fromVertex.firstEdge = new EdgeNode(toVertex, weight);
        } else {
            EdgeNode temp;
            EdgeNode edgeNode = fromVertex.firstEdge;

            do {
                // 检查是否添加了重复边
                if (edgeNode.adjVertex.data.equals(toVertex.data)) {
                    throw new Exception("添加了重复的边！");
                }
                temp = edgeNode;
                edgeNode = edgeNode.nextEdge;
            } while (edgeNode != null);

            temp.nextEdge = new EdgeNode(toVertex);
        }
    }

    /**
     * 打印顶点和边
     *
     * @param isDirectedGraph
     * @return
     */
    public String getGraphInfo(boolean isDirectedGraph) {
        StringBuilder sb = new StringBuilder();
        for (VertexNode<T> vertexNode : this.mVexs) {
            sb.append(vertexNode.data.toString()).append(":");
            if (vertexNode.firstEdge != null) {
                EdgeNode temp = vertexNode.firstEdge;
                while (temp != null) {
                    if (isDirectedGraph) {
                        sb.append(vertexNode.data.toString()).append("→").append(temp.adjVertex.data.toString()).append(" ");
                    } else {
                        sb.append(temp.adjVertex.data.toString());
                    }
                    temp = temp.nextEdge;
                }
            }
            sb.append("\r\n");
        }

        return sb.toString();
    }

    /**
     * 深度优先遍历
     */
    public void deepFirstSearch() {
        initVisited();
        for (VertexNode<T> vertexNode : this.mVexs) {
            if (!vertexNode.isVisited) {
                deepFirstSearch(vertexNode);
            }
        }
        System.out.println();

    }

    private void deepFirstSearch(VertexNode<T> vertexNode) {
        visitedVertex(vertexNode);
        EdgeNode edgeNode = vertexNode.firstEdge;

        while (edgeNode != null) {
            if (!edgeNode.adjVertex.isVisited) {
                deepFirstSearch(edgeNode.adjVertex);
            }
            edgeNode = edgeNode.nextEdge;
        }

    }

    /**
     * 广度优先遍历
     */
    public void breadthFirstSearch() {
        initVisited();

        // 非连通图
        for (VertexNode<T> vertexNode : this.mVexs) {
            if (!vertexNode.isVisited) {
                breadthFirstSearch(vertexNode);
            }
        }
        System.out.println();
    }

    private void breadthFirstSearch(VertexNode<T> vertexNode) {
        visitedVertex(vertexNode);

        Queue<VertexNode<T>> queue = new ArrayDeque<>();
        queue.offer(vertexNode);

        while (queue.size() > 0) {
            VertexNode<T> tmpVertexNode = queue.poll();
            EdgeNode edgeNode = tmpVertexNode.firstEdge;
            // 访问此顶点的所有邻接节点
            while (edgeNode != null) {
                if (!edgeNode.adjVertex.isVisited) {

                    visitedVertex(edgeNode.adjVertex);

                    queue.offer(edgeNode.adjVertex);
                }
                edgeNode = edgeNode.nextEdge;
            }
        }

    }

    private void visitedVertex(VertexNode<T> vertexNode) {
        vertexNode.isVisited = true;
        System.out.print(vertexNode.data.toString() + " ");
    }


    public static void main(String[] args) throws Exception {
        AdjacencyListGraph<String> adjacencyListGraph = new AdjacencyListGraph<>();
        adjacencyListGraph.addVertex("V1");
        adjacencyListGraph.addVertex("V2");
        adjacencyListGraph.addVertex("V3");
        adjacencyListGraph.addVertex("V4");
        adjacencyListGraph.addVertex("V5");
        adjacencyListGraph.addVertex("V6");
        adjacencyListGraph.addVertex("V7");
        adjacencyListGraph.addVertex("V8");

        // 添加边
        adjacencyListGraph.addEdge("V1", "V2");
        adjacencyListGraph.addEdge("V1", "V3");
        adjacencyListGraph.addEdge("V2", "V4");
        adjacencyListGraph.addEdge("V2", "V5");
        adjacencyListGraph.addEdge("V3", "V6");
        adjacencyListGraph.addEdge("V3", "V7");
        adjacencyListGraph.addEdge("V4", "V8");
        adjacencyListGraph.addEdge("V5", "V8");
        adjacencyListGraph.addEdge("V6", "V8");
        adjacencyListGraph.addEdge("V7", "V8");
        adjacencyListGraph.deepFirstSearch();
        adjacencyListGraph.breadthFirstSearch();
    }

}
