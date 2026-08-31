package aopExamProject.Spielemechanik;
//import scoring;

public class Player {

	private String name;
	private int id = (-1);
	
	public Player(String name) 
	{
		this.name = name;
		this.setId(id++);
		//this.score = new Scoreboard();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
