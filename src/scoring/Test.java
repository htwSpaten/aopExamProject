package scoring;
import javax.swing.JFrame;

public class Test {
	public static void main(String[] a) {
		JFrame f = new JFrame("Test");
		Scoreboard b = new Scoreboard();
		ScoreboardPanel p = new ScoreboardPanel(b);
		f.add(p); f.setSize(300,400);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setVisible(true);
		p.update(new int[]{1,1,1,4,6});
	 }
	}
