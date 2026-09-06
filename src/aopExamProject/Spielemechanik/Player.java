package aopExamProject.Spielemechanik;

import aopExamProject.Scoring.Scoreboard;

//import scoring;

public class Player {

	private String name;
	private int id;
	private Scoreboard scoreboard;
	private boolean isCurrent;
	
	public Player(String name, int id) 
	{
		this.name = name;
		this.id = id;
		this.scoreboard = new Scoreboard();
	}

	public int getId() {
		return id;
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
