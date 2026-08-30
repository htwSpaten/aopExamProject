package aopExamProject.dices;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;

public class DiceCup { 
	private List<Dice> dices;
	protected final JFrame cupUI; // change to JPanel when merged (?)
	
	public DiceCup() {
		
		cupUI = new JFrame("Würfelbecher");
		cupUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cupUI.setLayout(new FlowLayout());
		
		JButton button = new JButton("würfeln");
		cupUI.add(button);
		
		dices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
		    Dice dice = new Dice();
			dices.add(dice);
			cupUI.add(dice.getPanel());   
		}
		
		button.addActionListener(e -> {
			rollDices();
		});
		
		cupUI.pack();
		cupUI.setVisible(true);
	}
	
	public int[] getDiceValues() {
		int[] result = new int[dices.size()];
		for(int i = 0; i<dices.size(); i++) {
			result[i] = dices.get(i).getValue();
		}
		return result;
	}
	
	public void rollDices() {
		for(Dice dice : dices) {
			dice.rollDice();
		}
	}
	

}
