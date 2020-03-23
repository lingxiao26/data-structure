import com.sun.org.apache.xml.internal.utils.Trie;
import javafx.util.Pair;

import java.util.Random;

public class Main {

    /**
     *  比较通过heapify的方式创建一个堆和分别把一个一个元素添加到一个堆中创建堆之间的性能差异
     *  测试结果:
     *      With Heapify: 0.27s
     *      Without Heapify: 3.044s
     */
    private static double testHeap(Integer[] testData, boolean isHeapify) {
        long startTime = System.currentTimeMillis();
        MaxHeap<Integer> heap;

        if (isHeapify) {
            heap = new MaxHeap<>(testData);
        } else {
            heap = new MaxHeap<>();
            for (int num : testData) {
                heap.add(num);
            }
        }

        long endTime = System.currentTimeMillis();
        // test heap is success
        int[] arr = new int[heap.getSize()];
        for (int i=0; i<arr.length; i++) {
            arr[i] = heap.extractMax();
        }

        for (int i=1; i<arr.length; i++) {
            if (arr[i-1] < arr[i])
                System.out.println("create heap failed");
        }

        return (endTime-startTime)/1000.0;
    }

    public static void main(String[] args) {

        int n = 10000000;
        Random random = new Random();
        Integer[] testData = new Integer[n];

        for (int i=0; i<n; i++) {
            testData[i] = random.nextInt(Integer.MAX_VALUE);
        }


        double time1 = testHeap(testData, true);
        double time2 = testHeap(testData, false);


        System.out.println("With Heapify: " + time1 + "s");
        System.out.println("Without Heapify: " + time2 + "s");

    }
}
