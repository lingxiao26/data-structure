/**
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
class Solution_206 {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode resNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return resNode;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6};
        ListNode head = new ListNode(arr);

        ListNode res = new Solution_206().reverseList(head);
        System.out.println(res);
    }
}