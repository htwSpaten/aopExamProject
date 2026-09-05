package aopExamProject.Scoring;

import java.util.EnumMap;
import java.util.Map;

//13 Felder und Wert "null" = leer und 0 = gestrichen
public class Scoreboard {
	
	private Map<EScoreCategory, Integer> scores;
	
	// ScoreCategory.ONES -> an enum constant (an instance)
	// ScoreCategory.ONES.getFieldName() -> get german name ("Einser)
	// ScoreCategory.class -> class object, representing enum type itself
	// scores.values() -> returns Collection<Integer> all the values without a key
	// scores.put(ScoreCategory.ONES, 3) -> setScore...
	// ScoreCategory.ONES.ordinal() -> returns position in the enum
	// ScoreCategory.values() -> returns an array of all the enum names
	// ScoreCategory.values().length = total number of fields in enum
	
	// better readable because its clear, what is upper section
	private static final EScoreCategory[] UPPER_SECTION = {
		    EScoreCategory.ONES, EScoreCategory.TWOS, EScoreCategory.THREES,
		    EScoreCategory.FOURS, EScoreCategory.FIVES, EScoreCategory.SIXES
		};

	Scoreboard() {
		scores = new EnumMap<>(EScoreCategory.class);
		// empty until we fill it
	}
	
    public Integer getScore(EScoreCategory category) {
        return scores.get(category); // null if not yet scored
    }

	public int getTotalScore() {
		int total = 0;
		for (Integer s : scores.values()) {
			if (s != null) 
				total += s;
		}
		
		return total;
	}
	//for Dennis
	public int getUpperScore() {
		int sum = 0; 
		for (EScoreCategory cat : UPPER_SECTION) {
			Integer s = scores.get(cat);
			if (s != null) {
				sum += s;
				}
			}
		return sum; 
		}
	
	public boolean isFieldEmpty(EScoreCategory category) {
			return scores.get(category) == null;
	}
	
	public void setScore(EScoreCategory category, int value) {
		     scores.put(category, value);
	}
		  
   public String getFieldName(EScoreCategory category) {
        return category.getFieldName();
    }
}