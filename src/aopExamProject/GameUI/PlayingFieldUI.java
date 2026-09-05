package aopExamProject.GameUI;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aopExamProject.dices.DiceCup;

public class PlayingFieldUI extends JPanel
{
	public PlayingFieldUI()
	{
		setLayout(new BorderLayout(15,5));
		JButton change = new JButton("wechsel Spieler!");//testing
		this.add(change, BorderLayout.WEST);
		this.setBackground(Color.WHITE);
		this.setBorder(BorderFactory.createEmptyBorder(20,20,20,20));		
	}
}
