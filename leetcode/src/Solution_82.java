/**
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 示例 1:
 *
 * 输入: dummy->1->2->3->3->4->4->5->null
 * 输出: 1->2->5->null
 * 示例 2:
 *
 * 输入: dummy->1->1->1->2->3->null
 * 输出: 2->3->null
 *
 * 输入: dummy->1->1->1->2->2->3->null
 * 输出: 3->null
 */
class Solution_82 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode prev = dummyHead;
        ListNode cur = dummyHead.next;

        while ( cur != null ) {
            while ( cur.next != null && cur.val == cur.next.val )
                cur = cur.next;

            cur = cur.next;

            if ( prev.next.next == cur )
                prev = prev.next;
            else {
                prev.next = cur;
            }
        }

        return dummyHead.next;
    }

    public static void main(String[] args) {
        int[] arr1 = {1,2,2,2,5,5};
        int[] arr2 = {1,2,3,3,4,4,5,6};
        int[] arr3 = {1,1,1,2,2,3};
        ListNode head = new ListNode(arr3);
        Solution_82 sol = new Solution_82();
        ListNode res = sol.deleteDuplicates(head);
        System.out.println(res);
    }
}
