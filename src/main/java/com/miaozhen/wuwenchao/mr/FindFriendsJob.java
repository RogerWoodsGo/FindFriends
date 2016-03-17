package com.miaozhen.wuwenchao.mr;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapred.TextOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * Created by beyondwu on 2016/2/18.
 */
public class FindFriendsJob extends Configured implements Tool {
    @Override
    public int run(String[] strings) throws Exception {
        // get configuration
        Configuration conf = getConf();
        // create a job instance with the configuration
        Job job = Job.getInstance(conf);
        job.setJarByClass(FindFriendsJob.class);
        job.setJobName("FindFriends");
        job.setMapperClass(FindFriendsMapper.class);
        job.setReducerClass(FindFriendsReducer.class);

        job.setInputFormatClass(TextInputFormat.class);
        //job.setOutputFormatClass(TextOutputFormat.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.addInputPath(job, new Path(strings[0]));
        FileOutputFormat.setOutputPath(job, new Path(strings[1]));


        return job.waitForCompletion(true) ? 0 : -1;
    }
    public static void main(String[] args) throws Exception {
        System.exit(ToolRunner.run(new Configuration(), new FindFriendsJob(), args));
    }
}
