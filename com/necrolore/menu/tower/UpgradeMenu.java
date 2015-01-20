package com.necrolore.menu.tower;


import com.necrolore.entity.Tower;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

import java.util.ArrayList;
import java.util.List;

import com.necrolore.entity.Attribute;
import com.necrolore.entity.Entity;
import com.necrolore.greenfoot.Level;

public class UpgradeMenu extends TowerMenu {
	
	private Tower tower; // The tower this upgrade menu is for
	private Attribute upgrades[]; // The upgrades possible for this tower
	private GreenfootImage images[]; // The images of upgrades in this menu

	/**
	 * Creates a upgrade menu for the given tower
	 * 
	 * @param tower   The tower to create the upgrade menu for
	 */
	@SuppressWarnings("unused")
	public UpgradeMenu(Tower tower){
		
		/* Get the upgrades possible by this tower */
			Entity spawn = (Entity)tower.getAttribute(Attribute.SPAWNS);
			List<Attribute> upgrades = new ArrayList<Attribute>();
			upgrades.add(Attribute.MAX_COOLDOWN);
			upgrades.add(Attribute.RANGE);
			if(spawn.hasAttribute(Attribute.ATK))upgrades.add(Attribute.ATK);
			if(spawn.hasAttribute(Attribute.DEF))upgrades.add(Attribute.DEF);
			if(spawn.hasAttribute(Attribute.HP))upgrades.add(Attribute.HP);
			
		/* Get the images of the possible upgrades of this tower */
			images = new GreenfootImage[upgrades.size()];
			for(int i=0;i<upgrades.size();i++){
				
				/* Get the image of the current attribute */
					images[i] = upgrades.get(i).getImage();
				
			}// End for(Attribute attr : upgrades)
			
			
		/* Get the prices of the possible upgrades of this tower */
			prices = new int[upgrades.size()];
			for(int i=0;i<upgrades.size();i++){
				
				/* Get the image of the current attribute */
					if(i<2) prices[i] = (Integer)tower.getAttribute(upgrades.get(i))*2;
					else prices[i] = (Integer)spawn.getAttribute(upgrades.get(i))*2;
				
			}// End for(Attribute attr : upgrades)
			
			
		/* Draw the menu based on the images found */
			createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
			
			
		/* Store the given tower and change the upgrades into an array */
			this.tower = tower;
			this.upgrades = upgrades.toArray(new Attribute[upgrades.size()]);
			
	}// End one-argument constructor for UpgradeMenu 
	
	
	public void act() {
		
		/* Check to see if the menu was clicked */
			if(Greenfoot.mouseClicked(this)){
				
				/* Find the selected type of tower */
					int postion = Greenfoot.getMouseInfo().getY()-getY()+getImage().getHeight()/2;
					int selected = postion/Tower.HEIGHT-1;
				
				/* make sure a tower was selected */
					if(selected>=0){
						
						/* Check to see if there is enought gold to buy it */
						if(((Level)getWorld()).getGold()>=prices[selected]){
							
							/* Check to see if the upgrade is cooldown (because than it decrease in value) */
								int increase = 1;
								if(upgrades[selected]==Attribute.MAX_COOLDOWN)increase = -1;
	
	
							/* Spend the cost of the upgrade */
								((Level)getWorld()).spendGold(prices[selected]);
								
								
							/* Upgrade the tower */
									/* Check to see if the tower, its entity, or both have the attribute */
										Entity spawn = (Entity) tower.getAttribute(Attribute.SPAWNS);	
										if(spawn.hasAttribute(upgrades[selected])){
											
											/* increase the spawn's attribute */
												spawn.setAttribute(upgrades[selected], (Integer)spawn.getAttribute(upgrades[selected])+increase);
												
												
											/* Update the price of the upgrade */
												if(upgrades[selected]==Attribute.MAX_COOLDOWN) prices[selected] =  1000 - (Integer)spawn.getAttribute(upgrades[selected])*2;
												else prices[selected] = (Integer)spawn.getAttribute(upgrades[selected])*2;
										
										}// End if(spawn.hasAttribute(upgrades[selected]))
										if(tower.hasAttribute(upgrades[selected])){
											
											/* increase the tower's attribute */
												tower.setAttribute(upgrades[selected], (Integer)tower.getAttribute(upgrades[selected])+increase);
										
												
											/* Update the price of the upgrade */
												if(upgrades[selected]==Attribute.MAX_COOLDOWN) prices[selected] = 1000-(Integer) tower.getAttribute(upgrades[selected])*2;
												else prices[selected] = (Integer) tower.getAttribute(upgrades[selected])*2;
												
										}// End if(tower.hasAttribute(upgrades[selected]))
								
								/* Recreate the menu */
										createMenu(images, Tower.WIDTH, Tower.HEIGHT, prices);
						}
									
					}// End if(selected>=0)
					
				/* Remove the tower menu */
					getWorld().removeObject(this);
				
			}// End if(Greenfoot.mouseClicked(this))
		
	}// End method act

}// End class UpgradeMenu
