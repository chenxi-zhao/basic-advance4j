package com.chenxi.structure.tree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉搜索树
 *
 * @param <T>
 * @author zhaochx1
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private BSTNode<T> mRoot; // 根结点

    private class BSTNode<K extends Comparable<K>> {
        K value;
        BSTNode<K> left;
        BSTNode<K> right;
        BSTNode<K> parent;

        public BSTNode(K value, BSTNode<K> parent, BSTNode<K> left, BSTNode<K> right) {
            this.value = value;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }
    }

    public int getMaxDepth(BSTNode root) {
        if (root == null)
            return 0;
        else {
            int left = getMaxDepth(root.left);
            int right = getMaxDepth(root.right);
            return 1 + Math.max(left, right);
        }
    }

    // 获取最大宽度/深度
    public int getMaxWidth(BSTNode root) {
        if (root == null)
            return 0;

        Queue<BSTNode> queue = new ArrayDeque<BSTNode>();
        int maxWitdth = 1; // 最大宽度
        int level = 0; // 求最大深度
        queue.add(root); // 入队

        // 一层一层遍历
        while (true) {
            int len = queue.size(); // 当前层的节点个数
            if (len == 0)
                break;
            while (len > 0) {// 如果当前层，还有节点
                BSTNode t = queue.poll();
                len--;
                if (t.left != null)
                    queue.add(t.left); // 下一层节点入队
                if (t.right != null)
                    queue.add(t.right);// 下一层节点入队
            }
            maxWitdth = Math.max(maxWitdth, queue.size());
            level++;
        }
        return maxWitdth;
    }

    /**
     * 访问节点
     */
    private void visit(BSTNode<T> node) {
        System.out.print(node.value + " ");
    }

    /**
     * 先序遍历整棵树
     */
    public void preOrder() {
        preOrder(mRoot);
    }

    /**
     * 从指定节点开始先序遍历节点子树
     *
     * @param tree
     */
    private void preOrder(BSTNode<T> tree) {
        if (tree != null) {
            visit(tree);
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 中序遍历整棵树
     */
    public void inOrder() {
        inOrder(mRoot);
    }

    /**
     * 从指定节点开始中序遍历节点子树
     *
     * @param tree
     */
    private void inOrder(BSTNode<T> tree) {
        if (tree != null) {
            inOrder(tree.left);
            visit(tree);
            inOrder(tree.right);
        }
    }

    /**
     * 后序遍历整棵树
     */
    public void postOrder() {
        postOrder(mRoot);
    }

    /**
     * 后序遍历节点子树
     *
     * @param tree
     */
    private void postOrder(BSTNode<T> tree) {
        if (tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            visit(tree);
        }
    }

    /**
     * 层次遍历、广度优先搜索
     *
     * @param root
     */
    public void levelTraverse(BSTNode<T> root) {
        if (root == null) {
            return;
        }
        LinkedList<BSTNode<T>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            BSTNode<T> node = queue.poll();
            visit(node);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
    }

    /**********************************************************************************************/
    /**********************************************************************************************/
    /**
     * 非递归实现前序遍历
     */
    public void iterativePreorder(BSTNode<T> node) {
        Stack<BSTNode<T>> stack = new Stack<>();
        if (node != null) {
            stack.push(node);
            while (!stack.empty()) {
                node = stack.pop();
                visit(node);
                if (node.right != null)
                    stack.push(node.right);
                if (node.left != null)
                    stack.push(node.left);
            }
        }
    }

    /**
     * 非递归实现前序遍历2
     */
    public void iterativePreorder2(BSTNode<T> p) {
        Stack<BSTNode<T>> stack = new Stack<>();
        BSTNode<T> node = p;
        while (node != null || stack.size() > 0) {
            // 压入所有的左节点，压入前访问它。左节点压入完后pop访问右节点。
            // 像这样算法时思考规律性的东西在哪。不管哪个节点都要压所节点判断右节点。
            while (node != null) {
                visit(node);
                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.right;
            }
        }
    }

    /*******************************************************************************************/
    /**
     * 非递归实现中序遍历
     */ // 思路与上面iterativePreorder 一致。
    public void iterativeInorder(BSTNode<T> node) {
        Stack<BSTNode<T>> stack = new Stack<>();
        while (node != null) {
            while (node != null) {
                if (node.right != null)
                    stack.push(node.right);// 当前节点右子入栈
                stack.push(node);// 当前节点入栈
                node = node.left;
            }
            node = stack.pop();
            while (!stack.empty() && node.right == null) {
                visit(node);
                node = stack.pop();
            }
            visit(node);
            if (!stack.empty())
                node = stack.pop();
            else
                node = null;
        }
    }

    /**
     * 非递归实现中序遍历2
     */
    public void iterativeInorder2(BSTNode<T> p) {
        Stack<BSTNode<T>> stack = new Stack<>();
        BSTNode<T> node = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                node = stack.pop();
                // 与iterativePreorder2比较只有这句话的位置不一样，弹出时再访问。
                visit(node);
                node = node.right;
            }
        }
    }

    /*******************************************************************************************/

    /**
     * 非递归实现后序遍历
     */
    public void iterativePostorder(BSTNode<T> node) {
        BSTNode<T> q = node;
        Stack<BSTNode<T>> stack = new Stack<>();
        while (node != null) {
            // 左子树入栈
            for (; node.left != null; node = node.left)
                stack.push(node);
            // 当前节点无右子或右子已经输出
            while (node != null && (node.right == null || node.right == q)) {
                visit(node);
                q = node;// 记录上一个已输出节点
                if (stack.empty())
                    return;
                node = stack.pop();
            }
            // 处理右子
            stack.push(node);
            node = node.right;
        }
    }

    /**
     * 非递归实现后序遍历 双栈法
     */
    public void iterativePostorder2(BSTNode<T> p) {// 理解左子树 右子树
        // 根递归性质，把它运用到循环当中去。
        Stack<BSTNode<T>> lstack = new Stack<>();// 左子树栈
        Stack<BSTNode<T>> rstack = new Stack<>();// 右子树栈
        BSTNode<T> node = p, right;
        do {
            while (node != null) {
                right = node.right;
                lstack.push(node);
                rstack.push(right);
                node = node.left;
            }
            node = lstack.pop();
            right = rstack.pop();
            if (right == null) {
                visit(node);
            } else {
                lstack.push(node);
                rstack.push(null);
            }
            node = right;
        } while (lstack.size() > 0 || rstack.size() > 0);
    }

    /**
     * 非递归实现后序遍历 单栈法
     */
    public void iterativePostorder3(BSTNode<T> p) {
        Stack<BSTNode<T>> stack = new Stack<>();
        BSTNode<T> node = p, prev = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            if (stack.size() > 0) {
                BSTNode<T> temp = stack.peek().right;
                if (temp == null || temp == prev) {
                    node = stack.pop();
                    visit(node);
                    prev = node;
                    node = null;
                } else {
                    node = temp;
                }
            }

        }
    }

    /**
     * 非递归实现后序遍历4 双栈法
     */
    public void iterativePostorder4(BSTNode<T> p) {
        Stack<BSTNode<T>> stack = new Stack<>();
        Stack<BSTNode<T>> temp = new Stack<>();
        BSTNode<T> node = p;
        while (node != null || stack.size() > 0) {
            while (node != null) {
                temp.push(node);
                stack.push(node);
                node = node.right;
            }
            if (stack.size() > 0) {
                node = stack.pop();
                node = node.left;
            }
        }
        while (temp.size() > 0) {// 把插入序列都插入到了temp。
            node = temp.pop();
            visit(node);
        }
    }

    /*******************************************************************************************/
    /*******************************************************************************************/

    /**
     * (递归实现)查找"二叉树x"中键值为key的节点
     */
    private BSTNode<T> recurSearch(BSTNode<T> node, T value) {
        if (node == null)
            return node;

        int cmp = value.compareTo(node.value);
        if (cmp < 0)
            return recurSearch(node.left, value);
        else if (cmp > 0)
            return recurSearch(node.right, value);
        else
            return node;
    }

    public BSTNode<T> recurSearch(T value) {
        return recurSearch(mRoot, value);
    }

    /**
     * (非递归实现)查找"二叉树x"中键值为key的节点
     */
    private BSTNode<T> iterativeSearch(BSTNode<T> node, T value) {
        while (node != null) {
            int cmp = value.compareTo(node.value);

            if (cmp < 0)
                node = node.left;
            else if (cmp > 0)
                node = node.right;
            else
                return node;
        }

        return node;
    }

    public BSTNode<T> iterativeSearch(T value) {
        return iterativeSearch(mRoot, value);
    }

    /**
     * 查找最大结点：返回tree为根结点的二叉树的最大结点。
     */
    private BSTNode<T> maximum(BSTNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.right != null)
            tree = tree.right;
        return tree;
    }

    public T maximum() {
        BSTNode<T> p = maximum(mRoot);
        if (p != null)
            return p.value;

        return null;
    }

    /**
     * 查找最小结点：返回tree为根结点的二叉树的最小结点。
     */
    private BSTNode<T> minimum(BSTNode<T> tree) {
        if (tree == null)
            return null;

        while (tree.left != null)
            tree = tree.left;
        return tree;
    }

    public T minimum() {
        BSTNode<T> p = minimum(mRoot);
        if (p != null)
            return p.value;

        return null;
    }

    /**
     * 找结点(x)的前驱结点。即，查找"二叉树中数据值小于该结点"的"最大结点"。
     */
    public BSTNode<T> predecessor(BSTNode<T> x) {
        // 如果x存在左孩子，则"x的前驱结点"为 "以其左孩子为根的子树的最大结点"。
        if (x.left != null)
            return maximum(x.left);

        // 如果x没有左孩子。则x有以下两种可能：
        // (01) x是"一个右孩子"，则"x的前驱结点"为 "它的父结点"。
        // (01) x是"一个左孩子"，则查找"x的最低的父结点，并且该父结点要具有右孩子"，找到的这个"最低的父结点"就是"x的前驱结点"。
        BSTNode<T> y = x.parent;
        while ((y != null) && (x == y.left)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 找结点(x)的后继结点。即，查找"二叉树中数据值大于该结点"的"最小结点"。
     */
    public BSTNode<T> successor(BSTNode<T> x) {
        // 如果x存在右孩子，则"x的后继结点"为 "以其右孩子为根的子树的最小结点"。
        if (x.right != null)
            return minimum(x.right);

        // 如果x没有右孩子。则x有以下两种可能：
        // (01) x是"一个左孩子"，则"x的后继结点"为 "它的父结点"。
        // (02) x是"一个右孩子"，则查找"x的最低的父结点，并且该父结点要具有左孩子"，找到的这个"最低的父结点"就是"x的后继结点"。
        BSTNode<T> y = x.parent;
        while ((y != null) && (x == y.right)) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 将结点插入到二叉树中
     * <p>
     * 参数说明： tree 二叉树的 z 插入的结点
     */
    private void insert(BinarySearchTree<T> bst, BSTNode<T> node) {
        int cmp;
        BSTNode<T> y = null;
        BSTNode<T> x = bst.mRoot;

        // 查找z的插入位置
        while (x != null) {
            y = x;
            cmp = node.value.compareTo(x.value);
            if (cmp < 0)
                x = x.left;
            else
                x = x.right;
        }

        node.parent = y;
        if (y == null)
            bst.mRoot = node;
        else {
            cmp = node.value.compareTo(y.value);
            if (cmp < 0)
                y.left = node;
            else
                y.right = node;
        }
    }

    /**
     * 插入结点(key)，并将其插入到二叉树中
     * <p>
     * 参数说明： tree 二叉树的根结点 key 插入结点的键值
     */
    public void insert(T key) {
        BSTNode<T> node = new BSTNode<T>(key, null, null, null);

        // 如果新建结点失败，则返回。
        if (node != null)
            insert(this, node);
    }

    /**
     * 删除结点(z)，并返回被删除的结点 删除一个节点要分为三种情况：
     * <p>
     * 1. 删除节点z没有孩子节点，那么就可以直接删除
     * <p>
     * 2. 删除节点z只有一个孩子节点，那么直接将z的孩子节点接到z的父节点下
     * <p>
     * 3. 删除节点z有两个孩子的情况，根据算法导论上的方法，先找到节点z的中序后继节点s，把节点s上的数据转移到节点z上，然后删除节点s，
     * 由于s节点为z节点的右子树总最左节点（因为s为z的后继节点），所以s无左子树，删除节点s就和上面情况2一样了。
     * <p>
     * 参数说明： bst 二叉树 z 删除的结点
     */
    private BSTNode<T> remove(BinarySearchTree<T> bst, BSTNode<T> node) {
        BSTNode<T> x = null;
        BSTNode<T> y = null;

        // 左右结点只要有一个为空，被删除节点就只有当前节点
        if ((node.left == null) || (node.right == null))
            y = node;
        else // 否则删除中序后继结点，替换被删除节点的值为中继节点的值
            y = successor(node);

        // 当前被删除节点（原删除节点或中继节点）取子节点
        if (y.left != null)
            x = y.left;
        else
            x = y.right;

        // x不为空时，将子节点的父节点替换为父节点
        if (x != null)
            x.parent = y.parent;

        // 被删除节点是根节点时，替换根节点为左右子树
        if (y.parent == null)
            bst.mRoot = x;
        else if (y == y.parent.left)// 删除节点是父节点的左子树
            y.parent.left = x;
        else
            y.parent.right = x;

        if (y != node)
            node.value = y.value;

        return y;
    }

    /**
     * 删除结点(z)，并返回被删除的结点
     * <p>
     * 参数说明： tree 二叉树的根结点 z 删除的结点
     */
    public void remove(T key) {
        BSTNode<T> node;

        if ((node = iterativeSearch(mRoot, key)) != null)
            if ((node = remove(this, node)) != null)
                node = null;
    }

    /**
     * 销毁二叉树
     */
    private void destroy(BSTNode<T> tree) {
        if (tree == null)
            return;

        if (tree.left != null)
            destroy(tree.left);
        if (tree.right != null)
            destroy(tree.right);
        tree = null;
    }

    public void clear() {
        destroy(mRoot);
        mRoot = null;
    }

    /**
     * 打印"二叉查找树"
     * <p>
     * value -- 节点的键值
     * <p>
     * direction -- 0，表示该节点是根节点; -1，表示该节点是它的父结点的左孩子; 1，表示该节点是它的父结点的右孩子。
     */
    private void print(BSTNode<T> tree, T value, int direction) {

        if (tree != null) {

            if (direction == 0) // tree是根节点
                System.out.printf("%2d is root\n", tree.value);
            else // tree是分支节点
                System.out.printf("%2d is %2d's %6s child\n", tree.value, value, direction == 1 ? "right" : "left");

            print(tree.left, tree.value, -1);
            print(tree.right, tree.value, 1);
        }
    }

    public void print() {
        if (mRoot != null)
            print(mRoot, mRoot.value, 0);
    }

    public static void main(String[] args) {
        int arr[] = {1, 5, 4, 3, 2, 6};
        int i, ilen;
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        System.out.print("== 依次添加: ");
        ilen = arr.length;
        for (i = 0; i < ilen; i++) {
            System.out.print(arr[i] + " ");
            tree.insert(arr[i]);
        }

        System.out.print("\n== 前序遍历: ");
        tree.preOrder();

        System.out.print("\n== 中序遍历: ");
        tree.inOrder();

        System.out.print("\n== 后序遍历: ");
        tree.postOrder();
        System.out.println();

        System.out.println("== 最小值: " + tree.minimum());
        System.out.println("== 最大值: " + tree.maximum());
        System.out.println("== 树的详细信息: ");
        tree.print();

        System.out.print("\n== 删除根节点: " + arr[3]);
        tree.remove(arr[3]);

        System.out.print("\n== 中序遍历: ");
        tree.inOrder();
        System.out.println();

        // 销毁二叉树
        tree.clear();
    }

}
