public class Main {
    public static void main(String[] args) {
        String[] strs = {"pig", "dog", "cat"};
        Trie trie = new Trie();

        for (String str : strs) {
            trie.add(str);
        }

        System.out.println(trie.contains("pig"));
        System.out.println(trie.contains("pigg"));
        System.out.println(trie.contains("dog"));
        System.out.println(trie.contains("cat"));
        System.out.println(trie.getSize());
        System.out.println("=============");
        System.out.println(trie.isPrefix("di"));
    }
}
