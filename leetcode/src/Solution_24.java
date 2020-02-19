/**
 * 24. 两两交换链表中的节点
 *
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例:
 *
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 */
public class Solution_24 {
    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;


        while (prev.next != null && prev.next.next != null) {
            ListNode node1 = prev.next;
            ListNode node2 = node1.next;
            ListNode next = node2.next;

            node2.next = node1;
            node1.next = next;
            prev.next = node2;

            prev = node1;
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode res = new Solution_24().swapPairs(head);
        System.out.println(res);
    }
}
