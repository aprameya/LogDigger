package org.tutorial.bigdata.mapreduce.mrlogdigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the Map Reduce job
 */
public class MapReduceJobAppTest extends TestCase {
	MapDriver<Object, Text, Text, IntWritable> mapDriver;
	ReduceDriver<Text, IntWritable, Text, IntWritable> reduceDriver;
	MapReduceDriver<Object, Text, Text, IntWritable, Text, IntWritable> mapReduceDriver;

	@Before
	public void setUp() {
		LogLineMapper mapper = new LogLineMapper();
		IpCountReducer reducer = new IpCountReducer();

		mapDriver = MapDriver.newMapDriver(mapper);
		reduceDriver = ReduceDriver.newReduceDriver(reducer);

		mapReduceDriver = MapReduceDriver.newMapReduceDriver(mapper, reducer);
	}

	@Test
	public void testMapper() throws IOException {
		String ip = "127.0.0.1";
		mapDriver.withInput(new LongWritable(), new Text(ip))
				.withOutput(new Text(ip), new IntWritable(1)).runTest();
	}

	@Test
	public void testReducer() throws IOException {
		String ip = "127.0.0.1";
		List<IntWritable> values = new ArrayList<IntWritable>();
		values.add(new IntWritable(1));
		values.add(new IntWritable(1));
		reduceDriver.withInput(new Text(ip), values)
				.withOutput(new Text(ip), new IntWritable(2)).runTest();
	}

}
