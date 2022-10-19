package pers.richard.leetcode.dailyquestion.oct22;

/**
 * @ClassName: Day02CanTransform
 * @Description: 在LR字符串中交换相邻字符
 * 在一个由 'L' , 'R' 和 'X' 三个字符组成的字符串（例如"RXXLRXRXL"）中进行移动操作。
 * 一次移动操作指用一个"LX"替换一个"XL"，或者用一个"XR"替换一个"RX"。
 * 现给定起始字符串start和结束字符串end，请编写代码，当且仅当存在一系列移动操作使得start可以转换成end时， 返回True。
 *
 * 示例 :
 * 输入: start = "RXXLRXRXL", end = "XRLXXRRLX"
 * 输出: True
 *
 * 解释:
 * 我们可以通过以下几步将start转换成end:
 * RXXLRXRXL ->
 * XRXLRXRXL ->
 * XRLXRXRXL ->
 * XRLXXRRXL ->
 * XRLXXRRLX
 *
 * 提示：
 * 1 <= len(start) = len(end) <= 10000。
 * start和end中的字符串仅限于'L', 'R'和'X'。
 *
 * @Author: Richard_Chen
 * @Create: 2022-10-19 17:58
 */
public class Day02CanTransform {

    /**
     * 方法一：双指针
     * 每次移动操作将 “XL" 替换成 “LX"，或将 “RX" 替换成 “XR"，等价于如下操作：
     *
     * 如果一个字符‘L’ 左侧的相邻字符是‘X’，则将字符‘L’ 向左移动一位，将其左侧的‘X’ 向右移动一位；
     *
     * 如果一个字符‘R’ 右侧的相邻字符是‘X’，则将字符‘R’ 向右移动一位，将其右侧的‘X’ 向左移动一位。
     *
     * 由于每次移动操作只是交换两个相邻字符，不会增加或删除字符，因此如果可以经过一系列移动操作将 start 转换成 end，
     * 则 start 和 end 满足每一种字符的数量分别相同，字符‘L’ 和‘R’ 的相对顺序相同，
     * 且每个‘L’ 在end 中的下标小于等于对应的‘L’ 在 start 中的下标，以及每个‘R’ 在 end 中的下标大于等于
     * 对应的‘R’ 在 start 中的下标。
     *
     * 因此，可以通过判断 start 和 end 中的所有‘L’ 和‘R’ 是否符合替换后的规则，
     * 判断是否可以经过一系列移动操作将 start 转换成 end。
     *
     * 用 n 表示 start 和 end 的长度，用 i 和 j 分别表示 start 和 end 中的下标，
     * 从左到右遍历 start 和 end，跳过所有的 ‘X’，当 i 和 j 都小于 n 时，比较非‘X’ 的字符：
     *
     *  start[i] != end[j]，
     * 则 start 和 end 中的当前字符不匹配，返回 false；
     *
     * 如果start[i] = end[j]，
     * 则当前字符是‘L’ 时应有 i≥j，当前字符是‘R’ 时应有 i≤j，如果当前字符与两个下标的关系不符合该规则，返回false。
     *
     * 如果 i 和 j 中有一个下标大于等于 n，则有一个字符串已经遍历到末尾，
     * 继续遍历另一个字符串中的其余字符，如果其余字符中出现非‘X’ 的字符，则该字符不能与任意字符匹配，返回 false。
     *
     * 如果 start 和 end 遍历结束之后没有出现不符合移动操作的情况，
     * 则可以经过一系列移动操作将 start 转换成 end，返回 true。
     *
     * @param start
     * @param end
     * @return
     */
    public boolean canTransform(String start, String end) {
        int n = start.length();
        int i = 0, j = 0;
        while (i < n && j < n) {
            while (i < n && start.charAt(i) == 'X') {
                i++;
            }
            while (j < n && end.charAt(j) == 'X') {
                j++;
            }
            if (i < n && j < n) {
                if (start.charAt(i) != end.charAt(j)) {
                    return false;
                }
                char c = start.charAt(i);
                if ((c == 'L' && i < j) || (c == 'R' && i > j)) {
                    return false;
                }
                i++;
                j++;
            }
        }
        while (i < n) {
            if (start.charAt(i) != 'X') {
                return false;
            }
            i++;
        }
        while (j < n) {
            if (end.charAt(j) != 'X') {
                return false;
            }
            j++;
        }
        return true;
    }
}
