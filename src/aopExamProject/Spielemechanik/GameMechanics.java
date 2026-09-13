package aopExamProject.Spielemechanik;

import java.util.ArrayList;

import aopExamProject.dices.DiceCup;
import aopExamProject.scoring.ScoreSubmitListener;
import aopExamProject.scoring.ScoreboardPanel;

public class GameMechanics implements ScoreSubmitListener
{
	private int currentIndex = 0;
	private int roundCounter = 0;
	private DiceCup cup;
	private ScoreboardPanel board;
	private ArrayList<Player> knifflers;
	private Player kniffler;
	private GameOverListener listener;
	
	public GameMechanics() {
		this.knifflers = new ArrayList<Player>();
	}
	
	@Override
	public void onScoreSubmit() 
	{
		changePlayer();
		if(!isGameOver()) {
			board.changePlayer();
			cup.resetCup();
		}else {
			listener.onGameOver(getWinner());
		}
	}
	
	public void setGameOverListener(GameOverListener listener) {
		this.listener = listener;
	}
	
	public void setPanelAndDice(DiceCup cup, ScoreboardPanel board) 
	{
		this.cup = cup;
		this.board = board;
	}
	
	public Player getWinner() 
	{
		int nextScore = 0;
		int max = 0;
		Player winner = knifflers.get(0);
		for(int i = 0; i < knifflers.size(); i++) 
		{
			// TODO: what if there are 2 winners?
			nextScore = knifflers.get(i).getScore().getTotalScore();
			if(nextScore > max) 
			{
				max = nextScore;
				winner = knifflers.get(i);
			}
		};
		 return winner;
	}
	
	public boolean isGameOver() 
	{
		return roundCounter >= 13; 
	}
	
	public void gameSetup() 
	{
		knifflers.clear();
		roundCounter = 1;
	}
	
	public void addPlayer(String name) 
	{
		kniffler  = new Player(name);
		knifflers.add(kniffler);
	}
		
	public int countRounds() 
	{
		roundCounter += 1;
		return roundCounter;
	}
	
	public void changePlayer() 
	{
		getCurrentPlayer().toggleIsCurrent();
		currentIndex = (currentIndex + 1) % knifflers.size();
		getCurrentPlayer().toggleIsCurrent();
		if(currentIndex == 0) 
		{
			countRounds();
		}
	}
	
	public int getPlayerCount() {
		return knifflers.size();
	}
	
	public Player getCurrentPlayer() 
	{
		return knifflers.get(currentIndex);
	}
	
	public ArrayList<Player> getAllKnifflers(){
		return knifflers;
	}

	

}
