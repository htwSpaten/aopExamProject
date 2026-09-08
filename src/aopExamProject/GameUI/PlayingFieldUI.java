package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
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


public class PlayingFieldUI extends JPanel
{
	private Runnable onPress;
	
	private JPanel scoreboardContainer;
	private JLabel playerStatus;
	private ScoreboardPanel playersScoreboard;
	private ArrayList<Player> kniffler;
	public PlayingFieldUI(ArrayList<Player> kniffler)
	{
		setLayout(new BorderLayout(15,5));
		scoreboardContainer = new JPanel(new BorderLayout(15,5));
		scoreboardContainer.setPreferredSize(new Dimension(400, 0));
		
		this.kniffler = kniffler;
		
		this.playerStatus = new JLabel("-");
		this.add(playerStatus, BorderLayout.NORTH);
		playerStatus.setHorizontalAlignment(SwingConstants.CENTER);
		JButton change = new JButton("wechsel Spieler!");//testing
		this.add(change, BorderLayout.WEST);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		DiceCup cupUI = new DiceCup();
		this.add(cupUI.getPanel(), BorderLayout.SOUTH);
		this.add(scoreboardContainer, BorderLayout.EAST);
		
		
		change.addActionListener(e-> onPress.run());
		
				
	}

	public void startGame() 
	{
		playersScoreboard = new ScoreboardPanel(kniffler);
		scoreboardContainer.add(playersScoreboard);
		playersScoreboard.updatePossibleScore(new int[] {1,1,1,1,1});
		scoreboardContainer.revalidate();
		scoreboardContainer.repaint();
	}
	public void refreshName(Player currentPlayer) {
		playerStatus.setText("Am Zug: " + currentPlayer.getName() + " " +currentPlayer.getId());
		if (playersScoreboard != null) {
			playersScoreboard.changePlayer();
		}
	}
	public void ChangeOnPress(Runnable r) {
		onPress = r;
	}
}
