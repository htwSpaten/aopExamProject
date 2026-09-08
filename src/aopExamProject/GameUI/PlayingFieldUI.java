package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aopExamProject.Spielemechanik.Player;
import aopExamProject.dices.DiceCup;
import aopExamProject.scoring.*;

// keine Listener! WEIL PLAYER FEHLT!
public class PlayingFieldUI extends JPanel
{
	private Runnable onPress;
	
	private JPanel scoreboardContainer;
	private JLabel playerStatus;
	private ScoreboardPanel playersScoreboard;
	private DiceCup cupUI;
	private ArrayList<Player> knifflers;
	public PlayingFieldUI(ArrayList<Player> knifflers)
	{
		setLayout(new BorderLayout(15,5));
		scoreboardContainer = new JPanel(new BorderLayout(15,5));
		scoreboardContainer.setPreferredSize(new Dimension(1000, 400));
		
		this.knifflers = knifflers;
		
		this.playerStatus = new JLabel("-");
		this.add(playerStatus, BorderLayout.NORTH);
		playerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		JButton change = new JButton("wechsel Spieler!");//testing
		this.add(change, BorderLayout.WEST);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		this.add(scoreboardContainer, BorderLayout.EAST);
		
		
		change.addActionListener(e-> onPress.run());
		
				
	}

	public void startGame() 
	{
		playersScoreboard = new ScoreboardPanel(knifflers);
		scoreboardContainer.add(playersScoreboard);
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
	public void ChangeOnPress(Runnable r) {
		onPress = r;
	}
}
