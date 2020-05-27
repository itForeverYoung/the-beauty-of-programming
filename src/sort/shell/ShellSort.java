package sort.shell;

import sort.Test;
import sort.quick.QuickSort;

import java.util.Arrays;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class ShellSort {

    /**
     * 希尔排序, (插入排序的改进版，递减增量排序)
     * 参考文档：https://zh.wikipedia.org/wiki/%E5%B8%8C%E5%B0%94%E6%8E%92%E5%BA%8F
     * 当原数组顺序时，只会有一次外层for循环，执行n次，所以最好情况下时间复杂度为O(n)
     * 当原数组逆序时，循环要执行n*(n-1)/2次，所以最坏情况下时间复杂度为O(n^2)
     * 时间复杂度: 最坏O(n^2), 最好O(n), 平均O(n^1.3), 另外，已知最好gap下的最坏复杂度可以优化到O(nlogN)
     * 空间复杂度: O(1)
     * 稳定性: 非稳定
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        /**
         * 首先在这里，
         * 定义了一个gap，也就是步长，我们采用比较简单的gap/=2得到新的步长，相当于步长每循环一次后，都会变成原步长的一半，
         * 那么，最后一次循环时，gap=1，
         * 当gap=1时，最后一次循环就退化成了简单插入排序，
         * @see sort.insertion.InsertionSort#sort(int[]) 
         * 但是，在最后一次循环时，数组也是基本有序的了，所以最后一次循环（插入排序）的时间复杂度就可以趋近于O(n)
         */
        int temp;
        for (int gap=n/2; gap>0; gap/=2) {
            for (int i = gap; i<n; i++) {
                int j = i;
                /**
                 * 其次是这里，
                 * 每次比较，都是和(当前index)-gap的元素进行比较
                 * 也就是说，当一个较小的元素在数组后面时，不用每次只和前一个元素进行比较，而是可以一下跨越gap
                 * 举例：nums={10,9,8,7,6,5,4,3,2,1}
                 * 如果是希尔排序
                 * 第一次循环，gap=5，循环结束后：nums={5,4,3,2,1,10,9,8,7,6}，可以看出，所有小的元素都已经移到了数组中间位置的前面
                 * 如果是插入排序
                 * 第一次循环，循环结束后：nums={9,10,8,7,6,5,4,3,2,1}，可以看出，这一次排序的效率很低，因为index>1的元素还是完全逆序的
                 *
                 * 到这里，可以稍微总结一下希尔排序的优势
                 * 1. 首先，我们知道，插入排序，在数组逆序时，时间复杂度最高，为O(n^2)；在数组顺序时，时间复杂度最低，为O(n)
                 * 2. 而希尔排序着重优化的就是数组相对逆序时的时间复杂度，通过定义gap，在数组逆序时，可以很快的将小元素移到数组的前面
                 * 3. 最后一次循环，gap=1，此时数组基本有序，所以最后一次循环（插入排序）的时间复杂度就可以趋近于O(n)
                 */
                temp = nums[j];
                while ((j-gap) >= 0 && (temp < nums[j-gap])) {
                    nums[j] = nums[j-gap];
                    j -= gap;
                }
                nums[j] = temp;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = Test.gen(10);
        System.out.print("原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n排序后：");
        Arrays.stream(sort(nums)).forEach(r -> System.out.printf("%s%s", r, ", "));
    }

}
