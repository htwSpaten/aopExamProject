package aopExamProject.dices;

// TODO: awt correct or replace with swing elements?
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DiceImage extends JPanel {

	public DiceImage() {
		setBackground(Color.BLACK);
	}
	
	public void paintComponent (Graphics g) {
		//Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillOval(0, 0, getWidth(), getHeight());
	}

}
