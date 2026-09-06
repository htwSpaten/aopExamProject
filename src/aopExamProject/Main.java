package aopExamProject;

import aopExamProject.GameUI.*;
import aopExamProject.Spielemechanik.GameMechanics;


public class Main {

	public static void main(String[] args) 
	{
			GameMechanics game = new GameMechanics();
			GameUI ui = new GameUI(game);
			ui.initUI();		
	}

}
