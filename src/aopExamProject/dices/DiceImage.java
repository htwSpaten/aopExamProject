package aopExamProject.dices;

// TODO: awt correct or replace with swing elements?
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class DiceImage extends JPanel {
	private static final int SIZE = 60;
	private int value;

	public DiceImage() {
	}
	
	public DiceImage(int v) {
		value = v;
	}
	
	public void setValue(int v) {
		value = v;
		repaint(); // triggers paint component (need a source?)
	}
	
	@Override
	public Dimension getPreferredSize() { //get source for that!!
		return new Dimension(SIZE,SIZE);
	}
	
	@Override
	public void paintComponent (Graphics g) {
		int width = getWidth();
		int height = getHeight();
		int sizePoint = width/5;
		int xCenter = width/2-sizePoint/2;
		int xLeft = width/4-sizePoint/2;
		int xRight = width*3/4-sizePoint/2;
		int yCenter = height/2-sizePoint/2;
		int yUp = height/4-sizePoint/2;
		int yDown = height*3/4-sizePoint/2;;
		
		super.paintComponent(g); // clear background
		Graphics2D g2 = (Graphics2D) g; // get Graphics2D features
		g2.setColor(Color.WHITE);
		g2.fillRoundRect(0, 0, width, height, 15, 15);
		g2.setColor(Color.BLACK);
		
		// TODO: make this better organized
		switch(value) {
			case 1: g2.fillOval(xCenter, yCenter, sizePoint, sizePoint);
			        break;
			case 3: g2.fillOval(xCenter, yCenter, sizePoint, sizePoint);
			case 2: g2.fillOval(xLeft, yUp, sizePoint, sizePoint);
		            g2.fillOval(xRight, yDown, sizePoint, sizePoint);
		            break;
			case 5: g2.fillOval(xCenter, yCenter, sizePoint, sizePoint);     
		    case 4: g2.fillOval(xLeft, yUp, sizePoint, sizePoint);
		    		g2.fillOval(xRight, yDown, sizePoint, sizePoint);
		    		g2.fillOval(xRight, yUp, sizePoint, sizePoint);
		    		g2.fillOval(xLeft, yDown, sizePoint, sizePoint);
		    		break;
		    case 6: g2.fillOval(xLeft, yUp, sizePoint, sizePoint);
					g2.fillOval(xRight, yDown, sizePoint, sizePoint);
					g2.fillOval(xRight, yUp, sizePoint, sizePoint);
					g2.fillOval(xLeft, yDown, sizePoint, sizePoint);
					g2.fillOval(xLeft, yCenter, sizePoint, sizePoint);
					g2.fillOval(xRight, yCenter, sizePoint, sizePoint);
					break;
			default:;
		}
		/*
		case 3: 1 + 2
		case 4: 2 + -2
		case 5: 4 + 1
		case 6: 4 + weitere 2 in center;
		*/
		
		
		// create roll dice animation
	}

}
