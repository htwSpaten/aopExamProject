package aopExamProject.Spielemechanik;
import aopExamProject.scoring.*;

public class Player {

	private String name;
	private int id;
	private Scoreboard scoreboard;

	
	public Player(String name, int id) 
	{
		this.name = name;
		this.id = id;
		this.scoreboard = new Scoreboard();
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public Scoreboard getScore() {
		return scoreboard;
	}

	
	
	
}
