package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import aopExamProject.Spielemechanik.Player;
import aopExamProject.dices.DiceCup;
import aopExamProject.scoring.*;

// keine Listener! WEIL PLAYER FEHLT!
public class PlayingFieldUI extends JPanel
{
	private JPanel scoreboardContainer;
	private JLabel playerStatus;
	private ScoreboardPanel playersScoreboard;
	private DiceCup cupUI;
	private ArrayList<Player> knifflers;
	public PlayingFieldUI(ArrayList<Player> knifflers)
	{
		setLayout(new BorderLayout(15,5));
		scoreboardContainer = new JPanel(new BorderLayout(15,5));
		
		this.knifflers = knifflers;
		
		this.playerStatus = new JLabel("-");
		this.add(playerStatus, BorderLayout.NORTH);
		playerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		this.setBackground(Color.WHITE);
		this.add(scoreboardContainer, BorderLayout.CENTER);
					
	}

	public void startGame() 
	{
		
		playersScoreboard = new ScoreboardPanel(knifflers);
		JScrollPane scrollPane = new JScrollPane(playersScoreboard);
		scoreboardContainer.add(scrollPane);
		cupUI = new DiceCup();
		this.add(cupUI.getPanel(), BorderLayout.SOUTH);
		cupUI.addDiceRollListener(playersScoreboard);
		
	}
	
	public ScoreboardPanel getPanel() 
	{
		
		return playersScoreboard;
	}
	public DiceCup getDiceCup() 
	{
		return cupUI;
	}
	
	
	public void refreshName(Player currentPlayer) {
		playerStatus.setText("Am Zug: " + currentPlayer.getName() + " " +currentPlayer.getId());
		
		
	}
}
