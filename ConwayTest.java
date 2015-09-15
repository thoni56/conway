import static org.junit.Assert.*;

import org.junit.Test;


public class ConwayTest {

	private Conway conway = new Conway();

	@Test
	public void empty3x3returnsEmpty3x3() {
		String universe[] = {
				"   ",
				"   ",
				"   "};
		String expected[] = {
				"   ",
				"   ",
				"   "};
		String next[] = conway.generation(universe);
		assertEquals(next[0], expected[0]);
		assertEquals(next[1], expected[1]);
		assertEquals(next[2], expected[2]);
	}

	@Test
	public void singleCenteredCellIn3x3Dies() {
		String universe[] = {
				"   ",
				" X ",
				"   "};
		String expected[] = {
				"   ",
				"   ",
				"   "};
		String next[] = conway.generation(universe);
		assertEquals(next[0], expected[0]);
		assertEquals(next[1], expected[1]);
		assertEquals(next[2], expected[2]);
	}

	@Test
	public void centeredCellWithTwoNeigboursIn3x3Survives() {
		String universe[] = {
				"X  ",
				" X ",
				"  X"};
		String expected[] = {
				"   ",
				" X ",
				"   "};
		String next[] = conway.generation(universe);
		assertEquals(next[0], expected[0]);
		assertEquals(next[1], expected[1]);
		assertEquals(next[2], expected[2]);
	}


}
