package aopExamProject;

import aopExamProject.GameUI.GameMechanicUI;
import aopExamProject.Spielemechanik.GameMechanics;

public class Main {

	public static void main(String[] args) {
			GameMechanics game = new GameMechanics();
			GameMechanicUI play = new GameMechanicUI(game);
			
			game.gameSetup();
			play.setupUI();
			
			
	}

}
