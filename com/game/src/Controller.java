package com.game.src;

import java.awt.Graphics;
import java.util.LinkedList;

public class Controller {
	private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
	private LinkedList<Enemy> enemyList = new LinkedList<Enemy>();
	private LinkedList<Wall> walls = new LinkedList<Wall>();

	Bullet tempBullet;
	Enemy  tempEnemy;
	Wall   tempWall;

	Engine game;

	public Controller(Engine game, Textures text)
	{
		this.game = game;


		//Used to test spawning of enemies.
		for(int x = 0; x < game.WIDTH * game.SCALE; x+=32)
		{
			addEnemy(new Enemy(x, 0, text));
			addWall(new Wall(x, 600, text));
		}

	}
	public void spawnTest()
	{
		
	}
	public void tick(){
		for(int i = 0; i < bulletList.size(); i++)
		{
			tempBullet = bulletList.get(i);
			if(tempBullet.getY() < 0)
			{
				removeBullet(tempBullet);
			}
			tempBullet.tick();
		}
		for(int i = 0; i < enemyList.size(); i++)
		{
			tempEnemy = enemyList.get(i);

			tempEnemy.tick();
		}
	}
	public void render(Graphics g)
	{
		for(int i = 0; i < bulletList.size(); i++)
		{
			tempBullet = bulletList.get(i);
			tempBullet.render(g);
		}
		for(int i = 0; i < enemyList.size(); i++)
		{
			tempEnemy = enemyList.get(i);
			tempEnemy.render(g);
		}
		for(int i = 0; i < walls.size(); i++)
		{
			tempWall = walls.get(i);
			tempWall.render(g);
		}

		//Bullet to Enemy Collision
		for(int i = 0; i < enemyList.size(); i++)
		{
			tempEnemy = enemyList.get(i);
			for(int j = 0; j < bulletList.size(); j++)
			{
				tempBullet = bulletList.get(j);
				if((tempEnemy.getX()<(tempBullet.getX()+32)) &&((tempEnemy.getX()+32)>tempBullet.getX()))
				{
					if((tempBullet.getY() <=tempEnemy.getY()) && (tempBullet.getY()+32>=tempEnemy.getY()))
					{
						removeBullet(tempBullet);
						removeEnemy(tempEnemy);
					}
				}
			}
		}
		//Enemy to Wall Collision
		for(int i = 0; i < enemyList.size(); i++)
		{
			tempEnemy = enemyList.get(i);
			for(int j = 0; j < walls.size(); j++)
			{
				tempWall = walls.get(j);
				if(tempEnemy.getX()<(tempWall.getX()+32) && ((tempEnemy.getX()+32) > (tempWall.getX())))
				{
					if((tempWall.getY()<=tempEnemy.getY()) && ((tempWall.getY()+32) >= tempEnemy.getY() ))
							{
								removeWall(tempWall);
								removeEnemy(tempEnemy);
							}
				}
			}
		}
	}

	public void addBullet(Bullet bullet)
	{
		bulletList.add(bullet);
	}
	public void removeBullet(Bullet bullet)
	{
		bulletList.remove(bullet);
	}
	public void addEnemy(Enemy enemy)
	{
		enemyList.add(enemy);
	}
	public void removeEnemy(Enemy enemy)
	{
		enemyList.remove(enemy);
	}
	public void addWall(Wall wall)
	{
		walls.add(wall);
	}
	public void removeWall(Wall wall)
	{
		walls.remove(wall);
	}
}
