package com.necrolore.entity.behavior;
import com.necrolore.entity.Attribute;
import com.necrolore.entity.Entity;




/**
 * The Behavior of an Entity to be able to spawn other Entities.
 * 
 * @author NecroTheif
 * @version 2014.15.11
 */
public class Spawner extends Behavior{
    
    /**
     * Initializes an Spawner Behavior for the given entity
     * 
     * @param entity   The entity that has this behavior
     */
    public Spawner(Entity entity){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
        
    }// End one-argument constructor for Spawner
    
    
    /**
     * Creates an entity of the given type at this entity
     * 
     * @param entityType   The type of entity to spawn
     * @return             If the entity was spawned successfully
     */
    private boolean spawnOne(Entity entityType){

        /* Create a copy of the given entity and place it in the world at this entity */
            Entity newEntity = entityType.clone();
            entity.getWorld().addObject(newEntity, entity.getX(), entity.getY());
            
            
        /* Return true because spawn was successful */
            return true;
            
            
    }// End method spawnOne
    
    
    /**
     * Creates the given number of entities of the given entity at this entity
     * 
     * @param number       The number of Entities to spawn
     * @param entityType   The type of entity to Spawn
     * @return             The number of entities that were spawned successfully
     */
    public int spawn(int number, Entity entityType){
            
        /* Spawn that entity the number of given time */
            for(int i=0;i<number;i++){
                
                /* attempt to spawn one entity */
                if(!spawnOne(entityType)){
                    
                    /* return the number of spawns up to this point because spawn has failed */
                        return i;
                    
                }// End if(!spawnOne(newEntity))
                
            }// End for(int i=0;i<number;i++)
        
        
        /* 
         * return the number of entities to spawn given since all 
         * entities were spawned successfully 
         */
            return number;
            
    }// End method spawn
    
    
    /**
     * Creates the given number of entities of the given entity at this entity
     * 
     * @param number       The number of Entities to spawn
     * @param entityType   The type of entity to Spawn
     * @return             The number of entities that were spawned successfully
     */
    public int spawn(int number, Attribute entityType){
        
        /* Get the type of entity to spawn */
            Entity newEntity = (Entity) entity.getAttribute(entityType);
            
        /* Call the method spawn using the given values */
            return spawn(number, newEntity);
            
    }// End method spawn
    
    
    /**
     * Creates the given number of entities of the given entity at this entity
     * 
     * @param number        The number of Entities to spawn
     * @param entityArray   An array holding the type of entity to Spawn
     * @param entityIndex   The index of the type of entity to Spawn
     * @return              The number of entities that were spawned successfully
     */
    public int spawnfromArray(int number, Attribute entityArray, int entityIndex){
        
        /* Get the type of entity to spawn */
            Entity entityType = ((Entity[])entity.getAttribute(entityArray))[entityIndex];
            
        /* Call the method spawn using the given values */
            return spawn(number, entityType);
            
    }// End method spawn
    
}// End class Spawner
