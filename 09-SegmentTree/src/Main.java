import javax.swing.text.Segment;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = {-2, 0, 3, -5, 2, -1};

        SegmentTree<Integer> tree = new SegmentTree<>(arr, (a, b) -> a + b);

        System.out.println(tree);

        Integer query = tree.query(4, 5);
        System.out.println(query);
        System.out.println("==============");

        tree.set(2, 2);
        System.out.println(tree);

    }
}
