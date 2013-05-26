import java.util.ArrayList;
import java.util.List;

public class EelAndRabbit {
	public int getmax(int[] l, int[] t) {
		List<Eel> list = new ArrayList<EelAndRabbit.Eel>(l.length);
		for (int i = 0; i < l.length; i++) {
			list.add(new Eel(t[i], t[i] + l[i]));
		}
		// i�Ԗڂ̓��̃^�C�~���O�ŕ߂܂��鎖���ł���ꍇ��1�ŕ\���B
		long[] catched = new long[list.size()];
		for (int i = 0; i < list.size(); i++) {
			Eel first = list.get(i);
			catched[i] |= 1L << i;
			for (int j = 0; j < list.size(); j++) {
				Eel second = list.get(j);
				if (first.head >= second.head && first.head <= second.tail) {
					catched[i] |= 1L << j;
				}
			}
		}
		int total = 0;
		for (int i = 0; i < catched.length; i++) {
			for (int j = i; j < catched.length; j++) {
				// 2��̎��s�ł́A�ő升�v�l���v��
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
