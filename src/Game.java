import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.LinkedList;

import javax.swing.JFrame;

/*
 * C SCI 143 ()
 * Minh Vu Anh Phan 
 * Reagan Vu
 * Zirui Huang
 * 
 * 
 * In this game we will have a character moving around on a 2D surface and shoot enemies with his bullets.
 * The amount of enemies will increase after the player killed all of the enemies in a level
 */


public class Game extends Canvas implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Set width of the game window
	public static final int WIDTH = 900;
	//Set height of the game window
	public static final int HEIGHT = 600;
	
	//Character is not running until tell otherwise
	public boolean running = false;
	//Thread to control game's events
	private Thread thread;
	
	//BufferedImage for the background image
	private BufferedImage background = null;
	
	//Create a player object
	public Player player;
	
	//Create a list object that will hold Bullets and Enemies lists
	private GameList list;
	
	//Create an ImageLoader object to load images
	private ImageLoader loader;
	
	//Create a menu object to 
	private Menu menu;
	private boolean isShooting = false;
	
	//Set player's lives
	public int lives = 3;
	
	//Set enemy counter variables
	private int enemyCount = 5;
	private int enemyKilled = 0;
	public static int allEnemyKilled = 0;
	
	//Set lists for controlling enemies and bullets
	public LinkedList<Enemy> enemies;
	public LinkedList<Bullet> bullets;
	
	//Set state of the game
	public enum STATE {
		MENU,
		GAME
	};
	
	//Default state is at the menu start
	public static STATE state = STATE.MENU;
	
	//Default constructor
	public Game() {
	}
	
	//Run method when the game is operating (game loop)
	@Override
	public void run() {
		while (running) {
			//Refer back to the setup of the game
			init();
			
			//Variables to measure ticks and repaint game after every tick
			long lastTime = System.nanoTime();
			final double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime)/ns;
					lastTime = now;
				  
				if (delta >=1) {
					tick();
					delta--;
				}
				render();

			}
			//Stop the game if the running boolean is false
			stop();
		}
	}
	
	//Game setup (before the game is played and only after you click "play" button)
	private void init() {
		//request focus on the game so you don't have to click on the window to request focus
		requestFocus();
		
		//Generate the background buffered image
		loader = new ImageLoader(this);
		background = loader.background;
		
		//Add listeners to generate user interaction
		addMouseListener(new Mouse());
		addKeyListener(new KeyClass(this));
		
		//Create objects and lists
		player = new Player(0, 180, loader);
		list = new GameList(this, loader);
		menu = new Menu();
		enemies = list.getEnemies();
		bullets = list.getBullets();
		
		//Create enemy to play
		list.createEnemy(enemyCount);
		
	}
	
	//Start method of the game loop (when running boolean turns true)
	private synchronized void start() {
	    if (running)
	    	return;
	    running = true;
	    
	    //Create the thread of the game to organize paths while executing and start this thread
	    thread = new Thread(this);
	    thread.start();
	}
	
	//Stop method of the game loop (When running boolean turns false)
	private synchronized void stop() {
		    
		//If the game stops, turn the boolean variable to false;
		if (!running) {
		    	return;
		    }
		    
		    running = false;
		    try {
		    	//Terminate this thread
		    	thread.join();
		    } catch (InterruptedException e){
		    	e.printStackTrace();
		    }
		    System.exit(1);
	}
	
	//render the graphics in the game
	private void render() {
		
		//Create a buffer strategy to handle buffering images
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}
		
		//Get the graphics object
		Graphics g = bs.getDrawGraphics();
		
		//Draw the background
		g.drawImage(background, 0, 0, WIDTH, HEIGHT, this);
		
		//Draw the game 
		if (state == STATE.GAME) {
			player.render(g);
			list.render(g);
			
			g.setColor(Color.WHITE);
			g.setFont(new Font("arial", Font.PLAIN, 20));
			g.drawString("Lives: " + lives + " | Enemies killed: " + allEnemyKilled, 0, HEIGHT - 30);
		} else if (state == STATE.MENU) {
			//Draw the menu pane
			menu.render(g);
		}
		//Dispose this graphics object to free processing execution
		g.dispose();
		bs.show();
		
	}
	
	//General tick method for the game
	private void tick() {
		if (state == STATE.GAME) {
			player.tick();
			list.tick();
		}
		//If all enemies in a level is killed, create a new level
		if(enemyKilled >= enemyCount) {
			enemyCount +=2;
			enemyKilled = 0;
			list.createEnemy(enemyCount);
		}
	}
	
	
	//Setters and getters for the Enemy counter variables
	public int getEnemyCount() {
		return enemyCount;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}

	public int getEnemyKilled() {
		return enemyKilled;
	}

	public void setEnemyKilled(int enemyKilled) {
		this.enemyKilled = enemyKilled;
	}

	//Handle user's keyboard input
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		if (state == STATE.GAME ) {
			if (key == KeyEvent.VK_RIGHT) {
				player.setVelX(5);
			} else if(key ==KeyEvent.VK_LEFT) {
				player.setVelX(-5);
			} else if(key ==KeyEvent.VK_UP) {	
				player.setVelY(-5);
			} else if(key == KeyEvent.VK_DOWN) {
				player.setVelY(5);
			} else if (key == KeyEvent.VK_SPACE & !isShooting) {
				isShooting = true;
				list.addBullet(new Bullet((int)player.getX(), (int)player.getY()));
			}
		}

	}
	//When the user releases the key, the character stops moving
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_RIGHT) {
			player.setVelX(0);
		} else if(key ==KeyEvent.VK_LEFT) {
			player.setVelX(0);
		} else if(key ==KeyEvent.VK_UP) {	
			player.setVelY(0);
		} else if(key == KeyEvent.VK_DOWN) {
			player.setVelY(0);
		} else if (key == KeyEvent.VK_SPACE) {
			isShooting = false;
		}
	}

	//Main method to execute the game	
	public static void main(String args[]) {
		Game game = new Game();
		
		//Declare game window size
		game.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		//Create a frame to store game content
		JFrame frame = new JFrame("Adventure Quest");
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		//Start the game
		game.start();
	}
}
