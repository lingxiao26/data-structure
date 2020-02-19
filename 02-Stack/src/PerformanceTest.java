import java.util.Random;

public class PerformanceTest {

    public static double testStack(Stack<Integer> stack, int opCount) {
        long startTime = System.currentTimeMillis();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            stack.pop();

        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000.0;
    }

    public static void main(String[] args) {
        int opCount = 100000;
        ArrayStack<Integer> arrayStack = new ArrayStack<>();
        LinkedListStack<Integer> linkedListStack = new LinkedListStack<>();

        double time1 = testStack(arrayStack, opCount);
        double time2 = testStack(linkedListStack, opCount);

        System.out.println("ArrayStack time: " + time1 + "s");
        System.out.println("LinkedListStack time: " + time2 + "s");

        // 因为stack只操作栈顶,不管是用数组还是链表来实现,时间复杂度都是O(1)
        // 数组是把最后一个元素当做栈顶
        // 链表是把第一个元素当做栈顶
    }
}
