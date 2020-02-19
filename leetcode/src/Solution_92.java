/**
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 *
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 */
public class Solution_92 {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || head.next == null)
            return head;

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prev1 = dummyHead;

        for (int i=1; i<m; i++) {
            prev1 = prev1.next;
        }

        ListNode cur = prev1.next;

        ListNode prev2 = null;
        for (int i=m; i<=n; i++) {
            ListNode next = cur.next;
            cur.next = prev2;
            prev2 = cur;
            cur = next;
        }
        // 2->5
        prev1.next.next = cur;
        // 1->4
        prev1.next = prev2;

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        ListNode head = new ListNode(arr);
        System.out.println(head);

        ListNode res = new Solution_92().reverseBetween(head, 1, 3);
        System.out.println(res);
    }
}
