/**
 * 该类主要作为LeetCode上链表相关题目的一个测试类
 * 传入一个数组,可以把该数组转化为一个链表
 * Example:
 *      int[] arr = {1, 2, 5, 3, 4, 5, 6}
 *      ListNode head = new ListNode(arr)
 *
 *      head.toString ==> "1->2->5->3->4->5->6"
 */
public class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }

    /**
     * 传入一个数组,把数组转化为一个链表
     */
    ListNode(int[] arr) {

        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("cannot create a empty ListNode");

        this.val = arr[0];
        ListNode cur = this;
        for (int i=1; i<arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        ListNode cur = this;
        while (cur != null) {
            sb.append(cur.val+"->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}