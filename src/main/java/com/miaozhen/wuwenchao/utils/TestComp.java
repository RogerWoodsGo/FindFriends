package com.miaozhen.wuwenchao.utils;


import java.util.PriorityQueue;

/**
 * Created by beyondwu on 2016/2/20.
 */
public class TestComp {
    final static private int TopN = 3;

    public static void main(String[] args) {
        PriorityQueue<CitedNum> topNQueue;

        //topNQueue = new PriorityQueue<CitedNum>(TopN, new CitedComparator());
        topNQueue = new PriorityQueue<CitedNum>(TopN, new CitedComparator());
        topNQueue.add(new CitedNum("1", 2));
        topNQueue.add(new CitedNum("2", 1));
        topNQueue.add(new CitedNum("3", 2));
        topNQueue.add(new CitedNum("4", 3));
        for (int i = 0; i < 4; i++)
            System.out.println(topNQueue.poll().getCitedNum());
    }
}
