import pers.richard.leetcode.brinarySearch.BinarySearchModel1;
import pers.richard.leetcode.brinarySearch.BinarySearchModel2;

/**
 * @ClassName: Main
 * @Description: 二分查找
 * @Author: Richard_Chen
 * @Create: 2022-08-31 10:11
 */
public class Main {

    public static void main(String[] args) {
        BinarySearchModel1 binarySearchModel1 = new BinarySearchModel1();
        BinarySearchModel2 binarySearchModel2 = new BinarySearchModel2();
        int[] nums = {1};
        int search = binarySearchModel1.search(nums, 1);
        System.out.println(search);
    }
}
