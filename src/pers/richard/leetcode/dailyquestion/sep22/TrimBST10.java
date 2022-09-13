package pers.richard.leetcode.dailyquestion.sep22;

/**
 * @ClassName: TrimBST10
 * @Description: 修剪二叉搜索树
 * 给你二叉搜索树的根节点 root ，同时给定最小边界low 和最大边界 high。通过修剪二叉搜索树，使得所有节点的值在[low, high]中。
 * 修剪树 不应该改变保留在树中的元素的相对结构 (即，如果没有被移除，原有的父代子代关系都应当保留)。 可以证明，存在 唯一的答案。
 * 所以结果应当返回修剪好的二叉搜索树的新的根节点。注意，根节点可能会根据给定的边界发生改变。
 *
 * 示例 1：
 *     0               1
 *   /  \     →        \
 *  0    2               2
 *
 * 输入：root = [1,0,2], low = 1, high = 2
 * 输出：[1,null,2]
 *
 *
 * 示例 2：
 *        3                       3
 *     /    \                   /
 *    0      4      →        2
 *      \                   /
 *       2                 1
 *     /
 *    1
 *
 * 输入：root = [3,0,4,null,2,null,null,1], low = 1, high = 3
 * 输出：[3,2,null,1]
 *  
 *
 * 提示：
 * 树中节点数在范围 [1, 104] 内
 * 0 <= Node.val <= 104
 * 树中每个节点的值都是 唯一 的
 * 题目数据保证输入是一棵有效的二叉搜索树
 * 0 <= low <= high <= 104
 * 通过次数96,025提交次数140,926
 *
 *
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-13 14:41
 */
public class TrimBST10 {

     public class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;

         TreeNode() {
         }

         TreeNode(int val) {
             this.val = val;
         }

         TreeNode(int val, TreeNode left, TreeNode right) {
             this.val = val;
             this.left = left;
             this.right = right;
         }
     }

    /**
     * 递归
     * 对根结点 root 进行深度优先遍历。
     * 对于当前访问的结点，如果结点为空结点，直接返回空结点；
     * 如果结点的值小于 low，那么说明该结点及它的左子树都不符合要求，我们返回对它的右结点进行修剪后的结果；
     * 如果结点的值大于 high，那么说明该结点及它的右子树都不符合要求，我们返回对它的左子树进行修剪后的结果；
     * 如果结点的值位于区间 [low,high]，我们将结点的左结点设为对它的左子树修剪后的结果，右结点设为对它的右子树进行修剪后的结果。
     *
     * @param root
     * @param low
     * @param high
     * @return
     */
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if (root == null) {
            return null;
        }
        if (root.val < low) {
            return trimBST(root.right, low, high);
        } else if (root.val > high) {
            return trimBST(root.left, low, high);
        } else {
            root.left = trimBST(root.left, low, high);
            root.right = trimBST(root.right, low, high);
            return root;
        }
    }
}
