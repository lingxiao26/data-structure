import java.util.Arrays;

/**
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 *
 * 示例：
 *
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 *
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 *
 * 给定的 n 保证是有效的。
 */
public class Solution_19 {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode p = dummyHead;
        ListNode q = dummyHead;

        for (int i=0; i<n+1; i++) {
            q = q.next;
        }

        while (q != null) {
            p = p.next;
            q = q.next;
        }

        ListNode delNode = p.next;
        p.next = delNode.next;
        delNode.next = null;
        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode res = new Solution_19().removeNthFromEnd(head, 4);
        System.out.println(res);

    }
}
