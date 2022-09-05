package pers.richard.leetcode.dailyquestion.aug22;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: CanBeEqual220824
 * @Description: 通过翻转子数组使两个数组相等
 *  给你两个长度相同的整数数组 target 和 arr 。
 *  每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
 *  如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
 *
 * 示例 1：
 * 输入：target = [1,2,3,4], arr = [2,4,1,3]
 * 输出：true
 *  解释：你可以按照如下步骤使 arr 变成 target：
 *  1- 翻转子数组 [2,4,1] ，arr 变成 [1,4,2,3]
 *  2- 翻转子数组 [4,2] ，arr 变成 [1,2,4,3]
 *  3- 翻转子数组 [4,3] ，arr 变成 [1,2,3,4]
 *  上述方法并不是唯一的，还存在多种将 arr 变成 target 的方法。
 *
 * 示例 2：
 *  输入：target = [7], arr = [7]
 *  输出：true
 *  解释：arr 不需要做任何翻转已经与 target 相等。
 *
 * 示例 3：
 *  输入：target = [3,7,9], arr = [3,7,11]
 *  输出：false
 *  解释：arr 没有数字 9 ，所以无论如何也无法变成 target 。
 *  
 *
 *  提示：
 *  target.length == arr.length
 *  1 <= target.length <= 1000
 *  1 <= target[i] <= 1000
 *  1 <= arr[i] <= 1000
 *
 * @Author: Richard_Chen
 * @Create: 2022-08-24 14:38
 */
public class CanBeEqual24 {


    public boolean canBeEqual1(int[] target, int[] arr) {
        Map<Integer, Integer> counts1 = new HashMap<>();
        Map<Integer, Integer> counts2 = new HashMap<>();
        for (int num : target) {
            counts1.put(num, counts1.getOrDefault(num, 0) + 1);
        }
        for (int num : arr) {
            counts2.put(num, counts2.getOrDefault(num, 0) + 1);
        }
        if (counts1.size() != counts2.size()) {
            return false;
        }
        for (Map.Entry<Integer, Integer> entry : counts1.entrySet()) {
            int key = entry.getKey(), value = entry.getValue();
            if (!counts2.containsKey(key) || counts2.get(key) != value) {
                return false;
            }
        }
        return true;
    }

    public boolean canBeEqual2(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        for (int i=0; i < target.length; i++){
            if (target[i] != arr[i]) return false;
        }
        return true;
    }

    public boolean canBeEqual3(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target, arr);
    }
}
