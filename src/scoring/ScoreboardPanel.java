package scoring;

import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScoreboardPanel extends JPanel {//erbt von swing
	private Scoreboard board;//verwaltet punkteanzahl
	private JLabel[] text;//array mit 13 textfelder für zahlen 
	
	public ScoreboardPanel(Scoreboard scoreboard) {
		this.board = scoreboard; //speichert scoreboard objekt
		this.text = new JLabel[13]; //hier aary erstellt 
		//13 Zeilen, zwei spalten erstmal
		setLayout(new GridLayout(13, 2));//elemente im panel angelegt
		String[] name = {
				"Einser", "Zweier", "Dreier", "Vierer", "FÜnfer", "Sechser", "Dreierpasch", "Viererpasch", "Full House", "Kleine Straße", "Große Straße", "Kniffel", "Chance"
		};
		for (int i = 0; i < 13; i++) { //läuft 13 mal durch und erstellt :
			add(new JLabel(name[i])); //links
			text[i] = new JLabel("-"); //rechts
			add(text[i]);//speichert in text array
		}
	}
	public void testCompat() { //war empfehlung checke es aber nicht ganz 
		assert true;
	}
	//nach jedem wurf:
	public void update(int[] dice) { //nach jedem würfelwurf aktualisiert
		int[] possible = ScoreCalculation.getAllPossibleScores(dice); //berechnet
		for (int i = 0; i < 13; i++) {
			if (board.getScore()[i] != null) {//prüft ob schon voll
				text[i].setText("" + board.getScore()[i]);//wenn ja fester wert
			}
			else {
				text[i].setText("(" + possible[i] + ")"); //sonst was möglich wäre
			}
		}
	}
}
