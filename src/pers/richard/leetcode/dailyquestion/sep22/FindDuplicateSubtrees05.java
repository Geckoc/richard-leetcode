package pers.richard.leetcode.dailyquestion.sep22;

import javafx.util.Pair;

import java.util.*;

/**
 * @ClassName: FindDuplicateSubtrees05
 * @Description: 寻找重复的子树
 * 给定一棵二叉树 root，返回所有重复的子树。
 *
 * 对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 *
 * 如果两棵树具有相同的结构和相同的结点值，则它们是重复的。
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-05 18:25
 */
public class FindDuplicateSubtrees05 {


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
    Map<String, Pair<TreeNode, Integer>> seen = new HashMap<String, Pair<TreeNode, Integer>>();
    Set<TreeNode> repeat = new HashSet<TreeNode>();
    int idx = 0;

    /**
     * 使用三元组进行唯一表示
     * 思路与算法
     *
     * 通过方法一中的递归序列化技巧，我们可以进一步进行优化。
     *
     * 我们可以用一个三元组直接表示一棵子树，即 (x, l, r)(x,l,r)，它们分别表示：
     *
     * 根节点的值为 xx；
     *
     * 左子树的序号为 ll；
     *
     * 右子树的序号为 rr。
     *
     * 这里的「序号」指的是：每当我们发现一棵新的子树，就给这棵子树一个序号，用来表示其结构。那么两棵树是重复的，当且仅当它们对应的三元组完全相同。
     *
     * 使用「序号」的好处在于同时减少了时间复杂度和空间复杂度。方法一的瓶颈在于生成的序列会变得非常长，而使用序号替换整个左子树和右子树的序列，可以使得每一个节点只使用常数大小的空间。
     * @param root
     * @return
     */
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        dfs(root);
        return new ArrayList<TreeNode>(repeat);
    }

    public int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int[] tri = {node.val, dfs(node.left), dfs(node.right)};
        String hash = Arrays.toString(tri);
        if (seen.containsKey(hash)) {
            Pair<TreeNode, Integer> pair = seen.get(hash);
            repeat.add(pair.getKey());
            return pair.getValue();
        } else {
            seen.put(hash, new Pair<TreeNode, Integer>(node, ++idx));
            return idx;
        }
    }
}
