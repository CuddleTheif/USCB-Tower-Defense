package com.necrolore.entity.behavior;

import greenfoot.*;

import java.util.HashMap;

import com.necrolore.entity.Attribute;
import com.necrolore.entity.Entity;

/**
 * The Behavior of an Entity to Animate through images
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class Animation extends Behavior {
    
    private int counter; // Counter used for animations
    private int direction; // Direction of animation
    private HashMap<Attribute, GreenfootImage[]> animations; // The animations' images
    
    
    /**
     * Initializes an Animation Behavior for the given entity
     * 
     * @param entity   The entity that has this behavior
     */
    @SuppressWarnings("unchecked")
	public Animation(Entity entity){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
            
            
        /* Get the entities animations */
            animations = entity.getAttributesType(Attribute.Type.ANIMATION);
            
        
    }// End one-argument constructor for Animation
    
    
    /**
     * Animates a loop through the given images.
     * 
     * @param images   The images to loop through
     * @return         The number of times animation has looped
     */
    private int loopThrough(GreenfootImage images[]){
        
        /* Check if the loop as started yet or not */
            if(step==-1){
                
                /* Set counter to zero for counting number of loops */
                    counter = 0;
                 
                    
                /* Set step to zero to start the loop */
                    step = 0;
                    
            }// End if(step==-1)
            else{
                
                /* Set the image of the entity to the current step */
                    entity.setImage(images[step]);
                    
                    
                /* Increment the step variable */
                    step++;
                    
                    
                /* Check if the step has reached the last image */
                    if(step>=images.length){
                        
                        /* Reset the step variable and Increment the counter of loops */
                            step = 0;
                            counter++;
                        
                    }// End if(step>=images.length)
                
            }// End else for if(step==-1)
        
            
        /* Return the counter which holds the number of loops */
            return counter;
            
    }// End method loopThrough
    
    
    
    /**
     * Animates a loop back and forth with the given images.
     * 
     * @param images   The images to loop with
     * @return         The number of times animation has looped
     */
    private int loopBackAndForth(GreenfootImage images[]){
        
        /* Check if the loop as started yet or not */
            if(step==-1){
                
                /* Set counter to zero for counting number of loops */
                    counter = 0;
                 
                    
                /* Set step to 0 and direction to 1 to start the loop */
                    step = 0;
                    direction = 1;
                    
            }// End if(step==-1)
            else{
                
                /* Set the image of the entity to the current step */
                    entity.setImage(images[step]);
                    
                    
                /* Check if the step has reached the last image and is going 'back' */
                    if(step==images.length-1){
                        
                        /* Set the direction to -1 to go 'back' */
                            direction = -1;
                        
                    }// End if(step>=images.length)
                    
                    
                /* Check if the step has reached the first image and is going 'forth' */
                    if(step==0){
                        
                        /* Set the direction to 1 to go 'forth' and Increment the counter */
                            direction = 1;
                            counter++;
                        
                    }// End if(step==0)
                    
                    
                /* Increment the step variable in the direction animating */
                    step+=direction;
                
            }// End else for if(step==-1)
        
            
        /* Return the counter which holds the number of loops */
            return counter;
            
    }// End method loopBackAndForth
    
    
    /**
     * Animate through the given images a given number of times with.
     * 
     * @param animation   The animation attribute holding the images to animate with
     * @param times       The number of times to animate
     * @return            If the animation is done yet or not
     */
    public boolean loopThroughTimes(Attribute animation, int times){
        
        /* Get the images to animate with for the given attribute */
            GreenfootImage images[] = animations.get(animation);
        
            
        /* Check if the loops have started yet or not */
            if(step==-1){
                
                /* Set counter to zero for counting number of loops */
                    counter = 0;
                    
            }// End if(step==-1)
        
        /* Check to see if you still have loops left */
            if(counter<times){
                
                /* Loop through the images */
                    loopThrough(images);
                
            }// End if(counter<times)
            
            
        /* Return if there are no more loops left yet or not */
            return counter>=times;
        
    }// End method loopThroughTimes
    
    
    /**
     * Animate back and forth with the given images a given number of times.
     * 
     * @param attribute   The attribute holding the images to animate with
     * @param times       The number of times to animate
     * @return            If the animation is done yet or not
     */
    public boolean loopBackAndForthTimes(Attribute attribute , int times){
        
        /* Get the images to animate with for the given attribute */
            GreenfootImage images[] = (GreenfootImage[]) entity.getAttribute(attribute);
            
        
        /* Check if the loops have started yet or not */
            if(step==-1){
                
                /* Set counter to zero for counting number of loops */
                    counter = 0;
                    
            }// End if(step==-1)
        
        /* Check to see if you still have loops left */
            if(counter<times){
                
                /* Loop back and forth with the images */
                    loopBackAndForth(images);
                
            }// End if(counter<times)
            
            
        /* Return if there are no more loops left yet or not */
            return counter>=times;
        
    }// End method loopBackAndForthTimes
    
    
    /**
     * Animate through the given images a given number of times with the given sound.
     * 
     * @param animation   The animation attribute holding the images to animate with
     * @param times       The number of times to animate
     * @return            If the animation is done yet or not
     */
    public boolean loopThroughTimes(Attribute animation, int times, Attribute sound){
        
        /* Get the images to animate with for the given attribute */
            GreenfootImage images[] = animations.get(animation);
        
            
        /* Check if the loops have started yet or not */
            if(step==-1){
                
                /* Play the given sound */
                    String soundFile = (String) entity.getAttribute(sound);
                    Greenfoot.playSound(soundFile);
                
                /* Set counter to zero for counting number of loops */
                    counter = 0;
                    
            }// End if(step==-1)
        
        /* Check to see if you still have loops left */
            if(counter<times){
                
                /* Loop through the images */
                    loopThrough(images);
                
            }// End if(counter<times)
            
            
        /* Return if there are no more loops left yet or not */
            return counter>=times;
        
    }// End method loopThroughTimes
    
    
    /**
     * Animate back and forth with the given images a given number of times with the given sound.
     * 
     * @param attribute   The attribute holding the images to animate with
     * @param times       The number of times to animate
     * @param sound       The sound to play with the animation
     * @return            If the animation is done yet or not
     */
    public boolean loopBackAndForthTimes(Attribute attribute , int times, Attribute sound){
        
        /* Get the images to animate with for the given attribute */
            GreenfootImage images[] = (GreenfootImage[]) entity.getAttribute(attribute);
            
        
        /* Check if the loops have started yet or not */
            if(step==-1){
                
                /* Play the given sound */
                    String soundFile = (String) entity.getAttribute(sound);
                    Greenfoot.playSound(soundFile);
                
                /* Set counter to zero for counting number of loops */
                    counter = 0;
                    
            }// End if(step==-1)
        
        /* Check to see if you still have loops left */
            if(counter<times){
                
                /* Loop back and forth with the images */
                    loopBackAndForth(images);
                
            }// End if(counter<times)
            
            
        /* Return if there are no more loops left yet or not */
            return counter>=times;
        
    }// End method loopBackAndForthTimes
    
}// End class Animation
