import org.junit.Test;

public class Main {

    @Test
    public void testLinkedList() {
        LinkedList<Integer> list = new LinkedList<>();
        list.addLast(8);
        list.addLast(9);
        list.addLast(10);
        System.out.println(list);
        list.removeElement(9);
        System.out.println(list);
    }
}
