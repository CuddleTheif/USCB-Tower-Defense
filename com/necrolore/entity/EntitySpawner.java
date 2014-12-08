package com.necrolore.entity;

import greenfoot.GreenfootImage;

import com.necrolore.entity.behavior.Behavior;
import com.necrolore.entity.behavior.Spawner;

/**
 * Spawns entities at it's location
 * 
 * @author NecroTheif
 * @version 2014.15.11
 */
public class EntitySpawner extends Entity
{
    
    private int time; // variable to hold the time in game since this object was created 
                      // (number of act calls)
    private int numEntities; // The number of possible entities this spawner can spawn
    
    /**
     * Initializes an EnemySpawner with the given entities to spawn
     * 
     * @param entities   The entities to spawn (from weakest to strongest)
     */
    public EntitySpawner(Entity... entities){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
            
        /* Set this objects' image */
            setImage(new GreenfootImage("images/Transparent.gif"));
            
        /* Give this entity the Spawner Behavior*/
            behaviors.put(Behavior.Type.SPAWNER, new Spawner(this));
            
            
        /* 
         * Give this entity an attribute holding the spawning entities
         * and one holding this entity's position
         */
            attributes.put(Attribute.SPAWNS, entities);
            
            
        /* Get and store the max number of possible entities */
            numEntities = entities.length;
            
        /* Initialize time to zero */
            time = 0;
            
    }// End no-argument constructor for EnemySpawner
    
    
    /**
     * Act - do whatever the EnemySpawner wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        /* Check if it's spawning time */
            if(time%100==0){
                
                /* Spawn entities based on the time (later = more/harder entities) and max */
                    Spawner spawner = (Spawner) behaviors.get(Behavior.Type.SPAWNER);
                    for(int i=0;i<numEntities;i++){
                        
                        /* Spawn entities of the current level based on time and their level */
                            spawner.spawnfromArray(1, Attribute.SPAWNS, i);
                        
                    }// End for
                    
            }// End if(time%50==0)
        
        /* Add to the time */
            time++;
        
    }// End method act    
    
}// End class EnemySpawner
