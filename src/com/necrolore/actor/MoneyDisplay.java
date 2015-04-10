package com.necrolore.actor;




import android.graphics.Typeface;

import com.necrolore.Level;
import com.necrolore.actor.entity.tower.Tower;
import com.necrolore.menu.Menu;

import greenfoot.GreenfootImage;
import greenfoot.awt.Color;
import greenfoot.awt.Font;

public class MoneyDisplay extends Actors {

	
	/**
	 * Creates a MoneyDisplay
	 */
	public MoneyDisplay(){
		
		/* Set the size of the money display depending on the level size */
			setImage(new GreenfootImage(Menu.WIDTH/10, Menu.HEIGHT/20));
		
	}// End zero-argument constructor
	
	
	public void act() {
		
		/* Draw a black rectangle for the background */
			getImage().clear();
			getImage().setColor(Color.BLACK);
			getImage().fillRect(0, 0, getImage().getWidth(), getImage().getHeight());
		
			
		/* Draw a coin on the money display */
			GreenfootImage coin = new GreenfootImage("coin.png");
			coin.scale(Tower.WIDTH/2, Tower.WIDTH/2);
			getImage().drawImage(coin, 0, 0);
		
			
		/* Update the money value to the current gold count */
			String gold = String.valueOf(((Level)getWorld()).getGold());
			getImage().setColor(Color.WHITE);
			getImage().setFont(new Font(Typeface.DEFAULT, Menu.SCALE/30));
			getImage().drawString(gold, Tower.WIDTH/2, getImage().getHeight()*2/3);
		
	}// End method act

}// End class MoneyDisplay
