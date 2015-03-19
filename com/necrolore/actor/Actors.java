package com.necrolore.actor;



import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;
import java.awt.geom.Line2D;


/**
 * Added Methods to Actor class
 * 
 * @author NecroTheif 
 * @version 2014.14.11
 */
public abstract class Actors extends Actor{
	
    /**
     * Return all the objects that intersect this object. This takes the graphical extent of objects into consideration. 
     * 
     * @param cls   Class of objects to look for (passing 'null' will find all objects).
     * @return      all the objects that intersect this object
     */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public List getIntersectingObjects(Class cls){
        
    	/* Get all the objects of the world of the given class */
			List objects = getWorld().getObjects(cls);
			
			
		/* Initialize variable for holding all objects found to be intersecting this object */
			List foundObjects = new ArrayList<Actors>();
			
			
		/* Find which objects in the world intersect with this one */
			for(Object object : objects){
				
				/* Check if the current actor intersects with this one */
					if(intersects((Actors) object)){
						
						/* Add the current actor to the found list */
							foundObjects.add(object);
						
					}// End if(intersects(actor))
				
			}// End for(Actors actor : actors)
			
			
			
		/* Return the found actors */
			return foundObjects;
        
    }// End method getIntersectingObjects
    
    
    /**
     * Return all objects within range 'radius' around this object. An object is within 
     * range if the distance between its edge and this object's center is less than or 
     * equal to 'radius'.
     * 
     * @param radius   Radius of the circle (in cells)
     * @param cls      Class of objects to look for (passing 'null' will find all objects)
     * @return         all objects within range 'radius' around this object
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
    public List getObjectsInRange(int radius, Class cls){
        
        /* Get all actors of the given class in this world */
    		List<Actors> actors = getWorld().getObjects(cls);
    		
    		
		/* Initialize a list to hold all the actors within the radius */
    		List<Actors> foundActors = new ArrayList<Actors>();
    		
    		
		/* Check each actors position to find which ones are in the radius */
    		for(Actors actor : actors){
    			
    			/* Get the closest point on the current actor to this actor */
    				Point vertexes[] = actor.getVertexes();
    				Point position = vertexes[0];
    				for(Point vertex : vertexes){
    					
    					/* 
    					 * Check to see if the current vertex is closer 
    					 * than the current closest
    					 */
    					if(vertex.distance(getX(), getY())<
    							position.distance(getX(), getY())){
    						
    						/* Make the current vertex the current closest */
    							position = vertex;
    						
    					}// End if(vertex.distance(getX(), getY())<...
    					
    				}// End for(Point vertex : vertexes)
    			
    				
    			/* Check the current actor's position */
    				if(position.distance(getX(), getY())<=radius){
    					
    					/* Add the found actor the the list */
    						foundActors.add(actor);
    					
    				}// End if(postion.distance(actor.getX(), actor.getY())<=radius)
    			
    		}// End for(objects object : objects)
        
    		
		/* Return the actors found in the radius */
    		return foundActors;
    		
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
     * Get this actors' vertexes of it's image
     * 
     * @return   THe vertexes of this actors' image
     */
    private Point[] getVertexes(){
    	
    	/* Initialize variable for holding vertexes of this actor */
    		Point[] vertexes = new Point[4];
    	
    		
    	/* Get the top-left of this actors' image and rotate it to the image */
    		int x = getX()-getImage().getWidth()/2;
    		int y = getY()-getImage().getHeight()/2;
    		vertexes[0] = new Point(x,y);
    		//rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
    		
		/* Get the top-right of this actors' image and rotate it to the image */
    		x = getX()+getImage().getWidth()/2;
    		vertexes[1] = new Point(x,y);
    		//rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
    		
		/* Get the bottom-right of this actors' image and rotate it to the image */
    		y = getY()+getImage().getHeight()/2;
    		vertexes[2] = new Point(x,y);
    		//rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
		
		/* Get the bottom-left of this actors' image and rotate it to the image */
    		x = getX()-getImage().getWidth()/2;
    		vertexes[3] = new Point(x,y);
    		//rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
    		
		/* Return the vertexes found */
    		return vertexes;
    	
    }// End method getVertexes
    
    
//    /**
//     * Rotates the first given point about the second given point
//     * 
//     * @param point1     The point to rotate
//     * @param point2     The point to rotate about
//     * @param rotation   The amount to rotate (in radians)
//     */
//    private void rotatePoint(Point point1, Point point2, double rotation){
//    	
//    	/* Translate to origin to rotate */
//    		point1.translate(-(int)point2.getX(), -(int)point2.getX());
//    		
//    		
//		/* Multiply the x and y coordinate by cos and sin of the angle, respectively, to get the new x and y */
//    		int x = (int) (point1.getX()*Math.cos(rotation));
//    		int y = (int) (point1.getY()*Math.sin(rotation));
//    		point1.setLocation(x, y);
//    		
//    		
//		/* Translate back to final postion */
//    		point1.translate((int)point2.getX(), (int)point2.getX());
//    	
//    }// End method rotatePoint
	
	
	/**
	 * Checks if this actor intersects the given object
	 * 
	 * @param other   The actor to check for intersection
	 * @return        If the actors intersect each other or not
	 */
	public boolean intersects(Actors other){
		
		/* Get the sides of this actor */
			Point vertexes[] = getVertexes();
			Line2D.Double sides[] = new Line2D.Double[vertexes.length];
			for(int i=0;i<sides.length;i++){
				
				sides[i] = new Line2D.Double(
							vertexes[i-i/sides.length*sides.length], 
							vertexes[i+1-(i+1)/sides.length*sides.length]);
				
			}// End for(int i=0;i<sides.length;i++)
		

		/* Get the sides of the other actor */
			vertexes = other.getVertexes();
			Line2D.Double sidesOther[] = new Line2D.Double[vertexes.length];
			for(int i=0;i<sidesOther.length;i++){
				
				sidesOther[i] = new Line2D.Double(
							vertexes[i-i/sidesOther.length*sidesOther.length], 
							vertexes[i+1-(i+1)/sidesOther.length*sidesOther.length]);
				
			}// End for(int i=0;i<sidesOther.length;i++)
		
			
		/* Check to see if any of the actors' sides intersect */
			for(Line2D.Double side : sides){
				for(Line2D.Double sideOther : sidesOther){
					
					/* Check to see if the current sides intersect */
						if(side.intersectsLine(sideOther)){
							
							/* If one pair of sides intersect than the actors intersect */
								return true;
							
						}// End if(side.intersectsLine(sideOther))
						
				}// End for(Line2D.Double sideOther : sidesOther)
			}// End for(Line2D.Double side : sides)
		
			
		/* If none of the sides intersect than the actors don't intersect */
			return false;
		
	}// End method intersects
    
    
    /**
     * Act - do whatever the Actors wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public abstract void act(); 
    
}// End class Actors
