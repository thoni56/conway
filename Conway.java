
public class Conway {

	public String[] generation(String[] universe) {
		if (universe[1].substring(1,2).equals("X")) {
			int count = 0;
			for (int row=0; row<=2; row++) {
				for (int col=0; col<=2; col++) {
					if (universe[row].substring(col, col+1).equals("X"))
						count++;
				}
			}
			if (count == 3)
				return new String[] {
					"   ",
					" X ",
					"   "};
			else
				return new String[] {
					"   ",
					"   ",
					"   "};
		} else
			return new String[] {
				"   ",
				"   ",
				"   "};
	}

}
