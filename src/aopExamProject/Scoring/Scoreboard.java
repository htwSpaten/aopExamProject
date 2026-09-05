package aopExamProject.Scoring;
//13 Felder und Wert "null" = leer und 0 = gestrichen
public class Scoreboard {
	//position vom array festlegen
	public static final int ONES = 0;
	public static final int TWOS = 1;
	public static final int THREES = 2;
	public static final int FOURS = 3;
	public static final int FIVES = 4;
	public static final int SIXES = 5;
	public static final int THREE_OF_A_KIND = 6;
	public static final int FOUR_OF_A_KIND = 7;
	public static final int FULL_HOUSE = 8;
	public static final int SMALL_STRAIGHT = 9;
	public static final int LARGE_STRAIGHT = 10;
	public static final int YAHTZEE = 11;
	public static final int CHANCE = 12;
	
	public static final int FIELD_COUNT = 13;
	
	public Integer [] scores; // hier Punkte oder 0
	public String [] fieldNames; // hier name vom feld obviously
	
	public Scoreboard() {
		this.scores = new Integer[FIELD_COUNT]; //Integer soll wohl Zahlen UND Zustand speichern
		this.fieldNames = new String[FIELD_COUNT];
		
		initializeFieldNames();
		
		//ertsmal alle felder leer setzen war meine Überlegung
		for (int i = 0; i < scores.length; i++) {
			this.scores[i] = null;
		}
	}
	
	public Integer[] getScore() {
		return scores;
	}
	public int getTotalScore() {
		int total = 0;
		for (Integer s : scores) {
			if (s != null) 
				total += s;
		}
		return total;
	}
	public int getUpperScore() {
		int sum = 0; 
		for (int i = ONES; i <= SIXES; i++) {
			if (scores[i] != null) {
				sum += scores[i];
				}
			}
		return sum; 
		}
	public void initializeFieldNames() {
		this.fieldNames[ONES] = "Einser";
		this.fieldNames[TWOS] = "Zweier";
		this.fieldNames[THREES] = "Dreier";
		this.fieldNames[FOURS] = "Vierer";
		this.fieldNames[FIVES] = "Fünfer";
		this.fieldNames[SIXES] = "Sechser";
		this.fieldNames[THREE_OF_A_KIND] = "Dreierpasch";
		this.fieldNames[FOUR_OF_A_KIND] = "Viererpasch";
		this.fieldNames[FULL_HOUSE] = "Full House";
		this.fieldNames[SMALL_STRAIGHT] = "Kleine Straße";
		this.fieldNames[LARGE_STRAIGHT] = "Große Straße";
		this.fieldNames[YAHTZEE] = "Kniffel";
		this.fieldNames[CHANCE] = "Chance";
	}
	public boolean isFieldEmpty(int index) {
		if (index >= 0 && index < scores.length) {
			//prüft ob feld noch null ist 
			return scores[index] == null;
		}
		return false; 
	}
		public void setScore(int index, int points) {
			//prüfen ob nummer gültig
			if (index >= 0 && index < scores.length) {
				//punkte ins feld eintragen
				this.scores[index] = points;
			}
		}
		public void testCompat() {
			assert true;
		}
}