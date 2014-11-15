/**
 * A Behavior an Entity can have. Something an Entity can do.
 * 
 * @author NecroTheif
 * @version 2014.12.11
 */
public class Behavior{
    
    /**
     * The Behaviors Possible
     */
    public enum Type{
        
        MOVEMENT, ANIMATION, COMBAT;
        
    }// End enum Behaviors
    
    
    protected Entity entity; // The entity this behavior applies to
    protected int step; // Step the entity is on when following a type of behavior
    
    
    /**
     * Creates a blank Behavior tied to the given entity
     * 
     * @param entity   The entity this behavior applies to
     */
    public Behavior(Entity entity){
        
        /* Initilize class variable for entity to given entity */
            this.entity = entity;
            
            
        /* Initilize the step variable to doing nothing */
            step = -1;
        
    }//End one-argument constructor for Behavior
    
    
    /**
     * Stops all previous behaviors of this type.
     */
    public void stop(){
        
        /* Set the step variable to -1 to say that no behaviors are happening */
            step = -1;
        
    }// End method stop
    
}// End class Behavior
