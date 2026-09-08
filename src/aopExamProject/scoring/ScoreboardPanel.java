package aopExamProject.Scoring;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import aopExamProject.Spielemechanik.Player;

public class ScoreboardPanel extends JPanel {
	private List<Scoreboard> boards;
	private List<Player> knifflers;
	private Player currentPlayer;
	private String[] categories;
	private Map<Player,Map<String,JPanel>> cells;
	private ScoreSubmitListener submitListener;
	private List<JLabel> headerLabels;
	
	private Player getCurrentPlayer() {
		for(Player kniff : knifflers) {
			if(kniff.getIsCurrent()) {
				return kniff;
			} 
		}
		throw new IllegalStateException("No current player set");
	}
	
	private void highlightCurrentPlayer() {
		this.headerLabels = new ArrayList<>();//
		for (int i = 0; i < knifflers.size(); i++) {
			JLabel header = headerLabels.get(i);
			if (knifflers.get(i) == currentPlayer) {
				header.setFont(header.getFont().deriveFont(Font.BOLD));
				header.setForeground(Color.PINK);
			}
			else {
				header.setFont(header.getFont().deriveFont(Font.PLAIN));
				header.setForeground(null);
			}
		}
	}
	
	public ScoreboardPanel(List<Player> kifflers) {
		setPreferredSize(new Dimension(500, 500));
		//setLayout(new BorderLayout());
		//https://dbs.cs.uni-duesseldorf.de/lehre/docs/java/javabuch/html/k100155.html
		JLabel bigtitle = new JLabel("KNIFFEL");
		bigtitle.setFont(new Font("Comic Sans MS", Font.BOLD + Font.ITALIC, 22));
		add(bigtitle);
		
		this.setBackground(Color.lightGray);
		this.knifflers = kifflers;
		this.boards = new ArrayList<>();
		for(Player kniff : knifflers) {
			boards.add(kniff.getScore());
		}
		
		currentPlayer = getCurrentPlayer();
		
		this.categories= boards.get(0).fieldNames;
		
		this.cells= new HashMap<>();
		
		int cols = knifflers.size() + 2; // +1 for category-name col
		int rows = categories.length + 1; // +1 for header row
		setLayout(new GridLayout(rows, cols));
		
		// head row
		add(new JLabel("")); // top left corner, empty
		
		for (Player p : knifflers) {
			add(new JLabel(p.getName()));
			cells.put(p,new HashMap<>());
		}

		for (int i = 0; i < categories.length; i++) {
			String cat = categories[i];
			add(new JLabel(cat));//name cat
			String infoRow = boards.get(0).infoFieldNames[i];
			JLabel infoLabel = new JLabel(infoRow);
			add(infoLabel);

		
			for( Player p : knifflers) {
				JPanel wrapper = new JPanel(new FlowLayout());
				JLabel valueLabel = new JLabel("-");
				JButton submitButton = new JButton("submit");
				
				cells.get(p).put(cat, wrapper);
				
				wrapper.add(valueLabel);
				wrapper.add(submitButton);
				submitButton.setVisible(false);
				add(wrapper);
			}
		}
	}
	
	public void changePlayer() {
		currentPlayer = getCurrentPlayer();
		highlightCurrentPlayer();
	}
	
	//nach jedem wurf:
	public void updatePossibleScore(int[] dice) {
		int[] possible = ScoreCalculation.getAllPossibleScores(dice);
		
		
		
		
		for (int i = 0; i < categories.length; i++) {
			String category = categories[i];
			Integer score = currentPlayer.getScore().getScore()[i];
			final int catIndex = i; // because the listener needs a final int, since the loop can be already over when the button is clicked
			
			JPanel wrapper = cells.get(currentPlayer).get(category);
			JLabel valueLabel= (JLabel) wrapper.getComponent(0);
			JButton submitButton = (JButton) wrapper.getComponent(1);
			submitButton.setVisible(true);
			
			submitButton.addActionListener(e->{
				currentPlayer.getScore().setScore(catIndex,possible[catIndex]);
				handleSubmit();
			});
			
			if (score != null) {
				valueLabel.setText("" + score);
			}
			else if (possible[i] != 0) {
				valueLabel.setText("(" + possible[i] + ")");
				
			}
		}
	}
	
	public void updateActualScore() {
		for (int i = 0; i < categories.length; i++) {
			String category = categories[i];
			Integer score = currentPlayer.getScore().getScore()[i];
			
			JPanel wrapper = cells.get(currentPlayer).get(category);
			JLabel valueLabel= (JLabel) wrapper.getComponent(0);
			JButton submitButton = (JButton) wrapper.getComponent(1);
			submitButton.setVisible(false);
			
			if (score != null) {
				valueLabel.setText("" + score);
			}
			else {
				valueLabel.setText("-");
			}
		}
	}
	
	public void addScoreSubmitListener(ScoreSubmitListener listener) {
		this.submitListener = listener;
	}
	
	private void handleSubmit() {
		updateActualScore();
		submitListener.onScoreSubmit();
	}

}
