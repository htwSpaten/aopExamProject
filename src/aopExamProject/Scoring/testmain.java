package aopExamProject.Scoring;

import aopExamProject.Spielemechanik.GameMechanics;

public class testmain {

	public testmain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMechanics game = new GameMechanics();
		game.addPlayer("paul");

		ScoreboardPanel panel = new ScoreboardPanel(game.getAllKnifflers());
		
		int[] dice = {1,1,1,1,1};
		
		panel.update(dice);

	}

}
