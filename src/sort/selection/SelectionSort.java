package sort.selection;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class SelectionSort {

    /**
     * 最简单的选择排序
     * 很显然，两层for循环，而且无论原数组的有序性是怎样的，两层循环都必须要走完。
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(1)
     * 稳定性: 不稳定
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        int temp;
        for (int i=0; i<n; i++) {
            int minIndex = i;
            for (int j=i+1; j<n; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            temp = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = temp;
        }
        return nums;
    }

}
