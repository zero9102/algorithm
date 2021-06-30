import java.util.PriorityQueue;

/**
 *  https://mp.weixin.qq.com/s/mTFlRh7jNn_84UhYnQv1fw
 */
public class TopKProblem {

    /*
    针对一般的 top K 问题，一般都会默认 K 很小，所以一般的 top K 问题，可以选择使用堆来解决。
    堆有个重要的性质：每个结点的值均不大于其左右孩子结点的值，则堆顶元素即为整个堆的最小值。
    JDK 中 PriorityQueue 实现了堆这个数据结构堆，通过指定 comparator 字段来表示小顶堆或大顶堆，默认为自然序（natural ordering）。
    小顶堆解决 Top K 问题的思路：小顶堆维护当前扫描到的最大 K 个数，其后每一次扫描到的元素，若大于堆顶则入堆，然后删除堆顶；
      依此往复，直至扫描完所有元素
     */
    public static void main(String[] args) {
        int[] nums = new int[] {20, 10, 30, 50, 80, 60};
        System.out.println(findKthLargest(nums, 3));
        long[] qs = new long[]{20, 10, 30, 50, 80, 60};
        System.out.println(quickSelect(qs, 0, 5, 3));
    }

    public static int findKthLargest(int[] nums, int k) {
        PriorityQueue<Integer> minQueue = new PriorityQueue<>(k);
        for (int num : nums) {
            System.out.println("peek: " + minQueue.peek());
            if (minQueue.size() < k || num > minQueue.peek()) {
                // insert the specified element.
                minQueue.offer(num);
            }
            if (minQueue.size() > k) {
               Integer tmp =  minQueue.poll();
                System.out.println("poll---" + tmp);
            }
        }
        return minQueue.peek();
    }

    public static long quickSelect(long[] nums, int start, int end, int k) {
        if (start == end) {
            return nums[start];
        }
        int left = start;
        int right = end;
        long pivot = nums[(start + end) / 2];
        while (left <= right) {
            while (left <= right && nums[left] > pivot) {
                left++;
            }
            while (left <= right && nums[right] < pivot) {
                right--;
            }
            if (left <= right) {
                long temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        if (start + k - 1 <= right) {
            return quickSelect(nums, start, right, k);
        }
        if (start + k - 1 >= left) {
            return quickSelect(nums, left, end, k - (left - start));
        }
        return nums[right + 1];
    }

}
