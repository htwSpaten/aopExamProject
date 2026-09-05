package aopExamProject.Scoring;

import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {
	protected final JFrame scoreUI;
	
	private Scoreboard board;
	private JLabel[] text;
	
	public ScoreboardPanel(Scoreboard scoreboard) {
		scoreUI = new JFrame("Würfelbecher");
		scoreUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.board = scoreboard;
		this.text = new JLabel[13];
		//13 Zeilen, zwei spalten erstmal
		scoreUI.setLayout(new GridLayout(13, 2));
		String[] name = {
				"Einser", "Zweier", "Dreier", "Vierer", "FÜnfer", "Sechser", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel", "Chance"
		};
		for (int i = 0; i < 13; i++) {
			scoreUI.add(new JLabel(name[i])); //links
			text[i] = new JLabel("-"); //rechts
			scoreUI.add(text[i]);
		}
		
		scoreUI.pack();
		scoreUI.setVisible(true);
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
