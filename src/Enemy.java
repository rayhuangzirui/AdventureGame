import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Enemy {
	
	public double x,y;
	//Object to load the enemy image
	ImageLoader loader;
	//Random object to randomize speeds
	Random r = new Random();
	
	private Game game;
	private GameList list;
	
	//Generate random speed for each enemy
	private int speedX = r.nextInt(4) + 2;
	private int speedY = r.nextInt(4) + 2;
	
	//Enemy constructor
	public Enemy(int x, int y, ImageLoader loader, GameList list, Game game) {
		this.x = x;
		this.y = y;
		this.loader = loader;
		this.list = list;
		this.game = game;
		
	}

	//Execute this method after every tick
	public void tick() {
		
		//Change directions on the x and y dimensions when the enemy collides with the screen bounds
		if (x <= 0) {
			speedX = -speedX;
		}
		if (y <= 0) {
			speedY = -speedY;
		}
		if (x >= Game.WIDTH-30) {
			speedX = - speedX;
		}
		if (y >= Game.HEIGHT-30) {
			speedY = -speedY;
		}
		
		//Move the enemy
		x += speedX;
		y += speedY;

		//Check the collision of the enemy and the player after every tick
		if (Collision.collision(this, game.player)) {
			//If collision happens, reset player's position and remove lives
			game.lives--;
			game.player.x = 0;
			game.player.y = 0;
			if (game.lives == 0) {
				//After losing all three lives, the controller will say "Game over" and the game loop stops
				System.out.print("GAME OVER");
				game.running=false;				
			}
		}
		
		//Check the collision of the enemy and the bullet after every tick
		for (int i = 0; i< game.bullets.size(); i++) {
			//Choose a specific bullet object to evaluate collision
			Bullet tempBullet = game.bullets.get(i);
			if(Collision.collision(this, tempBullet)) {
				//Remove that bullet and this enemy, adding to total enemy killed and increase the enemy killed in this level by 1 
				list.removeBullet(tempBullet);
				list.removeEnemy(this);
				Game.allEnemyKilled++;
				game.setEnemyKilled(game.getEnemyKilled()+1);
			}
		}
	}
	
	//Return bounds for collision checking
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 30, 30);
	}
	
	//Render this enemy object
	public void render(Graphics g) {
		g.drawImage(loader.enemy, (int)x, (int)y, 30, 30, null);
	}
}
