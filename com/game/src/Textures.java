package com.game.src;

import java.awt.image.BufferedImage;


public class Textures {
	private Spritesheet ss = null;
	
	public BufferedImage player, bullet, enemy, wall;
	
	public Textures(Engine game)
	{
		ss = new Spritesheet(game.getSpriteSheet());
		getTextures();
	}
	
	private void getTextures()
	{
		player = ss.grabImage(1, 1, 32, 32);
		bullet = ss.grabImage(2, 1, 32, 32);
		enemy = ss.grabImage(3, 1, 32, 32);
		wall = ss.grabImage(4, 1, 32, 32);
	}
}
