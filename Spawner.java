/**
 * The Behavior of an Entity to be able to spawn other Entities.
 * 
 * @author NecroTheif
 * @version 2014.15.11
 */
public class Spawner extends Behavior{
    
    /**
     * Initilizes an Spawner Behavior for the given entity
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
     * @return             If the entity was spawned succesfully
     */
    private boolean spawnOne(Entity entityType){
        
        /* Make sure the given Entity can be spawned */
            if(!entityType.hasBehavior(Type.SPAWNABLE)){
                
                /* Spawn falied so return false */
                    return false;
                
            }// End if(!entityType.isAssignableFrom(Entity.class))
            
            
        /* Spawn an entity of the given type into the world at this entity and return the result */
            Spawnable newlySpawn = (Spawnable) entityType.clone().getBehavior(Type.SPAWNABLE);
            return newlySpawn.spawn(entity.getWorld(), entity.getX(), entity.getY());
        
    }// End method spawnOne
    
    
    /**
     * Creates the given number of entities of the given entity at this entity
     * 
     * @param number       The number of Enitities to spawn
     * @param entityType   The type of entity to Spawn
     * @return             The number of entities that were spawned succesfully
     */
    public int spawn(int number, Attribute entityType){
        
        /* Get the type of entity to spawn */
            Entity newEntity = (Entity) entity.getAttribute(entityType);
            
            
        /* Spawn that enity the number of given time */
            for(int i=0;i<number;i++){
                
                /* attempt to spawn one entity */
                if(!spawnOne(newEntity)){
                    
                    /* return the number of spawns up to this point because spawn has failed */
                        return i;
                    
                }// End if(!spawnOne(newEntity))
                
            }// End for(int i=0;i<number;i++)
        
            
        /* 
         * return the number of entites to spawn given since all 
         * entities were spawned succesfully 
         */
            return number;
            
    }// End method spawn
    
}// End class Spawner
