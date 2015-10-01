package com.game.src;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import com.game.src.entities.FoeEntity;

public class Enemy extends GameObject implements FoeEntity{
	private Random random = new Random();
	
	private int speed = random.nextInt(2)+1; //Directly controls the speed of each enemy spawned.
	private Textures text;
	private Engine game;
	private Controller controller;
	private int damage = 0;
	
	
	public Enemy(double x, double y, Textures text, Controller controller, Engine game)
	{
		super(x,y);
		this.text = text;
		this.controller = controller;
		this.game = game;
		
	}
	
	public void tick()
	{
		y+=speed;
		offScreen();
		
		//Enemy collision with Bullets.
		if(Physics.Collision(this, game.friendList))
		{
			controller.removeEntity(this);
			controller.removeEntity(Physics.getCollided(this, game.friendList));
			game.setEnemiesDestroyed(game.getEnemiesDestroyed()+1);
			game.addPoints(100);
		}
	}
	public void render(Graphics g)
	{
		g.drawImage(text.enemy, (int)x, (int)y, null);
	}
	public int getX()
	{
		return (int)x;
	}
	public int getY()
	{
		return (int)y;
	}
	
	//collision.
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	//When the enemy dips below the bottom of the screen, it will be moved to the top again, until it is destroyed.
	public void offScreen()
	{
		if(y>=640)
		{
			damage++;
			y = 0;
			x =random.nextInt(Engine.WIDTH * Engine.SCALE -32);
		}
	}
	//determines if the enemy is within the bounds of the game window (visible).
	public boolean onScreen()
	{
		if((this.y<640) && (this.y>0))
		{
			return true;
		}
		return false;
	}
	public int getDamage() //getter class so I can know how many enemies have gotten past me.
	{
		return damage;
	}
}
