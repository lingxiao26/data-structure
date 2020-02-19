import java.util.Random;

public class PerformanceTest {

    public static double testQueue(Queue<Integer> queue, int opCount) {
        long startTime = System.currentTimeMillis();

        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            queue.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            queue.dequeue();

        long endTime = System.currentTimeMillis();
        return (endTime - startTime) / 1000.0;
    }

    public static void main(String[] args) {
        int opCount = 1000000;
        ArrayQueue<Integer> arrayQueue = new ArrayQueue<>();
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        LinkedListQueue<Integer> linkedListQueue = new LinkedListQueue<>();

//        double time1 = testQueue(arrayQueue, opCount);
        double time2 = testQueue(loopQueue, opCount);
        double time3 = testQueue(linkedListQueue, opCount);

//        System.out.println("Array Queue time: " + time1 + "s");
        System.out.println("Loop Queue time: " + time2 + "s");
        System.out.println("LinkedList Queue time: " + time3 + "s");

        // opCount = 1000000时
        //Array Queue time: 358.39s
        //Loop Queue time: 0.071s

        // opCount = 100000时
        //Array Queue time: 3.384s
        //Loop Queue time: 0.01s
    }
}
