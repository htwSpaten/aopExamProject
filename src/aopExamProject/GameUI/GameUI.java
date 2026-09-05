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
	private JLabel label;
	private MenuUI menu;
	private PlayingFieldUI playfield;
	private PodiumUI podium;
	ArrayList<Player> kniffler;
	private Player currentPlayer;
	
	public GameUI(GameMechanics game) 
	{
		this.game = game;
		
		this.frame = new JFrame("Kniffel");
		this.label = new JLabel("Am Zug: - ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);
		
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
				playfield.showPlayer(currentPlayer);
				playfield.startGame();
				
				
				cards.show(container, "game");
				
			}else {
				menu.showError("------Keine Spieler vorhanden!-------");
			}
		});
		
		
		container.add(menu, "menu");
		container.add(playfield, "game");
		container.add(podium , "podest");
		
		frame.setContentPane(container);
		frame.setVisible(true);
	}
	
	
	
	public void refreshName(Player currentPlayer) {
		label.setText("Am Zug: " + currentPlayer.getName() + " " +currentPlayer.getId());
	}
}
