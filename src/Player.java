import java.awt.Graphics;
import java.awt.Rectangle;


//The character class
public class Player {
	
	
	public double x,y;
	
	//Initiate variables for character's speed
	private double velX;
	private double velY;
	
	//Create object to load image for the character
	private ImageLoader loader;
	
	//Player constructor
	public Player(double x, double y, ImageLoader loader) {
		this.x = x;
		this.y = y;
		this.loader = loader;
	}
	
	
	//Getters and setters for x,y positions and velocities
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

	public double getVelY() {
		return velY;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	//Update the character's position after every tick
	public void tick() {
		x+= velX;
		y+= velY;
		
		//Prevent the movement of character out of the screen
		if (x <= 0) 
			x = 0;
		
		if (y >= 570) 
			y = 570;
		
		if (y<0) 
			y = 0;
		
		if (x >= 870)
			x = 870;
	}
	
	//Render the character's image by using the image in the ImageLoader object
	public void render(Graphics g) {
		g.drawImage(loader.player, (int) x, (int) y, 30, 30, null);
	}
	
	//Return the bounds of the character for collision checking
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 30, 30);
	}
}
