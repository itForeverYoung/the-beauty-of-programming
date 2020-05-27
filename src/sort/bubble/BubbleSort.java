package sort.bubble;

import sort.Test;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class BubbleSort {

    /**
     * 冒泡排序
     * 比较两个相邻的元素，如果前一个大于后一个，则交换位置，这样在第一次循环走完之后，可以保证最大的元素在最后
     * 当原数组顺序时，只会有一次内层循环，执行(n-1)次，所以最好情况下时间复杂度为O(n)
     * 当原数组逆序时，循环要执行n*(n-1)/2次，所以最坏情况下时间复杂度为O(n^2)
     * 时间复杂度: 最坏O(n^2), 最好O(n), 平均O(n^2)
     * 空间复杂度: O(1)
     * 稳定性: 稳定
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        /**
         * 这种写法下，
         * 当数组顺序时，只会有一次内层for循环，执行(n-1)次，所以最好情况下时间复杂度为O(n)
         * 当数组逆序时，内外层循环相加，要执行n*(n-1)/2次，所以最坏情况下时间复杂度为O(n^2)
         */
        int end = n;
        int temp;
        while (end > 1) {
            int newEnd = 0;
            for (int j=0; j<end-1; j++) {
                if (nums[j] > nums[j+1]) {
                    temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    newEnd = j+1;
                }
            }
            end = newEnd;
        }
        return nums;
    }

    public static int[] oldSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        /**
         * 这种写法，
         * 无论数组的有序性怎样，循环都要执行n*(n-1)/2次，所以最好/最坏情况下时间复杂度都为O(n^2)
         */
        for (int i=0; i<n; i++) {
            for (int j=0; j<n-1-i; j++) {
                if (nums[j] > nums[j+1]) {
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        sort(Test.gen(20));
        sort(new int[]{1,2,3,4,5,6,7,8,9,10});
    }

}
