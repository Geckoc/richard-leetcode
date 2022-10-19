package pers.richard.leetcode.dailyquestion.oct22;

/**
 * @ClassName: Day01ReformatNumber
 * @Description: 重新格式化电话号码
 * 给你一个字符串形式的电话号码 number 。number 由数字、空格 ' '、和破折号 '-' 组成。
 * 请你按下述方式重新格式化电话号码。
 * 首先，删除 所有的空格和破折号。
 * 其次，将数组从左到右 每 3 个一组 分块，直到 剩下 4 个或更少数字。剩下的数字将按下述规定再分块：
 * 2 个数字：单个含 2 个数字的块。
 * 3 个数字：单个含 3 个数字的块。
 * 4 个数字：两个分别含 2 个数字的块。
 * 最后用破折号将这些块连接起来。
 * 注意，重新格式化过程中 不应该 生成仅含 1 个数字的块，并且 最多 生成两个含 2 个数字的块。
 * 返回格式化后的电话号码。
 *
 * 示例 1：
 * 输入：number = "1-23-45 6"
 * 输出："123-456"
 * 解释：数字是 "123456"
 * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
 * 步骤 2：剩下 3 个数字，将它们放入单个含 3 个数字的块。第 2 个块是 "456" 。
 * 连接这些块后得到 "123-456" 。
 *
 * 示例 2：
 * 输入：number = "123 4-567"
 * 输出："123-45-67"
 * 解释：数字是 "1234567".
 * 步骤 1：共有超过 4 个数字，所以先取 3 个数字分为一组。第 1 个块是 "123" 。
 * 步骤 2：剩下 4 个数字，所以将它们分成两个含 2 个数字的块。这 2 块分别是 "45" 和 "67" 。
 * 连接这些块后得到 "123-45-67" 。
 *
 * 示例 3：
 * 输入：number = "123 4-5678"
 * 输出："123-456-78"
 * 解释：数字是 "12345678" 。
 * 步骤 1：第 1 个块 "123" 。
 * 步骤 2：第 2 个块 "456" 。
 * 步骤 3：剩下 2 个数字，将它们放入单个含 2 个数字的块。第 3 个块是 "78" 。
 * 连接这些块后得到 "123-456-78" 。
 *
 * 示例 4：
 * 输入：number = "12"
 * 输出："12"
 *
 * 示例 5：
 * 输入：number = "--17-5 229 35-39475 "
 * 输出："175-229-353-94-75"
 *  
 *
 * 提示：
 * 2 <= number.length <= 100
 * number 由数字和字符 '-' 及 ' ' 组成。
 * number 中至少含 2 个数字。
 *
 * @Author: Richard_Chen
 * @Create: 2022-10-19 17:53
 */
public class Day01ReformatNumber {

    /**
     * 思路与算法
     *
     * 我们首先对给定的字符串 number 进行一次遍历，找出所有的数字，并记录在字符串 digits 中。
     * 如果使用的语言不支持可修改的字符串，也可以记录在数组中。
     *
     * 随后我们对digits 进行一次遍历。
     * 在遍历的过程中，我们可以存储剩余的数字数量 n 以及当前遍历到的字符位置 pt：
     *
     * 当 n>4 时，我们取出三个连续的字符，作为一个块；
     *
     * 当 n≤4 时，我们根据题目的要求，将剩余的 n 个字符进行分块，并结束遍历。
     *
     * 我们还需要在块之间添加破折号。根据使用的语言不同，可以考虑在遍历的过程中添加破折号
     * 并在遍历完成后直接返回答案；或者在遍历结束后再添加破折号，并在遍历完成后使用 join() API 得到答案。
     *
     * @param number
     * @return
     */
    public String reformatNumber(String number) {
        StringBuilder digits = new StringBuilder();
        for (int i = 0; i < number.length(); ++i) {
            char ch = number.charAt(i);
            if (Character.isDigit(ch)) {
                digits.append(ch);
            }
        }

        int n = digits.length();
        int pt = 0;
        StringBuilder ans = new StringBuilder();
        while (n > 0) {
            if (n > 4) {
                ans.append(digits.substring(pt, pt + 3) + "-");
                pt += 3;
                n -= 3;
            } else {
                if (n == 4) {
                    ans.append(digits.substring(pt, pt + 2) + "-" + digits.substring(pt + 2, pt + 4));
                } else {
                    ans.append(digits.substring(pt, pt + n));
                }
                break;
            }
        }
        return ans.toString();
    }
}
