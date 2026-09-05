package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import aopExamProject.Spielemechanik.Player;
import aopExamProject.dices.DiceCup;
import aopExamProject.scoring.*;


public class PlayingFieldUI extends JPanel
{
	
	private JPanel scoreboardContainer;
	private Player currentplayer;
	private ScoreboardPanel playersScoreboard;
	private ScoreboardPanel score;
	private ArrayList<Player> kniffler;
	public PlayingFieldUI(ArrayList<Player> kniffler)
	{
		setLayout(new BorderLayout(15,5));
		scoreboardContainer = new JPanel(new BorderLayout(15,5));
		scoreboardContainer.setPreferredSize(new Dimension(400, 0));
		this.kniffler = kniffler;
		
		
		
		
		JButton change = new JButton("wechsel Spieler!");//testing
		this.add(change, BorderLayout.WEST);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		DiceCup cupUI = new DiceCup();
		this.add(cupUI.getPanel(), BorderLayout.SOUTH);
		this.add(scoreboardContainer, BorderLayout.EAST);
		
	
		System.out.println("mission impossible");
		
		
	}
	

	public void showPlayer(Player currentplayer) 
	{
		currentplayer.toggleIsCurrent();
		
		

		
	}
	public void startGame() 
	{
		playersScoreboard = new ScoreboardPanel(kniffler);
		scoreboardContainer.add(playersScoreboard);
		scoreboardContainer.revalidate();
		scoreboardContainer.repaint();
	}
}
