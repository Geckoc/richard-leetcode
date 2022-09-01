package pers.richard.leetcode.dailyquestion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @ClassName: validateStackSequences220831
 * @Description: 验证栈序列
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 *
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 *
 * 提示：
 *  1 <= pushed.length <= 1000
 *  0 <= pushed[i] <= 1000
 *  pushed 的所有元素 互不相同
 *  popped.length == pushed.length
 *  popped 是 pushed 的一个排列
 *
 * @Author: Richard_Chen
 * @Create: 2022-08-31 16:22
 */
public class ValidateStackSequences220831 {

    /**
     * 出入栈模拟
     * Stack.peek();
     * peek()方法用于从此Stack中返回顶部元素，并且它不删除就检索元素。
     *
     * peek()方法是一种非静态方法，只能通过类对象访问，如果尝试使用类名访问该方法，则会收到错误消息。
     *
     * peek()方法在返回top元素时不会引发异常。
     *
     * 语法：public Object peek();
     *
     * 参数：它不接受任何参数。
     *
     * 返回值：该方法的返回类型为Object，它从堆栈中返回head元素。
     *
     * @param pushed 入栈队列
     * @param popped 出栈队列
     * @return true/false 正常出入栈
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = pushed.length;
        for (int i = 0, j = 0; i < n; i++) {
            stack.push(pushed[i]);
            while (!stack.isEmpty() && stack.peek() == popped[j]){
                stack.pop();
                j++;
            }
        }
        return stack.isEmpty();
    }

    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        Deque<Integer> d = new ArrayDeque<>();
        for (int i = 0, j = 0; i < pushed.length; i++) {
            d.addLast(pushed[i]);
            while (!d.isEmpty() && d.peekLast() == popped[j] && ++j >= 0){
                d.pollLast();
            }
        }
        return d.isEmpty();
    }
}
