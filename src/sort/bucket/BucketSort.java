package sort.bucket;

import sort.insertion.InsertionSort;

import java.util.Arrays;

/**
 * @author zhanj566
 * @date 2020/5/27 10:16 AM
 **/
public class BucketSort {

    /**
     * 桶排序以下列程序进行：
     *      设置一个定量的数组当作空桶子。
     *      寻访序列，并且把项目一个一个放到对应的桶子去。
     *      对每个不是空的桶子进行排序。
     *      从不是空的桶子里把项目再放回原来的序列中。
     * 桶排序（计数排序的升级版）
     * 时间复杂度: 最好 O(n), 最坏 O(n^2), 平均 O(n+k)
     * 空间复杂度: O(n+k)
     * 稳定性: 稳定
     * 注: 方法内对0的特殊处理，是因为我用的是二维数组，如果换成ArrayList<List<Integer>>，那些代码就没啥用了
     * @param nums
     * @return
     */
    public static int[] sort(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return nums;
        }
        int n = nums.length;
        /**
         * 这里写的不好
         * 因为原数组只有n个元素，但是这里初始化的二维数组有10*n个元素格，导致了空间浪费，空间复杂度变大
         * 将二维数组改成ArrayList<List<Integer>>就可以避免这个问题
         */
        // 这里维护一个元素为0的数量-----1
        int zeroCount = 0;
        int max = nums[0];
        int min = nums[0];
        for (int i=1; i<n; i++) {
            if (max < nums[i]) {
                max = nums[i];
            }
            if (min > nums[i]) {
                min = nums[i];
            }
        }
        // 比较最大值和最小值的差值
        int diff = max - min;
        // 假设我们取原数组长度的四分之一作为桶的数量
        int bucketLen = n/4;
        int[][] bucket = new int[bucketLen][n];
        // 每个桶的数值区间
        int section = diff / (bucketLen - 1);
        int newIndex;
        for (int i=0; i<n; i++) {
            if (0 == nums[i]) {
                zeroCount++;
                continue;
            }
            // 这里是关键，要找到合适的映射函数，将原数组映射到各个桶内

            // 将当前的数值除以桶区间，得到当前数值要进入哪个桶
            newIndex = (nums[i] / section)-1;
            if (newIndex < 0) {
                newIndex = 0;
            }
            bucket[newIndex][i] = nums[i];
        }
        int index = 0;
        // 这里重新插入原数组之前，先将zeroCount个0插入-----2
        while (zeroCount > 0) {
            nums[index++] = 0;
            zeroCount--;
        }
        for (int i=0; i<bucketLen; i++) {
            // 调用插入排序，把单个桶内的元素进行排序
            InsertionSort.sort(bucket[i]);
            for (int j=0; j<n; j++) {
                /**
                 * 这里写的不好
                 * 因为我使用的是二维数组，而Java的int二维数组默认初始化为0，
                 * 这就导致，如果原数组中有0，我这边会忽略，导致最终的排序结果有问题
                 * 同样的，将二维数组改成ArrayList<List<Integer>>就可以避免这个问题，并且上面的1,2代码段可以去除
                 */
                if (bucket[i][j] != 0) {
                    nums[index++] = bucket[i][j];
                }
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{95, 44, 0, 53, 19, 91, 96, 33, 35, 32, 58, 70, 45, 18, 7, 86, 96, 62, 1, 50};
        System.out.printf("%n原数组：");
        Arrays.stream(nums).forEach(r -> System.out.printf("%s%s", r, ", "));
        System.out.printf("%n调用自动排序后：");
        Arrays.stream(nums).sorted().forEach((value) -> System.out.printf("%s%s", value, ", "));
        System.out.printf("%n最小堆排序后：");
        Arrays.stream(sort(nums)).forEach(r -> System.out.printf("%s%s", r, ", "));
    }

}
