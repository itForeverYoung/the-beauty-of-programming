package sort.insertion;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class InsertionSort {

    /**
     * 插入排序
     * 当原数组顺序时，只会有一次外层for循环，执行n次，所以最好情况下时间复杂度为O(n)
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
         * 当数组顺序时，只会有一次外层for循环，执行(n-1)次，所以最好情况下时间复杂度为O(n)
         * 当数组逆序时，内外层循环相加，要执行n*(n-1)/2次，所以最坏情况下时间复杂度为O(n^2)
         */
        int temp;
        for (int i=1; i<n; i++) {
            int j = i;
            temp = nums[j];
            // 由于这里的判断用的是<符号，当两个元素相等时不会进入内层循环，所以此算法是稳定的
            while (j > 0 && (temp < nums[j-1])) {
                nums[j] = nums[j-1];
                j--;
            }
            nums[j] = temp;
        }
        return nums;
    }

}
