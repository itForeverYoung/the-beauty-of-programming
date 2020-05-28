package sort.counting;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class CountingSort {

    /**
     * 计数排序
     * 思路：首先得到原数组的最大值，把最大值+1作为length创建一个新的数组newArr，新数组用来计数
     * 循环遍历原数组，以元素值为index放入新数组的对应位置，如果有重复元素，则newArr[index]++
     * 然后，维护一个x作为原数组的下标，循环遍历新数组，当newArr[index]>0时，则将index放入原数组nums[x]处，并且newArr[index]--
     * 循环结束后，原数组即为有序
     * 时间复杂度: O(2n+k)
     * 空间复杂度: O(2n+k)
     * 稳定性: 稳定
     * 注：计数排序是一个稳定的排序算法，当输入的元素是n个0-k之间的数值时，时间复杂度为O(n+k)，空间复杂度为O(n+k)，其排序速度很快。
     * 但是，
     * 1. 由于我们需要把元素值作为下标，这就要求数组中的元素必须是整数
     * 2. 由于新数组newArr的长度是原数组的最大元素+1，这就要求原数组最好是一定范围内的数据，这样计算出来的k可以比较小。
     * 所以，当k较小，并且原数组元素相对集中是，使用计数排序很有效。
     * @param nums 待排序数组
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        int max = nums[0];
        // 循环一次，找到最大值
        for (int i=1; i<n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        int len = max+1;
        int[] arr = new int[len];
        // 循环第二次，填充新数组
        for (int i=0; i<n; i++) {
            arr[nums[i]]++;
        }
        int x = 0;
        // 循环第三次，根据新数组重新填充原数组
        for (int i=0; i<len; i++) {
            while (arr[i] > 0) {
                nums[x++] = i;
                arr[i]--;
            }
        }
        return nums;
    }


}
