import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.List;
import java.awt.Point;

/**
 * Added Methods to Actor class
 * 
 * @author NecroTheif 
 * @version 2014.14.11
 */
public class Actors extends Actor{
    
    /**
     * Return all the objects that intersect this object. This takes the graphical extent of objects into consideration. 
     * 
     * @param cls   Class of objects to look for (passing 'null' will find all objects).
     * @return      all the objects that intersect this object
     */
    @SuppressWarnings({ "rawtypes" })
	@Override
    public List getIntersectingObjects(Class cls){
        
        /* Call the superclass' protected method with given values */
            return super.getIntersectingObjects(cls);
        
    }// End method getIntersectingObjects
    
    
    /**
     * Return all objects within range 'radius' around this object. An object is within 
     * range if the distance between its centre and this object's centre is less than or 
     * equal to 'radius'.
     * 
     * @param radius   Radius of the circle (in cells)
     * @param cls      Class of objects to look for (passing 'null' will find all objects)
     * @return         all objects within range 'radius' around this object
     */
    @SuppressWarnings({ "rawtypes" })
	@Override
    public List getObjectsInRange(int radius, Class cls){
        
        /* Call the superclass' protected method with given values */
            return super.getObjectsInRange(radius, cls);
        
    }// End method getObjectsInRange
        
    
    /**
     * Gets the closest actor of a given class to this actor
     * 
     * @param cls   The class of the actors to look for
     */
    @SuppressWarnings("rawtypes")
	public Actor getClosestActor(Class cls){
        
        /* Get all objects in this actor's world */
            @SuppressWarnings("unchecked")
			List<Actors> actors = getWorld().getObjects(cls);
            
            
        /* Initialize Point variable to hold this actor's position for easy distance calculations */
            Point postion = new Point(getX(), getY());
            
        /* 
         * Initialize variable to hold the most current Actor that is the closest and
         * set it equal to the first actor only if the first actor is not this one and
         * if it is set it equal to the second actor
         */
            Actor closestActor = actors.get(0)==(Actor)this ? actors.get(1) : actors.get(0);
            
            
        /* 
         * Initialize a variable to hold the currently closest distance from the closest 
         * actor to this one and set it equal to the distance between this and closestActor
         */
            double closestDistance = postion.distance(closestActor.getX(), closestActor.getY());
        
            
        /* Check all of the actors positions */
            for(int i=0;i<actors.size();i++){
                
                /* Check to make sure the current actor is not this actor */
                    if(actors.get(i)!=(Actor)this){
                        
                       /* Get the distance from current actor to this actor */
                           double currentDistance = 
                                    postion.distance(
                                        actors.get(i).getX(), actors.get(i).getY());
                                        
                                        
                       /* Check to see if the current distance is smaller than the closest */
                           if(currentDistance<closestDistance){
                               
                               /* Set the new closest variable's to this actor's */
                                   closestDistance = currentDistance;
                                   closestActor = actors.get(i);
                               
                            }// End if(currentDistance<closestDistance)
                       
                    }// End if(actors.get(i)!=(Actor)this)
                
            }// End for(int i=0;i<actors.size();i++)
        
            
        /* Return the closestActor */
            return closestActor;
            
    }// End method getCLosestObject(Class object)
    
    
    /**
     * Act - do whatever the Actors wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
