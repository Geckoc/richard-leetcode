import pers.richard.leetcode.dailyquestion.oct22.Day11AreAlmostEqual;

/**
 * @ClassName: Main
 * @Description: 二分查找
 * @Author: Richard_Chen
 * @Create: 2022-08-31 10:11
 */
public class Main {

    public static void main(String[] args) {
        Day11AreAlmostEqual day11AreAlmostEqual = new Day11AreAlmostEqual();
        String s1 = "siyolsdcjthwsiplccjbuceoxmpjgrauocx";
        String s2 = "siyolsdcjthwsiplccpbuceoxmjjgrauocx";
        System.out.println(day11AreAlmostEqual.areAlmostEqual(s1, s2));
    }
}
