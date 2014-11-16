import java.lang.CloneNotSupportedException;
import java.util.Collections;

/**
 * A Behavior an Entity can have. Something an Entity can do.
 * 
 * @author NecroTheif
 * @version 2014.12.11
 */
public class Behavior implements Cloneable{
    
    /**
     * The Behaviors Possible
     */
    public enum Type{
        
        MOVEMENT, ANIMATION, COMBAT, SPAWNER;
        
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
     * Sets the entity for this behavior to the given one
     * 
     * @param entity   The new entity this behavior applies to
     */
    public void setEntity(Entity entity){
        
        /* Initilize class variable for entity to given entity */
            this.entity = entity;
        
    }// End method setEntity
    
    
    /**
     * Creates and returns a copy of this behavior
     * 
     * @return   A copy of this behavior
     */
    public Behavior clone(){
        
        /* Try to Use clone method to get a copy */
            try{
                
                /* Return a clone with Object's clone method */
                    return (Behavior) super.clone();
                
            }
            catch(CloneNotSupportedException e){
                
                /* return null for the error */
                    return null;
                    
            }
            
    }// End method getClone
    
    
    /**
     * Stops all previous behaviors of this type.
     */
    public void stop(){
        
        /* Set the step variable to -1 to say that no behaviors are happening */
            step = -1;
        
    }// End method stop
    
}// End class Behavior
