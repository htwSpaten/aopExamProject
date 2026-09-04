package aopExamProject.Spielemechanik;
//import scoring;

public class Player {

	private String name;
	private int id;

	
	public Player(String name, int id) 
	{
		this.setName(name);
		this.id = id;
		//this.score = new Scoreboard();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
