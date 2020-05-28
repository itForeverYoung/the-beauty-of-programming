package sort.heap;

import sort.Test;

import java.util.Arrays;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class HeapSort {

    /**
     * 首先，
     * 1. 需要理解堆积的特性
     *     1. 最大堆，父节点大于子节点
     *     2. 最小堆，父节点小于子节点
     * 2. 理解下堆节点的访问位置
     *     1. 父节点i的左子节点在位置(2i+1);
     *     2. 父节点i的右子节点在位置(2i+2);
     *     3. 子节点i的父节点在位置{floor((i-1)/2);
     *
     * 堆排序，
     * 肯定是选择2.1的方案啦，这也是wiki上推荐的方案；
     * 2.2是我尝试一下的方案（空间复杂度变成了O(n)）
     * 1. 先将数组堆化
     * 2.1. 如果使用最大堆，每次都将堆顶元素弹出放到最后一个位置，然后index--
     * 2.2. 如果使用最小堆，则需要新建一个数组，每次都将堆顶元素弹出放到新数组的第i个位置，然后将堆A[0]=正无穷，然后将数组重新堆化
     * 时间复杂度: O(nlogN)
     * 空间复杂度: O(1)
     * 稳定性: 不稳定
     * @param nums 待排序数组
     * @param max true: 最大堆, false: 最小堆
     * @return
     */
    public static int[] sort(int[] nums, boolean max) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length-1;
        // TODO 为什么从这个index开始
        int beginIndex = (nums.length >> 1) - 1;
        // 第一步，将数组堆化
        for (int i=beginIndex; i>=0; i--) {
            if (max) {
                maxHeapify(nums, i, n);
            } else {
                minHeapify(nums, i, n);
            }
        }
        // 第二步，对堆化的数据排序
        if (max) {
            for (int i=n; i>0; i--) {
                /**
                 * 最大堆，堆化后第0个元素是最大的，
                 * 所以此处:
                 * 1. 将第0个元素移到最后一位
                 * 2. 然后将剩下的(0, n-1)个元素重新堆化
                 */
                swap(nums, 0 , i);
                // 将去除了最后一个元素的数组重新堆化
                maxHeapify(nums, 0, i-1);
            }
        } else {
            int[] result = new int[nums.length];
            for (int i=0; i<nums.length; i++) {
                /**
                 * 最小堆，堆化后第0个元素是最小的，
                 * 所以此处:
                 * 1. 将第0个元素添加到result数组中，i++
                 * 2. 将Integer的最大值赋值给num[0]
                 * 3. 重新将nums数组堆化
                 */
                result[i] = nums[0];
                nums[0] = Integer.MAX_VALUE;
                // 将原数组重新堆化
                minHeapify(nums, 0, n);
            }
            nums = result;
        }
        return nums;
    }

    public static void maxHeapify(int[] nums, int index, int len) {
        // 当前节点的左子节点为2i+1
        int li = (index << 1) + 1;
        // 当前节点的左子节点为2i+2
        int ri = li + 1;
        // 最大子节点值的索引，默认是左子节点
        int cMax = li;
        // 左子节点索引超过计算范围，直接返回
        if (li > len) {
            return;
        }
        // 然后比较左右子节点，到底谁大
        if (ri <= len && nums[li] < nums[ri]) {
            cMax = ri;
        }
        // 如果发现子节点比父节点大，就要调整子节点和父节点的位置
        if (nums[cMax] > nums[index]) {
            swap(nums, cMax, index);
            // 调整完子节点和父节点位置后，还需要判断新的父节点和其子节点是否满足堆的关系
            maxHeapify(nums, cMax, len);
        }
    }

    public static void minHeapify(int[] nums, int index, int len) {
        // 当前节点的左子节点为2i+1
        int li = (index << 1) + 1;
        // 当前节点的左子节点为2i+2
        int ri = li + 1;
        // 最小子节点值的索引，默认是左子节点
        int cMin = li;
        // 左子节点索引超过计算范围，直接返回
        if (li > len) {
            return;
        }
        // 然后比较左右子节点，到底谁小
        if (ri <= len && nums[li] > nums[ri]) {
            cMin = ri;
        }
        // 如果发现子节点比父节点小，就要调整子节点和父节点的位置
        if (nums[cMin] < nums[index]) {
            swap(nums, cMin, index);
            // 调整完子节点和父节点位置后，还需要判断新的父节点和其子节点是否满足堆的关系
            minHeapify(nums, cMin, len);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = Test.gen(10);
        /*System.out.print("原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n调用自动排序后：");
        Arrays.stream(nums).sorted().forEach((value) -> System.out.printf("%s%s", value, ", "));
        System.out.printf("%n最大堆排序后：");
        Arrays.stream(sort(nums, true)).forEach(r -> System.out.printf("%s%s", r, ", "));
        nums = Test.gen(10);*/
        System.out.printf("%n原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n调用自动排序后：");
        Arrays.stream(nums).sorted().forEach((value) -> System.out.printf("%s%s", value, ", "));
        System.out.printf("%n最小堆排序后：");
        Arrays.stream(sort(nums, false)).forEach(r -> System.out.printf("%s%s", r, ", "));
    }

}
