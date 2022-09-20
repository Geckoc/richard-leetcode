package pers.richard.leetcode.dailyquestion.sep22;

import java.util.Arrays;

/**
 * @ClassName: Day17MaxLengthBetweenEqualCharacters
 * @Description: 两个相同字符之间的最长子字符串
 * 给你一个字符串 s，请你返回 两个相同字符之间的最长子字符串的长度 ，计算长度时不含这两个字符。
 * 如果不存在这样的子字符串，返回 -1 。
 * 子字符串 是字符串中的一个连续字符序列。
 *
 * 示例 1：
 * 输入：s = "aa"
 * 输出：0
 * 解释：最优的子字符串是两个 'a' 之间的空子字符串。
 *
 * 示例 2：
 * 输入：s = "abca"
 * 输出：2
 * 解释：最优的子字符串是 "bc" 。
 *
 * 示例 3：
 * 输入：s = "cbzxy"
 * 输出：-1
 * 解释：s 中不存在出现出现两次的字符，所以返回 -1 。
 *
 * 示例 4：
 * 输入：s = "cabbac"
 * 输出：4
 * 解释：最优的子字符串是 "abba" ，其他的非最优解包括 "bb" 和 "" 。
 *  
 *
 * 提示：
 * 1 <= s.length <= 300
 * s 只含小写英文字母
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-20 18:10
 */
public class Day17MaxLengthBetweenEqualCharacters {
    /**
     * 直接遍历
     * 题目要求求出两个相同字符之间的最长子字符串的长度。对于字符 ch，只需要求出 ch 第一次出现在字符串中的索引位置 first 和最后一次出现在字符串中的索引位置 last，
     * 则以 ch 为相同字符之间的子字符串的最大长度一定为 last−first−1，我们依次求出所有可能的子字符的长度的最大值即可。
     * 我们设数组 firstIndex 记录每个字符 i 在字符串中第一次出现的索引，maxLength 表示当前子字符串的最大长度。
     *
     * 初始化时 firstIndex 中的每个元素都初始化为 −1，表示该字符还未出现。
     * 当遍历到第 i 个字符 ch 时，如果当前数组中 firstIndex[ch]=−1，则记录该字符第一次出现的索引为 i，更新 firstIndex[ch]=1；如果当前数组中 firstIndex[ch]≥0 时，
     * 则表示字符 ch 之前已经出现过，此时两个 ch 之间的子字符串长度为 i−firstIndex[ch]−1，同时更新 maxLength。
     * 返回最大的长度 maxLength 即可。
     *
     * @param s
     * @return
     */
    public int maxLengthBetweenEqualCharacters(String s) {
        int[] firstIndex = new int[26];
        Arrays.fill(firstIndex, -1);
        int maxLength = -1;
        for (int i = 0; i < s.length(); i++) {
            if (firstIndex[s.charAt(i) - 'a'] < 0) {
                firstIndex[s.charAt(i) - 'a'] = i;
            } else {
                maxLength = Math.max(maxLength, i - firstIndex[s.charAt(i) - 'a'] - 1);
            }
        }
        return maxLength;
    }
}
