package aopExamProject.scoring;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import aopExamProject.Spielemechanik.Player;
import aopExamProject.dices.DiceRollListener;

public class ScoreboardPanel extends JPanel implements DiceRollListener{
	private List<Scoreboard> boards;
	private List<Player> knifflers;
	private Player currentPlayer;
	private Map<Player,Map<ScoreCategory,JPanel>> cells;
	private List<ScoreSubmitListener> submitListeners = new ArrayList<>();
	private List<JLabel> headerLabels;

	public ScoreboardPanel(List<Player> kifflers) {
		 
		//https://dbs.cs.uni-duesseldorf.de/lehre/docs/java/javabuch/html/k100155.html
		
		this.setBackground(Color.lightGray);
		this.knifflers = kifflers;
		this.boards = new ArrayList<>();
		for(Player kniff : knifflers) {
			boards.add(kniff.getScore());
		}
		
		currentPlayer = getCurrentPlayer();
		this.cells= new HashMap<>();
		
		ScoreCategory[] categories = ScoreCategory.values();
		int cols = knifflers.size() + 2; // +1 for category-name col
		int rows = categories.length + 1; // +1 for header row
		setLayout(new GridLayout(rows, cols, 0, 10));
		
		// head row
		JLabel bigtitle = new JLabel("KNIFFEL");
		bigtitle.setFont(new Font("Comic Sans MS", Font.BOLD + Font.ITALIC, 22));
		add(bigtitle);
		add(new JLabel("")); // top left corner, empty
		
		for (Player p : knifflers) {
			JLabel nameTag = new JLabel(p.getName());
			nameTag.setHorizontalAlignment(SwingConstants.CENTER);
			add(nameTag);
			cells.put(p,new HashMap<>());
		}

		for (ScoreCategory category : ScoreCategory.values()) {
			String cat = category.getfieldName();
			add(new JLabel(cat));//name cat
			String infoRow = category.getinfoFieldName();
			JLabel infoLabel = new JLabel(infoRow);
			add(infoLabel);

		
			for( Player p : knifflers) {
				JPanel wrapper = new JPanel(new GridLayout(2,1));
				JLabel valueLabel = new JLabel("-");
				valueLabel.setHorizontalAlignment(SwingConstants.CENTER);
				JButton submitButton = new JButton("submit");
				
				
				cells.get(p).put(category, wrapper);
				
				wrapper.add(valueLabel);
				wrapper.add(submitButton);
				submitButton.setVisible(false);
				add(wrapper);
			}
		}
	}
	
	public void changePlayer() {
		currentPlayer = getCurrentPlayer();
		//highlightCurrentPlayer();
	}
	
	@Override
	public void onDiceRolled(int[] dices) 
	{
		updatePossibleScore(dices);
	}
	
	public void addScoreSubmitListener(ScoreSubmitListener listener) {
		this.submitListeners.add(listener);
	}
	
	private Player getCurrentPlayer() {
		for(Player kniff : knifflers) {
			if(kniff.getIsCurrent()) {
				return kniff;
			} 
		}
		throw new IllegalStateException("No current player set");
	}
	
	private void updatePossibleScore(int[] dice) {
		Map<ScoreCategory, Integer> possible = ScoreCalculation.getAllPossibleScores(dice);
		
		for (ScoreCategory category : ScoreCategory.values()) {
			Integer score = currentPlayer.getScore().getScorePoints(category);
			final ScoreCategory catFinal = category; // because the listener needs a final int, since the loopcan be already over when the button is clicked
			Integer possibleScore = possible.get(catFinal);
			
			JPanel wrapper = cells.get(currentPlayer).get(category);
			JLabel valueLabel= (JLabel) wrapper.getComponent(0);
			JButton submitButton = (JButton) wrapper.getComponent(1);
			
			
			if(!category.isSettable()) {
				continue;
			}
			
			submitButton.setVisible(true);
							
			removeButtonListeners(submitButton);
			
			submitButton.addActionListener(e->{
				currentPlayer.getScore().setScore(catFinal,possibleScore);
				handleSubmit();
			});
			
			if (score != null) {
				valueLabel.setText("" + score);
			}
			else {
				valueLabel.setText("(" + possibleScore + ")");
				
			}
		}
	}
	
	private void handleSubmit() {
		updateActualScore();
		for(ScoreSubmitListener listener : submitListeners ) 
		{
			listener.onScoreSubmit();
		}
	}
	
	private void updateActualScore() {
		for (ScoreCategory category : ScoreCategory.values()) {
			Integer score = currentPlayer.getScore().getScorePoints(category);
			
			JPanel wrapper = cells.get(currentPlayer).get(category);
			JLabel valueLabel= (JLabel) wrapper.getComponent(0);
			JButton submitButton = (JButton) wrapper.getComponent(1);
			submitButton.setVisible(false);
			removeButtonListeners(submitButton);
			
			if (score != null) {
				valueLabel.setText("" + score);
			}
			else {
				valueLabel.setText("-");
			}
		}
	}
	
	public void addScoreSubmitListener(ScoreSubmitListener listener) {
		this.submitListeners.add(listener);
	}
	
	private void handleSubmit() {
		updateActualScore();
		for(ScoreSubmitListener listener : submitListeners ) 
		{
			listener.onScoreSubmit();
		}
		//changePlayer();
		
		
	}
	
	private void removeButtonListeners(JButton submitButton){
		ActionListener[] buttonListeners = submitButton.getActionListeners();
		for(ActionListener listi : buttonListeners) {
			submitButton.removeActionListener(listi);
		}
	}
	
	@Override
	public void onDiceRolled(int[] dices) 
	{
		updatePossibleScore(dices);
	}

}
	
	