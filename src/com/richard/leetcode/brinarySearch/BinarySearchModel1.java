package com.richard.leetcode.brinarySearch;

/**
 * @ClassName: BinarySearchModel1
 * @Description: 二分查找 LeetCode - LeetBook
 *  model #1 是二分查找的最基础和最基本的形式。这是一个标准的二分查找模板。
 *  关键属性
 *      二分查找的最基础和最基本的形式。
 *      查找条件可以在不与元素的两侧进行比较的情况下确定（或使用它周围的特定元素）。
 *      不需要后处理，因为每一步中，你都在检查是否找到了元素。如果到达末尾，则知道未找到该元素。
 *
 * 区分语法
 *      初始条件：left = 0, right = length-1
 *      终止：left > right
 *      向左查找：right = mid-1
 *      向右查找：left = mid+1
 *
 * @Author: Richard_Chen
 * @Create: 2022-08-31 10:11
 */
public class BinarySearchModel1 {

    /**
     * 题目：x 的平方根
     * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
     *
     * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
     *
     * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
     *  示例 1：
     *  输入：x = 4
     *  输出：2
     *  示例 2：
     *  输入：x = 8
     *  输出：2
     *  解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
     * @return x的算数平方根
     */
    public int mySqrt(int x){
        int left = 0, right = x, ans = -1;
        while (left <= right){
            int mid = left + (right - left) / 2;
            if (mid * mid <= x){
                ans = mid;
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return ans;
    }


    /**
     * 题目：猜数字大小
     *
     *  每轮游戏，我都会从 1 到 n 随机选择一个数字。 请你猜选出的是哪个数字。
     *  如果你猜错了，我会告诉你，你猜测的数字比我选出的数字是大了还是小了。
     *  你可以通过调用一个预先定义好的接口 int guess(int num) 来获取猜测结果，返回值一共有 3 种可能的情况（-1，1 或 0）：
     *
     *  -1：我选出的数字比你猜的数字小 pick < num
     *  1：我选出的数字比你猜的数字大 pick > num
     *  0：我选出的数字和你猜的数字一样。恭喜！你猜对了！pick == num
     *  返回我选出的数字。
     *
     * @param n guessNum
     * @return pickNum
     */
    public int guessNumber(int n) {
        int start = 0, end = n, flag = 0;
        while(start <= end){
            int mid = start + (end - start) / 2;
            // guess(int num)为题目预定接口
            // flag = guess(mid);
            if(-1 == flag){
                end = mid - 1;
            }else if(1 == flag){
                start = mid + 1;
            }else{
                return mid;
            }
        }
        return start;
    }

}
