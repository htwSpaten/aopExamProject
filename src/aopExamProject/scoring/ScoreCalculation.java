package aopExamProject.scoring;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScoreCalculation {

	private static int calculateUpper(int[] diceValues, int targetNumber) {
		int sum = 0;
		
		for (int value : diceValues) {
			if (value == targetNumber) { 
				sum += value;
			}
		}
		return sum;
	}

	// counts array für wie oft Augenzahl vorkommt
	private static int calculateNumberOfAKind(int[] values, int number) {
		int[]counts = new int[7];// wegen 1-6 Augen
		int sum = 0;
		for (int v : values) { 
			counts[v]++;
			sum +=v;
		}

		for (int i = 1; i <= 6; i++) {
			if (counts[i] >= number) {
				return sum;
			}
		}
		return 0;
	}
	
	private static int calculateFullHouse(int[] values) {
		int[] counts = new int[7];
		for (int v : values) {
			counts[v]++;
		}
		boolean hasThree = false;
		boolean hasTwo = false; 
		
		for (int i = 1; i <= 6; i++) {
			if (counts[i] == 3) {
				hasThree = true;
			}
			if (counts[i] == 2) {
				hasTwo = true;
			}
		}
		
		if (hasThree && hasTwo) {
			return 25;
		}
		return 0;
	}
	
	private static int calculateStraight(int[] values, int straightSize) {
		List<Integer> sorted = new ArrayList<>();
		for (int v : values) 
			sorted.add(v);
		
		sorted.sort((a,b) -> {
			// aufsteigend sortieren
			return a-b;
		});
		
		// zählt aufeinander folgende Zahlen
		int count = 1;
		
		for(int i=0;i<=sorted.size()-2;i++) {
			if(sorted.get(i)+1 == sorted.get(i+1)) {
				count++;
			}
		}
		
		if (count >= 4 && straightSize == 4) {
			return 30;
		} else if (count == 5 ) {
			return 40;
		}
		return 0;
		
	}
	
	private static int calculateYahtzee(int[] values) {
		int[] counts = new int[7];
		for (int v : values)
			counts[v]++;
		
		for (int i = 1; i <= 6; i++) {
			if (counts[i] == 5)
				return 50;
		}
		return 0;
	}
	
	public static int calculateChance(int[] values) {
		int sum = 0;
		for (int v : values) {
			sum += v;
		}
		return sum;
	}
	
	public static Map<ScoreCategory, Integer> getAllPossibleScores(int[] dice) {
		Map<ScoreCategory, Integer> result = new HashMap<>();
		result.put(ScoreCategory.ONES, calculateUpper(dice, 1));
		result.put(ScoreCategory.TWOS, calculateUpper(dice, 2));
		result.put(ScoreCategory.THREES, calculateUpper(dice, 3));
		result.put(ScoreCategory.FOURS, calculateUpper(dice, 4));
		result.put(ScoreCategory.FIVES, calculateUpper(dice, 5));
		result.put(ScoreCategory.SIXES, calculateUpper(dice, 6));
		result.put(ScoreCategory.THREE_OF_A_KIND, calculateNumberOfAKind(dice, 3));
		result.put(ScoreCategory.FOUR_OF_A_KIND,calculateNumberOfAKind(dice, 4));
		result.put(ScoreCategory.FULL_HOUSE, calculateFullHouse(dice));
		result.put(ScoreCategory.SMALL_STRAIGHT, calculateStraight(dice, 4));
		result.put(ScoreCategory.LARGE_STRAIGHT, calculateStraight(dice, 5));
		result.put(ScoreCategory.YAHTZEE, calculateYahtzee(dice));
		result.put(ScoreCategory.CHANCE, calculateChance(dice));
		
		return result;
	}
}
