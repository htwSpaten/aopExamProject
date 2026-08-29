package aopExamProject.GameUI;

import java.awt.BorderLayout;

import javax.swing.*;

import aopExamProject.Spielemechanik.GameMechanics;

public class GameMechanicUI 
{
	private final GameMechanics game;
	private final JFrame frame;
	
	public GameMechanicUI(GameMechanics game) 
	{
		this.game = game;
		this.frame = new JFrame("Kniffel");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1920, 1080);
	}
	
	public void setupUI()
	{
		JPanel panel = new JPanel(new BorderLayout(5,5));
		
		
		JPanel topPanel = new JPanel();
		JTextField nameField = new JTextField(15);
		JButton addButton = new JButton("Spieler hinzufügen");
		topPanel.add(nameField);
		topPanel.add(addButton);
		
		DefaultListModel<String> list = new DefaultListModel<>();
		JList<String> playerList = new JList<>(list);
		JScrollPane scrollPane = new JScrollPane(playerList);
		
		JButton startButton = new JButton("Spiel starten");
		
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
		
		panel.add(topPanel, BorderLayout.NORTH);
		panel.add(scrollPane, BorderLayout.CENTER);
		panel.add(startButton, BorderLayout.SOUTH);
		
		frame.setContentPane(panel);
		frame.setVisible(true);
	}
}
