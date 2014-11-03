package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Test that the program successfully completes.
	 */
	@Test
	public void testAppTerminates() {
		String[] commands = { "ready", "higher", "lower", "yes", "end" };
		StringWriter output = new StringWriter();
		for (String command : commands) {
			App app = new App(new Scanner(command), new PrintWriter(output));
			try {
				app.execute();
				Assert.assertTrue("Game ended successfully.", true);
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ":"
						+ e.getMessage());
				Assert.assertTrue(e.getMessage(), false);
			}
		}
	}
}
