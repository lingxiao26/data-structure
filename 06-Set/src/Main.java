import java.util.ArrayList;

public class Main {

    public static double testSet(Set<String> set, String filename) {
        long startTime = System.currentTimeMillis();

        System.out.println("========="+set.getClass().getName()+"=========");
        ArrayList<String> words = new ArrayList<>();
        if (FileOperation.readFile(filename, words)) {
            System.out.println("Total words: " + words.size());
            for (String word : words) {
                set.add(word);
            }
            System.out.println("Total difference words: " + set.getSize());
        }

        long endTime = System.currentTimeMillis();

        return (endTime-startTime)/1000.0;
    }

    public static void main(String[] args) {
        BSTSet<String> bstSet = new BSTSet<>();
        LinkedListSet<String> linkedListSet = new LinkedListSet<>();

        String fileName1 = "E:\\data-structure\\06-Set\\pride-and-prejudice.txt";
        String fileName2 = "E:\\data-structure\\06-Set\\a-tale-of-two-cities.txt";

        double time1 = testSet(bstSet, fileName2);
        System.out.println(time1);
        double time2 = testSet(linkedListSet, fileName2);
        System.out.println(time2);
    }
}
