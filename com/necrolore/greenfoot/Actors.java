package com.necrolore.greenfoot;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import java.util.ArrayList;
import java.util.List;
import java.awt.Point;


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
    		rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
    		
		/* Get the top-right of this actors' image and rotate it to the image */
    		x = getX()+getImage().getWidth()/2;
    		vertexes[1] = new Point(x,y);
    		rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
    		
		/* Get the bottom-right of this actors' image and rotate it to the image */
    		y = getY()+getImage().getHeight()/2;
    		vertexes[2] = new Point(x,y);
    		rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
		
		/* Get the bottom-left of this actors' image and rotate it to the image */
    		x = getX()-getImage().getWidth()/2;
    		vertexes[3] = new Point(x,y);
    		rotatePoint(vertexes[0], new Point(getX(), getY()), Math.toRadians(getRotation()));
    		
    		
		/* Return the vertexes found */
    		return vertexes;
    	
    }// End method getVertexes
    
    
    /**
     * Rotates the first given point about the second given point
     * 
     * @param point1     The point to rotate
     * @param point2     The point to rotate about
     * @param rotation   The amount to rotate (in radians)
     */
    private void rotatePoint(Point point1, Point point2, double rotation){
    	
    	/* Translate to origin to rotate */
    		point1.translate(-(int)point2.getX(), -(int)point2.getX());
    		
    		
		/* Multiply the x and y coordinate by cos and sin of the angle, respectively, to get the new x and y */
    		int x = (int) (point1.getX()*Math.cos(rotation));
    		int y = (int) (point1.getY()*Math.sin(rotation));
    		point1.setLocation(x, y);
    		
    		
		/* Translate back to final postion */
    		point1.translate((int)point2.getX(), (int)point2.getX());
    	
    }// End method rotatePoint
    
    
	/**
	 * Gets the axes of this actor
	 * 
	 * @return   The axes of this actor
	 */
	public Point[] getAxes(){
		
		/* Get the vertexes of this actor */
			Point vertexes[] = getVertexes();
		
			
		/* Initialize variable for holding the axes of this actor */
			Point[] axes = new Point[vertexes.length];
		
		for (int i=0;i<axes.length;i++){
		
		  /* Get the next vertex */
		  	Point nextVertex = vertexes[i+1==vertexes.length ? 0:i+1];
		
		
		  /* Subtract the two points to get the normal vector */
		  	int xVal = (int) (vertexes[i].getX()-nextVertex.getX());
		  	int yVal = (int) (vertexes[i].getY()-nextVertex.getY());
		  	
		  	
	  	/* Get the perpendicular vector and normalize it */
		  	double length = Math.sqrt(xVal*xVal+yVal*yVal);
		  	axes[i] = new Point((int)(-yVal/length), (int)(xVal/length));
		  	
		}// End for (int i=0;i<axes.length;i++)

		
		return axes;
		
	}// End method getAxes
	
	
	/**
	 * Gets the projection on the given axis of this actor
	 * 
	 * @param axis   Axis to check for
	 * @return       The projection of this actor on the axis
	 */
	public Point getProjection(Point axis){
		
		/* Initialize variables for holding the min and max projections */
			Point[] vertexes = getVertexes();
			double min = axis.getX()*vertexes[0].getX()+axis.getY()*vertexes[0].getY();
			double max = min;
			
			
		/* Find the min and max points */
			for (int i=1;i<vertexes.length;i++){
				
				/* Do the dot product of the point and the axes to find the projection */
					double projection = axis.getX()*vertexes[i].getX()+axis.getY()*vertexes[i].getY();
				
				
				/* Check the projection to see if it's the new min or max */
					if(projection<min) min = projection;
					if(projection>max) max = projection;
				
			}// End for (int i=1;i<vertexes.length;i++)
			
			
		/* Return the projection of this actor */
			return new Point((int)min, (int)max);
			
	}// End method getOverlap
	
	
	/**
	 * Checks if this actor intersects the given object
	 * 
	 * @param other   The actor to check for intersection
	 * @return        If the actors intersect each other or not
	 */
	public boolean intersects(Actors other){
		
		/* Get the axes between these two actors */
			Point axes1[] = getAxes();
			Point axes2[] = other.getAxes();
			Point allAxes[] = new Point[axes1.length+axes2.length];
			System.arraycopy(axes1, 0, allAxes, 0, axes1.length);
			System.arraycopy(axes2, 0, allAxes, axes1.length, axes2.length);
			
			
		/* Check every projection to see if any don't overlap */
			for (int i=0;i<allAxes.length;i++){
				  
				  /* Get both HitBoxes Projections onto the current axis */
				  	Point p1 = getProjection(allAxes[i]);
				  	Point p2 = other.getProjection(allAxes[i]);
				  
				  /* Check to see if the projections overlap */
				  	if (p1.getX()>p2.getY() || p2.getX()>p1.getY()) return false;
				  
				}// End for (int i=0;i<allAxes.length;i++)

		
		
		/* Since every projection overlaps they intersect */
			return true;
		
	}// End method intersects
    
    
    /**
     * Act - do whatever the Actors wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
}
