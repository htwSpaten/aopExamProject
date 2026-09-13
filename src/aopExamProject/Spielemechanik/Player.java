package aopExamProject.Spielemechanik;

import aopExamProject.scoring.Scoreboard;

public class Player {

	private String name;
	private Scoreboard scoreboard;
	private boolean isCurrent;
	
	public Player(String name) 
	{
		this.name = name;
		this.scoreboard = new Scoreboard();
		this.isCurrent = false;
	}
	
	public Scoreboard getScore() {
		return scoreboard;
	}

	public String getName() {
		return name;
	}
	
	public boolean getIsCurrent() {
		return isCurrent;
	}
	
	public void toggleIsCurrent() {
		isCurrent = !isCurrent;
	}
	
	
}
