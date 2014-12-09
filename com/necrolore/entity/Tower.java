package com.necrolore.entity;

import greenfoot.GreenfootImage;

import com.necrolore.entity.behavior.Spawner;
import com.necrolore.entity.behavior.Behavior;
import com.necrolore.greenfoot.Level;

/**
 * A Tower that shots out shots when enemy's are in it's range
 * 
 * @author NecroTheif
 * @version 2014.16.11
 */
public abstract class Tower extends Entity
{
	public static final int WIDTH=Level.WORLD_WIDTH/20;
	public static final int HEIGHT=Level.WORLD_HEIGHT/10;
    
    /**
     * Creates a Tower with the given range, cooldown, and spawns
     * 
     * @param range      The range of the tower
     * @param cooldown   The cooldown of the tower (how long it must wait between shots)
     * @param spawns     What the tower spawns or attacks with
     */
    public Tower(int range, int cooldown, Entity spawns){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
        
        /* Set this objects' image */
            setImage(new GreenfootImage("images/Tower.png"));
            getImage().scale(WIDTH, HEIGHT);
            
            
        /* Store the given range and cooldown as attributes */
            attributes.put(Attribute.RANGE, range);
            attributes.put(Attribute.MAX_COOLDOWN, cooldown);
            attributes.put(Attribute.CUR_COOLDOWN, 0);
            
            
        /* Store a shot as it's entity Spawn attribute */
            attributes.put(Attribute.SPAWNS, spawns);
            
            
        /* Give spawning behavior to spawn shots */
            behaviors.put(Behavior.Type.SPAWNER, new Spawner(this));
        
    }// End no-argument constructor Tower
    
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public abstract void act();
    
}// End class Tower
