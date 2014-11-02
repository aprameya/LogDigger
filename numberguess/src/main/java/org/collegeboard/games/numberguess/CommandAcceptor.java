package org.collegeboard.games.numberguess;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import org.collegeboard.games.numberguess.App.Command;

public class CommandAcceptor {

	static String INVALID_INPUT = "Invalid Input. Please try again.";

	private Scanner scanner;
	private PrintWriter output;

	public CommandAcceptor(Scanner scanner, PrintWriter output) {
		this.scanner = scanner;
		this.output = output;
	}

	Command accept(String message, Command... commands) {
		List<Command> expectedCommands = new ArrayList<Command>(
				Arrays.asList(commands));
		prompt(message+String.format(" Type one of %s", Arrays.asList(commands)));
		try {
			String input = scanner.next();
			while (input == null
					|| (Command.fromString(input) == null)
					|| !expectedCommands
							.contains(Command.fromString(input))) {
				prompt(INVALID_INPUT+String.format(" Expected %s", Arrays.asList(commands)));
				input = scanner.next();
			}
			return Command.fromString(input);
		} catch (java.util.NoSuchElementException e) {
			return null;
		}
	}

	private void prompt(String message) {
		output.println(message);
		output.flush();
	}

}
