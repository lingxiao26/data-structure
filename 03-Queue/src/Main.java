public class Main {
    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>();

        for (int i=0; i<5; i++) {
            queue.enqueue(i+1);
            System.out.println(queue);
        }
        queue.dequeue();
        System.out.println(queue);

        System.out.println("============= Loop Queue ===============");
        LoopQueue<Integer> loopQueue = new LoopQueue<>();
        for (int i=0; i<11; i++) {
            loopQueue.enqueue(i+1);
            System.out.println(loopQueue);
        }
        System.out.println("============= dequeue ==================");
        for (int i=0; i<6; i++) {
            loopQueue.dequeue();
            System.out.println(loopQueue);
        }
    }
}
