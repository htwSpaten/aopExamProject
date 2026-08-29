package aopExamProject.Spielemechanik;

import java.util.ArrayList;

import aopExamProject.GameUI.*;

public class GameMechanics 
{
	private int currentIndex = 0;
	private int roundCounter = 0;
	ArrayList<Player> knifflers;
	
	public GameMechanics() {
		this.knifflers = new ArrayList<Player>();
	}
	
	public void gameSetup() 
	{
		knifflers.clear();
		roundCounter = 1;
	}
	
	public void play() 
	{
		starteZug(getCurrentPlayer());
		//rufe Spiel UI AUF
	}
	
	public void addPlayer(String name) 
	{
		Player kniffler  = new Player(name);
		knifflers.add(kniffler);
	}
		
	public int countRounds() 
	{
		roundCounter += 1;
		System.out.println(roundCounter);
		return roundCounter;
	}
	
	public void changePlayer() 
	{
		currentIndex = (currentIndex + 1) % knifflers.size();
		if(currentIndex == 0) 
		{
			roundCounter++;
		}
		starteZug(getCurrentPlayer()); // Svenjas Methode
		
	}
	
	public Player getCurrentPlayer() 
	{
		return knifflers.get(currentIndex);
	}
}
