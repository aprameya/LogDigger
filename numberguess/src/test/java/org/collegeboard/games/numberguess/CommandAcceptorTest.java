package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Scanner;

import org.collegeboard.games.numberguess.App.Command;
import org.junit.Assert;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class CommandAcceptorTest {

	/**
	 * Test that the program accepts only the correct inputs from the user.
	 */
	@Test
	public void testUserInputAcceptanceOnValidInput() {
		String[] commands = { "ready", "higher", "lower", "yes", "end" };
		StringWriter output = new StringWriter();
		for (String command : commands) {
			CommandAcceptor commandAcceptor = new CommandAcceptor(new Scanner(command), new PrintWriter(output));
			Command c = commandAcceptor.accept("testUserInputAcceptance",
					Command.fromString(command));
			Assert.assertTrue(Command.fromString(command).equals(c));
		}
	}

	/**
	 * Test that the program rejects incorrect inputs from the user.
	 */
	@Test
	public void testUserInputRejectionOnInvalidInput() {
		String[] commands = { "testNegative", "ready", "\n", "1", "-1", "1.23", "-0.01", "1234567890123456789012345678901234567890" };
		StringWriter output = new StringWriter();
		for (String command : commands) {
			CommandAcceptor commandAcceptor = new CommandAcceptor(new Scanner(command), new PrintWriter(output));
			Command c = commandAcceptor.accept("testUserInputAcceptance", Command.end);
			Assert.assertTrue(output.toString().contains(CommandAcceptor.INVALID_INPUT));
			Assert.assertTrue(c == null);
		}
	}
}
