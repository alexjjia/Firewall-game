package com.game.src;

import java.util.LinkedList;

import com.game.src.entities.FoeEntity;
import com.game.src.entities.FriendEntity;

public class Physics {
	
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
	}
		