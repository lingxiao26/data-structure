/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class Solution_92_2 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode superior = dummyHead;

        for ( int i = 1; i < m; i ++ ) {
            superior = superior.next;
        }

        ListNode prev = null;
        ListNode cur = superior.next;

        for ( int i = 0; i <= n-m; i ++ ) {
            ListNode next = cur.next;

            cur.next = prev;
            prev = cur;
            cur = next;
        }

        superior.next.next = cur;
        superior.next = prev;
        return head;
    }
}
