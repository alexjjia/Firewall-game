package com.game.src;

import java.awt.Graphics;

public class Bullet {
	private double x;
	private double y;
	
	private Textures text;
	
	public Bullet(double x, double y, Textures text)
	{
		this.setX(x);
		this.setY(y);
		this.text = text;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}
	
	public void tick()
	{
		y-=7;
	}
	
	public void render(Graphics g)
	{
		g.drawImage(text.bullet, (int)x, (int)y, null);
	}
}
