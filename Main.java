import java.io.IOException;

public class Main {

	private String[] initial = {
			"XX    XX",
			"X X  X X",
			"X      X",
			"        ",
			"X      X",
			"X X  X X",
			"XX    XX"
	};
	
	private void printUniverse(String[] universe) {
		printDivider(universe);
		printRows(universe);
		printDivider(universe);
	}

	private void printRows(String[] universe) {
		for (int row = 0; row < universe.length; row++) {
			System.out.print("|");
			System.out.print(universe[row]);
			System.out.println("|");
		}
	}

	private void printDivider(String[] universe) {
		System.out.print("+");
		for (int i=0; i<universe[0].length(); i++)
			System.out.print("-");
		System.out.println("+");
	}

	public static void main(String[] args) throws InterruptedException {
		Main runner = new Main();
		runner.run();
	}

	private void run() throws InterruptedException {
		Universe universe = new Universe(initial);
		printUniverse(initial);
		for (int i=0; i<200; i++) {
			Thread.sleep(100);
//			try {
//				System.in.read();
//			} catch (IOException e) {
//			}
			System.out.print("\033[2J\033[;H");
			String[] next = universe.generation();
			printUniverse(next);
		}
	}

}
