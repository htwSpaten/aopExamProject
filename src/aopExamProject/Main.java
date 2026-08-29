package aopExamProject;

import aopExamProject.dices.DiceCup;

public class Main {

	public static void main(String[] args) {
		
		// test run for a dice roll: 
		DiceCup cup = new DiceCup();
		cup.getDiceValues();
		for(int v : cup.getDiceValues()) {
			System.out.print(v);
		}
		System.out.println();
		cup.rollDices();
		for(int v : cup.getDiceValues()) {
			System.out.print(v);
		}
			
	}

}
