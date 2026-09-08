package aopExamProject.scoring;

import aopExamProject.Spielemechanik.GameMechanics;
import aopExamProject.Spielemechanik.Player;

public class testmain implements ScoreSubmitListener{

	@Override
	public void onScoreSubmit(){
		System.out.println("Chlick!");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameMechanics game = new GameMechanics();
		
		game.addPlayer("paul");
		game.addPlayer("50sven");
		Player paul=game.getCurrentPlayer();
		paul.toggleIsCurrent();
		
		testmain x = new testmain();
		ScoreboardPanel panel = new ScoreboardPanel(game.getAllKnifflers());
		panel.addScoreSubmitListener(x);
		
		int[] dice = {1,1,1,1,1};
		
		panel.updatePossibleScore(dice);

	}

}
