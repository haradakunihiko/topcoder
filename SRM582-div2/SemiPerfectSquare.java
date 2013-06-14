import java.util.LinkedList;
import java.util.List;

public class SemiPerfectSquare {

	public String check(int N) {
		if (check(N, getPerfectSquares(N))) {
			return "Yes";
		} else {
			return "No";
		}
	}

	private boolean check(int N, List<Integer> perfectSquares) {
		for (Integer ps : perfectSquares) {
			int intPs = ps.intValue();
			if (N % (intPs * intPs) == 0) {
				if (N / (intPs * intPs) < intPs) {
					return true;
				}
			}
		}
		return false;
	}

	private List<Integer> getPerfectSquares(int max) {
		List<Integer> list = new LinkedList<Integer>();
		int i = 2;
		while (i * i <= max) {
			list.add(i);
			i++;
		}

		return list;
	}
}
