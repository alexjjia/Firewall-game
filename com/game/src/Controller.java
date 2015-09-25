package com.game.src;

import java.awt.Graphics;
import java.util.LinkedList;
import java.util.Random;

import com.game.src.entities.FoeEntity;
import com.game.src.entities.FriendEntity;

public class Controller {
	private LinkedList<FriendEntity> friendList = new LinkedList<FriendEntity>();
	private LinkedList<FoeEntity> foeList = new LinkedList<FoeEntity>();
	
	private FriendEntity friendly;
	private FoeEntity foe;
	private Random randomX = new Random(640);
	private Textures text;
	private Engine game;
	
	
	public Controller(Textures text, Engine game)
	{
		this.text = text;
		this.game = game;
	}
	public void tick(){
		for(int i = 0; i < friendList.size(); i++)
		{
			friendly = friendList.get(i);
			friendly.tick();
		}
		for(int i = 0; i < foeList.size(); i++)
		{
			foe = foeList.get(i);
			foe.tick();
		}
	}
	public void render(Graphics g)
	{
		for(int i = 0; i < friendList.size(); i++)
		{
			friendly = friendList.get(i);
			friendly.render(g);
		}
		for(int i = 0; i < foeList.size(); i++)
		{
			foe = foeList.get(i);
			foe.render(g);
		}
	}

	public void spawnEnemy(int enemyCount)
	{
		
		for(int i = 0; i < enemyCount; i++)
		{
			addEntity(new Enemy(randomX.nextInt(624), -15, text, this, game));
		}
	}
	public void spawnWall(int wallCount)
	{
		for(int i = 0; i < wallCount; i++)
		{
			addEntity(new Wall(i*32, 600, text, this, game));
		}
	}
	public void addEntity(FriendEntity friend)
	{
		friendList.add(friend);
		
	}
	public void removeEntity(FriendEntity friend)
	{
		friendList.remove(friend);
		
	}
	public void addEntity(FoeEntity foe)
	{
		foeList.add(foe);
		
	}
	public void removeEntity(FoeEntity foe)
	{
		foeList.remove(foe);
		
	}
	public LinkedList<FriendEntity> getFriendlies()
	{
		return friendList;
	}
	public LinkedList<FoeEntity> getFoes()
	{
		return foeList;
	}
}
