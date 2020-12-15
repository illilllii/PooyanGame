package pooyangame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Bomb extends JLabel{
	
	private Bomb bomb = this;
	private static final String TAG = "Bomb : ";
	
	public int x = 0;
	public int y = 0;
	
	private ImageIcon icBomb;
	
	public PooyanApp pooyanApp;
	public Wolf wolf;
	public Pooyan pooyan;
	
	public Bomb(PooyanApp pooyanApp, Wolf wolf, Pooyan pooyan) {
		this.pooyanApp = pooyanApp;
		this.wolf = wolf;
		this.pooyan = pooyan;
		icBomb = new ImageIcon("images/bomb");
		setIcon(icBomb);
		setSize(75, 80);
		setLocation(0,0);
	}
	
}
