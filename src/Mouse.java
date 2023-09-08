
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


//The mouse class to control the interaction of the mouse on the menu pane
public class Mouse implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	
	@Override
	public void mousePressed(MouseEvent e) {
		//Get the x and y position where the mouse is pressed
		int xPos = e.getX();
		int yPos = e.getY();
		
		
		//Check if the mouse is click on any button
		if (xPos >= Game.WIDTH/2-100 && xPos <= Game.WIDTH/2 + 200) {
			
			//Press play button and the game will start
			if (yPos >= 300 && yPos <= 350) {
				Game.state = Game.STATE.GAME;
			}
			
			//Press "instructions" button to show the instructions on the console
			if (yPos >=400 && yPos<=450) {
				System.out.println(
						"How to play: Press Arrow Up, Arrow Right, Arrow Left, Arrow Down for character's movements \n");
				System.out.println(
						"Press space bar to shoot bullets \n");
				System.out.println(
						"You have three lives. When you lose, the screen will freeze");
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
