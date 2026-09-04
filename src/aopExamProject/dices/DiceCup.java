package aopExamProject.dices;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DiceCup { 
	private List<DiceRollListener> listeners = new ArrayList<>();
	
	private List<Dice> dices;
	private int count = 3;
	private JLabel counterLabel;
	JButton button = new JButton("würfeln");
	protected final JFrame cupUI; // change to JPanel when merged (?)
	
	public DiceCup() {
		
		cupUI = new JFrame("Würfelbecher");
		cupUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		cupUI.setLayout(new FlowLayout());
		
		cupUI.add(button);
		
		counterLabel = new JLabel(String.format("übrige Würfe: %d", count));
		cupUI.add(counterLabel);
		
		dices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
		    Dice dice = new Dice();
			dices.add(dice);
			cupUI.add(dice.getPanel());   
		}
		
		button.addActionListener(e -> {
			rollDices();
			notifyListeners();
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
		count--;
		counterLabel.setText(String.format("übrige Würfe: %d", count));
		for(Dice dice : dices) {
			dice.rollDice();
		}
		
		if(count<=0) {
			button.setEnabled(false);
			return;
		}
	}
	
	public void addDiceRollListener(DiceRollListener listener) {
		listeners.add(listener);
	}
	
	public void removeDiceRollListener(DiceRollListener listener) {
        listeners.remove(listener);
    }
	
	public void notifyListeners() {
		int[] values = getDiceValues();
		// for Loop, in case of multiple listeners (in the future?)
		for (DiceRollListener listener : listeners) {
			listener.onDiceRolled(values);
		}
	}
	

}
