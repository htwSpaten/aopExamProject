package aopExamProject.Scoring;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {
	
	protected final JFrame scoreUI;
	
	private Scoreboard board;
	private Map<EScoreCategory, JLabel> text;
	
	public ScoreboardPanel(Scoreboard scoreboard) {
		
		scoreUI = new JFrame("Würfelbecher");
		scoreUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.board = scoreboard;
		this.text = new EnumMap<>(EScoreCategory.class);
		//13 Zeilen, zwei spalten erstmal
		scoreUI.setLayout(new GridLayout(EScoreCategory.values().length, 2));
		/*
		String[] name = {
				"Einser", "Zweier", "Dreier", "Vierer", "FÜnfer", "Sechser", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel", "Chance"
		};
		-> replacing the separately-typed copy here, that has alreay a typo in FÜnfer
		*/
		for(EScoreCategory category : EScoreCategory.values()){
			scoreUI.add(new JLabel(category.getFieldName())); //links
			JLabel valueLabel = new JLabel("-");
			text.put(category, valueLabel);
			scoreUI.add(valueLabel);
		}
		
		
		scoreUI.pack();
		scoreUI.setVisible(true);
	}

	//nach jedem wurf:
	public void update(int[] dice) {
		int[] possible = ScoreCalculation.getAllPossibleScores(dice); 
		for (EScoreCategory category : EScoreCategory.values()) {
			Integer score = board.getScore(category);
	        text.get(category).setText(score != null ? String.valueOf(score) : "(" + possible[category.ordinal()] + ")");
		}
	}
}
