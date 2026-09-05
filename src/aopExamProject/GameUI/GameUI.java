package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
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
	private Player currentPlayer; //aktueller Spieler
	private Scoreboard scoreboard; //scoreboard daten
	private ScoreboardPanel playersScoreboard;  //ui aufruf
	private JPanel scoreboardContainer; //platzhalter
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
		scoreboardContainer = new JPanel(new BorderLayout(15,5));
		scoreboardContainer.setPreferredSize(new Dimension(400, 0));
		container.add(lobbyUI(), "menu");
		container.add(playUI(), "game");
		container.add(podiumUI(), "podest");
		
		frame.setContentPane(container);
		frame.setVisible(true);
	}
	
	public JPanel lobbyUI()
	{
		JPanel panel = new JPanel(new BorderLayout(15,5));
		panel.setBackground(Color.white);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		
		DefaultListModel<String> list = new DefaultListModel<>();
		JList<String> playerList = new JList<>(list);
		JScrollPane scrollPane = new JScrollPane(playerList);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Spieler"));
		playerList.setVisibleRowCount(8);
		
		JTextField nameField = new JTextField(20);
		JButton addButton = new JButton("Spieler hinzufügen");
		JPanel input = new JPanel(new BorderLayout(0,5));
		input.add(nameField, BorderLayout.NORTH);
		input.add(addButton, BorderLayout.SOUTH);
		
		JPanel sidebar = new JPanel(new BorderLayout(0,5));
		sidebar.setPreferredSize(new Dimension(220,0));
		sidebar.add(scrollPane, BorderLayout.CENTER);
		sidebar.add(input, BorderLayout.SOUTH);
		
		JButton startButton = new JButton("Spiel starten");
		JPanel centerPanel = new JPanel(new GridBagLayout());
		centerPanel.add(startButton);
		startButton.setFont(startButton.getFont().deriveFont(20f));
		startButton.setMargin(new Insets(15,40,15,40));
		
		JPanel topPanel = new JPanel(new BorderLayout());
		JLabel errorprint = new JLabel();
		errorprint.setHorizontalAlignment(SwingConstants.CENTER);
		errorprint.setForeground(Color.red);
		topPanel.add(errorprint, BorderLayout.NORTH);
		
		
		addButton.addActionListener(e -> {
			String name = nameField.getText().trim();
			if(!name.isEmpty()) 
			{
				game.addPlayer(name);
				list.addElement(name);
				nameField.setText("");
				nameField.requestFocus();
			}
		});
		startButton.addActionListener(e-> {
			if(game.getPlayerCount() >= 1) {
				game.play();
				currentPlayer= game.getCurrentPlayer();
				refreshName(currentPlayer);
				scoreboard = currentPlayer.getScore();
				playersScoreboard = new ScoreboardPanel(scoreboard);
				scoreboardContainer.add(playersScoreboard);
				scoreboardContainer.revalidate();
				scoreboardContainer.repaint();
				
				cards.show(container, "game");
			}else {
				errorprint.setText("Nicht genug Spieler!");
			}
		});
		
		
		panel.add(sidebar, BorderLayout.WEST);
		panel.add(centerPanel, BorderLayout.CENTER);
		centerPanel.add(topPanel);
		
		
		return panel;
	}
	
	private JPanel playUI() {
		JPanel panel = new JPanel(new BorderLayout(15,5));
		JButton change = new JButton("wechsel Spieler!");//testing
		panel.add(change, BorderLayout.WEST);
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		
		DiceCup cupUI = new DiceCup();
		panel.add(cupUI.getPanel(), BorderLayout.SOUTH);
		panel.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(scoreboardContainer, BorderLayout.EAST);
		
		change.addActionListener(e-> {
			game.changePlayer();
			currentPlayer = game.getCurrentPlayer();
			refreshName(currentPlayer);
		});
		
		
		return panel;
	}
	private JPanel podiumUI() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Platzhalter PodiumUI"), BorderLayout.CENTER);
		return panel;
	}
	public void refreshName(Player currentPlayer) {
		label.setText("Am Zug: " + currentPlayer.getName() + " " +currentPlayer.getId());
	}
}
