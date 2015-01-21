package com.necrolore.menu.tower;


import com.necrolore.entity.Tower;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.necrolore.entity.Attribute;
import com.necrolore.entity.Entity;
import com.necrolore.greenfoot.Level;
import com.necrolore.java.Pair;

public class UpgradeMenu extends TowerMenu {
	
	private Pair<Entity, Attribute> upgrades[]; // The upgrades possible for this tower
	private GreenfootImage images[]; // The images of upgrades in this menu

	/**
	 * Creates a upgrade menu for the given tower
	 * 
	 * @param tower   The tower to create the upgrade menu for
	 */
	@SuppressWarnings("unchecked")
	public UpgradeMenu(Tower tower){
		
		/* Initialize variable for holding upgrades found */
			List<Pair<Entity, Attribute>> upgrades = new ArrayList<Pair<Entity, Attribute>>();
		
		
		/* Get the upgrades possible by this tower and store them in the class variable */
			Attribute[] tempUpgrades = tower.getUpgradeAttr();
			for(Attribute attr : tempUpgrades)upgrades.add(new Pair<Entity, Attribute>(tower, attr));
			
			
		/* Get the upgrades possible by this tower's spawn and store them in the class variable */
			Entity spawn = (Entity)tower.getAttribute(Attribute.SPAWNS);
			tempUpgrades = spawn.getUpgradeAttr();
			for(Attribute attr : tempUpgrades)upgrades.add(new Pair<Entity, Attribute>(spawn, attr));
			
			
		/* Get the images of the possible upgrades of this tower */
			images = new GreenfootImage[upgrades.size()];
			for(int i=0;i<images.length;i++){
				
				/* Get the image of the current attribute */
					images[i] = upgrades.get(i).getB().getImage();
				
			}// End for(Attribute attr : upgrades)
			
			
		/* Get the prices of the possible upgrades of this tower */
			prices = new int[upgrades.size()];
			for(int i=0;i<prices.length;i++){
				
				/* Get the price of the current attribute */
					Pair<Entity, Attribute> curPair = upgrades.get(i);
					if(curPair.getB().getIncreaseVal()<0){
						
						prices[i] = -1*(int)(curPair.getB().getPriceMuti()/(Double)curPair.getA().getAttribute(curPair.getB()));
						
					}// End if(curPair.getB().getPriceMuti()<0)
					else{
						
						prices[i] = (int)((Double)curPair.getA().getAttribute(curPair.getB())*curPair.getB().getPriceMuti());
						
					}// End else for if(curPair.getB().getPriceMuti()<0)
					
				
			}// End for(Attribute attr : upgrades)
			
			
		/* Draw the menu based on the images found */
			createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
			
			
		/* Store the upgrades into an array */
			this.upgrades = (Pair<Entity, Attribute>[]) upgrades.toArray();
			
	}// End one-argument constructor for UpgradeMenu 
	
	
	public void act() {
		
		/* Check to see if the menu was clicked */
			if(Greenfoot.mouseClicked(this)){
				
				/* Find the selected type of tower */
					int postion = Greenfoot.getMouseInfo().getY()-getY()+getImage().getHeight()/2;
					int selected = postion/Tower.HEIGHT-1;
				
				/* make sure a tower was selected */
					if(selected>=0){
						
						/* Check to see if there is enough gold to buy it */
						if(((Level)getWorld()).getGold()>=prices[selected]){
	
	
							/* Spend the cost of the upgrade */
								((Level)getWorld()).spendGold(prices[selected]);
								
								
							/* Upgrade the tower or spawn */
								Double curVal = (Double) upgrades[selected].getA().getAttribute(upgrades[selected].getB());
								upgrades[selected].getA().setAttribute(upgrades[selected].getB(), curVal+upgrades[selected].getB().getIncreaseVal());
										
								
							/* Recreate the menu */
								createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
						}
									
					}// End if(selected>=0)
					
				/* Remove the tower menu */
					getWorld().removeObject(this);
				
			}// End if(Greenfoot.mouseClicked(this))
		
	}// End method act

}// End class UpgradeMenu
