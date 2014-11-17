package org.tutorial.bigdata.mapreduce.mrlogdigger;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class LogLineMapper extends Mapper<Object, Text, Text, IntWritable> {

	public void map(Object key, Text value, Context context)
			throws IOException, InterruptedException {
		//TODO: Retrofit to the actual log line format, to incorporate the timestamp of the log along with the IP
		//System.out.println("key="+key.toString());
		//System.out.println("value="+value.toString());
		context.write(new Text(value.toString()), new IntWritable(1));
	}
}
