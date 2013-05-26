import java.util.ArrayList;
import java.util.List;

public class EelAndRabbit {
	public int getmax(int[] l, int[] t) {
		List<Eel> list = new ArrayList<EelAndRabbit.Eel>(l.length);
		for (int i = 0; i < l.length; i++) {
			list.add(new Eel(t[i], t[i] + l[i]));
		}
		long[] catched = new long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			int head = list.get(i).head;
			catched[i] |= 1L << i;
			for (int j = 0; j < list.size(); j++) {
				Eel eel = list.get(j);
				if (head >= eel.head && head <= eel.tail) {
					catched[i] |= 1L << j;
				}
			}
		}
		int total = 0;
		for (int i = 0; i < catched.length; i++) {
			for (int j = i; j < catched.length; j++) {
				total = Math.max(total, Long.bitCount(catched[i] | catched[j]));
			}
		}

		return total;
	}

	private static final class Eel {
		private int head;
		private int tail;

		public Eel(int start, int end) {
			super();
			this.head = start;
			this.tail = end;
		}

	}
}
