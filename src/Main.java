import pers.richard.leetcode.dailyquestion.sep22.Day14TrimMean;

/**
 * @ClassName: Main
 * @Description: 二分查找
 * @Author: Richard_Chen
 * @Create: 2022-08-31 10:11
 */
public class Main {

    public static void main(String[] args) {
        int[] arr = {6,0,7,0,7,5,7,8,3,4,0,7,8,1,6,8,1,1,2,4,8,1,9,5,4,3,8,5,10,8,6,6,1,0,6,10,8,2,3,4};
        Day14TrimMean trimMean14 = new Day14TrimMean();
        double v = trimMean14.trimMean(arr);
        System.out.println(v);
    }
}
