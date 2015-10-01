package com.game.src;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import com.game.src.entities.FoeEntity;
import com.game.src.entities.FriendEntity;

public class Engine extends Canvas implements Runnable{

	/****
	 * Use threads to implement bullets.
	 ***/
	private static final long serialVersionUID = 2380565353040870831L;
	public static final int HEIGHT = 320;
	public static final int WIDTH = HEIGHT/4 * 3;
	public static final int SCALE = 2;
	public final String TITLE = "Firewall.exe";
	
	private boolean running = false;
	
	private Thread thread;
	
	//buffered images basically load images one behind another.
	//private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private BufferedImage spriteSheet = null;
	private BufferedImage background = null;
	
	private Player player;
	private Controller controller;
	private Textures texture;
	
	
	private int enemyCount = 1; //tracks current number of enemies. When all are destroyed, enemyCount+1 enemies will spawn..endlessly.
	private int enemiesDestroyed = 0; //tracks # of enemies destroyed. Will be used in determining how many points player has.
	private int score = 0;
	
	private int numOnScreen = 0;
	private int damage = 0; //# of enemies that have gotten past the player and the firewall.
	
	public LinkedList<FriendEntity> friendList;
	public LinkedList<FoeEntity> foeList;
	
	private boolean isShooting = false;
	
	private boolean enoughPoints = false;
	
/////////////////////////*Thread Stuffs*////////////////////////////
	private synchronized void start()
	{
		if(running) //to catch if the boolean turns true again.
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private synchronized void stop()
	{
		if(running)
			return;
		
		running = false;
		try{
		thread.join();
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		System.exit(1);
	}
///////////////////////////*End Thread Stuffs*/////////////////////////////
	
	public void init()
	{
		requestFocus();
		BufferedImageLoader loader = new BufferedImageLoader();
		
		try {
			spriteSheet = loader.LoadImage("/spritesheet.png");
			background = loader.LoadImage("/background.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		addKeyListener(new KeyInput(this));
		
		texture = new Textures(this);
		player = new Player(224,585, texture);
		controller = new Controller(texture, this);
		controller.spawnEnemy(enemyCount);
		controller.spawnWall(WIDTH/16);
		friendList = controller.getFriendlies();
		foeList = controller.getFoes();
		damage = foeList.getFirst().getDamage();
		
	}
	
	
	public void run(){
		/*Game loop*/
		init();
		long lastTime = System.nanoTime();
		final double FPS = 60.0;
		double nsec = 1000000000 / FPS;
		double delta = 0;
		
		int updates = 0;
		int frames = 0;
		long timer = System.currentTimeMillis();
		
		while(running){
		long currentTime = System.nanoTime();
		delta +=(currentTime - lastTime) / nsec;
		lastTime = currentTime;
		if(delta>=1)
		{
			tick();
			updates++;
			delta--;
		}
		render();
		frames++;
		
		if(System.currentTimeMillis() - timer > 1000)
		{
			timer+= 1000;
			System.out.println(updates+ " Ticks, FPS: "+ frames);
			System.out.println("Current Score: "+score);
			System.out.println("Enemies destroyed: "+enemiesDestroyed); //Debugging; used to track # of enemies destroyed.
			System.out.println("Enemy count: "+enemyCount); //Debugging; used to track # of enemies spawned.
			
			//For debugging purposes, tells me how many enemies are on the screen. 
			for(int i = 0; i < foeList.size(); i++)
			{
				if(foeList.get(i).onScreen() == true)
				{
					numOnScreen++;
				}
			}
			System.out.println("Enemies currently on screen: "+ numOnScreen + "");
			System.out.println("Damage is "+damage);
			numOnScreen = 0;
			//System.out.println(player.getX());
			updates = 0;
			frames = 0;
		}
		}
		
		stop();
	}
	
	private void tick(){ //tick method that updates the ticks of player and bullet.
		player.tick();
		controller.tick();
		if(enemiesDestroyed >= enemyCount)
		{
			enemyCount +=1;
			enemiesDestroyed = 0; //resets.
			controller.spawnEnemy(enemyCount);
		}
		for(int i = 0; i < foeList.size(); i++)
		{
//			System.out.println("Damage is: "+damage);
		damage += foeList.get(i).getDamage();
		if(damage>10)
		{
			running = false; //game ends.
			System.out.println("You lose! Your score was: "+score);
		}
		}

		
	}
	
	private void render(){
		BufferStrategy bstrategy = this.getBufferStrategy(); //this refers to the extended class, Canvas
	
		if(bstrategy == null) //to run bs once.
		{
			createBufferStrategy(2); //will create 2 buffers, will make three when implements animation.
			return;
		}
		
		Graphics g = bstrategy.getDrawGraphics();
		///////////////////////////Actually Draw Stuff Here/////////////////////////////////////////
																							////////
																							////////
																							////////
		g.drawImage(background, 0, 0, getWidth(), getHeight(),this);						////////
		player.render(g);																	////////
		controller.render(g);																////////
		////////////////////////////////////////////////////////////////////////////////////////////
		g.dispose(); //gets rid of buffered strategy once done.
		bstrategy.show();
	}
	
	
/////////////////////Keyboard Input////////////////////////////////
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_LEFT)
			{
				player.setVelX(-3);	
			}
			else if(key == KeyEvent.VK_RIGHT)
			{
				player.setVelX(3);	
			}
			else if(key == KeyEvent.VK_SPACE && !isShooting)
			{
				controller.addEntity(new Bullet(player.getX(), player.getY()-16, texture, controller, this));
				isShooting= true; 	
			}
			else if(key == KeyEvent.VK_ENTER)
			{
				if(score >10000 && (score % 100 ==0)) //every 10,000 points, will destroy all enemies on the screen.
				{
					score-=10000; //deduct appropriate points. Will implement a button
					enoughPoints = true;
				}
				else
				{
					System.out.println("Not enough points! You need "+(10000-score)+" more points.");
				}
			}
		}
		public void keyReleased(KeyEvent e)
		{
			int key = e.getKeyCode();
			if(key == KeyEvent.VK_LEFT)
			{
				player.setVelX(0);
			}
			else if(key == KeyEvent.VK_RIGHT)
			{
				player.setVelX(0);	
			}
			else if(key ==KeyEvent.VK_SPACE)
			{
				isShooting = false;
			}
			else if(key ==KeyEvent.VK_ENTER && (enoughPoints == true))
			{
//				enemiesDestroyed = enemyCount; //causes the next wave of enemies to spawn.
				for(int i = 0; i < foeList.size(); i++)
				{
					if(foeList.get(i).onScreen() == true) //purges the screen of all enemies.
					{
						controller.removeEntity(foeList.get(i));
						enemiesDestroyed++;
					}
				}
//				controller.spawnEnemy(enemyCount);
				enoughPoints =false;
			}
		}
	///////////////////////////////////////////////////////////////////////////
		
	////////////////////////Main Method ///////////////////////////////////////
	public static void main(String args[])
	{
		System.setProperty("sun.java2d.opengl", "true");
		Engine game = new Engine();
		
		/*To set stuff up*/
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
	
		JFrame frame = new JFrame(game.TITLE);
		frame.add(game);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		game.start();
	}
	
	public BufferedImage getSpriteSheet()
	{
		return spriteSheet;
	}

	public int getEnemiesDestroyed() {
		return enemiesDestroyed;
	}

	public void setEnemiesDestroyed(int enemiesDestroyed) {
		this.enemiesDestroyed = enemiesDestroyed;
	}
	public int getEnemyCount() {
		return enemyCount;
	}
	public void addPoints(int points)
	{
		score+=points;
	}

	public void setEnemyCount(int enemyCount) {
		this.enemyCount = enemyCount;
	}
}
