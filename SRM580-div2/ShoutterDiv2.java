public class ShoutterDiv2 {

	public int count(int[] s, int[] t) {
		int friendCount = 0;
		for (int i = 0; i < s.length; i++) {
			for (int j = i + 1; j < s.length; j++) {
				if (t[j] >= s[i] && s[j] <=  t[i]) {
					friendCount++;
				}
			}
		}
		return friendCount;
	}
}
