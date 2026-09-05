package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.function.Consumer;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class MenuUI extends JPanel
{
	private Runnable onPress;
	private Consumer<String> onAdd;
	private DefaultListModel<String>list;
	private JLabel errorprint;
	private JTextField nameField;
	
	public void addOnPress(Runnable r) {
		onPress = r;
	}
	
	public void setOnAdd(Consumer<String> c) 
	{
		onAdd = c;
	}
	public MenuUI() 
	{
		setLayout(new BorderLayout(15,5));
		this.setBackground(Color.white);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
		
		
		list = new DefaultListModel<>();
		JList<String> playerList = new JList<>(list);
		JScrollPane scrollPane = new JScrollPane(playerList);
		scrollPane.setBorder(BorderFactory.createTitledBorder("Spieler"));
		playerList.setVisibleRowCount(8);
		
		nameField = new JTextField(20);
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
		
		
		errorprint = new JLabel();
		errorprint.setHorizontalAlignment(SwingConstants.CENTER);
		errorprint.setForeground(Color.red);
		this.add(errorprint, BorderLayout.NORTH);
		
		addButton.addActionListener(e -> 
		{
			String name = nameField.getText().trim();
			if(!name.isEmpty()) 
			{
				onAdd.accept(name);
			}
		});
		
		startButton.addActionListener(e-> onPress.run());
		
		this.add(sidebar, BorderLayout.WEST);
		this.add(centerPanel, BorderLayout.CENTER);
		
	}
	public void addPlayerToList(String name) 
	{
		list.addElement(name);
		nameField.setText("");
		nameField.requestFocus();
	}
	public void showError(String text) 
	{
		errorprint.setText(text);
	}
}

