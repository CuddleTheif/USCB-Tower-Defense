package com.necrolore.greenfoot;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import com.necrolore.entity.Entity;
import com.necrolore.entity.Tower;

public class TowerMenu extends Actors {

	private Tower towers[]; // The towers in this tower menu
	
	/**
	 * Creates a tower menu of a size based on the towers
	 * 
	 * @param towers   Towers to put into the menu
	 * @param prices   The price of each tower given
	 */
	public TowerMenu(Tower[] towers, String[] prices){
		
		/* Get the coin image and scale it to the tower size */
			GreenfootImage coin = new GreenfootImage("images/coin.png");
			coin.scale(Tower.WIDTH/2, Tower.HEIGHT/2);
			
		
		/* Create the image size for the tower menu based on the tower sizes */
			setImage(new GreenfootImage(Tower.WIDTH*3, Tower.HEIGHT*(towers.length+1)));
			
			
		/* Draw a black box for the background of the tower menu */
			getImage().setColor(Color.BLACK);
			getImage().fillRect(0, 0, getImage().getWidth(), getImage().getHeight());
			
			
		/* Get the font and it's size */
			Font font = new Font(Font.MONOSPACED, Font.PLAIN, 12);
			int textHeight = getImage().getAwtImage().getGraphics().getFontMetrics(font).getHeight();
			int textWidth = getImage().getAwtImage().getGraphics().getFontMetrics(font).getWidths()[0];
			
		/* Draw the title for the menu */
			getImage().setColor(Color.WHITE);
			getImage().setFont(font);
			getImage().drawString("Shop", getImage().getWidth()/2-textWidth*2, textHeight);
			
			
		/* Draw each tower in the tower menu with it's price*/
			for(int i=1;i<towers.length+1;i++){
				
				getImage().drawImage(towers[i-1].getImage(), 0, Tower.HEIGHT/(towers.length+1)*i);
				getImage().drawString(prices[i-1], Tower.WIDTH, Tower.HEIGHT/(towers.length+1)*i+textHeight*3/2);
				getImage().drawImage(coin, Tower.WIDTH*2, Tower.HEIGHT/(towers.length+1)*i+coin.getHeight()/2);
				
				
			}// End for(int i=0;i<towers.length;i++)
			
			
		/* Store the towers given in a class variable */
			this.towers = towers;
		
	}// End three-argument constructor for TowerMenu
	
	public void act() {
		
		/* Check to see if the menu was clicked */
			if(Greenfoot.mouseClicked(this)){
				
				/* Find the selected type of tower */
					int postion = Greenfoot.getMouseInfo().getY()-getY();
					int selected = postion/(getImage().getHeight()/towers.length);
				
					
				/* Create and place a tower of the selected type at the tower location position */
					getWorld().addObject(towers[selected].clone(), getX(), getY());
					
					
				/* Remove the tower menu */
					getWorld().removeObject(this);
				
			}// End if(Greenfoot.mouseClicked(this))
		
	}// End method act
	
}// End class TowerMenu
