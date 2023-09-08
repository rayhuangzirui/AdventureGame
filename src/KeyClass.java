import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

//Create a key controller class to recognize user's keyboard input
public class KeyClass extends KeyAdapter {
	
	Game game ;
	
	//KeyClass constructor
	public KeyClass(Game game) {
		this.game = game;
	}
	//Refer back to the keyPressed class in Game class
	public void keyPressed(KeyEvent e) {
		game.keyPressed(e);
	}
	//Refer back to the keyReleased method in Game class
	public void keyReleased(KeyEvent e) {
		game.keyReleased(e);
	}
}
