package com.wnj.util;



public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void main(String[] args) {
        ListNode head =new ListNode(0, null);
        ListNode cur = head;
        for (int i = 0; i < 5; i++) {
            ListNode next = new ListNode(i+1, null);
            cur.next = next;
            cur = next;
        }
        System.out.println(head);

        reverseList(head);
        System.out.println(head);

    }

    public static ListNode reverseList(ListNode head) {
        //申请节点，pre和 cur，pre指向null
        ListNode pre = null;
        ListNode cur = head;
        ListNode tmp = null;
        while(cur!=null) {
            //记录当前节点的下一个节点
            tmp = cur.next;
            //然后将当前节点指向pre
            cur.next = pre;
            //pre和cur节点都前进一位
            pre = cur;
            cur = tmp;
        }
        return pre;
    }

}

