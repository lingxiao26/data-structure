import java.util.List;
import java.util.Random;

public class TestHelper {

    public static Integer[] generateRandomArray(int n) {
        Random random = new Random();
        Integer[] arr = new Integer[n];
        for (int i=0; i<n; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }

    public static boolean isSorted(List<Integer> list) {
        for (int i=1; i<list.size(); i++) {
            if (list.get(i-1) > list.get(i))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Integer[] arr = generateRandomArray(10);
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
