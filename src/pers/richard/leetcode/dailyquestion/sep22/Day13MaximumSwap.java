package pers.richard.leetcode.dailyquestion.sep22;

/**
 * @ClassName: Day13MaximumSwap
 * @Description: 最大交换
 * 给定一个非负整数，你至多可以交换一次数字中的任意两位。
 * 返回你能得到的最大值。
 *
 * 示例 1 :
 * 输入: 2736
 * 输出: 7236
 * 解释: 交换数字2和数字7。
 *
 * 示例 2 :
 * 输入: 9973
 * 输出: 9973
 * 解释: 不需要交换。
 *
 * 注意:
 * 给定数字的范围是 [0, 108]
 * @Author: Richard_Chen
 * @Create: 2022-09-13 15:09
 */
public class Day13MaximumSwap {


    public int maximumSwap(int num) {
        char[] charArray = String.valueOf(num).toCharArray();
        int n = charArray.length;
        int maxNum = num;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                swap(charArray, i, j);
                maxNum = Math.max(maxNum, Integer.parseInt(new String(charArray)));
                swap(charArray, i, j);
            }
        }
        return maxNum;
    }

    public void swap(char[] charArray, int i, int j) {
        char temp = charArray[i];
        charArray[i] = charArray[j];
        charArray[j] = temp;
    }
}
