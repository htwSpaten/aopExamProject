package aopExamProject.scoring;

public class Scoreboard {
	
	public static final int ONES = 0;
	public static final int TWOS = 1;
	public static final int THREES = 2;
	public static final int FOURS = 3;
	public static final int FIVES = 4;
	public static final int SIXES = 5;
	
	public static final int UPPER_SCORE = 6;
	public static final int BONUS_SCORE = 7;
	public static final int FINAL_UPPER_SCORE = 8;
	
	public static final int THREE_OF_A_KIND = 9;
	public static final int FOUR_OF_A_KIND = 10;
	public static final int FULL_HOUSE = 11;
	public static final int SMALL_STRAIGHT = 12;
	public static final int LARGE_STRAIGHT = 13;
	public static final int YAHTZEE = 14;
	public static final int CHANCE = 15;
	
	public static final int LOWER_SCORE = 16;
	public static final int FINAL_UPPER_SCORE_2 = 17;
	public static final int TOTAL_SCORE = 18;
	public static final int FIELD_COUNT = 19;
	
	public Integer[] scores = new Integer[FIELD_COUNT];
	public String[] fieldNames = {"Einser","Zweier","Dreier","Vierer","Fünfer","Sechser","gesamt","Bonus bei 63 oder mehr","gesamt oberer Teil","Dreierpasch","Viererpasch","Full House","Kleine Straße","Große Straße","Kniffel","Chance","gesamt unterer Teil","gesamt oberer Teil","Endsumme"};
	public String[] infoFieldNames = {"nur 1er zählen","nur 2er zählen","nur 3er zählen","nur 4er zählen","nur 5er zählen","nur 6er zählen","-->","+35","-->","alle Augen zählen","alle Augen zählen","25 Punkte","30 Punkte","40 Punkte","50 Punkte","alle Augen zählen","-->","-->","-->"};
	
	public int getUpperScore() {
		int sum = 0; 
		for (int i = ONES; i <= SIXES; i++) {
			if (scores[i] != null) {
				sum += scores[i];
			}
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
		for (int i = THREE_OF_A_KIND; i <= CHANCE; i++) {
			if (scores[i] != null) {
				sum += scores[i]; 
			}
		}
		return sum;
	} 
	
	//dennis
	public int getTotalScore() {
		return getFinalUpperScore() + getLowerScore();
	}
	
	public boolean isFieldEmpty(int index) {
		if (index >= 0 && index < scores.length) {
			//prüft ob feld noch null ist 
			return scores[index] == null;
		}
		return false; 
	}
	
	public Integer[] getScore() {
		return scores;
	}
	
	public void setScore(int index, int points) {
		//prüfen ob nummer gültig
		if (index >= 0 && index < scores.length) {
			//punkte ins feld eintragen
			this.scores[index] = points;
		}
	}
}