import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;
import java.lang.CloneNotSupportedException;
import java.util.Collections;
 

/**
 * Any actor that has Behaviors and Attributes
 * 
 * @author NecroTheif
 * @version 2014.12.11
 */
public class Entity extends Actors implements Cloneable{
    
    protected HashMap<Behavior.Type, Behavior> behaviors; // The behaviors of the Entity
    protected HashMap<Attribute, Object> attributes; // The attributes of the Entity
    protected boolean pause; // If the entity is paused or not
    
    
    /**
     * Initilizes an Entity with no attributes and no behaviors
     */
    public Entity(){
        
        /* Initilize beahviors and attributes */
            behaviors = new HashMap<Behavior.Type, Behavior>();
            attributes = new HashMap<Attribute, Object>();
        
    }// End no-argument constructor for Entity
    
    
    /**
     * Sets the given attribute to the given value
     * 
     * @param attribute   The attribute to set the value to
     * @param value       The value to set the attribute to
     */
    public void setAttribute(Attribute attribute, Object value){
        
        /* Check to see if the Attribute already exists */
            if(attributes.containsKey(attribute)){
                
                /* Remove the Atrribute */
                    attributes.remove(attribute);
                
            }// End if(attributes.containsKey(attribute))
            
        /* Add the Attribute with its value to the class variable */
            attributes.put(attribute, value);
        
    }// End method setAttribute
    
    /**
     * Gets the wanted attribute's value
     * 
     * @param attribute   The attribute wanted
     * @return            The value of the attribute wanted
     */
    public Object getAttribute(Attribute attribute){
        
        /* Return the attribute's value in the class variable */
            return attributes.get(attribute);
        
    }// End method getAttribute
    
    
    /**
     * Gets the attributes this entity has of the given type
     * 
     * @param type   The type of attributes to get
     * @return       The attributes this entity has of the given types
     */
    public HashMap getAttributesType(Attribute.Type type){
        
        /* Initilize a variable to hold all the attributes found */
            HashMap<Attribute, Object> foundAttributes = new HashMap<Attribute, Object>();
            
            
        /* Check each attribute to see if it is the given type */
            for(Attribute attribute : attributes.keySet()){
                
                /* Check the current attribute's type */
                    if(attribute.getType()==type){
                        
                        /* Add the current attribute to the attributes found */
                            foundAttributes.put(attribute, attributes.get(attribute));
                        
                    }// End if(attribute.getType()==type)
                
            }// End for(Attribute attr : attributes.keySet())
            
            
        /* Return the attributes found to have the given type */
            return foundAttributes;
        
    }// End method getAttributesType
    
    
    /**
     * Determines if this entity has the given attribute or not
     * 
     * @param attribute   The attribute to look for
     * @return            Whether or not this entity has the attribute
     */
    public boolean hasAttribute(Attribute attribute){
        
        /* Return if the Map holding attributes has the given attribute */
            return attributes.containsKey(attribute);
        
    }//End method hasAttribute
    
    
    /**
     * Gets the wanted behavior
     * 
     * @param behaviorType   The behavior wanted's type
     * @return               The behavior wanted
     */
    public Object getBehavior(Behavior.Type behaviorType){
        
        /* Return the behavior from the class variable */
            return behaviors.get(behaviorType);
        
    }// End method getBehavior
    
    
    /**
     * Determines if this entity has the given behavior or not
     * 
     * @param behavior   The behavior to look for
     * @return           Whether or not this entity has the behavior
     */
    public boolean hasBehavior(Behavior.Type behavior){
        
        /* Return if the Map holding behaviors has the given behavior */
            return behaviors.containsKey(behavior);
        
    }//End method hasBehavior
    
    
    /**
     * Creates and returns a copy of this entity
     * 
     * @return   A copy of this entity
     */
    public Entity clone(){
        
        /* Try to Use clone method to get a copy */
            try{
                
                /* Get a clone with Object's clone method */
                    Entity entity = (Entity) super.clone();
                
                    
                /* Copy all the behaviors to a new Map */
                    HashMap<Behavior.Type, Behavior> newBehaviors = 
                        (HashMap<Behavior.Type, Behavior>) behaviors.clone();
                    
                        
                /* Create clones of each behavior */
                    for(Behavior.Type type : newBehaviors.keySet()){
                        
                        /* Clone the current behavior */
                            Behavior behavior = newBehaviors.get(type).clone();
                            
                            
                        /* Replace it's entity with the new one and put it back in the Map */
                            behavior.setEntity(entity);
                            newBehaviors.replace(type, behavior);
                        
                    }// End for(Behavior.Type type : newBehaviors.keySet())
                    
                    
                /* Replace the entity's behaviors with the newley cloned ones */
                    entity.replaceAllBehaviors(newBehaviors);
                    
                    
                /* Return the newley cloned entity */
                    return entity;
                
            }
            catch(CloneNotSupportedException e){
                
                /* return null for the error */
                    return null;
                    
            }
            
    }// End method getClone
    
    
    /**
     * Set all this entities behaviors to the new given Hashmap (used in cloning)
     * 
     * @param behaviors   The new behaviors
     */
    public void replaceAllBehaviors(HashMap<Behavior.Type, Behavior> behaviors){
        
        /* Make sure the new behaviors is the same size as the old */
            if(behaviors.size()==this.behaviors.size()){
                
                /* Set the old class variable for behaviors to the given one */
                    this.behaviors = behaviors;
                
            }// End ifif(behaviors.size()==this.behaviors.size())
        
    }// End method setAllBehaviors
    
    
    /**
     * Pause the Entity
     */
    public void pause(){
        
        /* Turn the pause variable to true for being paused */
            pause = true;
        
    }// End method pause
    
    
    
    /**
     * Unpause the Entity
     */
    public void unpause(){
        
        /* Turn the pause variable to false for not being paused */
            pause = false;
        
    }// End method unpause
    
    
    /**
     * A default Entity doesn't act. So this is left empty
     */
    public void act() 
    {
        
    }// End method act
    
}// End class Entity
