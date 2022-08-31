package pers.richard.leetcode.brinarySearch;

/**
 * @ClassName: BinarySearchModel2
 * @Description: 二分查找 LeetCode - LeetBook
 * 关键属性
 *  一种实现二分查找的高级方法。
 *  查找条件需要访问元素的直接右邻居。
 *  使用元素的右邻居来确定是否满足条件，并决定是向左还是向右。
 *  保证查找空间在每一步中至少有 2 个元素。
 *  需要进行后处理。 当你剩下 1 个元素时，循环 / 递归结束。 需要评估剩余元素是否符合条件。
 *  
 *
 * 区分语法
 *  初始条件：left = 0, right = length
 *  终止：left == right
 *  向左查找：right = mid
 *  向右查找：left = mid+1
 *
 * @Author: Richard_Chen
 * @Create: 2022-08-31 14:00
 */
public class BinarySearchModel2 {
    /**
     * 模板 #2 是二分查找的高级模板。
     * 它用于查找需要访问数组中当前索引及其直接右邻居索引的元素或条件。
     * @param nums   数组
     * @param target 目标
     * @return 下标
     */
    int binarySearch(int[] nums, int target){
        if(nums == null || nums.length == 0)
            return -1;

        int left = 0, right = nums.length;
        while(left < right){
            // Prevent (left + right) overflow
            int mid = left + (right - left) / 2;
            if(nums[mid] == target){ return mid; }
            else if(nums[mid] < target) { left = mid + 1; }
            else { right = mid; }
        }

        // Post-processing:
        // End Condition: left == right
        if(left != nums.length && nums[left] == target) return left;
        return -1;
    }
}
