package pers.richard.leetcode.dailyquestion.sep22;

/**
 * @ClassName: Day08ConstructArray
 * @Description: 优美的排列 II
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 *
 * 示例 1：
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
 *
 * 示例 2：
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
 *  
 *
 * 提示：
 * 1 <= k < n <= 104
 * @Author: Richard_Chen
 * @Create: 2022-09-08 18:08
 */
public class Day08ConstructArray {

    /**
     *
     * 思路与算法
     * 当 k=1k=1 时，我们将 1 \sim n1∼n 按照 [1, 2, \cdots, n][1,2,⋯,n] 的顺序进行排列，那么相邻的差均为 11，满足 k=1k=1 的要求。
     *
     * 当 k=n-1k=n−1 时，我们将 1 \sim n1∼n 按照 [1, n, 2, n-1, 3, \cdots][1,n,2,n−1,3,⋯] 的顺序进行排列，那么相邻的差从 n-1n−1 开始，依次递减 11。这样一来，所有从 11 到 n-1n−1 的差值均出现一次，满足 k=n-1k=n−1 的要求。
     *
     * 对于其它的一般情况，我们可以将这两种特殊情况进行合并，即列表的前半部分相邻差均为 11，后半部分相邻差从 kk 开始逐渐递减到 11，这样从 11 到 kk 的差值均出现一次，对应的列表即为：
     *
     * [1,2,⋯,n−k,n,n−k+1,n−1,n−k+2,⋯]
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] answer = new int[n];
        int idx = 0;
        for (int i = 1; i < n - k; ++i) {
            answer[idx] = i;
            ++idx;
        }
        for (int i = n - k, j = n; i <= j; ++i, --j) {
            answer[idx] = i;
            ++idx;
            if (i != j) {
                answer[idx] = j;
                ++idx;
            }
        }
        return answer;
    }
}
