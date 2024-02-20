package com.wnj.util;

public class SingleLinkedList<E> {
    private Node head;
    private Node tail;

    private int size = 0;

    public void add(E e){
        if(head == null){
            head = new Node(e, null);
            tail = head;
        }else{
            Node newNode = new Node(e, null);
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    public static void main(String[] args) {
        SingleLinkedList list = new SingleLinkedList();
        for (int i = 0; i < 5; i++) {
            list.add(i);
        }
        System.out.println(list);
        list.reverse();
        System.out.println(list);
    }

    public void reverse(){
        if(getSize() < 2){
            return;
        }

        Node pre = null;
        Node cur = head;
        while(cur != null){
            Node tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        Node temp = head;
        head = tail;
        tail = temp;

    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node tail) {
        this.tail = tail;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    class Node<E> {
        E val;
        Node next;

        Node() {
        }

        Node(E val) {
            this.val = val;
        }

        Node(E val, Node next) {
            this.val = val;
            this.next = next;
        }
    }
}
