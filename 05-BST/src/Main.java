import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Integer[] arr = TestHelper.generateRandomArray(1000);
        BST<Integer> bst = new BST<>(arr);
        List<Integer> list = new LinkedList<>();
        while (!bst.isEmpty()) {
            list.add(bst.removeMin());
        }
        //System.out.println(list);
        System.out.println(TestHelper.isSorted(list));
    }

    @Test
    public void testRemove() {
        Integer[] arr = TestHelper.generateRandomArray(7);
        Integer[] arr2 = {5, 3, 6, 2, 4, 8};
        BST<Integer> bst = new BST<>(arr2);
//        bst.removeMin();
        bst.remove(3);
        System.out.println(bst);

    }

    @Test
    public void testDepth() {
        Integer[] arr = {5, 3, 6, 2, 4, 8};
        BST<Integer> bst = new BST<>(arr);
        System.out.println(bst.depth());
    }
}
