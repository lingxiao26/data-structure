public class SumOfArray {

    public static int sumNR(int[] arr) {
        int sum = 0;
        for (int i=0; i<arr.length; i++) {
            sum += arr[i];
        }
        return sum;
    }

    public static int sum(int[] arr) {
        int n = arr.length;
        // 求数组arr[0...n-1]的和
        return sum(arr, n-1);
    }

    //
    private static int sum(int[] arr, int n) {
        if (n == 0)
            return arr[0];

        return arr[n] + sum(arr, n-1);
    }

    // 求arr[l, n)区间内所有数字的和
    private static int sum2(int[] arr, int l) {
        if (l == arr.length)
            return 0;

        return arr[l] + sum2(arr, l+1);
    }

    // arr[0] + arr[1] + ... + arr[n-1] + arr[n]

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        int sum = sum(arr);
        System.out.println(sum);

    }
}
