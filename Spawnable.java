import greenfoot.*;

/**
 * The Behavior of an Entity to be able to be spawned
 * 
 * @author NecroTheif
 * @version 2014.15.11
 */
public class Spawnable extends Behavior 
{
    
    /**
     * Initilizes an Spawnable Behavior for the given entity
     * 
     * @param entity   The entity that has this behavior
     */
    public Spawnable(Entity entity){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
        
    }// End one-argument constructor for Spawnable
    
    
    /**
     * Spawns the entity in the given world and at the given location and runs the 
     * spawn behavior for this entity
     * 
     * @param world   The world to spawn this entity into
     * @param xPos    The postion to spawn at on the x-axis
     * @param yPos    The postion to spawn at on the y-axis
     * @return        If the spawn was successful or not
     */
    public boolean spawn(World world, int xPos, int yPos){
        
        /* add this entity to the world at the given location */
            world.addObject(entity, xPos, yPos);
            
            
        /* Run the spawn behavior for this entity */
            
        
        /* Return true because spawn was successful */
            return true;
            
    }// End method spawn
    
}// End class Spawnable
