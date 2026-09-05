package aopExamProject.Scoring;

public enum EScoreCategory {
	ONES("Einser"),
    TWOS("Zweier"),
    THREES("Dreier"),
    FOURS("Vierer"),
    FIVES("Fünfer"),
    SIXES("Sechser"),
    THREE_OF_A_KIND("Dreierpasch"),
    FOUR_OF_A_KIND("Viererpasch"),
    FULL_HOUSE("Full House"),
    SMALL_STRAIGHT("Kleine Straße"),
    LARGE_STRAIGHT("Große Straße"),
    YAHTZEE("Kniffel"),
    CHANCE("Chance");
	
    private final String fieldName;

    EScoreCategory(String fieldName) {
        this.fieldName = fieldName;
    }
    
    public String getFieldName() {
        return fieldName;
    }
}
