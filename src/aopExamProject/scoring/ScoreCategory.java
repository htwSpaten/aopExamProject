package aopExamProject.scoring;

public enum ScoreCategory {
	
	ONES("Einser", "nur 1er zählen", true),
	TWOS("Zweier", "nur 2er zählen", true),
	THREES("Dreier", "nur 3er zählen", true),
	FOURS("Vierer", "nur 4er zählen", true),
	FIVES("Fünfer", "nur 5er zählen", true),
	SIXES("Sechser", "nur 6er zählen", true),
	
	UPPER_SCORE("gesamt", "->", false),
	BONUS_SCORE("Bonus bei 63 oder mehr", "+ 35", false), 
	FINAL_UPPER_SCORE("gesamt oberer Teil", "->", false),
	
	THREE_OF_A_KIND("Dreierpasch", "alle Augen zählen", true),
	FOUR_OF_A_KIND("Viererpasch", "alle Augen zählen", true),
	FULL_HOUSE("Full House", "+ 25", true),
	SMALL_STRAIGHT("Kleine Straße", "+ 30", true),
	LARGE_STRAIGHT("Große Straße","+ 40", true),
	YAHTZEE("Kniffel","+ 50", true),
	CHANCE("Chance","alle Augen zählen", true),
	
	LOWER_SCORE("gesamt unterer Teil","->", false),
	FINAL_UPPER_SCORE_2("gesamt oberer Teil","->", false),
	TOTAL_SCORE("Endsumme","->", false);
		
	private final boolean settable;
	private final String fieldName;
	private final String infoFieldName;
		
	ScoreCategory(String fieldName,String infoFieldName, boolean settable) {
		this.fieldName = fieldName;
		this.infoFieldName = infoFieldName;
		this.settable = settable;
	}
	
	public String getinfoFieldName() {
		return infoFieldName;
	}
		
	public String getfieldName() {
			return fieldName;
	}
		
	public boolean isSettable() {
			return settable;
	}
}

