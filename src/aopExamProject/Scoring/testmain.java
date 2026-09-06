package aopExamProject.Scoring;

import aopExamProject.Spielemechanik.GameMechanics;
import aopExamProject.Spielemechanik.Player;

public class testmain {

	public testmain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMechanics game = new GameMechanics();
		game.addPlayer("paul");
		
		Player paul=game.getCurrentPlayer();
		paul.toggleIsCurrent();
		
		
		ScoreboardPanel panel = new ScoreboardPanel(game.getAllKnifflers());
		
		//int[] dice = {1,1,1,1,1};
		
		//panel.update(dice);

	}

}
