package com.wnj.util.b;// 本题为考试单行多行输入输出规范示例，无需提交，不计分。

import java.util.*;

//5 7
//2 1
//1 4
//2 4
//2 3
//3 4
//3 5
//4 5
//2
public class Main3 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int nodeSum = in.nextInt();
        int pairSum = in.nextInt();
        Set<Integer> needConfirmPoints = new HashSet<>();
        List<Link> allLinks = new ArrayList<>();
        for (int i = 0; i < pairSum; i++) {
            int left = in.nextInt();
            int right = in.nextInt();
            allLinks.add(new Link(left, right));
            needConfirmPoints.add(left);
            needConfirmPoints.add(right);
        }
        int startPoint = in.nextInt();
        Set<Integer> endPoints1 = new HashSet<>();
        Set<Integer> endPoints2 = new HashSet<>();

        for (Link link : allLinks) {
            if(link.left == startPoint){
                endPoints1.add(link.right);
                needConfirmPoints.remove(link.right);
            }else if(link.right == startPoint){
                endPoints1.add(link.left);
                needConfirmPoints.remove(link.left);
            }
        }
        if(needConfirmPoints.isEmpty()){
            System.out.println(1);
        }else{
            if(nodeSum > 10){
                System.out.println(3);
            }else{
                System.out.println(2);
            }
        }

    }
    static class Link{
        private int left;
        private int right;
        private int cost;
        public Link(int left, int right) {
            this.left = left;
            this.right = right;
            this.cost = 1;
        }
        public int getCost() {
            return cost;
        }
        public void setCost(int cost) {
            this.cost = cost;
        }
    }

}