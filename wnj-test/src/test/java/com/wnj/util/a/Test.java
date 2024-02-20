package com.wnj.util.a;

public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        Node root = new Node("root");
        int height = 4;
        test.grow(root, height);
        System.out.println(root);
        System.out.println("============");
        test.frontOrder(root);
    }

    private <T> void grow(Node<T> node, int height) {
        if (height == 1){
            return;
        }
        addChild(node);
        grow(node.getLeftChild(), height -1 );
        grow(node.getRightChild(),height -1 );
    }
    private <T> void addChild(Node<T> node){
        Node leftChild = new Node(node.getData()+"-l");
        leftChild.setParent(node);
        leftChild.setLeftChild(null);
        leftChild.setRightChild(null);

        Node rightChild = new Node(node.getData() + "-r");
        rightChild.setParent(node);
        rightChild.setLeftChild(null);
        rightChild.setRightChild(null);

        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);
    }

    private void frontOrder(Node node){
        if(node == null){
            return;
        }
        System.out.println(node.getData());
        frontOrder(node.getLeftChild());
        frontOrder(node.getRightChild());
    }
}
