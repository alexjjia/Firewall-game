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
	
	public Controller(Textures text)
	{
		this.text = text;
//		for(int x = 0; x < 480; x+=32)
//		{
//
//			addEntity(new Wall(x, 600, text));
//		}
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
			addEntity(new Enemy(randomX.nextInt(640), -10, text));
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
