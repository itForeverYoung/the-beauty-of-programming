package sort.radix;

import sort.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class RadixSort {

    /**
     * 基数排序
     * 思路: 将所有待比较数值（正整数）统一为同样的数位长度，数位较短的数前面补零。
     * 时间复杂度: O(n*k)
     * 空间复杂度: O(n+k)
     * 稳定性: 稳定
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        int max = nums[0];
        // 循环一次，找到原数组的最大值
        for (int i=1; i<n; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }
        // 初始化counter二维数组
        int counterLen = 10;
        int[][] counter;
        // 计算radix值
        int radix = 0;
        while (max > 0) {
            max /= 10;
            radix++;
        }
        int mod = 10;
        int dev = 1;
        for (int i=0; i<radix; i++, mod *= 10, dev *= 10) {
            /**
             * 这个写法有两个缺点
             * 1. 数组nums只有n个元素，但是初始化的counter数组却有10*n个元素格，导致了空间浪费
             * 2. 每次radix循环，都要重新初始化counter数组，
             *      1. 要不就是循环遍历置为零，增加时间复杂度
             *      2. 要不就是重新new一个，增加空间复杂度
             * 二维数组改成用ArrayList<List<Integer>>()应该可以避免问题1
             * 二维数组改成用ArrayList<LinkedBlockingQueue>()应该可以避免问题2，每次在尾部插入，头部弹出
             */
            counter = new int[counterLen][n];
            for (int j=0; j<n; j++) {
                int index = (nums[j] / dev) % mod;
                // 这里需要将当前元素添加到对应桶里
                counter[index][j] = nums[j];
            }
            int oriIndex = 0;
            for (int x=0; x<counterLen; x++) {
                for (int m=0; m<n; m++) {
                    if (counter[x][m] != 0) {
                        nums[oriIndex++] = counter[x][m];
                    }
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = Test.gen(10);
        System.out.printf("%n原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n自动排序后：");
        Arrays.stream(nums).sorted().forEach((value) -> System.out.printf("%s%s", value, ", "));
        System.out.printf("%n基数排序后：");
        Arrays.stream(sort(nums)).forEach(r -> System.out.printf("%s%s", r, ", "));
    }

}
