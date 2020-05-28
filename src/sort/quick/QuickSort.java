package sort.quick;

import sort.Test;

import java.util.Arrays;

/**
 * @author zhanj566
 * @date 2020/5/27 10:11 AM
 **/
public class QuickSort {

    /**
     * 快速排序
     * 把找到的基准位置的变量，和最后一个元素交换位置，然后遍历当前数组，把小于基准变量的元素放前边，大于基准变量的元素放后边
     * 最后，再分别递归左右子序列
     * 时间复杂度: 最好O(nlogN), 最坏O(n^2), 平均O(nlogN)
     * 空间复杂度: O(logn)
     * 稳定性: 不稳定
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        // 递归实现
        recursive(nums, 0, n-1);
        return nums;
    }

    /**
     * 递归实现（原地分割版）
     * @param nums
     * @param start 本次调用的起始点，也是上次调用的基准变量的最终位置（start-1/start+1）
     * @param end
     * @return
     */
    public static void recursive(int[] nums, int start, int end) {
        if (start >= end) {
            return;
        }
        // 把中间位置作为基准点
        int mid = (start + end)/2;
        // mid == start，代表此时end-start=1，那如果此时nums[end]>nums[start]，说明这两个元素是已有序的
        if (mid == start && nums[start] < nums[end]) {
            return;
        }
        // 保存定义本次递归的起始位置
        int storeIndex = start;
        // 将中间变量和最后一个变量交换位置
        swap(nums, mid, end);
        int temp;
        for (int i = start; i < end; i++) {
            if (nums[i] < nums[end]) {
                if (i != start) {
                    temp = nums[start];
                    nums[start] = nums[i];
                    nums[i] = temp;
                }
                start++;
            }
        }
        // 将当前start位置的变量和最后一个变量交换位置
        swap(nums, start, end);
        // 递归左序列
        recursive(nums, storeIndex, start-1);
        // 递归右序列
        recursive(nums, start+1, end);
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = Test.gen(10);
        System.out.print("原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n自动排序后：");
        Arrays.stream(nums).sorted().forEach((value) -> System.out.printf("%s%s", value, ", "));
        System.out.printf("%n算法排序后：");
        Arrays.stream(sort(nums)).forEach(r -> System.out.printf("%s%s", r, ", "));
    }

}
