package pers.richard.leetcode.dailyquestion.sep22;

/**
 * @ClassName: NumSpecial04
 * @Description: 二进制矩阵中的特殊位置
 * 给你一个大小为 rows x cols 的矩阵 mat，其中 mat[i][j] 是 0 或 1，请返回 矩阵 mat 中特殊位置的数目 。
 * 特殊位置 定义：如果 mat[i][j] == 1 并且第 i 行和第 j 列中的所有其他元素均为 0（行和列的下标均 从 0 开始 ），则位置 (i, j) 被称为特殊位置。
 *
 *  
 *
 * 示例 1：
 * 输入：mat = [[1,0,0],
 *             [0,0,1],
 *             [1,0,0]]
 * 输出：1
 * 解释：(1,2) 是一个特殊位置，因为 mat[1][2] == 1 且所处的行和列上所有其他元素都是 0
 *
 * 示例 2：
 * 输入：mat = [[1,0,0],
 *             [0,1,0],
 *             [0,0,1]]
 * 输出：3
 * 解释：(0,0), (1,1) 和 (2,2) 都是特殊位置
 *
 * 示例 3：
 * 输入：mat = [[0,0,0,1],
 *             [1,0,0,0],
 *             [0,1,1,0],
 *             [0,0,0,0]]
 * 输出：2
 *  
 *
 * 提示：
 * rows == mat.length
 * cols == mat[i].length
 * 1 <= rows, cols <= 100
 * mat[i][j] 是 0 或 1
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-08 18:14
 */
public class NumSpecial04 {

    /**
     * 模拟
     * 思路与算法
     *
     * 题目给定了一个大小为 m×n 的矩阵 mat，并满足矩阵中的任意元素为 1 或者 0。
     * 现在给出特殊位置的定义：如果 mat[i][j]=1,i∈[0,m),j∈[0,n)
     * 并且第 i 行和第 j 列的其他元素均为 0，则位置(i,j) 为特殊位置。
     * 那么我们枚举每一个位置，然后按照特殊位置的定义来判断该位置是否满足要求，又因为矩阵中的每一个元素只能为 1 或者 0
     * 所以我们可以预处理出每一行和列的和来快速的得到每一行和列中的 1 的个数。
     *
     * @param mat
     * @return
     */
    public int numSpecial(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[] rowsSum = new int[m];
        int[] colsSum = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                rowsSum[i] += mat[i][j];
                colsSum[j] += mat[i][j];
            }
        }
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 1 && rowsSum[i] == 1 && colsSum[j] == 1) {
                    res++;
                }
            }
        }
        return res;
    }

}
