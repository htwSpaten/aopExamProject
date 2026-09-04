package aopExamProject.dices;

//TODO: awt correct or replace with swing elements?
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

	public Dice() {
		locked = false;
		number = 0;
		
		//create UI for a dice
		valueLabel = new JLabel(String.valueOf(number));
		lockBox = new JCheckBox("lock");
		lockBox.setSelected(locked);
		lockBox.addItemListener(this);
		
		panel = new JPanel();
		panel.add(new DiceImage());
		panel.add(valueLabel);
		panel.add(lockBox);
	}

	public int getValue() {
		return number;
	}
	
	public void setValue(int v) {
		if(v>0 && v<=6) {
			number = v;
			valueLabel.setText(String.valueOf(number));
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
			// start roll dice animation
			setValue(v);
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		setLocked();
		
	}
	
	// create roll dice animation
}
