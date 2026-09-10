package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aopExamProject.Spielemechanik.Player;

public class PodiumUI extends JPanel 
{
	JPanel podium;
	JLabel name;
	
	public PodiumUI() 
	{
		setLayout(new BorderLayout());
		name = new JLabel("Gewonnen hat: ");
		name.setHorizontalAlignment(SwingConstants.CENTER);
		name.setFont(new Font("Comic Sans MS", Font.BOLD + Font.ITALIC, 22));
		name.setForeground(Color.GREEN);
		this.add(name);
		
	}
	
	public void showPodium(Player winner) 
	{
		name.setText("Gewonnen hat: " + winner.getName() + "!");
	}
}