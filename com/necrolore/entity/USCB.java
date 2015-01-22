package com.necrolore.entity;

import com.necrolore.entity.behavior.Animation;
import com.necrolore.entity.behavior.Behavior;
import com.necrolore.entity.behavior.Behavior.Type;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The USCB Building that has Health and a Death Animation
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class USCB extends Entity{
    
    /**
     * Creates a USCB Building With Max Health being the given value and with the given size
     * 
     * @param maxHealth   The max health of the USCB Building
     * @param size        The physical size of the building
     */
    public USCB(int maxHealth, int size){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
            
        /* Set this objects' image */
            setImage(new GreenfootImage("images/USCBSandSharks.png"));
            getImage().scale(size, size);
            
            
        /* Get and Store the start and end death animation images */
            GreenfootImage images[] = new GreenfootImage[27];
            images[0] = new GreenfootImage("images/USCBSandSharks.png");
            images[0].scale(size, size);
            images[26] = new GreenfootImage("images/skull-crossbones.png");
            images[26].scale(size, size);
            
            
        /* Load and Store the explosion animation images for the death animation */
            for(int i=0;i<25;i++){
                
                /* Check if in first or second half of animation */
                    if(i<12){
                        
                        /* Load the building for under the explosion */
                            images[i+1] = new GreenfootImage("images/USCBSandSharks.png");
                        
                    }// End if(1<12)
                    else{
                        
                        /* Load the skull for under the explosion */
                            images[i+1] = new GreenfootImage("images/skull-crossbones.png");

                    }// End else for if(i<12)
                    
                
                /* Get and Overlay the current explosion image */
                    GreenfootImage curImage = new GreenfootImage(
                                                    "images/explosion"+String.valueOf(i)+".png");
                    curImage.scale(size, size);
                    images[i+1].scale(size, size);
                    images[i+1].drawImage(curImage, 0, 0);
                
            }// End for(int i=0;i<25;i++)
            
            
        /* Store the death animation images, death sound, and building health as an attribute */
            attributes.put(Attribute.DEATH_ANIMATION, images);
            attributes.put(Attribute.DEATH_SOUND, "sounds/Explosion.wav");
            attributes.put(Attribute.MAX_HP, maxHealth);
            attributes.put(Attribute.HP, maxHealth);
            attributes.put(Attribute.DIE, false);
            
        /* Initialize and store the Animation Behavior */
            behaviors.put(Behavior.Type.ANIMATION, new Animation(this));
            
    }// End one-argument constructor
    
    
    /**
     * Act - do whatever the USCB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    	/* Make sure the game is not paused */
			if(pause)return;
    	
			
        if((Integer)attributes.get(Attribute.HP)==0){
        	boolean die = ((Animation)behaviors.get(Behavior.Type.ANIMATION)).loopThroughTimes(Attribute.DEATH_ANIMATION, 1, Attribute.DEATH_SOUND);
        	attributes.replace(Attribute.DIE, die);
        }// End if((Integer)attributes.get(Attribute.HP)==0)
    }    
}
