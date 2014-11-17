package org.tutorial.bigdata.mapreduce.mrlogdigger;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

/**
 * The mapper component of MR algorithm, that parses each line of the access log
 * file to produce key-value pairs of IP-address:log-timestamp tuples.
 */
public class LogLineMapper extends Mapper<Object, Text, Text, Text> {

	// log line of the form [2013-07-16 02:55:43] 94.120.169.22
	private static Pattern pattern = Pattern.compile("\\[(.*)\\] (.*)$");

	public void map(Object irrelevant, Text logline, Context context)
			throws IOException, InterruptedException {
		// TODO: Change as per the actual format of the access log line.
		// For now, Parse log line, expected to be in the form [2013-07-16 02:55:43] 94.120.169.22
		// and throw it back as 94.120.169.22 -> 2013-07-16 02:55:43 so we can
		// compute the time interval of user visit in reduce stage.
		Matcher matcher = pattern.matcher(logline.toString());
		if (matcher.find()) {
			context.write(new Text(matcher.group(2)),
					new Text(matcher.group(1)));
			// System.out.printf("Reconstructed: [%s] %s", matcher.group(1),
			// matcher.group(2));
		}
	}
}
