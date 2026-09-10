package aopExamProject.scoring;

import java.util.HashMap;
import java.util.Map;

public class Scoreboard {
	
	private Map<ScoreCategory, Integer> scores = new HashMap<>();

	public void setScore(ScoreCategory cat, int points) {
		if (cat.isSettable()) {
			scores.put(cat, points);
		}
	}

	public int getUpperScore() {
		int sum = 0; 
		ScoreCategory[] upperFields = {
			ScoreCategory.ONES, ScoreCategory.TWOS, ScoreCategory.THREES, ScoreCategory.FOURS, ScoreCategory.FIVES, ScoreCategory.SIXES
		};
		for (ScoreCategory cat : upperFields) {
			sum += scores.getOrDefault(cat, 0);
		}
		return sum; 
	}		
	
	public int getBonusScore() {
		if (getUpperScore() >= 63) {
			return 35;
		}
		return 0;
	}
	
	public int getFinalUpperScore() {
		return getBonusScore() + getUpperScore();
	}
	
	public int getLowerScore() {
		int sum = 0; 
		ScoreCategory[] lowerFields = {
			ScoreCategory.THREE_OF_A_KIND, ScoreCategory.FOUR_OF_A_KIND, ScoreCategory.FULL_HOUSE, ScoreCategory.SMALL_STRAIGHT, ScoreCategory.LARGE_STRAIGHT, ScoreCategory.YAHTZEE, ScoreCategory.CHANCE
		};
		for (ScoreCategory cat : lowerFields) {
			sum += scores.getOrDefault(cat, 0);
		}
		return sum;
	}
		
	public int getFinalUpperScore2() {
		return getFinalUpperScore();
	}
	
	public int getTotalScore() {
		return getFinalUpperScore() + getLowerScore();
	}
	
	public boolean isFieldEmpty(ScoreCategory cat) {
		return scores.get(cat) == null;
	}
}