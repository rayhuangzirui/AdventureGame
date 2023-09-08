import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

//The class of bullet that extends the rectangle class
public class Bullet extends Rectangle {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Declare position of the bullet object
	private int x,y;

	
	//Declare bullet constructor
	public Bullet(int x, int y) {
		super(x, y, 15, 15);
		this.x = x;
		this.y = y;
	}
	
	//after every tick, move the bullet by 5 units
	public void tick() {
		x +=5;
	}
	
	//Render the bullets with circle shape by using graphics
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(x, y, 15, 15);
	}
	
	
	//Get bounds of the bullet
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 15, 15);
	}
	
}
