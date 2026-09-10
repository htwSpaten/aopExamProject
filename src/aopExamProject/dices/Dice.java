package aopExamProject.dices;

import java.awt.Dimension;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class Dice {
	private static final int SIZE = 150;
	private int number;
	private final Random random = new Random();
	
	private final JPanel panel;
	private final JCheckBox lockBox;
	private final DiceImage diceImage;
	private final JSlider slider;

	public Dice() {
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.setPreferredSize(new Dimension(SIZE, SIZE));
		
		lockBox = new JCheckBox("");
		slider = new JSlider(1,6);
		diceImage = new DiceImage(number, lockBox);
		
		resetDice();
		
		slider.addChangeListener(e -> {
			setValue(slider.getValue());
			lockBox.setEnabled(true);
		});
		
		panel.add(diceImage);
		panel.add(slider);
	}

	public int getValue() {
		return number;
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	public void rollDice() {
		int v = random.nextInt(6)+1;
		if (lockBox.isSelected() != true) {
			setValue(v);
		}
		lockBox.setEnabled(true);
	}
	
	public void toggleDebug(boolean debugOn) {		
		slider.setVisible(debugOn);
		if(number==0 && debugOn) {
			setValue(getSliderValue());
		}
	}
	
	public void resetDice() {
		setValue(0);
		lockBox.setSelected(false);
		lockBox.setEnabled(false); // before first dice roll the dice shoudn't be lockable
		slider.setVisible(false); // should only be visible in debug mode
	}
	
	private void setValue(int v) {
		if(v>=0 && v<=6) {
			number = v;
			diceImage.setValue(number);
		}
	}
	
	private int getSliderValue() {
		return slider.getValue();
	}
}
