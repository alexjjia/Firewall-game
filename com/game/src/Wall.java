package com.game.src;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Wall {
	private int x;
	private int y;
	private Textures text;
	private int numHits; //to be implemented if walls can take multiple collisions before being removed.
	
	public Wall(int x, int y, Textures text)
	{
		this.setX(x);
		this.setY(y);
		this.text = text;
	}
	
//	public boolean gotHit()
//	{
//		if()/*collision occurred*/
//		{
//			//numHits++;
//			return true;
//		}
//			return false;
//	}
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public int numTimesHit()
	{
		return numHits;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.drawImage(text.wall, x, y, null);
	}
}
