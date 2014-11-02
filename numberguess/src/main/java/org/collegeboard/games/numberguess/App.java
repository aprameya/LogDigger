package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A number guessing game!
 *
 */
public class App {

	static String READY_MESSAGE = "Chooses a number in your mind and type 'ready' to indicate that you are ready to begin playing. Or type 'end' to quit.";
	// Use %f for foloating point numbers.
	static String REPROMT_MESSAGE = "Is the number %d?";
	int answer = 0;

	enum Command {
		ready, higher, lower, yes, end;
		public static Command fromString(String s) {
			try {
				return valueOf(s);
			} catch (java.lang.IllegalArgumentException e) {
				return null;
			}
		}
	}

	private CommandAcceptor commandAcceptor;

	public App(Scanner scanner, PrintWriter output) {
		this.commandAcceptor = new CommandAcceptor(scanner, output);
	}

	public void execute() {
		Command c = commandAcceptor.accept(READY_MESSAGE, Command.ready,
				Command.end);

		// float guess = 30;
		int guess = 30;
		int upperLimit = guess, lowerLimit = guess;

		while (!c.equals(Command.yes)) {
			c = commandAcceptor.accept(String.format(REPROMT_MESSAGE, guess),
					Command.higher, Command.lower, Command.yes, Command.end);
			if(Command.higher.equals(c)) {
				upperLimit = guess*2;
				lowerLimit = guess;
			}
			else if(Command.lower.equals(c)) {
				upperLimit = guess;
				lowerLimit = guess/2;
			}
			guess=(upperLimit+lowerLimit)/2;
		}
		answer = guess;
	}

	public static void main(String... args) {
		App app = new App(new Scanner(System.in), new PrintWriter(System.out));
		app.execute();
	}

}
