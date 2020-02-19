public class Main {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>();

        for (int i=0; i<10; i++) {
            arr.insert(i, i);
        }
        System.out.println("Before resize");
        System.out.println(arr);
        System.out.println("====================");
        arr.insert(2, 100);
        System.out.println(arr);

        arr.remove(4);

        System.out.println(arr);
        arr.addLast(5);
        System.out.println(arr);



    }
}
