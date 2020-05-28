package sort.merge;

import sort.Test;

import java.util.Arrays;

/**
 * @author zhanj566
 * @date 2020/5/27 10:17 AM
 **/
public class TwoMergeSort {

    /**
     * 二路归并排序
     * 左右递归，直到待排序的数组长度为2/1时，对比后返回有序数组
     * 得到左右有序数组后，开始进行归并，即从两个有序子序列的开始进行比较
     * 时间复杂度: O(nlogN)
     * 空间复杂度: O(n)
     * 稳定性: 稳定
     * 注：归并排序是稳定的排序，并且它的性能不受输入数据的影响，时间复杂度恒是O(nlogN)，缺点是需要占用额外的内存空间
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        return recursive(nums, 0, n-1);
        // cycle(nums, 0, n-1);
    }

    /**
     * 循环写法
     * @param nums
     * @param start
     * @param end
     */
    public static void cycle(int[] nums, int start, int end) {

    }

    /**
     * 我的递归写法
     * @param nums
     * @param start
     * @param end
     * @return
     */
    public static int[] recursive(int[] nums, int start, int end) {
        int[] result = new int[end-start+1];
        if (end - start > 1) {
            int mid = (start + end)/2;
            // 递归左序列
            int[] left = recursive(nums, start, mid);
            // 递归右序列
            int[] right = recursive(nums, mid+1, end);
            // 执行到这里，左右序列一定是各自有序的，所以开始归并
            int i = 0;
            int j = 0;
            int index = 0;
            // 循环退出时，一定是left或者right遍历到了最后一个元素
            while (i < left.length && j < right.length) {
                if (left[i] < right[j]) {
                    result[index++] = left[i++];
                } else {
                    result[index++] = right[j++];
                }
            }
            // 所以下面分别判断，是left遍历完了，还是right遍历完了
            // 然后，把后续元素直接添加到目标数组中
            while (i < left.length) {
                result[index++] = left[i++];
            }
            while (j < right.length) {
                result[index++] = right[j++];
            }
        } else if (end - start == 1) {
            result[0] = nums[start] > nums[end] ? nums[end] : nums[start];
            result[1] = nums[start] < nums[end] ? nums[end] : nums[start];
        } else {
            result[0] = nums[start];
        }
        return result;
    }

    /**
     * wiki sort
     * @param nums
     * @return
     */
    public static int[] wikiSort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        int[] result = new int[n];
        recursive(nums, result, 0, n-1);
        return nums;
    }

    /**
     * wiki上的递归写法
     * @param nums
     * @param result
     * @param start
     * @param end
     * @return
     */
    public static void recursive(int[] nums, int[] result, int start, int end) {
        if (start >= end) {
            return;
        }
        int mid = (start + end)/2;
        // 递归左序列
        recursive(nums, result, start, mid);
        // 递归右序列
        recursive(nums, result, mid+1, end);
        // 执行到这里，左右序列一定是各自有序的，所以开始归并\
        // 左序列起始点
        int i = start;
        // 右序列起始点
        int j = mid+1;
        // 左右序列组合时的起始点
        int index = start;
        // 下面这个循环退出时，一定是左序列或者右序列遍历到了最后一个有效元素
        while (i <= mid && j <= end) {
            result[index++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        // 所以下面分别判断，是左序列遍历完了，还是右序列遍历完了
        // 然后，把后续元素直接添加到目标数组中
        while (i <= mid) {
            result[index++] = nums[i++];
        }
        while (j <= end) {
            result[index++] = nums[j++];
        }
        for (int x=start; x <= end; x++) {
            nums[x] = result[x];
        }
    }

    public static void main(String[] args) {
        int[] nums = Test.gen(20);
        System.out.print("原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n调用自动排序后：");
        Arrays.stream(nums).sorted().forEach((value) -> System.out.printf("%s%s", value, ", "));
        System.out.printf("%n我的算法排序后：");
        Arrays.stream(sort(nums)).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%nwiki算法排序后：");
        Arrays.stream(wikiSort(nums)).forEach(r -> System.out.printf("%s%s", r, ", "));
    }

}
