package org.tutorial.bigdata.mapreduce.mrlogdigger;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mrunit.mapreduce.MapDriver;
import org.apache.hadoop.mrunit.mapreduce.MapReduceDriver;
import org.apache.hadoop.mrunit.mapreduce.ReduceDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for the Map Reduce job that tests Mapper and Reducer components
 * independently, and without using a Hadoop setup. This will ensure that the
 * MapReduce job is correctly written without having to use it in a hadoop
 * infrastructure.
 */
public class MapReduceJobAppTest extends TestCase {
	MapDriver<Object, Text, Text, Text> mapDriver;
	ReduceDriver<Text, Text, Text, Text> reduceDriver;
	MapReduceDriver<Object, Text, Text, Text, Text, Text> mapReduceDriver;

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
		String ip = "200.158.1.71";
		String ts = "2014-11-23 23:21:52";
		String logLine = "[" + ts + "] " + ip;
		mapDriver.withInput(new LongWritable(), new Text(logLine))
				.withOutput(new Text(ip), new Text(ts)).runTest();
	}

	@Test
	public void testReducer() throws IOException {
		String ip = "127.0.0.1";
		List<Text> values = new ArrayList<Text>();
		String firstTS = "2014-11-23 23:01:52";
		String lastTS = "2014-11-23 23:59:52";
		values.add(new Text(firstTS));
		values.add(new Text(lastTS));
		reduceDriver.withInput(new Text(ip), values)
				.withOutput(new Text(ip), new Text(firstTS + " - " + lastTS))
				.runTest();
	}

}
