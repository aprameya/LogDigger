package org.collegeboard.games.numberguess;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.collegeboard.games.numberguess.App.Command;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.AdditionalMatchers;
import org.mockito.Matchers;

/**
 * Unit test for simple App.
 */
public class AppTest {

	/**
	 * Test that the program successfully completes.
	 */
	@Test
	public void testAppTerminates() {
		int testGuesses[] = { 0, 1, 11, 29, 30, 31, 37, 100, 1000000 };
		for (int expectedAnswer : testGuesses) {
			CommandAcceptor commandAcceptor = mock(CommandAcceptor.class);
			when(
					commandAcceptor.accept(anyString(),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.ready);
			when(
					commandAcceptor.accept(anyString(),
							AdditionalMatchers.gt(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.lower);
			when(
					commandAcceptor.accept(anyString(),
							AdditionalMatchers.lt(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.higher);
			when(
					commandAcceptor.accept(anyString(), eq(expectedAnswer),
							Matchers.<Command> anyVararg())).thenReturn(
					Command.yes);

			App app = new App(commandAcceptor);
			try {
				int answer = app.execute();
				System.out.println("User Mind:"+expectedAnswer+" Computer guess:"+answer);
				Assert.assertTrue("Game ended successfully.",
						answer == expectedAnswer);
			} catch (Exception e) {
				System.out.println(e.getClass().getName() + ":"
						+ e.getMessage());
				Assert.assertTrue(e.getMessage(), false);
			}
		}
	}
}
