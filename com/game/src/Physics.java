package com.game.src;

import java.util.LinkedList;

import com.game.src.entities.FoeEntity;
import com.game.src.entities.FriendEntity;

public class Physics {
	
		//Compares collision between a FriendEntity and a list of FoeEntities.
		public static boolean Collision(FriendEntity friend, LinkedList<FoeEntity> foeList)
		{
			for(int i = 0; i < foeList.size(); i++)
			{
				if(friend.getBounds().intersects(foeList.get(i).getBounds()))
				{
					return true;
				}
			}
			return false;
		}
		//Compares collision between a FoeEntity and a list of FriendEntities.
		public static boolean Collision(FoeEntity foe, LinkedList<FriendEntity> friendList)
		{
			for(int i = 0; i < friendList.size(); i++)
			{
				if(foe.getBounds().intersects(friendList.get(i).getBounds()))
				{
					return true;
				}
			}
			return false;
		}

		//returns the FriendEntity that is hit when a collision occurs between a FoeEntity and it.
		public static FriendEntity getCollided(FoeEntity foe, LinkedList<FriendEntity> friendList)
		{
			FriendEntity collided = null;
			for(int i = 0; i < friendList.size(); i++)
			{
				if(foe.getBounds().intersects(friendList.get(i).getBounds()))
				{
					collided = friendList.get(i);
				}
			}
			return collided;
		}
		//returns the FoeEntity that is hit when a collision occurs between a FriendEntity and it.
		public static FoeEntity getCollided(FriendEntity foe, LinkedList<FoeEntity> foeList)
		{
			FoeEntity collided = null;
			for(int i = 0; i < foeList.size(); i++)
			{
				if(foe.getBounds().intersects(foeList.get(i).getBounds()))
				{
					collided = foeList.get(i);
				}
			}
			return collided;
		}
}
		