package scoring;

public class ScoreCalculation {

	// oberer teil vom block 
	//dice value array mit 5 würfelergebnissen 
	//target zahl die gezählt werden soll
	public static int calculateUpper(int[] diceValues, int targetNumber) {
		int sum = 0;
		//hier gehts jeden würfel durch
		for (int value : diceValues) {
			if (value == targetNumber) { 
				sum += value;
			}
		}
		return sum;
	}
	//values array für würfelergebnis
	// counts array für wie oft augenzahl vorkommt
	public static int calculateThreeOfAKind(int[] values) {
		int[]counts = new int[7];//wegen1-6 augen
		int sum = 0;
		for (int v : values) { //hier jede zahl angucken
			counts[v]++; //hier die vorkommen zählen
			sum +=v;
		}
		//jede box checken von 1-6
		for (int i = 1; i <= 6; i++) {
			if (counts[i] >= 3) {
				return sum;
			}
		}
		return 0;
	}
	public static int calculateFourOfAKind(int[] values) {
		int[] counts = new int[7];
		int sum = 0;
		for (int v : values) {
			counts[v]++;
			sum += v;
		}
		for (int i = 1; i <= 6; i++) {
			if (counts[i] >= 4) {
				return sum;
			}
		}
		return 0;
	}
	public static int calculateFullHouse(int[] values) {
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
		if (hasThree && hasTwo == true) {
			return 25;
		}
		return 0;
	}
	//da wusste ich im leben nicht mehr weiter, hab Ki gefragt 
	//andere lösungsvorsätze die einafcher sind gerne erwünscht
	public static int calculateSmallStraight(int[] values) {
		int[] counts = new int[7]; 
		for (int v : values) 
			counts[v]++; 
		//hier entweder 1234 || 2345 || 3456
		//counts array besser
		//value array erst sortieren ist mir zu blöd
		//counts array guckt ob wenigsten 4 verschiedene zahlen da sind 
		boolean case1 = (counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1);
		boolean case2 = (counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1);
		boolean case3 = (counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1 && counts[6] >= 1);
		
		if (case1 || case2 || case3) {
			return 30;
		}
		return 0;
	}
	public static int calculateLargeStraight(int[] values) {
		int[] counts = new int[7]; 
		for (int v : values) 
			counts[v]++; 
		 
		boolean case1 = (counts[1] >= 1 && counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1);
		boolean case2 = (counts[2] >= 1 && counts[3] >= 1 && counts[4] >= 1 && counts[5] >= 1 && counts[6] >= 1);
		
		if (case1 || case2) {
			return 40;
		}
		return 0;
	}
	public static int calculateKniffel(int[] values) {
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
}
