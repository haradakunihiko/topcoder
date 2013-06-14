import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SpaceWarDiv2 {

	public int minimalFatigue(int[] magicalGirlStrength, int[] enemyStrength,
			int[] enemyCount) {
		PriorityQueue<Fatigue> fatigueQueue = new PriorityQueue<Fatigue>();
		Arrays.sort(magicalGirlStrength);
		List<Enemy> enemies = getSortedEnemies(enemyStrength, enemyCount);
		int mgIndex = magicalGirlStrength.length - 1;
		for (int i = enemies.size() - 1; i >= 0; i--) {
			Enemy enemy = enemies.get(i);
			for (int j = mgIndex; j >= 0; j--) {
				int mgSt = magicalGirlStrength[j];
				if (mgSt >= enemy.strength) {
					mgIndex--;
					fatigueQueue.add(new Fatigue());
				} else {
					break;
				}
			}
			for (int k = 0; k < enemy.cnt; k++) {
				if (fatigueQueue.isEmpty()) {
					return -1;
				}
				Fatigue f = fatigueQueue.poll();
				f.cnt++;
				fatigueQueue.add(f);
			}
		}
		int result = -1;
		for (Fatigue f : fatigueQueue) {
			result = Math.max(result, f.cnt);
		}
		return result;
	}

	private List<Enemy> getSortedEnemies(int[] enemyStrength, int[] enemyCount) {
		List<Enemy> list = new LinkedList<SpaceWarDiv2.Enemy>();
		for (int i = 0; i < enemyStrength.length; i++) {
			list.add(new Enemy(enemyStrength[i], enemyCount[i]));
		}
		Collections.sort(list, new Comparator<Enemy>() {
			public int compare(Enemy o1, Enemy o2) {
				return Integer.valueOf(o1.strength).compareTo(o2.strength);
			}
		});
		return list;
	}

	private class Enemy {

		public Enemy(Integer strength, Integer cnt) {
			super();
			this.strength = strength;
			this.cnt = cnt;
		}

		int strength;
		int cnt;

		@Override
		public String toString() {
			return "st:" + strength + "cn:" + cnt;
		}
	}

	private class Fatigue implements Comparable<Fatigue> {
		int cnt;
		public int compareTo(Fatigue o) {
			if (cnt < o.cnt) {
				return -1;
			} else if (cnt == o.cnt) {
				return 0;
			} else {
				return 1;
			}
		}

		@Override
		public String toString() {
			return String.valueOf(cnt);
		}
	}

}
