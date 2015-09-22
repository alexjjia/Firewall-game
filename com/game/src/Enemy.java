package com.game.src;

import java.awt.Graphics;

public class Enemy {
	private double x;
	private double y;
	
	private Textures text;
	
	public Enemy(double x, double y, Textures text)
	{
		this.x = x;
		this.y = y;
		this.text = text;
	}
	
	public void tick()
	{
		y+=1;
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
}
