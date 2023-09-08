import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;
//List to control Game objects
public class GameList {
	//Declare lists to hold and control Bullet and Enemy objects 
	private LinkedList<Bullet> bullets = new LinkedList<Bullet>();
	private LinkedList<Enemy> enemies = new LinkedList<Enemy>();
	
	Game game;
	//Declare a specific bullet and enemy
	Bullet tempBullet;
	Enemy tempEnemy;
	ImageLoader loader;
	//Randomize enemy's position when creating enemy
	Random r = new Random();
	
	public GameList(Game game, ImageLoader loader) {
		this.game = game;
		this.loader = loader;
		
	}
	
	//Check the lists after each tick
	public void tick() {
		//For loop for the bullet list
		for (int i = 0; i< bullets.size(); i++) {
			tempBullet = bullets.get(i);
			if (tempBullet.getX() > 1000) {
				//If the bullet is out of the screen, remove it to free storage and render process
				removeBullet(tempBullet);
			}
			//Refer back to the tick method in the bullet class
			tempBullet.tick();
		}
		//For loop for the enemy list
		for (int i = 0; i< enemies.size(); i++) {
			tempEnemy = enemies.get(i);
			//Refer back to the tick method in the enemy class
			tempEnemy.tick();
		}
	}
	
	//Render objects in the class
	public void render(Graphics g) {
		for (int i = 0; i< bullets.size(); i++) {
			tempBullet = bullets.get(i);
			tempBullet.render(g);
		}
		for (int i = 0; i< enemies.size(); i++) {
			tempEnemy = enemies.get(i);
			tempEnemy.render(g);
		}
	}
	
	//Add and remove methods for the objects
	public void addBullet(Bullet b) {
		bullets.add(b);
	}
	
	public void removeBullet(Bullet b) {
		bullets.remove(b);
	}
	
	public void addEnemy(Enemy e) {
		enemies.add(e);
	}
	
	public void removeEnemy(Enemy e) {
		enemies.remove(e);
	}
	
	public LinkedList<Enemy> getEnemies() {
		return enemies;
	}

	public LinkedList<Bullet> getBullets() {
		return bullets;
	}

	//Create a new enemy at a random position
	public void createEnemy(int enemy_count) {
		for (int i = 0; i < enemy_count; i++) 
			addEnemy(new Enemy(r.nextInt(Game.WIDTH-30), r.nextInt(Game.HEIGHT -30), loader, this, game));
	}
}
