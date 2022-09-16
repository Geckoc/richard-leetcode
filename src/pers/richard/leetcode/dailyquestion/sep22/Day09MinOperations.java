package pers.richard.leetcode.dailyquestion.sep22;

/**
 * @ClassName: Day09MinOperations
 * @Description: 文件夹操作日志搜集器
 * 每当用户执行变更文件夹操作时，LeetCode 文件系统都会保存一条日志记录。
 *
 * 下面给出对变更操作的说明：
 * "../" ：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则 继续停留在当前文件夹 。
 * "./" ：继续停留在当前文件夹。
 * "x/" ：移动到名为x的子文件夹中。题目数据 保证总是存在文件夹x。
 *
 * 给你一个字符串列表 logs ，其中 logs[i] 是用户在 ith 步执行的操作。
 * 文件系统启动时位于主文件夹，然后执行 logs 中的操作。
 * 执行完所有变更文件夹操作后，请你找出 返回主文件夹所需的最小步数。
 *
 * 输入：logs = ["d1/","d2/","../","d21/","./"]
 * 输出：2
 * 解释：执行 "../" 操作变更文件夹 2 次，即可回到主文件夹
 *
 * @Author: Richard_Chen
 * @Create: 2022-09-13 14:32
 */
public class Day09MinOperations {

    /**
     * 方法一：直接模拟
     * 根据题意可知返回主文件夹的操作为连续退回到上一层目录，直到返回主目录为止，在这种操作下使用的操作数最少。
     * 我们用一个变量记录depth 当前目录的层次深度，depth 初始化为0，根据题意可知：
     *
     * 如果当前的操作为"../"：移动到当前文件夹的父文件夹。如果已经在主文件夹下，则继续停留在当前文件夹。
     *  则此时如果层次深度 depth >0 则将 depth 减 11，否则 depth 保持不变；
     * 如果当前的操作为"./"：继续停留在当前文件夹，此时depth 保持不变；
     * 如果当前的操作为"x/"：移动到下一层名为 x 的子文件夹中。则此时将 depth 加 11。
     * 最终返回当前的文件层次深度depth 即可。
     *
     * @param logs
     * @return
     */
    public int minOperations(String[] logs) {
        int depth = 0;
        for (String log : logs) {
            if ("./".equals(log)) {
                continue;
            } else if ("../".equals(log)) {
                if (depth > 0) {
                    depth--;
                }
            } else {
                depth++;
            }
        }
        return depth;
    }
}
