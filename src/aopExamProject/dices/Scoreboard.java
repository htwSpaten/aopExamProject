package aopExamProject.dices;

public class Scoreboard implements DiceRollListener {

	@Override
	public void onDiceRolled(int[] values) {
		// check values and calculate points
		for (int v : values) System.out.print(v+ " ");
	}

	public static void main(String[] args) {

		// test run for a dice roll: 
				DiceCup cup = new DiceCup();
				Scoreboard scoreboard = new Scoreboard();
				cup.addDiceRollListener(scoreboard);
				
		//if gamer is done:
				//cup.removeDiceRollListener(scoreboard);
				
	}

}
