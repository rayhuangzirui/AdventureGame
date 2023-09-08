import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;


//Menu class to display menu game pane and options
public class Menu {
	
	//Create rectangles to represent play and how to play "buttons"
	public Rectangle playButton = new Rectangle(Game.WIDTH/2-100, 300, 200, 50);
	public Rectangle howButton = new Rectangle(Game.WIDTH/2-100, 400, 200, 50);
	
	//Render these buttons
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
		//Draw Title
		Font title = new Font("arial", Font.BOLD, 60);
		g.setFont(title);
		g.setColor(Color.WHITE);
		g.drawString("ADVENTURE GAME", Game.WIDTH/5, 200);
		
		//Draw buttons
		Font heading = new Font("arial",Font.PLAIN, 20);
		g.setFont(heading);
		g2d.draw(playButton);
		g2d.draw(howButton);

		g.drawString("Play", playButton.x + 80 , playButton.y + 30);
		g.drawString("Instructions", howButton.x + 50, howButton.y + 30);
	}
}
