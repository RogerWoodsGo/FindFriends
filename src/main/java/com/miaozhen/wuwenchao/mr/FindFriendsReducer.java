package com.miaozhen.wuwenchao.mr;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.PriorityQueue;

/**
 * Created by beyondwu on 2016/2/18.
 */


public class FindFriendsReducer extends Reducer<Text,IntWritable, Text, IntWritable> {

    private PriorityQueue<CitedNum> topNQueue;
    final private int TopN = 11;
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        //super.setup(context);
        topNQueue = new PriorityQueue<CitedNum>(TopN, new CitedComparator());
    }

    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int appearedTimes = 0;
        for (IntWritable value : values){
            appearedTimes += value.get();
        }
        if(appearedTimes > 1){
            System.out.println("Appeared Times Num " + key.toString());
        }
        if(topNQueue.size() < TopN){
            topNQueue.add(new CitedNum(key.toString(),appearedTimes));
        }
        else{
            CitedNum min = topNQueue.peek();


            if(min.getCitedNum() < appearedTimes){
                System.err.println("currentd key and value " + key.toString() + " " + min.getCitedNum());
                topNQueue.poll();
                topNQueue.add(new CitedNum(key.toString(),appearedTimes));
            }
        }

    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        //super.cleanup(context);
        CitedNum cn;
        while ((cn = topNQueue.poll()) != null){
            context.write(new Text(cn.getCitedName()), new IntWritable(cn.getCitedNum()));
        }

    }
}