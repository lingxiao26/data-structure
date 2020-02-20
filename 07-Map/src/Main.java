import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * 词频统计, 统计<<傲慢与偏见>>中单词出现的频率
     * 比较用链表实现的映射和用二分搜索树实现的映射之间的性能诧异
     * 比较的结果:
     *      =============LinkedListMap=============
     *          pride-and-prejudice
     *          Total Words: 125901
     *          Difference Words: 6530
     *          Frequency of PRIDE: 53
     *          Frequency of PREJUDICE: 11
     *          Spent 12.238s
     *      =============BSTMap=============
     *          pride-and-prejudice
     *          Total Words: 125901
     *          Difference Words: 6530
     *          Frequency of PRIDE: 53
     *          Frequency of PREJUDICE: 11
     *          Spent 0.07s
     *
     *   可以看出,使用二分搜索树实现的映射时间复杂度远小于使用链表实现的映射
     *   时间复杂度:
     *      LinkedListMap    O(N)
     *      BSTMap           O(logN)
     */
    @Test
    public void testPerformance() {
        LinkedListMap<String, Integer> linkedListMap = new LinkedListMap<>();
        BSTMap<String, Integer> bstMap = new BSTMap<>();
        double time1 = testMap(linkedListMap);
        System.out.println("Spent " + time1 + "s");
        double time2 = testMap(bstMap);
        System.out.println("Spent " + time2 + "s");
    }

    private static double testMap(Map<String, Integer> map) {
        String fileName = "pride-and-prejudice.txt";
        List<String> words = new ArrayList<>();
        FileOperation.readFile(fileName, words);
        long startTime = System.currentTimeMillis();
        for (String word : words) {
            if (map.contains(word)) {
                map.set(word, map.get(word)+1);
            } else
                map.add(word, 1);
        }
        System.out.println("============="+map.getClass().getName()+"=============");
        System.out.println("pride-and-prejudice");
        System.out.println("Total Words: " + words.size());
        System.out.println("Difference Words: " + map.getSize());
        System.out.println("Frequency of PRIDE: " + map.get("pride"));
        System.out.println("Frequency of PREJUDICE: " + map.get("prejudice"));
        long endTime = System.currentTimeMillis();
        return (endTime-startTime)/1000.0;
    }
}
