package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A number guessing game!
 *
 */
public class App {

	static String READY_MESSAGE = "Chooses a number in your mind and type 'ready' to indicate that you are ready to begin playing. Or type 'end' to quit.";
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

		// TODO: Is there really a best set of initial values? Probably not.
		int guess = 30;
		int floor = 0, ceiling = guess * 2;
		boolean ceiling_bound = false, floor_bound = false;

		while (!c.equals(Command.yes)) {
			c = commandAcceptor.accept(String.format(REPROMT_MESSAGE, guess),
					Command.higher, Command.lower, Command.yes, Command.end);
			if (ceiling_bound && floor_bound) {
				if (Command.higher.equals(c)) {
					floor = guess;
				} else if (Command.lower.equals(c)) {
					ceiling = guess;
				}
				guess = (ceiling + floor) / 2;
			} else {
				if (Command.higher.equals(c)) {
					floor_bound = true;
					floor = guess;
					guess = (guess == 0 ? 1 : guess * 2);
				} else if (Command.lower.equals(c)) {
					ceiling_bound = true;
					ceiling = guess;
					guess = (guess == 0 ? 0
							: (floor_bound ? ((ceiling + floor) / 2)
									: (ceiling / 2)));
				}
			}
		}
		answer = guess;
	}

	public static void main(String... args) {
		App app = new App(new Scanner(System.in), new PrintWriter(System.out));
		app.execute();
	}

}
