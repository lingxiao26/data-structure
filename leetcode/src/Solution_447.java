import java.util.HashMap;

/**
 * 给定平面上 n 对不同的点，“回旋镖” 是由点表示的元组 (i, j, k) ，
 * 其中 i 和 j 之间的距离和 i 和 k 之间的距离相等（需要考虑元组的顺序）。
 * 找到所有回旋镖的数量。你可以假设 n 最大为 500，所有点的坐标在闭区间 [-10000, 10000] 中。
 *
 * 示例:
 * 输入:
 * [[0,0],[1,0],[2,0]]
 * 输出:
 * 2
 * 解释:
 * 两个回旋镖为 [[1,0],[0,0],[2,0]] 和 [[1,0],[2,0],[0,0]]
 */
class Solution_447 {
    // 使用map ==> 使用双重循环遍历points
    // K存储points中point[i]到point[0...points.length]的距离
    // V存储距离的个数
    // 时间复杂度: O(n^2)
    public int numberOfBoomerangs(int[][] points) {

        int res = 0;
        for ( int i = 0; i < points.length; i ++ ) {
            HashMap<Integer, Integer> map = new HashMap<>();
            for ( int j = 0; j < points.length; j ++ ) {
                if ( j != i ) {
                    map.merge( distance(points[i], points[j]), 1, (oldVal, newVal)->(oldVal+1) );
                }
            }

            for (Integer count : map.values()) {
                res += count * (count - 1);
            }
        }

        return res;
    }

    // 求pa到pb 两点间的距离
    private int distance(int[] pa, int[] pb) {
        return (pa[0]-pb[0]) * (pa[0]-pb[0]) +
               (pa[1]-pb[1]) * (pa[1]-pb[1]);
    }
}