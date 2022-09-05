package pers.richard.leetcode.dailyquestion.aug22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @ClassName: findClosestElements220825
 * @Description: 找到 K 个最接近的元素
 * 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。
 * 返回的结果必须要是按升序排好的。
 * 整数 a 比整数 b 更接近 x 需要满足：
 *
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 *  
 * 示例 1：
 *  输入：arr = [1,2,3,4,5], k = 4, x = 3
 *  输出：[1,2,3,4]
 *  示例 2：
 * 示例 2：
 *  输入：arr = [1,2,3,4,5], k = 4, x = -1
 *  输出：[1,2,3,4]
 *  
 *
 * 提示：
 *  1 <= k <= arr.length
 *  1 <= arr.length <= 104
 *  arr 按 升序 排列
 *  -104 <= arr[i], x <= 104
 *
 * @Author: Richard_Chen
 * @Create: 2022-08-25 14:55
 */
public class FindClosestElements220825 {

    /**
     * 排序
     * @param arr 排序好的数组
     * @param k  整数k
     * @param x  整数x
     * @return 升序数集
     */
    public List<Integer> findClosestElements1(int[] arr, int k, int x) {
        List<Integer> list = new ArrayList<>();
        for (int num : arr) {
            list.add(num);
        }
        list.sort((a, b) -> {
            if (Math.abs(a - x) != Math.abs(b - x)) {
                return Math.abs(a - x) - Math.abs(b - x);
            } else {
                return a - b;
            }
        });
        List<Integer> res = list.subList(0, k);
        Collections.sort(res);
        return res;
    }

    /**
     * 二分查找
     * @param arr 排序好的数组
     * @param k  整数k
     * @param x  整数x
     * @return 升序数集
     */
    public List<Integer> findClosestElements2(int[] arr, int k, int x) {
        int size = arr.length;
        int left = 0;
        int right = size - 1;
        int removeNums = size - k;
        while (removeNums > 0) {
            if (x - arr[left] <= arr[right] - x) {
                right--;
            } else {
                left++;
            }
            removeNums--;
        }

        List<Integer> res = new ArrayList<>();
        for (int i = left; i < left + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }
}
