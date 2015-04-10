package com.necrolore.menu;



import android.graphics.Typeface;

import com.necrolore.actor.Actors;
import com.necrolore.util.FontMetrics;

import greenfoot.GreenfootImage;
import greenfoot.awt.Color;
import greenfoot.awt.Font;

public abstract class TowerMenu extends Actors {

	protected int prices[]; // The prices of the towers
	
	/**
	 * Creates a tower menu of a size based on the given sizes
	 * 
	 * @param objects   Objects selectable in this menu
	 * @param prices   	The price of each objects given
	 */
	public void createMenu(GreenfootImage[] objects,int objectsWidth, int objectsHeight, int[] prices){
		
		/* Store the prices given in class variables */
			this.prices = prices;
			
		
		/* Get the coin image and scale it to the tower size */
			GreenfootImage coin = new GreenfootImage("coin.png");
			coin.scale(objectsWidth/2, objectsHeight/2);
			
		
		/* Create the image size for the tower menu based on the tower sizes */
			setImage(new GreenfootImage(objectsWidth*3, objectsHeight*(objects.length+1)));
			
			
		/* Draw a black box for the background of the tower menu */
			getImage().setColor(Color.BLACK);
			getImage().fillRect(0, 0, getImage().getWidth(), getImage().getHeight());
			
			
		/* Get the font and it's size */
			Font font = new Font(Typeface.DEFAULT, Menu.SCALE/25);
			int textHeight = new FontMetrics(font).getTextHeight("Shop");
			int textWidth = new FontMetrics(font).getTextWidth("Shop");
			
		/* Draw the title for the menu */
			getImage().setColor(Color.WHITE);
			getImage().setFont(font);
			getImage().drawString("Shop", getImage().getWidth()/2-textWidth/2, textHeight);
			
			
		/* Draw each tower in the tower menu with it's price*/
			for(int i=1;i<objects.length+1;i++){
				
				objects[i-1].scale(objectsWidth, objectsHeight);
				getImage().drawImage(objects[i-1], 0, objectsHeight*i);
				getImage().drawString(String.valueOf(prices[i-1]), objectsWidth, objectsHeight*i+textHeight*3/2);
				getImage().drawImage(coin, objectsWidth*2, objectsHeight*i+coin.getHeight()/2);
				
			}// End for(int i=0;i<towers.length;i++)
		
	}// End three-argument constructor for TowerMenu
	
	public abstract void act();
	
}// End class TowerMenu
