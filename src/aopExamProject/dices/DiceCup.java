package aopExamProject.dices;

import java.util.ArrayList;
import java.util.List;

public class DiceCup {
	private List<Dice> dices;
	
	public DiceCup() {
		dices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
		    dices.add(new Dice());
		}
	}
	
	public int[] getDiceValues() {
		int[] result = new int[5];
		for(int i = 0; i<5; i++) {
			result[i] = dices.get(i).getValue();
		}
		return result;
	}
	
	public void rollDices() {
		for(Dice dice : dices) {
			dice.rollDice();
		}
	}
	

}
