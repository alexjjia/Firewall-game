package com.game.src;

import java.awt.Graphics;
import java.awt.Rectangle;

import com.game.src.entities.FriendEntity;

public class Player extends GameObject implements FriendEntity{
	
	private double velX;
	private Textures text;
	
	public Player(double x, double y, Textures text)
	{
		super(x,y);
		this.text = text;
		
	}
	public Rectangle getBounds()
	{
		return new Rectangle((int)x,(int)y,32,32);
	}
	public int getX()
	{
		return (int)x;
	}
	public int getY()
	{
		return (int)y;
	}
	public void setX(double x)
	{
		this.x=x;
	}
	public void setY(double y)
	{
		this.y=y;
	}

	public void tick()
	{
		x+=velX;
		if(x<=0)
		x=0;
		if(x>=445)
		x=445;
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(text.player, (int)x, (int)y, null);
	}

	public double getVelX() {
		return velX;
	}

	public void setVelX(double velX) {
		this.velX = velX;
	}

}
