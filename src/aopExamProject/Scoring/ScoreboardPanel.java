package aopExamProject.Scoring;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {
	private Scoreboard board;
	private JLabel[] text;
	
	public ScoreboardPanel(Scoreboard scoreboard) {
		this.board = scoreboard;
		this.text = new JLabel[13];
		//13 Zeilen, zwei spalten erstmal
		setLayout(new GridLayout(13, 2));
		String[] name = {
				"Einser", "Zweier", "Dreier", "Vierer", "FÜnfer", "Sechser", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel", "Chance"
		};
		for (int i = 0; i < 13; i++) {
			add(new JLabel(name[i])); //links
			text[i] = new JLabel("-"); //rechts
			add(text[i]);
		}
	}
	public void testCompat() {
		assert true;
	}
	//nach jedem wurf:
	public void update(int[] dice) {
		int[] possible = ScoreCalculation.getAllPossibleScores(dice); 
		for (int i = 0; i < 13; i++) {
			if (board.getScore()[i] != null) {
				text[i].setText("" + board.getScore()[i]);
			}
			else {
				text[i].setText("(" + possible[i] + ")");
			}
		}
	}
}
