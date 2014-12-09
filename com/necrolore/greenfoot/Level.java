package com.necrolore.greenfoot;

import java.util.ArrayList;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import com.necrolore.entity.*;
import com.necrolore.road.Path;
import com.necrolore.road.PathShape;

/**
 * A Test level of USCB Tower Defense
 * 
 * @author NecroTheif
 * @version 2014.14.14
 */
public class Level extends World{

    private Path path; // The current Path of the Level
    private USCB uscb; // The current USCB Building of the Level
    private EntitySpawner spawners[] = new EntitySpawner[1]; // The spawners of the level
    private ArrayList<Tower> towers = new ArrayList<Tower>(); // The towers on the current level
    private Range range; // The current range of the currently selected tower
    private PauseButton pause; // The pause button
    private final static int WORLD_HEIGHT = 400; // The height of the world
    private final static int WORLD_WIDTH = 600; // The width of the world
    
    
    /**
     * Create A Level with road shape "Z"
     */
    public Level()
    {    
        
        /* 
         * Create a new world with 600x400 cells with a cell size of 1x1 pixels 
         * and green background. 
         */
            super(WORLD_WIDTH, WORLD_HEIGHT, 1, false); 
            setBackground(new GreenfootImage("images/green-grass-texture.jpg"));
            
            
        /* Create pause button, tower menu, and money display */
            pause = new PauseButton(WORLD_WIDTH, WORLD_HEIGHT);
            
            
        /* Create Road, Building, EntitySpawner, tower, and range objects */
            path = new Path(25, PathShape.HORIZONTAL_BOLT, 0, 50, 550, 50);
            path.createPath();
            uscb = new USCB(100, 50);
            spawners[0] = new EntitySpawner(new Bee());
            towers.add(new Tower(75, 50, new Shot(75)));
            range = new Range();
            
            
        /* Add the pause button and money display to the world */
            addObject(pause, WORLD_WIDTH-pause.getImage().getWidth(), WORLD_HEIGHT/25);
            
            
        /* Add the path with the building at the end and the spawner at the start */
            path.addToWorld(this);
            addObject(uscb, 550, 50);
            addObject(spawners[0], 0, 50);
            
        
        /* Place the tower */
            addObject(towers.get(0), 300, 75);
            
            
        /* Set the paint order so the ranges appear below entities */
            setPaintOrder(Entity.class, Range.class);
            
            
    }// End zero-argument constructor for USCB
    
    
    public void act(){
    	
    	/* Check to see if the user has clicked the mouse */
    		if(Greenfoot.mouseClicked(null)){
    			
    			/* Remove the current range from the world */
    				removeObject(range);
    				
    				
				/* Check to see if a tower was clicked */
    				for(Tower tower : towers){
    					
    					/* Check to see if the current tower was clicked */
    						if(Greenfoot.mouseClicked(tower)){
    							
    							/* 
    							 * Make the range the range of this tower and 
    							 * stop checking the others 
    							 */
    							range.setRange((int) tower.getAttribute(Attribute.RANGE));
    							addObject(range, tower.getX(), tower.getY());
    							break;
    							
    						}// End if(Greenfoot.mouseClicked(tower))
    					
    				}// End for(Tower tower : towers)
    			
    		}// End if(Greenfoot.mouseClicked(null))
    	
    }// End method act 
    
    
    /**
     * Gets the current Path of the Level
     * 
     * @return   The current Path of the Level
     */
    public Path getPath(){
        
        /* Return the current Path */
            return path;
        
    }// End method getPath
    
    
    /**
     * Gets the current USCB Building of the Level
     * 
     * @return   The current USCB Building of the Level
     */
    public USCB getUSCB(){
        
        /* Return the current USCB */
            return uscb;
            
    }// End method USCB
    
}// End class USCB