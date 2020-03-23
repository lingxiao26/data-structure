import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

class Solution_40 {
    // 堆
    public int[] getLeastNumbers2(int[] arr, int k) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a));
        int[] res = new int[k];

        for (int value : arr) {
            pq.add(value);
        }

        for (int i = 0; i < k; i++)
            res[i] = pq.remove();

        return res;
    }

    // 快排
    public int[] getLeastNumbers(int[] arr, int k) {
        if ( k <= 0 )
            return new int[0];
        // [0, k-1]
        return sort(arr, 0, arr.length - 1, k - 1);
        //return null;
    }

    // sort [l, k]
    private int[] sort(int[] arr, int l, int r, int k) {
        int p = partition(arr, l, r);
        if (p == k)
            return Arrays.copyOfRange(arr, l, k + 1);
        if (p > k)
            return sort(arr, l, p - 1, k);
        else
            return sort(arr, p + 1, r, k);
    }

    private int partition(int[] arr, int l, int r) {
        // 生成一个在[l...r]范围内的随机数
        int x = (int) (Math.random() * (r - l + 1) + l);
        // 交换数组中下标为x与l的值, 主要是为了使得标定点随机
        swap(arr, l, x);
        int v = arr[l];

        int i = l + 1, j = r; // arr[l+1...i) <= v <= arr(j...r]
        while ( true ) {
            while ( i <= r   && arr[i] < v )  i++;
            while ( j >= l+1 && arr[j] > v )  j--;
            if ( i > j ) break;
            swap(arr, i, j);
            i++;
            j--;
        } // 循环结束后, i处在第一个大于等于v的位置; j处在第一个小于等于v的位置

        swap(arr, l, j);
        return j;
    }

    private void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] arr = {0,0,0,1,2,2,3,7,6,1};
        int k = 3;
        Solution_40 sol = new Solution_40();
        int[] res = sol.getLeastNumbers(arr, k);
        for (int re : res) {
            System.out.print(re + " ");
        }
    }
}