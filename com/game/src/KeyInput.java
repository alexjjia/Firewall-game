package com.game.src;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class KeyInput extends KeyAdapter {
	
	Engine game;
	
	public KeyInput (Engine game)
	{
		this.game = game;
	}
	
	public void keyPressed(KeyEvent e)
	{
		game.keyPressed(e);
	}
	public void keyReleased(KeyEvent e)
	{
		game.keyReleased(e);
	}
}
