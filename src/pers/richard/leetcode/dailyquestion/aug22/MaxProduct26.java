package pers.richard.leetcode.dailyquestion.aug22;

import java.util.Arrays;

/**
 * @ClassName: maxProduct220826
 * @Description: 数组中两元素的最大乘积
 * 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 *
 * 示例 1：
 *  输入：nums = [3,4,5,2]
 *  输出：12
 *  解释：如果选择下标 i=1 和 j=2（下标从 0 开始），则可以获得最大值，(nums[1]-1)*(nums[2]-1) = (4-1)*(5-1) = 3*4 = 12 。
 * 示例 2：
 *  输入：nums = [1,5,4,5]
 *  输出：16
 *  解释：选择下标 i=1 和 j=3（下标从 0 开始），则可以获得最大值 (5-1)*(5-1) = 16 。
 * 示例 3：
 *  输入：nums = [3,7]
 *  输出：12
 *  
 *
 * 提示：
 *  2 <= nums.length <= 500
 *  1 <= nums[i] <= 10^3
 * @Author: Richard_Chen
 * @Create: 2022-08-26 15:03
 */
public class MaxProduct26 {

    /**
     * 一次循环，求最大值、次最大值
     * @param nums 整数数组
     * @return 最大乘积
     */
    public int maxProduct1(int[] nums) {
        int x = nums[0], y = nums[1];
        if (x < y) {
            int temp = y;
            y = x;
            x = temp;
        }
        for (int i = 2; i < nums.length; i++) {
            if (nums[i] > x) {
                y = x;
                x = nums[i];
            }else if (nums[i] > y) {
                y = nums[i];
            }
        }
        return (x - 1) * (y - 1);
    }

    /**
     * 排序乘积
     * @param nums 整数数组
     * @return 最大乘积
     */
    public int maxProduct2(int[] nums) {
        Arrays.sort(nums);
        return (nums[nums.length -1] - 1) * (nums[nums.length -2] - 1);
    }
}
