/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode(int x) { val = x; }
 * }
 * <p>
 * 删除链表中等于给定值 val 的所有节点。
 * <p>
 * 示例:
 * <p>
 * 输入: 1->2->6->3->4->5->6, val = 6
 * 输出: 1->2->3->4->5
 */
class Solution2_203 {

    // 删除以head为头结点的链表中所有值为val的节点
    public ListNode removeElements(ListNode head, int val) {
        if (head == null)
            return null;

        ListNode node = removeElements(head.next, val);
        if (head.val == val)
            return node;
        else {
            head.next = node;
            return head;
        }

//        head.next = removeElements(head.next, val);
//        return head.val == val ? head.next : head;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 6, 3, 4, 5, 6};
        int[] arr2 = {1, 2, 2};
        ListNode head = new ListNode(arr2);

        ListNode res = new Solution2_203().removeElements(head, 2);
        System.out.println(res);
    }
}