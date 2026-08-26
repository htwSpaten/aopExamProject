package scoring;
//13 Felder und Wert "null" = leer und 0 = gestrichen
public class Scoreboard {
	//position vom array festlegen
	public static final int EINSER = 0; 
	public static final int ZWEIER = 1; 
	public static final int DREIER = 2; 
	public static final int VIERER = 3; 
	public static final int FUENFER = 4; 
	public static final int SECHSER = 5;
	public static final int DREIERPASCH = 6;
	public static final int VIERERPASCH = 7;
	public static final int FULL_HOUSE = 8;
	public static final int KLEINE_STRASSE = 9;
	public static final int GROSSE_STRASSE = 10;
	public static final int KNIFFEL = 11;
	public static final int CHANCE = 12;
	
	public static final int ANZAHL_FELDER = 13;
	
	public Integer [] scores; // hier Punkte oder 0
	public String [] feldNamen; // hier name vom feld obviously
	
	public Scoreboard() {
		this.scores = new Integer[ANZAHL_FELDER]; //Integer soll wohl Zahlen UND Zustand speichern
		this.feldNamen = new String[ANZAHL_FELDER];
		
		//ertsmal alle felder leer setzen war meine Überlegung
		for (int i = 0; i < score.length; i++) {
			this.scores[i] = null;
		}
		
		this.feldNamen[EINSER] = "Einser";
		this.feldNamen[ZWEIER] = "Zweier";
		this.feldNamen[DREIER] = "Dreier";
		this.feldNamen[VIERER] = "Vierer";
		this.feldNamen[FUENFER] = "Fünfer";
		this.feldNamen[SECHSER] = "Sechser";
		this.feldNamen[DREIERPASCH] = "Dreierpasch";
		this.feldNamen[VIERERPASCH] = "Viererpasch";
		this.feldNamen[FULL_HOUSE] = "Full House";
		this.feldNamen[KLEINE_STRASSE] = "Kleine Straße";
		this.feldNamen[GROSSE_STRASSE] = "Große Straße";
		this.feldNamen[KNIFFEL] = "Kniffel";
		this.feldNamen[CHANCE] = "Chance";
		
	}
		public void setScore(int index, int point) {
			//prüfen ob nummer gültig
			if (index >= 0 && index < scores.length) {
				//punkte ins feld eintragen
				this.scores[index] = points;
			}
		}
		public boolean isFieldEmpty(int index) {
			if (index >= 0 && index < scores.length) {
				//prüft ob feld noch null ist 
				return scores[index] == null;
			}
		}
		
}