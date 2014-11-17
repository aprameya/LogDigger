package org.tutorial.bigdata.mapreduce.mrlogdigger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

/**
 * The reducer component of MR algorithm, that collects the
 * IP-address:log-timestamp tuples produced by the mapper and computes the
 * interval of user visit session to the website, eventually producing tuples of
 * the form IP-address:visit-start-time - visit-end-time.
 */
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
