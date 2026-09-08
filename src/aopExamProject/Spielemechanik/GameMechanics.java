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
	
	@Override
	public void onScoreSubmit() 
	{
		changePlayer();
		board.changePlayer();
		cup.resetCup();
	}
	public GameMechanics() {
		this.knifflers = new ArrayList<Player>();
	}
	
	public void setPanelAndDice(DiceCup cup, ScoreboardPanel board) 
	{
		this.cup = cup;
		this.board = board;
	}
	
	public void gameSetup() 
	{
		knifflers.clear();
		roundCounter = 1;
		
	}
	
	public void play() 
	{
		currentIndex = 0;
	}
	
	public void addPlayer(String name) 
	{
		int id = knifflers.size();
		Player kniffler  = new Player(name, id);
		knifflers.add(kniffler);
		System.out.println("Spieler hinzugefügt: ID " + kniffler.getId() + " - " + name);
	}
		
	public int countRounds() 
	{
		roundCounter += 1;
		//System.out.println(roundCounter);
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
