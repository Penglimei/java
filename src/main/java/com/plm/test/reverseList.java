package com.plm.test;

public class reverseList {

    /**
     *  迭代实现
     *      pre记录当前节点左边已经反转好的链表，next记录当前节点右边未反转的链表
     *
     *      当前节点的后半部分链表存储到 next中，next = head.next
     *      当前节点与左边已经反转的链表连接起来，head.next = pre
     *      记录已经反转好的链表，pre = head
     *      继续反转右边未处理的链表，head = next;
     *
     * @param head
     * @return
     */
    public static ListNode reverseItera(ListNode head){
        ListNode pre = null;
        ListNode next = null;

        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    // 递归实现
    public static ListNode reverse(ListNode head){
        if(head==null || head.next==null){
            return head;
        }

        ListNode next = head.next;
        ListNode newHead = reverse(head.next);
        next.next = head;
        head.next = null;
        return newHead;
    }


    public static void main(String[] args) {
        // 创建节点
        ListNode head = new ListNode(0);
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);

        // 形成链表
        head.setNext(node1);
        node1.setNext(node2);
        node2.setNext(node3);

        // 打印反转前的链表
        System.out.println("反转前的链表：");
        ListNode temp = head;
        while (temp != null){
            System.out.print(temp.getVal()+ " ");
            temp = temp.next;
        }

        // 反转链表
        head = reverseItera(head);
        System.out.println();
        System.out.println("===============");
        System.out.println("反转后的链表：");
        while (head != null){
            System.out.print(head.getVal()+" ");
            head = head.next;
        }
    }
}
