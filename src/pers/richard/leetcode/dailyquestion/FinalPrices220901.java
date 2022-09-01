package pers.richard.leetcode.dailyquestion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName: FinalPrices
 * @Description: 商品折扣后的最终价格
 * 给你一个数组 prices ，其中 prices[i] 是商店里第 i 件商品的价格。
 * 商店里正在进行促销活动，如果你要买第 i 件商品，那么你可以得到与 prices[j] 相等的折扣
 * 其中 j 是满足 j > i 且 prices[j] <= prices[i] 的 最小下标 
 * 如果没有满足条件的 j ，你将没有任何折扣。
 *
 * 请你返回一个数组，数组中第 i 个元素是折扣后你购买商品 i 最终需要支付的价格。
 *
 * 示例 1：
 * 输入：prices = [8,4,6,2,3]
 * 输出：[4,2,4,2,3]
 * 解释：
 * 商品 0 的价格为 price[0]=8 ，你将得到 prices[1]=4 的折扣，所以最终价格为 8 - 4 = 4 。
 * 商品 1 的价格为 price[1]=4 ，你将得到 prices[3]=2 的折扣，所以最终价格为 4 - 2 = 2 。
 * 商品 2 的价格为 price[2]=6 ，你将得到 prices[3]=2 的折扣，所以最终价格为 6 - 2 = 4 。
 * 商品 3 和 4 都没有折扣。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：[1,2,3,4,5]
 * 解释：在这个例子中，所有商品都没有折扣。
 * 示例 3：
 *
 * 输入：prices = [10,1,1,6]
 * 输出：[9,0,1,6]
 *  
 *
 * 提示：
 * 1 <= prices.length <= 500
 * 1 <= prices[i] <= 10^3
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-01 11:57
 */
public class FinalPrices220901 {

    /**
     * 暴力双循环
     * 对于第 i 件商品的价格为 prices[i]，我们需要查找到相应可能的折扣。
     * 按照题目要求，我们从第 i+1 件商品开始依次向后遍历，直到找到第一个满足 prices[j]≤prices[i] 的下标 j 即可求出该物品的最终折扣价格。
     * 我们按照题目要求依次遍历即可。
     *
     * @param prices 价格数组
     * @return 最终价格数组
     */
    public int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] <= prices[i]){
                    prices[i] -= prices[j];
                    break;
                }
            }
        }
        return prices;
    }

    public int[] finalPricesA(int[] prices) {
        int n = prices.length;
        int[] ans = new int[n];
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!d.isEmpty() && prices[d.peekLast()] >= prices[i]) {
                int idx = d.pollLast();
                ans[idx] = prices[idx] - prices[i];
            }
            d.addLast(i); ans[i] = prices[i];
        }
        return ans;
    }
}
