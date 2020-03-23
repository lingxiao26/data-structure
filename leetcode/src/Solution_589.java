import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution_589 {

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;


    public List<Integer> preorder(Node root) {
        List<Integer> res = new ArrayList<>();

        Stack<Node> stack = new Stack<>();
        if (root == null)
            return res;
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);


            for (Node n : node.children) {
                if ( n != null )
                    stack.push(n);
            }

        }

        return res;
    }


}
