package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.*;
import aopExamProject.Spielemechanik.*;
import aopExamProject.dices.*;
import aopExamProject.scoring.Scoreboard;
import aopExamProject.scoring.ScoreboardPanel;

public class GameUI 
{
	private final GameMechanics game;
	private final JFrame frame;
	private CardLayout cards;
	private JPanel container;
	private MenuUI menu;
	private PlayingFieldUI playfield;
	private PodiumUI podium;
	ArrayList<Player> kniffler;
	private Player currentPlayer;
	
	public GameUI(GameMechanics game) 
	{
		this.game = game;
		
		this.frame = new JFrame("Kniffel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1280, 720);
		
	}
	
	public void initUI() 
	{
		cards = new CardLayout();
		container = new JPanel(cards);
		menu = new MenuUI();
		kniffler = game.getAllKnifflers();
		playfield = new PlayingFieldUI(kniffler);
		podium = new PodiumUI();
		
		
		menu.setOnAdd(name -> {
			game.addPlayer(name);
			menu.addPlayerToList(name);
		});
		menu.addOnPress(()-> {
			if(game.getPlayerCount() >=1) {
				game.play();
				currentPlayer = game.getCurrentPlayer();
				playfield.refreshName(currentPlayer);
				currentPlayer.toggleIsCurrent();
				playfield.startGame();
				
				
				cards.show(container, "game");
				
			}else {
				menu.showError("------Keine Spieler vorhanden!-------");
			}
		});
		playfield.ChangeOnPress(()-> 
		{
			game.changePlayer();
			System.out.println("wechsle Spieler");
			currentPlayer = game.getCurrentPlayer();
			playfield.refreshName(currentPlayer);
			System.out.println("Spieler gewechselt");
		});
		
		
		container.add(menu, "menu");
		container.add(playfield, "game");
		container.add(podium , "podest");
		
		frame.setContentPane(container);
		frame.setVisible(true);
	}
	
	
	
	
}
