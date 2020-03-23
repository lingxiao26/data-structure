import java.sql.Statement;

public class Solution_141 {
    public boolean hasCycle(ListNode head) {
        if (head == null)
            return false;

        ListNode slow = head;
        ListNode fast = head;

        while ( slow != null && fast != null ) {
            slow = slow.next;
            fast = fast.next.next;

            if ( slow == fast )
                return true;
        }

        return false;
    }

    public static void main(String[] args) {


        System.out.println(Math.pow(10, 2));
    }
}
