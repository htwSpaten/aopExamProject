package aopExamProject.dices;

import java.util.Random;

public class Dice {
	private int number;
	private boolean locked;

	public Dice() {
		locked = false;
		number = 0;
	}

	public int getValue() {
		return number;
	}
	
	public void setValue(int v) {
		if(v>0 && v<=6) {
			number = v;
		}
	}
	
	public boolean getLocked() {
		return locked;
	}
	
	public void setLocked() {
		locked = !locked;
	}
	
	public void rollDice() {
		Random r = new Random();
		int v = r.nextInt(6)+1;
		if (locked != true) {
			setValue(v);
		}
	}
}
