package aopExamProject.dices;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Random;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dice implements ItemListener {
	private int number;
	private boolean locked;
	private final Random random = new Random();
	
	private final JPanel panel;
	private final JCheckBox lockBox;
	private final JLabel valueLabel;
	private final DiceImage diceImage;

	public Dice() {
		locked = false;
		number = 0;
		
		//create UI for a dice
		valueLabel = new JLabel(String.valueOf(number));
		lockBox = new JCheckBox("");
		lockBox.setSelected(locked);
		lockBox.addItemListener(this);
		lockBox.setEnabled(false);
		diceImage = new DiceImage(number, lockBox);
		
		panel = new JPanel();
		panel.add(diceImage);
		panel.add(valueLabel);
	}

	public int getValue() {
		return number;
	}
	
	public void setValue(int v) {
		if(v>0 && v<=6) {
			number = v;
			valueLabel.setText(String.valueOf(number));
			diceImage.setValue(number);
		}
	}
	
	public boolean getLocked() {
		return locked;
	}
	
	public void setLocked() {
		locked = !locked;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void rollDice() {
		int v = random.nextInt(6)+1;
		if (locked != true) {
			setValue(v);
		}
		lockBox.setEnabled(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		setLocked();
		
	}
}
