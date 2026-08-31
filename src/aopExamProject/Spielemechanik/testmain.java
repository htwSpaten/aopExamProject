package aopExamProject.Spielemechanik;
import aopExamProject.GameUI.*;

public class testmain {

	public static void main(String[] args) {
		GameMechanics game = new GameMechanics();
		GameMechanicUI play = new GameMechanicUI(game);
		play.setupUI();

	}

}
