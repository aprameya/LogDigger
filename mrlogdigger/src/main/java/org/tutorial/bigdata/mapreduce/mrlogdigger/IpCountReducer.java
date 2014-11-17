package org.tutorial.bigdata.mapreduce.mrlogdigger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class IpCountReducer extends Reducer<Text, Text, Text, Text> {
	// log line of the form [2013-07-16 02:55:43] 94.120.169.22
	private SimpleDateFormat dateformat = new SimpleDateFormat(
			"yyy-MM-dd HH:mm:ss");

	public void reduce(Text text, Iterable<Text> values, Context context)
			throws IOException, InterruptedException {
		Date floor = null;
		Date ceil = null;
		try {
			for (Text value : values) {
				Date d = dateformat.parse(value.toString());
				if (floor == null)
					floor = d;
				if (ceil == null)
					ceil = d;
				if (d.before(floor))
					floor = d;
				if (d.after(ceil))
					ceil = d;
			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		context.write(
				text,
				new Text((floor == null ? "unknown" : dateformat.format(floor))
						+ " - "
						+ (floor == null ? "unknown" : dateformat.format(ceil))));
	}
}
