import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.awt.Point;
 

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
    public Behavior getBehavior(Behavior.Type behaviorType){
        
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
                    
                    
                /* Copy all the attributes to a new Map */
                    HashMap<Attribute, Object> newAttributes = 
                        (HashMap<Attribute, Object>) attributes.clone();
                    
                        
                /* Create clones of each attribute */
                    for(Attribute attribute : newAttributes.keySet()){
                        
                        /* Get the current attribute */
                            Object value = newAttributes.get(attribute);
                            
                            
                        /* Check if the current attribute is anything cloneable */
                            if(Entity.class==value.getClass()){
                                
                                /* clone the Entity by calling it's clone method */
                                    value = ((Entity)value).clone();
                                
                            }// End else if(Entity.class==value.getClass()))
                            
                            
                        /* Put it back in the Map */
                            newAttributes.replace(attribute, value);
                        
                    }// End for(Attribute attribute : newAttributes.keySet())
                    
                    
                /* Replace the entity's behaviors with the newley cloned ones */
                    entity.replaceAllAttributes(newAttributes);
                    
                    
                /* Return the newley cloned entity */
                    return entity;
                
            }
            catch(CloneNotSupportedException e){
                
                /* return null for the error and print the error */
                    System.out.println(e);
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
        
    }// End method replaceAllBehaviors
    
    
    /**
     * Set all this entities attributes to the new given Hashmap (used in cloning)
     * 
     * @param attributes   The new attributes
     */
    public void replaceAllAttributes(HashMap<Attribute, Object> attributes){
        
        /* Make sure the new attributes is the same size as the old */
            if(attributes.size()==this.attributes.size()){
                
                /* Set the old class variable for attributes to the given one */
                    this.attributes = attributes;
                
            }// End ifif(attributes.size()==this.attributes.size())
        
    }// End method replaceAllAttributes
    
    
    /**
     * Gets the closest entity with the given attribute set to true
     * 
     * @param attribute   the attribute to look for and check
     */
    public Entity getClosestEntity(Attribute attribute){
        
        /* Get all entities in this entity's world */
            List<Entity> entities = getWorld().getObjects(Entity.class);
            
            
        /* Intilize a list to hold the entity's that have the attribute set to true */
            ArrayList<Entity> trueEntities = new ArrayList<Entity>();
            
            
        /*
         * Create a list holding all the entities from the list of entities that have that 
         * attribute and have it set to true
         */
            for(int i=0;i<entities.size();i++){
                
                /* Check the current entity for the attribute and it's value */
                    if(entities.get(i).hasAttribute(attribute) && 
                        (Boolean) entities.get(i).getAttribute(attribute)){
                            
                            /* Add this entity to the new list */
                                trueEntities.add(entities.get(i));
                            
                    }// End if(entities.get(i).hasAttribute(attribute) &&...
                
            }// End for(int i=0;i<entities.size();i++)
            
            
        /* Check to make sure any entities were found */
            if(trueEntities.size()==0){
                
                /* return null because no entity's were found */
                    return null;
                
            }// End if(trueEntities.size()==0)
        
        
        /* 
         * Intilize variable to hold the current Entity that is the closest and
         * set if the first entity on the list is not this entity set it to that one
         * otherwise set it to the second entity on the list
         */
            Entity closestEntity = trueEntities.get(0)!=this ? 
                                        trueEntities.get(0) : trueEntities.get(1);
            
            
            
        /* Intilize Point variable to hold this actor's postion for easy distance calculations */
            Point postion = new Point(getX(), getY());
            
            
        /* 
         * Intilize a variable to hold the currently closest distance from the closest 
         * actor to this one and set it equal to the distance between this and closestActor
         */
            double closestDistance = postion.distance(closestEntity.getX(), closestEntity.getY());
        
            
        /* Check all of the entities postions */
            for(int i=0;i<trueEntities.size();i++){
                
                /* Check to make sure the current entity is not this entity */
                    if(trueEntities.get(i)!=this){
                        
                       /* Get the distance from current entity to this entity */
                           double currentDistance = 
                                    postion.distance(
                                        trueEntities.get(i).getX(), trueEntities.get(i).getY());
                                        
                                        
                       /* Check to see if the current distance is smaller than the closest */
                           if(currentDistance<closestDistance){
                               
                               /* Set the new closest variable's to this entity's */
                                   closestDistance = currentDistance;
                                   closestEntity = trueEntities.get(i);
                               
                            }// End if(currentDistance<closestDistance)
                       
                    }// End if(trueEntities.get(i)!=this)
                
            }// End for(int i=0;i<trueEntities.size();i++)
        
            
        /* Return the closest Entity */
            return closestEntity;
            
    }// End method getCLosestObject(Class object)
    
    
    /**
     * Gets all entities within the given radius around this entity and that have the
     * given attribute set to true
     * 
     * @param radius         radius around the entity to look in
     * @param attribute      The attribute to check for
     * @return               The List of entites found
     */
    public List<Entity> getEntitiesInRange(int radius, Attribute attribute){
        
        /* Get all the entities in the given radius */
            List<Entity> entities = getObjectsInRange(radius, Entity.class);
            
            
        /* Intilize variable to hold found entites with the attribute set to true */
            List<Entity> foundEntities = new ArrayList<Entity>();
            
            
        /* Check each entity to see if any have the attribute set to true */
            for(int i=0;i<entities.size();i++){
                
                /* Check the current entity for the attribute and it's value */
                    if(entities.get(i).hasAttribute(attribute) && 
                        (Boolean) entities.get(i).getAttribute(attribute)){
                            
                            /* add the found entity to the list */
                                foundEntities.add(entities.get(i));
                            
                    }// End if(entities.get(i).hasAttribute(attribute) &&...
                
            }// End for(int i=0;i<entities.size();i++)
            
            
        /* Return the found entities */
            return foundEntities;
        
    }// End method getObjectsInRange
    
    
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
