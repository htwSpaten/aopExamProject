package aopExamProject;

import java.util.ArrayList;

import aopExamProject.GameUI.*;
import aopExamProject.Spielemechanik.GameMechanics;
import aopExamProject.Spielemechanik.Player;


public class Main {

	public static void main(String[] args) 
	{
		
			GameMechanics game = new GameMechanics();
			GameUI ui = new GameUI(game);
			ui.initUI();
		
		
		/*
		GameMechanics game = new GameMechanics();
		PodiumUI pod = new PodiumUI();
		GameUI ui = new GameUI(game);
		ui.initUI();
		game.addPlayer("Paul");
		game.addPlayer("Justus");
		game.addPlayer("Steve");
		ArrayList<Player> spieler = game.getAllKnifflers();
		Player paul = spieler.get(0);
		paul.getScore().setScore(0, 200);
		Player steve = spieler.get(2);
		steve.getScore().setScore(0, 20);
		Player justus = spieler.get(1);
		justus.getScore().setScore(0, 300);
		
		Player winner = game.getWinner();
		ui.onGameOver(winner);
		*/
	}

}
