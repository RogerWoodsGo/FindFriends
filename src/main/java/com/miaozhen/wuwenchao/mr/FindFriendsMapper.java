package com.miaozhen.wuwenchao.mr;

import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


import java.io.IOException;
import java.util.Map;

/**
 * Created by beyondwu on 2016/2/18.
 */
    public class FindFriendsMapper extends Mapper<LongWritable, Text, Text, IntWritable> {


        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Configuration conf = context.getConfiguration();
            System.out.println("Splited cited_patent is");
           // System.err.println("Splited cited_patent is");
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String cited_patent = line.split(",")[1];
            //System.out.println("Splited cited_patent is" + cited_patent);
            context.write(new Text(StringUtils.strip(cited_patent, "\n")), new IntWritable(1));
        }
    }

