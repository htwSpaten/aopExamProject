package aopExamProject.dices;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class DiceCup { 
	private List<DiceRollListener> listeners = new ArrayList<>();
	
	private List<Dice> dices;
	private int count;
	private JLabel counterLabel;
	private JButton button;
	private boolean debugOn;
	private final JCheckBox debugBox;
	protected final JPanel cupUI; 
	
	public DiceCup() {
		cupUI = new JPanel();
		cupUI.setLayout(new BorderLayout());
		
		// "würfeln" button area left
		JPanel rollPanel = new JPanel();
		rollPanel.setLayout(new BorderLayout());
		button = new JButton("würfeln");
		counterLabel = new JLabel(String.format("übrige Würfe: "));
		
		rollPanel.add(button, BorderLayout.CENTER);
		rollPanel.add(counterLabel, BorderLayout.SOUTH);
		cupUI.add(rollPanel, BorderLayout.WEST);
		
		// dices area in center
		JPanel dicePanel = new JPanel();
		dicePanel.setLayout(new FlowLayout());
		dices = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
		    Dice dice = new Dice();
			dices.add(dice);
			dicePanel.add(dice.getPanel());   
		}
		
		cupUI.add(dicePanel, BorderLayout.CENTER);
		
		// debug option right
		debugBox = new JCheckBox("debug mode");
		cupUI.add(debugBox, BorderLayout.EAST);
		
		// listeners
		debugBox.addItemListener(e -> {
			debugOn = debugBox.isSelected();
			toggleDicesDebug();
		});
		
		button.addActionListener(e -> {
			rollDices();
			notifyListeners();
		});
		
		resetCup();
	}
	
	private int[] getDiceValues() {
		int[] result = new int[dices.size()];
		for(int i = 0; i<dices.size(); i++) {
			result[i] = dices.get(i).getValue();
		}
		return result;
	}
	
	private void rollDices() {
		if(debugOn) {
			debugOn = false;
			debugBox.setSelected(false);
			toggleDicesDebug();
		} else {
			for(Dice dice : dices) {
				dice.rollDice();
			}
		}
		
		count--;
		counterLabel.setText(String.format("übrige Würfe: %d", count));
		
		if(count<=0) {
			button.setEnabled(false);
			debugBox.setEnabled(false);
		}
	}
	
	private void notifyListeners() {
		int[] values = getDiceValues();
		// for-loop, in case of multiple listeners (in the future)
		for (DiceRollListener listener : listeners) {
			listener.onDiceRolled(values);
		}
	}
	
	private void toggleDicesDebug() {
		for(Dice dice : dices) {
			dice.toggleDebug(debugOn);
		}
	}
	
	public void resetCup() {
		count = 3;
		debugOn = false;
		debugBox.setSelected(debugOn);
		counterLabel.setText(String.format("übrige Würfe: %d", count));
		button.setEnabled(true);
		debugBox.setEnabled(true);
		
		for(Dice dice : dices) {
			dice.resetDice();
		}
	}
	
	public void addDiceRollListener(DiceRollListener listener) {
		listeners.add(listener);
	}
	
	public void removeDiceRollListener(DiceRollListener listener) {
        listeners.remove(listener);
    }
	
	public JPanel getPanel() 
	{
		return cupUI;
	}

}
