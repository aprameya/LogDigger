package org.collegeboard.games.numberguess;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {
	//private InputStream is;
	//private OutputStream os;
	//private String command = "";
	private App systemUnderTest;

	@Before
	public void setUp() {
		//systemUnderTest = new App(is, os);
	}

	/**
	 * Test that the program accepts only the correct inputs from the user.
	 */
	@Test
	public void testUserInputAcceptance() {
		String command = "testNegative\n";
		//InputStream is = new ByteArrayInputStream(command.getBytes());
		//OutputStream os = new ByteArrayOutputStream();
		StringWriter output = new StringWriter();
		App app = new App(new Scanner(command), new PrintWriter(output));
		try {
			app.acceptReady();
		} catch (IOException e) {
			Assert.assertTrue(e.getMessage(), false);
		}
		Assert.assertTrue(App.READY_MESSAGE.equals(output.toString()));
	}
}
