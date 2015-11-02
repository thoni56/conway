
public class Universe {

	private String[] universe;
	private String upwards;
	private String downwards;
	private String right;
	private String left;

	public Universe(String[] initial) {
		universe = initial;
	}

	public String[] generation() {
		String[] next = calculate_next(universe);
		next = expand_universe(next);
		universe = next;
		return universe;
	}

	private String[] calculate_next(String[] universe) {
		String[] next = empty_universe();
		for (int row = -1; row < height(next)+1; row++) {
			for (int col = -1; col < width(next)+1; col++) {
				if (is_alive(universe, row, col)) {
					if (will_survive(universe, row, col))
						set_alive(next, row, col);
				} else {
					if (will_be_born(universe, row, col))
						set_alive(next, row, col);
				}
			}
		}
		return next;
	}

	private boolean will_be_born(String[] universe, int row, int col) {
		return count_neighbours(universe, row, col) == 3;
	}

	private boolean will_survive(String[] universe, int row, int col) {
		int neighbourcount = count_neighbours(universe, row, col);
		return neighbourcount == 2 || neighbourcount == 3;
	}

	private String[] expand_universe(String[] next) {
		if (expanding(upwards))
			next = expand_upwards(next);
		if (expanding(downwards))
			next = expand_downwards(next);
		if (expanding(left))
			next = expand_left(next);
		if (expanding(right))
			next = expand_right(next);
		return next;
	}

	private boolean expanding(String direction) {
		return direction.contains("X");
	}

	private String[] expand_upwards(String[] next) {
		String[] new_next = new String[height(next)+1];
		for (int row=0; row<height(next); row++)
			new_next[row+1] = next[row];
		new_next[0] = upwards;
		return new_next;
	}

	private String[] expand_downwards(String[] next) {
		String[] new_next = new String[height(next)+1];
		for (int row=0; row<height(next); row++)
			new_next[row] = next[row];
		new_next[next.length] = downwards;
		return new_next;
	}

	private String[] expand_right(String[] next) {
		for (int row=0; row<height(next); row++)
			next[row] = next[row].concat(right.substring(row,row+1));
		return next;
	}

	private String[] expand_left(String[] next) {
		for (int row=0; row<height(next); row++)
			next[row] = left.substring(row,row+1).concat(next[row]);
		return next;
	}
	
	private boolean is_alive(String[] universe, int row, int col) {
		if (row < 0 || col < 0 || row >= height(universe) || col >= width(universe))
			return false;
		return universe[row].substring(col).charAt(0) == 'X';
	}

	private void set_alive(String[] universe, int row, int col) {
		if (row < 0)
			upwards = set_alive_in_row(upwards, col);
		else if (row >= height(universe))
			downwards = set_alive_in_row(downwards, col);
		else if (col < 0)
			left = set_alive_in_row(left, row);
		else if (col >= width(universe))
			right = set_alive_in_row(right, row);
		else
			universe[row] = set_alive_in_row(universe[row], col);
	}

	private String set_alive_in_row(String row, int pos) {
		return row.substring(0, pos) + "X" + row.substring(pos+1);
	}

	private int width(String[] universe) {
		return universe[0].length();
	}

	private int height(String[] universe) {
		return universe.length;
	}

	private int count_neighbours(String[] universe, int cellrow, int cellcol) {
		int count = 0;
		for (int row = cellrow-1; row <= cellrow+1; row++) {
			for (int col = cellcol-1; col <= cellcol+1; col++) {
				if (!is_current_cell(row, col, cellrow, cellcol))
					if (is_alive(universe, row, col)) {
						count++;
				}
			}
		}
		return count;
	}

	private boolean is_current_cell(int row, int col, int cellrow, int cellcol) {
		return row == cellrow && col == cellcol;
	}

	private String[] empty_universe() {
		String[] empty = new String[height(universe)];
		upwards = an_empty_row();
		downwards = an_empty_row();
		left = an_empty_column();
		right = an_empty_column();
		for (int row=0; row < height(universe); row++)
			empty[row] = an_empty_row();
		return empty;
	}

	private String an_empty_row() {
		return new String(new char[width(universe)]).replace('\0', ' ');
	}

	private String an_empty_column() {
		return new String(new char[height(universe)]).replace('\0', ' ');
	}
}
