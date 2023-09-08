
//The collision class to check collision
public class Collision {
	
	//Overloaded methods to check collisions 	
	public static boolean collision(Enemy enemy, Player player) {
		if (enemy.getBounds().intersects(player.getBounds())) {
			return true;
		}
		
		return false;
	}
	
	public static boolean collision(Enemy enemy, Bullet bullet) {
		
		if (enemy.getBounds().intersects(bullet.getBounds())) {
			return true;
			
		}
		
		return false;
	}
	
}
