package com.game.src;

import java.awt.Graphics;

public class Player {
	private double x;
	private double y;
	
	private double velX;
	private Textures text;
	
	public Player(double x, double y, Textures text)
	{
		this.x=x;
		this.y=y;
		this.text = text;
		
	}
	
	public double getX()
	{
		return x;
	}
	public double getY()
	{
		return y;
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
