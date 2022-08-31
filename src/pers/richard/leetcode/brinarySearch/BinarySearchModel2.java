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

    /*  */

    /**
     * The isBadVersion API is defined in the parent class VersionControl.
     * boolean isBadVersion(int version);
     * 第一个错误的版本
     * 你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。
     * 由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的。
     *
     * 假设你有 n 个版本 [1, 2, ..., n]，你想找出导致之后所有版本出错的第一个错误的版本。
     *
     * 你可以通过调用 bool isBadVersion(version) 接口来判断版本号 version 是否在单元测试中出错。
     * 实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
     *
     * 示例 1：
     *  输入：n = 5, bad = 4
     *  输出：4
     *  解释：
     *  调用 isBadVersion(3) -> false
     *  调用 isBadVersion(5) -> true
     *  调用 isBadVersion(4) -> true
     *  所以，4 是第一个错误的版本。
     *
     * 示例 2：
     *  输入：n = 1, bad = 1
     *  输出：1
     * 提示：
     *  1 <= bad <= n <= 231 - 1
     * @param n 版本号
     * @return 错误的版本n
     * 思路及算法
     *
     * 因为题目要求尽量减少调用检查接口的次数，所以不能对每个版本都调用检查接口，而是应该将调用检查接口的次数降到最低。
     *
     * 注意到一个性质：当一个版本为正确版本，则该版本之前的所有版本均为正确版本；当一个版本为错误版本，则该版本之后的所有版本均为错误版本。我们可以利用这个性质进行二分查找。
     *
     * 具体地，将左右边界分别初始化为 11 和 nn，其中 nn 是给定的版本数量。设定左右边界之后，每次我们都依据左右边界找到其中间的版本，检查其是否为正确版本。如果该版本为正确版本，那么第一个错误的版本必然位于该版本的右侧，我们缩紧左边界；否则第一个错误的版本必然位于该版本及该版本的左侧，我们缩紧右边界。
     *
     * 这样我们每判断一次都可以缩紧一次边界，而每次缩紧时两边界距离将变为原来的一半，因此我们至多只需要缩紧 O(\log n)O(logn) 次。
     *
     */
    public int firstBadVersion(int n) {
        int left = 1, right = n;
        boolean isBad = false;
        while (left < right){
           int mid = left + (right - left)/2;
           //isBad = isBadVersion(mid);
           if (isBad){
                right = mid;
           }else {
               left = mid + 1;
           }
        }
        return left;
    }
}
