package org.collegeboard.games.numberguess;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * A number guessing game!
 *
 */
public class App {
	private Scanner scanner;
	private PrintWriter output;
	
	static String READY_MESSAGE = "Chooses a number in your mind and type “ready” to indicate that you are ready to begin playing. Or type “end” to quit. \n";
	
	enum commands{ready,higher,lower,yes,end}

	public App(Scanner scanner, PrintWriter output) {
		this.scanner = scanner;
		this.output = output;
	}
	
	private void execute() {
		try {
			acceptReady();
		} catch (IOException e) {
			System.out.println("Something is wrong with the system setup. More details below:");
			e.printStackTrace();	
		}
	}

	void acceptReady() throws IOException {
		String command = this.accept();
		while(!commands.ready.toString().equals(command)){
			output.println(READY_MESSAGE);
			output.flush();
		}
	}

	private String accept(){
		//return scanner.nextLine();
		return scanner.next();
	}

	public static void main(String... args) {
		App app = new App(new Scanner(System.in), new PrintWriter(System.out));
		app.execute();
	}

}
