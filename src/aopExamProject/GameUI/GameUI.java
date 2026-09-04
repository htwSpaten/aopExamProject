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

public class GameUI 
{
	private final GameMechanics game;
	private final JFrame frame;
	private CardLayout cards;
	private JPanel container;
	private JLabel label;
	
	public GameUI(GameMechanics game) 
	{
		this.game = game;
		this.frame = new JFrame("Kniffel");
		this.cards = new CardLayout();
		this.label = new JLabel("Am Zug: - ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);
		
	}
	
	public void initUI() 
	{
		cards = new CardLayout();
		container = new JPanel(cards);
		container.add(lobbyUI(), "menu");
		container.add(playUI(), "game");
		container.add(podiumUI(), "podest");
		lobbyUI();
		frame.setContentPane(container);
		frame.setVisible(true);
	}
	
	public JPanel lobbyUI()
	{
		JPanel panel = new JPanel(new BorderLayout(15,5));
		panel.setBackground(Color.GREEN);
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
				refreshName();
				cards.show(container, "game");
			}
		});
		
		
		panel.add(sidebar, BorderLayout.WEST);
		panel.add(centerPanel, BorderLayout.CENTER);
		
		return panel;
	}
	
	private JPanel playUI() {
		JPanel panel = new JPanel(new BorderLayout(15,5));
		panel.setBackground(Color.WHITE);
		panel.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		panel.add(new JLabel("Platzhalter PLAYUI"), BorderLayout.CENTER);
		DiceCup cupUI = new DiceCup();
		panel.add(cupUI.getPanel(), BorderLayout.SOUTH);
		panel.add(label, BorderLayout.NORTH);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(new JLabel("Scoreboard Platzhalter"), BorderLayout.EAST);
		return panel;
	}
	private JPanel podiumUI() {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(new JLabel("Platzhalter PodiumUI"), BorderLayout.CENTER);
		return panel;
	}
	public void refreshName() {
		Player currentPlayer = game.getCurrentPlayer();
		label.setText("Am Zug: " + currentPlayer.getName() + " " +currentPlayer.getId());
		
	}
}
