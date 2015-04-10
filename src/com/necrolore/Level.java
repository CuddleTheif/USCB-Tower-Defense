package com.necrolore;



import java.util.ArrayList;

import com.necrolore.actor.MoneyDisplay;
import com.necrolore.actor.PauseButton;
import com.necrolore.actor.Range;
import com.necrolore.actor.entity.Attribute;
import com.necrolore.actor.entity.Bee;
import com.necrolore.actor.entity.Entity;
import com.necrolore.actor.entity.EntitySpawner;
import com.necrolore.actor.entity.USCB;
import com.necrolore.actor.entity.tower.ComputationalScience;
import com.necrolore.actor.entity.tower.Nursing;
import com.necrolore.actor.entity.tower.Tower;
import com.necrolore.actor.path.Path;
import com.necrolore.actor.path.PathShape;
import com.necrolore.menu.BuyTowerMenu;
import com.necrolore.menu.Menu;
import com.necrolore.menu.TowerMenu;
import com.necrolore.util.Point;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.awt.Color;
import greenfoot.awt.Font;


/**
 * A Test level of USCB Tower Defense
 * 
 * @author NecroTheif
 * @version 2014.14.14
 */
public class Level extends World{

    private Path paths[]; // The current Paths of the Level
    private USCB uscb; // The current USCB Building of the Level
    private EntitySpawner spawners[] = new EntitySpawner[1]; // The spawners of the level
    private ArrayList<Tower> towers = new ArrayList<Tower>(); // The towers on the current level
    private Range range; // The current range of the currently selected tower
    private PauseButton pause; // The pause button
    private BuyTowerMenu buyMenu; // The tower menu for buying towers
    private MoneyDisplay moneyDisplay; // The money display on the world
    private int gold; // The amount of gold the player has on the level
    private boolean gameOver = false; // If the level is over yet because the player lost
    
    
    /**
     * Create A Level with given shapes and points
     */
    public Level(int width, int height, PathShape[] shape, Point[] start, Point[] end)
    {    
        
        /* 
         * Create a new world with 600x400 cells with a cell size of 1x1 pixels 
         * and green background. 
         */
            super(width, height, 1); 
    		GreenfootImage background = new GreenfootImage("Title.jpg");
    		background.scale(width, height);
            setBackground(new GreenfootImage("green-grass-texture.jpg"));
            
            
        /* Create pause button, tower menu, and money display */
            pause = new PauseButton(width, height);
            Nursing nursing = new Nursing(Menu.SCALE/10, 100, 10, 15);
            ComputationalScience compSci = new ComputationalScience(Menu.SCALE/10, 100, 1, 30, 20, 10, 10);
            buyMenu = new BuyTowerMenu(nursing, compSci);
            gold = 60;
            moneyDisplay = new MoneyDisplay();
            
            
        /* Create Road, Building, EntitySpawner, tower, and range objects */
            paths = new Path[shape.length];
            for(int i=0;i<paths.length;i++){
            	
                paths[i] = new Path(Menu.SCALE/25, shape[i], (int)start[i].getX(), (int)start[i].getY(), (int)end[i].getX(), (int)end[i].getY());
                paths[i].createPath();
                paths[i].addToWorld(this);
            	
            }
            uscb = new USCB(200, Menu.SCALE/20);
            spawners[0] = new EntitySpawner(new Bee(20, 50, 20, 5));
            range = new Range();
            
            
        /* Add the pause button and money display to the world */
            addObject(pause, pause.getImage().getWidth(), pause.getImage().getHeight());
            addObject(moneyDisplay, moneyDisplay.getImage().getWidth()/2, height-moneyDisplay.getImage().getHeight());
            
            
        /* Add the path with the building at the end and the spawner at the start */
            addObject(uscb, (int)end[0].getX(), (int)end[0].getY());
            addObject(spawners[0], (int)start[0].getX()-10, (int)start[0].getY()-10);
            
            
        /* Set the paint order so the ranges appear below entities */
            setPaintOrder(Entity.class, TowerMenu.class, Range.class);
            
            
    }// End zero-argument constructor for USCB
    
    
    public void act(){
    	
    	/* Check to see if the user has clicked the mouse */
    		if(Greenfoot.mousePressed(null)){
    			
    			/* Remove the current range from the world */
    				removeObject(range);
    				
    				
				/* Check to see if the tower menu was not clicked */
    				if(!Greenfoot.mousePressed(buyMenu)){
    					
    					/* Remove the towerMenu */
    						removeObject(buyMenu);
    					
    				}// End if(!Greenfoot.mousePressed(towerMenu))
    				
    				
				/* Check to see if this world was clicked */
    				if(Greenfoot.mousePressed(this)){
    					
    					/* Place the towerMenu at the location clicked */
    						addObject(buyMenu, Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY());
    					
    				}// End if(Greenfoot.mousePressed(this))
    				
    				
				/* Check to see if a tower was clicked */
    				for(Tower tower : towers){
    					
    					/* Check to see if the current tower was clicked */
    						if(Greenfoot.mousePressed(tower)){
    							
    							/* 
    							 * Make the range the range of this tower and 
    							 * stop checking the others 
    							 */
    							range.setRange((Integer) tower.getAttribute(Attribute.RANGE));
    							addObject(range, tower.getX(), tower.getY());
    							break;
    							
    						}// End if(Greenfoot.mousePressed(tower))
    					
    				}// End for(Tower tower : towers)
    			
    		}// End if(Greenfoot.mousePressed(null))
    		
    		/* Check if the player has lost yet */
	    		if((Boolean)uscb.getAttribute(Attribute.DIE) && !gameOver){
	    			
	    			getBackground().setColor(Color.WHITE);
	    			getBackground().setFont(new Font("Monospaced", Font.BOLD, 42));
	    			getBackground().drawString("Game Over", getBackground().getWidth()/4, getBackground().getHeight()/32);
	    			gameOver = true;
	    			
	    		}// End if((boolean) uscb.getAttribute(Attribute.DIE))
    		
	    		
    		/* Check to see if it's game over and return to menu on click */
	    		if(gameOver && Greenfoot.mousePressed(null))Greenfoot.setWorld(new Menu());;
	    		
	    		
    }// End method act 
    
    
    /**
     * Adds the given tower to the world
     * 
     * @param tower   The tower to add to the world
     * @param x       The position to place the tower at on the x-axis
     * @param y       The position to place the tower at on the y-axis
     */
    public void addTower(Tower tower, int x, int y){
    	
    	/* Add the tower to the list holding all the towers */
    		towers.add(tower);
    		
    		
		/* Add the tower to the world at the given position */
    		addObject(tower, x, y);
    	
    }// End method addTower
    
    
    /**
     * Removes the given tower from the world
     * 
     * @param tower   The tower to remove from the world
     */
    public void removeTower(Tower tower){
    	
    	/* remove the tower from the list holding all the towers */
    		towers.remove(tower);
    		
    		
		/* remove the tower from the world */
    		removeObject(tower);
    	
    }// End method removeTower
    
    
    /**
     * Gets the current Paths of the Level
     * 
     * @return   The current Patsh of the Level
     */
    public Path[] getPaths(){
        
        /* Return the current Paths */
            return paths;
        
    }// End method getPaths
    
    
    /**
     * Gets the current USCB Building of the Level
     * 
     * @return   The current USCB Building of the Level
     */
    public USCB getUSCB(){
        
        /* Return the current USCB */
            return uscb;
            
    }// End method USCB
    
    
    /**
     * Adds the given amount to the total gold count
     * 
     * @param amount   The amount of gold to add
     */
    public void addGold(int amount){
    	
    	/* Add the given amount of gold */
    		gold += amount;
    	
    }// End method addGold
    
    
    /**
     * Removes the given amount from the total gold count
     * 
     * @param amount   The amount of gold to remove
     */
    public void spendGold(int amount){
    	
    	/* Remove the given amount of gold */
    		gold -= amount;
    	
    }// End method spendGold
    
    
    /**
     * Gets the total gold count
     * 
     * @param return   the total gold count
     */
    public int getGold(){
    	
    	/* Remove the gold count */ 
    		return gold;
    	
    }// End method getGold
    
}// End class USCB