package aopExamProject.scoring;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JPanel;

import aopExamProject.Spielemechanik.Player;

public class ScoreboardPanel extends JPanel {
	private List<Scoreboard> boards;
	private JLabel[] text;
	private List<Player> knifflers;
	private String[] categories;
	private Map<Player,Map<String,JLabel>> cells;
	
	public ScoreboardPanel(List<Player> kifflers) {
		this.knifflers = kifflers;
		this.boards = new ArrayList<>();
		for(Player kniff : knifflers) {
			boards.add(kniff.getScore());
		}
		
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
				//cells.forEach(((p,score) ->{score.put(cat, valueLabel);}));
				cells.get(p).put(cat, valueLabel);
				add(valueLabel);
			}
			
		}
		
	}

	//nach jedem wurf:
	public void update(int[] dice) {
		int[] possible = ScoreCalculation.getAllPossibleScores(dice); 
		Player currentPlayer = knifflers.get(0);
		for (int i = 0; i < 13; i++) {
			String category = categories[i];
			JLabel valueLabel= cells.get(currentPlayer).get(category);
			if (boards.get(0).getScore()[i] != null) {
				
				valueLabel.setText("" + boards.get(0).getScore()[i]);
			}
			else {
				valueLabel.setText("(" + possible[i] + ")");
			}
		}
	}
}
