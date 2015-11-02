import static org.junit.Assert.*;

import org.junit.Test;


public class ConwayTest {

	@Test
	public void emptyCellWithNoNeighboursIsStillDead() {
		String[] empty = {
				"   ",
				"   ",
				"   "};
		Universe universe = new Universe(empty);
		String[] next = universe.generation();
		assertEqualUniverses(empty, next);
	}
	
	@Test
	public void livingCellWithNoNeighboursDies() throws Exception {
		String[] singleCell = {
				"   ",
				" X ",
				"   "};
		String[] empty = {
				"   ",
				"   ",
				"   "};
		Universe universe = new Universe(singleCell);
		String[] next = universe.generation();
		assertEqualUniverses(empty, next);
	}

	@Test
	public void emptyCellWithThreeNeighboursIsBorn() throws Exception {
		String[] initial = {
				"  X",
				"X  ",
				" X "};
		String[] singleCell = {
				"   ",
				" X ",
				"   "};
		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(singleCell, next);
	}

	@Test
	public void livingCellWithTwoNeighboursLivesOn1() throws Exception {
		String[] initial = {
				"  X",
				" X ",
				" X "};
		String[] singleCell = {
				"   ",
				" XX",
				"   "};
		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(singleCell, next);
	}

	@Test
	public void livingCellWithTwoNeighboursLivesOn2() throws Exception {
		String[] initial = {
				" X ",
				"X  ",
				" X "};
		String[] singleCell = {
				"   ",
				"XX ",
				"   "};
		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(singleCell, next);
	}

	@Test
	public void livingCellWithThreeNeighboursLivesOn() throws Exception {
		String[] initial = {
				"   ",
				"XX ",
				"XX "};
		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(initial, next);
	}

	@Test
	public void squareBlockIn4x4LivesMoreThanOneGeneration() throws Exception {
		String[] initial = {
				"    ",
				" XX ",
				" XX ",
				"    "};
		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		next = universe.generation();
		assertEqualUniverses(initial, next);
	}

	@Test
	public void livingCellWithMoreThanThreeNeighboursDies() throws Exception {
		String[] initial = {
				"X  ",
				" X ",
				"X X"};
		String[] expected = {
				"   ",
				"XX ",
				" X "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(expected, next);
	}

	@Test
	public void lineOfThreeLivingCellsBlinks() throws Exception {
		String[] initial = {
				" X ",
				" X ",
				" X "};
		String[] flipped = {
				"   ",
				"XXX",
				"   "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(flipped, next);
	}

	@Test
	public void lineOfThreeInUpperRowGeneratesAnotherRow() throws Exception {
		String[] initial = {
				"XXX",
				"   ",
				"   "};
		String[] enlarged_vertically = {
				" X ",
				" X ",
				" X ",
				"   "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(enlarged_vertically, next);
	}

	@Test
	public void lineOfThreeInLowerRowGeneratesAnotherRow() throws Exception {
		String[] initial = {
				"   ",
				"   ",
				"XXX"};
		String[] enlarged_vertically = {
				"   ",
				" X ",
				" X ",
				" X "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(enlarged_vertically, next);
	}

	@Test
	public void lineOfThreeInRightColumnGeneratesAnotherColumn() throws Exception {
		String[] initial = {
				"   X",
				"   X",
				"   X"};
		String[] enlarged_vertically = {
				"     ",
				"  XXX",
				"     "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(enlarged_vertically, next);
	}

	@Test
	public void lineOfThreeInLeftColumnGeneratesAnotherColumn() throws Exception {
		String[] initial = {
				"X ",
				"X ",
				"X "};
		String[] enlarged_vertically = {
				"   ",
				"XXX",
				"   "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(enlarged_vertically, next);
	}

	@Test
	public void canEnlargeBothUpAndDown() throws Exception {
		String[] initial = {
				" XXX ",
				"     ",
				"     ",
				" XXX "};
		String[] expanded = {
				"  X  ",
				"  X  ",
				"  X  ",
				"  X  ",
				"  X  ",
				"  X  "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(expanded, next);
	}
	
	@Test
	public void canEnlargeLeftAndRight() throws Exception {
		String[] initial = {
				"X   X",
				"X   X",
				"X   X"};
		String[] expanded = {
				"       ",
				"XXX XXX",
				"       "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(expanded, next);
	}
	@Test
	public void gliderMoves() throws Exception {
		String[] initial = {
				"  X  ",
				"   X ",
				" XXX "};
		String[] glider_move_one = {
				"     ",
				" X X ",
				"  XX ",
				"  X  "};
		String[] glider_move_two = {
				"     ",
				"   X ",
				" X X ",
				"  XX "};
		String[] glider_move_three = {
				"     ",
				"  X  ",
				"   XX",
				"  XX "};

		Universe universe = new Universe(initial);
		String[] next = universe.generation();
		assertEqualUniverses(glider_move_one, next);
		next = universe.generation();
		assertEqualUniverses(glider_move_two, next);
		next = universe.generation();
		assertEqualUniverses(glider_move_three, next);
	}
	
	

	public void assertEqualUniverses(String[] expected, String[] actual) {
		Boolean same = true;
		if (expected.length != actual.length)
			same = false;
		else
			for (int row=0; row<expected.length; row++)
				if (!actual[row].equals(expected[row]))
					same = false;
		if (!same) {
			System.out.println("Expected:");
			printUniverse(expected);
			System.out.println("Actual:");
			printUniverse(actual);
			fail();
		}
	}

	public void printUniverse(String[] universe) {
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

}
