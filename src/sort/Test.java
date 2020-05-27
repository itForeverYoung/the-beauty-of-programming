package sort;

import sort.bubble.BubbleSort;
import sort.heap.HeapSort;
import sort.insertion.InsertionSort;
import sort.merge.MultiMergeSort;
import sort.merge.TwoMergeSort;
import sort.quick.QuickSort;
import sort.selection.SelectionSort;
import sort.shell.ShellSort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author zhanj566
 * @date 2020/5/27 10:19 AM
 **/
public class Test {

    /**
     * 一些概念，写在最前。
     * 参考文档：
     * https://www.cnblogs.com/onepixel/articles/7674659.html
     * https://zhuanlan.zhihu.com/p/50479555
     * 稳定：如果a原本在b前面，而a=b，排序之后a仍然在b的前面。
     * 不稳定：如果a原本在b的前面，而a=b，排序之后a可能会出现在b的后面。
     * 时间复杂度：对排序数据的总的操作次数。反映当n变化时，操作次数呈现什么规律。
     * 空间复杂度：是指算法在计算机内执行时所需存储空间的度量，它也是数据规模n的函数。
     * 时间复杂度量级：
     *      常数阶: O(1)
     *      对数阶: O(logN)
     *      线性阶: O(n)
     *      线性对数阶: O(nlogN)
     *      平方阶: O(n^2)
     *      平方阶变形: O(n*m)
     *      立方阶: O(n^3)
     *      K次方阶: O(n^k)
     *      指数阶: O(2^n)
     * 空间复杂度量级：
     *      常数阶: O(1)
     *      线性阶: O(n)
     *      平方阶: O(n^2)
     * @param args
     */
    public static void main(String[] args) {
        // 冒泡排序
        System.out.print("冒泡排序结果：");
        Arrays.stream(BubbleSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 快速排序
        System.out.printf("%n快速排序结果：");
        Arrays.stream(QuickSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 二路归并排序
        System.out.printf("%n二路归并结果：");
        Arrays.stream(TwoMergeSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 多路归并排序
        System.out.printf("%n多路归并结果：");
        Arrays.stream(MultiMergeSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 插入排序
        System.out.printf("%n插入排序结果：");
        Arrays.stream(InsertionSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 希尔排序
        System.out.printf("%n希尔排序结果：");
        Arrays.stream(ShellSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 选择排序
        System.out.printf("%n选择排序结果：");
        Arrays.stream(SelectionSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        // 堆排序
        System.out.printf("%n堆排序结果：");
        Arrays.stream(HeapSort.sort(gen(10))).forEach(r -> System.out.printf("%s%s", r, ", "));
        //
    }

    public static int[] gen(int length) {
        int[] nums = new int[length];
        Random random = new Random();
        for (int i = 0; i < nums.length; i++) {
            nums[i] = random.nextInt(100);
        }
        return nums;
    }

}
