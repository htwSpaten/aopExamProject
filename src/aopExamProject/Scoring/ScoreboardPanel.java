package aopExamProject.Scoring;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aopExamProject.Spielemechanik.Player;

public class ScoreboardPanel extends JPanel {
	protected final JFrame scoreUI;
	private List<Scoreboard> boards;
	private List<Player> knifflers;
	private Player currentPlayer;
	private String[] categories;
	private Map<Player,Map<String,JLabel>> cells;
	
	private Player getCurrentPlayer() {
		Player result= new Player("",0);
		for(Player kniff : knifflers) {
			if(kniff.getIsCurrent()) {
				result= kniff;
			} 
		}
		return result;
	}
	
	public ScoreboardPanel(List<Player> kifflers) {
		scoreUI = new JFrame("Würfelbecher");
		scoreUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.knifflers = kifflers;
		this.boards = new ArrayList<>();
		for(Player kniff : knifflers) {
			boards.add(kniff.getScore());
		}
		
		currentPlayer = getCurrentPlayer();
		
		this.categories= boards.get(0).fieldNames;
		
		this.cells= new HashMap<>();
		
		int cols = knifflers.size() +1; // +1 for category-name col
		int rows = categories.length +1; // +1 for header row
		setLayout(new GridLayout(rows, cols));
		
		// head row
		add(new JLabel("")); // top left corner, empty
		for (Player p : knifflers) {
			add(new JLabel(p.getName()));
			cells.put(p,new HashMap<>());
		}
		
		//one row per category
		for(String cat : categories) {
			add(new JLabel(cat));
			
			for( Player p : knifflers) {
				JLabel valueLabel = new JLabel("-");
				cells.get(p).put(cat, valueLabel);
				add(valueLabel);
			}
			
		}
		
		scoreUI.pack();
		scoreUI.setVisible(true);
	}
	
	public void changePlayer() {
		currentPlayer = getCurrentPlayer();
	}
	
	//nach jedem wurf:
	public void update(int[] dice) {
		int[] possible = ScoreCalculation.getAllPossibleScores(dice); 
		for (int i = 0; i < categories.length; i++) {
			String category = categories[i];
			JLabel valueLabel= cells.get(currentPlayer).get(category);
			Integer score = currentPlayer.getScore().getScore()[i];
			
			if (score != null) {
				valueLabel.setText("" + score);
			}
			else {
				valueLabel.setText("(" + possible[i] + ")");
			}
		}
	}
}
