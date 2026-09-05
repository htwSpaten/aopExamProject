package aopExamProject.Scoring;

public class testmain {

	public testmain() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scoreboard board = new Scoreboard();
		ScoreboardPanel panel = new ScoreboardPanel(board);
		
		int[] dice = {1,1,1,1,1};
		
		panel.update(dice);

	}

}
