import java.util.ArrayList;

/**
 * 303. 区域和检索 - 数组不可变
 给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。

 示例：

 给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

 sumRange(0, 2) -> 1
 sumRange(2, 5) -> -1
 sumRange(0, 5) -> -3
 说明:

 你可以假设数组不可变。
 会多次调用 sumRange 方法。
 */
class NumArray {

    // sum[i] = nums[]前i个元素的和
    private int[] sum;

    public NumArray(int[] nums) {
        sum = new int[nums.length + 1];
        sum[0] = 0;
        for (int i=1; i<sum.length; i++) {
            sum[i] = sum[i-1] + nums[i-1];
        }
    }


    public int sumRange(int i, int j) {
        return sum[j+1] - sum[i];
    }

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
    }
}

