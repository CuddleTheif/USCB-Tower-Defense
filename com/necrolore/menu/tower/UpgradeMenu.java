package com.necrolore.menu.tower;


import com.necrolore.entity.Tower;

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.ArrayList;
import java.util.List;

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
		
		
		/* Get the upgrades possible by this tower and its spawn and store them in the class variable */
			Entity spawn = (Entity)tower.getAttribute(Attribute.SPAWNS);
			Attribute[] towerUpgrades = tower.getUpgradeAttr();
			Attribute[] spawnUpgrades = spawn.getUpgradeAttr();
			for(Attribute attr : towerUpgrades)upgrades.add(new Pair<Entity, Attribute>(tower, attr));
			for(Attribute spawnAttr : spawnUpgrades){
				
				/* Initialize variable for determining if the current attribute is used by the tower */
					boolean used = false;
				
				for(Attribute towerAttr : towerUpgrades){
						
					/* Check if the current spawn attribute is already a tower attribute */
						if(towerAttr==spawnAttr)used = true;
					
				}// End for(Attribute towerAttr : towerUpgrades)
				
				/* If the spawn attribute is not a tower attribute add it */
					if(!used)upgrades.add(new Pair<Entity, Attribute>(spawn, spawnAttr));
				
			}// End for(Attribute spawnAttr : spawnUpgrades)
			
			
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
					prices[i] = getPrice(upgrades.get(i));
					
				
			}// End for(Attribute attr : upgrades)
			
			
		/* Draw the menu based on the images found */
			createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
			
			
		/* Store the upgrades into an array */
			this.upgrades = (Pair<Entity, Attribute>[]) upgrades.toArray(new Pair[upgrades.size()]);
			
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
								
								
							/* Check to see if it is selling the tower not upgrading */
								if(upgrades[selected].getB()==Attribute.PRICE){
									
									/* Remove the tower, remove the menu, and end this method */
										getWorld().removeObject(upgrades[selected].getA());
										getWorld().removeObject(this);
										return;
									
								}// End if(upgrades[selected].getB()==Attribute.PRICE)
									
								
							/* Upgrade the tower or spawn */
								int curVal = (Integer)upgrades[selected].getA().getAttribute(upgrades[selected].getB());
								upgrades[selected].getA().setAttribute(upgrades[selected].getB(), curVal+upgrades[selected].getB().getIncreaseVal());
								if(upgrades[selected].getA() instanceof Tower && ((Entity)upgrades[selected].getA().getAttribute(Attribute.SPAWNS)).hasAttribute(upgrades[selected].getB()))
									((Entity)upgrades[selected].getA().getAttribute(Attribute.SPAWNS)).setAttribute(upgrades[selected].getB(), curVal+upgrades[selected].getB().getIncreaseVal());
								
								
							/* Reset the price of the upgrade and increase the price of the tower */
								upgrades[0].getA().setAttribute(Attribute.PRICE, (int)((int)upgrades[0].getA().getAttribute(Attribute.PRICE)-prices[selected]*Attribute.PRICE.getPriceMuti()));
								prices[selected] = getPrice(upgrades[selected]);
								
								
							/* Recreate the menu */
								createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
								
						}// End if(((Level)getWorld()).getGold()>=prices[selected])
									
					}// End if(selected>=0)
					
				/* Remove the tower menu */
					getWorld().removeObject(this);
				
			}// End if(Greenfoot.mouseClicked(this))
		
	}// End method act
	
	
	/**
	 * Gets the price of upgrading the give pair of entity and attribute
	 * 
	 * @param pair   The pair of entity and attribute
	 * @return       The price of the upgrading the attribute
	 */
	private int getPrice(Pair<Entity, Attribute> pair){
		
		/* Get the value of the current attribute */
			double curVal;
			if(pair.getA().getAttribute(pair.getB()) instanceof Double)
				curVal = (double)((Double)pair.getA().getAttribute(pair.getB()));
			else if(pair.getA().getAttribute(pair.getB()) instanceof Float)
				curVal = (double)((Float)pair.getA().getAttribute(pair.getB()));
			else
				curVal = (double)((Integer)pair.getA().getAttribute(pair.getB()));
		
		
		/* Check if the attribute is increasing or decreasing */
			if(pair.getB().getIncreaseVal()<0){
				
				/* Calculate and return it's current price for upgrade */
					return (int)(pair.getB().getPriceMuti()/curVal);
				
			}// End if(pair.getB().getIncreaseVal()<0)
			else{
				
				/* Calculate and return it's current price for upgrade */
					return (int)(curVal*pair.getB().getPriceMuti());
				
			}// End else for if(pair.getB().getIncreaseVal()<0)
		
	}// End method getPrice

}// End class UpgradeMenu
