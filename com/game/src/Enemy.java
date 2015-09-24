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
	
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	
	public void offScreen()
	{
		if(y>=640)
		{
			y = 0;
			x =random.nextInt(Engine.WIDTH * Engine.SCALE -32);
		}
	}
}
