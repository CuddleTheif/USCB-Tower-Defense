package com.necrolore.entity;

import greenfoot.GreenfootImage;

import com.necrolore.entity.behavior.Behavior;
import com.necrolore.entity.behavior.Combat;
import com.necrolore.entity.behavior.Movement;
import com.necrolore.greenfoot.Level;

/**
 * An Entity to test Behaviors
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class Bee extends Entity{
    
    /**
     * Creates a Bee
     */
    public Bee(){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
            
        /* Set this objects' image */
            setImage(new GreenfootImage("images/bee2.png"));
            getImage().scale(25, 25);
            
            
        /* Create and Add the Bee's Behaviors */
            behaviors.put(Behavior.Type.MOVEMENT, new Movement(this));
            behaviors.put(Behavior.Type.COMBAT, new Combat(this));
        
            
        /* Add the Bee's Attributes */
            attributes.put(Attribute.ENEMY, true);
            attributes.put(Attribute.HP, 20);
            attributes.put(Attribute.ATK, 10);
        
    }// End no-argument constructor
    
    /**
     * Act - do whatever the Bee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    	/* Make sure the game is not paused */
    		if(pause)return;

        /* Follow the path */
            if(((Movement)behaviors.get(Behavior.Type.MOVEMENT)).moveAlongPath(((Level)getWorld()).getPath())){
                ((Combat)behaviors.get(Behavior.Type.COMBAT)).attackEntity(((Level)getWorld()).getUSCB(), Combat.Maneuver.NORMAL);
                getWorld().removeObject(this);
            }

        /* If the entity is dead remove it */
            if((Integer)attributes.get(Attribute.HP)==0)getWorld().removeObject(this);
            
    }    
}
