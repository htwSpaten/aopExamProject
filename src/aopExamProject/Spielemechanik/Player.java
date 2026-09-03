package aopExamProject.Spielemechanik;
//import scoring;

public class Player {

	private String name;
	private int id;

	
	public Player(String name, int id) 
	{
		this.name = name;
		this.id = id;
		//this.score = new Scoreboard();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
