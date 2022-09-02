package pers.richard.leetcode.dailyquestion;

/**
 * @ClassName: LongestUnivaluePath220902
 * @Description: 最长同值路径
 * 给定一个二叉树的root，返回最长的路径的长度 ，这个路径中的每个节点具有相同值。
 * 这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * 示例 1:
 *            5
 *          /  \
 *         4    5
 *       /  \    \
 *      1    1    5
 *  输入：root = [5,4,5,1,1,5]
 *  输出：2
 *
 * 示例 2:
 *           1
 *         /  \
 *       4     5
 *     /  \     \
 *    4   4      5
 * 输入：root = [1,4,5,4,4,5]
 * 输出：2
 *
 * 提示:
 *  树的节点数的范围是 [0, 104]
 *  -1000 <= Node.val <= 1000
 *  树的深度将不超过 1000
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-02 10:37
 */
public class LongestUnivaluePath220902 {

    /**
     * Definition for a binary tree node.
     */
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

    int result = 0;

    /**
     * 方法:深度优先搜索
     *
     * 我们将二叉树看成一个有向图(从父结点指向子结点的边)，定义同值有向路径为从某一结点出发，
     * 到达它的某一后代节点的路径，且经过的结点的值相同。
     * 最长同值路径长度必定为某节点的左最长同值有向路径长度与右最长同值有向路径长度之和。
     * 使用变量res保存最长同值路径长度。我们对根结点进行深度优先搜索，对于当前搜索的结点root,
     * 我们分别获取它左结点的最长同值有向路径长度left,右结点的最长同值有向路径长度right。
     * 如果结点root的左结点非空且结点root的值与它的左结点的值相等，
     * 那么结点root的左最长同值有向路径长度lefti = left + 1,
     * 否则lefti = 0;如果结点root的右结点非空且结点root的值与它的右结点的值相等，
     * 那么结点root的右最长同值有向路径长度right1 = right+1,
     * 否则right1 =0。令res = max(res, lefti + right),并且返回结点
     * root对应的最长同值有向路径长度max(left, right)。
     * @param root
     * @return
     */
    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return result;
    }
    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int leftValue = dfs(node.left);
        int rightValue = dfs(node.right);
        leftValue = (node.left != null && node.val == node.left.val) ? ++leftValue : 0;
        rightValue = (node.right != null && node.val == node.right.val) ? ++rightValue : 0;
        result = Math.max(result, leftValue + rightValue);
        return Math.max(leftValue, rightValue);
    }
}
