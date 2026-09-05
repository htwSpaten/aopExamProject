package aopExamProject.Spielemechanik;

import java.util.ArrayList;

import aopExamProject.scoring.ScoreboardPanel;

public class GameMechanics 
{
	private int currentIndex = 0;
	private int roundCounter = 0;
	private ArrayList<Player> knifflers;
	private ScoreboardPanel playersScoreboard;
	
	public GameMechanics() {
		this.knifflers = new ArrayList<Player>();
	}
	
	public void gameSetup() 
	{
		knifflers.clear();
		roundCounter = 1;
		
	}
	
	public void play(ScoreboardPanel playersScoreboard) 
	{
			int[] dice= {1,1,1,1,1};
			playersScoreboard.update(dice);
			this.playersScoreboard=playersScoreboard;
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
		// set the old current player to false
		getCurrentPlayer().toggleIsCurrent();
		// change Player
		currentIndex = (currentIndex + 1) % knifflers.size();
		// set the new current player to true
		getCurrentPlayer().toggleIsCurrent();
		playersScoreboard.changePlayer();
		
		if(currentIndex == 0) 
		{
			countRounds();
		}
		System.out.println(currentIndex);
		
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
