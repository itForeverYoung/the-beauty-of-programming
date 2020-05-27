package search.binary;

/**
 * @author zhanj566
 * @date 2020/4/20 4:31 PM
 * 二分查找
 * 推荐循环写法，简单直接明了
 * 递归写法和循环的思路基本一致，没必要强行递归（因为递归不一定好）
 **/
public class BinarySearch {

    /**
     * 循环写法
     * @param nums
     * @param target
     * @return
     */
    public static int cycle(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        int start = 0;
        int end = nums.length-1;
        while (start <= end) {
            int mid = start + (end-start)/2;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归写法
     * @param nums
     * @param target
     * @return
     */
    public static int recursive(int[] nums, int target) {
        if (nums.length == 0) {
            return -1;
        }
        return recursive(nums, target, 0, nums.length - 1);
    }

    public static int recursive(int[] nums, int target, int start, int end) {
        if (start <= end) {
            int mid = start + (end-start)/2;
            if (target < nums[mid]) {
                return recursive(nums, target, start, mid - 1);
            } else if (target > nums[mid]) {
                return recursive(nums, target, mid + 1, end);
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        /**
         * 如果存在，返回下标
         * 如果不存在，返回-1
         */
        int[] nums = new int[] {1,2,3,4,5,6,7,8,9,10};
        int target = 7;
        System.out.println(cycle(nums, target));
        System.out.println(recursive(nums, target));

    }

}
