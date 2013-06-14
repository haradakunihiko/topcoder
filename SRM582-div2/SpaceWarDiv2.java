import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
		List<Enemy> list = new ArrayList<SpaceWarDiv2.Enemy>(
				enemyStrength.length);
		for (int i = 0; i < enemyStrength.length; i++) {
			list.add(new Enemy(enemyStrength[i], enemyCount[i]));
		}
		Collections.sort(list);
		return list;
	}

	private class Enemy implements Comparable<Enemy> {

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

		public int compareTo(Enemy o) {
			return strength - o.strength;
		}
	}

	private class Fatigue implements Comparable<Fatigue> {
		int cnt;

		public int compareTo(Fatigue o) {
			return cnt - o.cnt;
		}

		@Override
		public String toString() {
			return String.valueOf(cnt);
		}
	}

}
