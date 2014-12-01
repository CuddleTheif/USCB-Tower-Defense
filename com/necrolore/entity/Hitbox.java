package com.necrolore.entity;

import java.awt.Point;
import java.util.Vector;

/**
 * Shape with the given vertexes that can be used to detect collisions with other Hitboxes
 * 
 * @author NecroTheif
 * @version 2014.25.11
 */
public class Hitbox extends Entity {
	
	/**
	 * Creates a Hitbox with the given vertexes at the origin (0,0)
	 * 
	 * @param vertexes   The vertexes of the Hitbox shape
	 */
	public Hitbox(Point vertexes[]){
		
		/* Call the constructor with the origin (0,0) */
        	this(vertexes, 0, 0);
		
	}// End one-argument constructor for Hitbox
	
	
	/**
	 * Creates a Hitbox with the given vertexes, "attached" to the given Entity.
	 * "Attached" means this Hitbox's origin of it's vertexes is always equal to 
	 * the position of the given Entity.
	 * 
	 * @param entity     The entity to attach this Hitbox to
	 * @param vertexes   The vertexes of the Hitbox shape
	 */
	public Hitbox(Entity entity, Point vertexes[]){
		
		/* Call the super class' constructor to initialize behaviors and attributes */
        	super();
        

		/* Set this entities position to the given entity's position */
        	Point entityPos = (Point) entity.getAttribute(Attribute.POSTION);
			attributes.put(Attribute.POSTION, entityPos);
			
			
		/* Store this Hitbox's entity and vertexes */
			attributes.put(Attribute.ENTITY, entity);
			attributes.put(Attribute.VERTEXES, vertexes);
		
	}// End one-argument constructor for Hitbox
	
	
	/**
	 * Creates a Hitbox with the given vertexes at the given origin
	 * 
	 * @param vertexes   The vertexes of the Hitbox shape
	 * @param xOrigin    The origin of the given vertexes' x-postion
	 * @param yOrigin    The origin of the given vertexes' y-postion
	 */
	public Hitbox(Point vertexes[], int xOrigin, int yOrigin){
		
		/* Call the super class' constructor to initialize behaviors and attributes */
        	super();
        	
		
		/* Set this entities position to the given origin position */
			attributes.put(Attribute.POSTION, new Point(xOrigin,yOrigin));
		
		
		/* Store this Hitboxes vertexes */
			attributes.put(Attribute.VERTEXES, vertexes);
		
	}// End three-argument constructor for Hitbox
	
	
	/**
	 * Gets the axes of this hitbox
	 * 
	 * @return   The axes of this hitbox
	 */
	public Point[] getAxes(){
		
		/* Get the vertexes of this hitbox */
			Point vertexes[] = (Point[]) attributes.get(Attribute.VERTEXES);
		
			
		/* Initialize variable for holding the axes of this hitbox */
			Point[] axes = new Point[vertexes.length];
		
		for (int i=0;i<axes.length;i++){
		
		  /* Get the next vertex */
		  	Point nextVertex = vertexes[i+1==vertexes.length ? 0:i+1];
		
		
		  /* Subtract the two and get the perpendicular vector */
		  	int xVal = (int) (vertexes[i].getX()-nextVertex.getX());
		  	int yVal = (int) (vertexes[i].getY()-nextVertex.getY());
		  	axes[i] = new Point(-yVal, xVal);
		  	
		}// End for (int i=0;i<axes.length;i++)

		
		return axes;
		
	}// End method getAxes
	
	
	/**
	 * Gets the projection on the given axis of this hitbox
	 * 
	 * @param axis   Axis to check for
	 * @return       The projection of this hitbox on the axis
	 */
	public Point getProjection(Point axis){
		
		/* Initialize variables for holding the min and max projections */
			Point[] vertexes = (Point[]) attributes.get(Attribute.VERTEXES);
			double min = axis.dot(vertexes[0]);
			double max = min;
			
			
		/* Find the min and max points */
			for (int i=1;i<vertexes.length;i++){
				
				/* Do the dot * of the point and the axes to find the projection */
					double p = axis.dot(vertexes[i]);
				
				
				/* Check the projection to see if it's the new min or max */
					if(p<min) min = p;
					if(p>max) max = p;
				
			}// End for (int i=1;i<vertexes.length;i++)
			
			
		/* Return the projection of this hitbox */
			return new Point((int)min, (int)max);
			
	}// End method getOverlap
	
	
	/**
	 * Checks if this hitbox intersects the given hitbox
	 * 
	 * @param hitbox   The hitbox to check for intersection
	 * @return         If the hitboxs intersect each other or not
	 */
	public boolean intersects(Hitbox hitbox){
		
		/* Get the axes between these two hitboxes */
			Point axes1[] = getAxes();
			Point axes2[] = hitbox.getAxes();
			Point allAxes[] = new Point[axes1.length+axes2.length];
			System.arraycopy(axes1, 0, allAxes, 0, axes1.length);
			System.arraycopy(axes2, 0, allAxes, axes1.length, axes2.length);
			
			
		/* Check every projection to see if any don't overlap */
			for (int i=0;i<allAxes.length;i++){
				  
				  /* Get both HitBoxes Projections onto the current axis */
				  Point p1 = projectionOn(allAxes[i]);
				  Point p2 = hitbox.projectionOn(allAxes[i]);
				  
				  /* Check to see if the projections overlap */
				  if (!p1.overlap(p2)) return false;
				  
				}// End for (int i=0;i<allAxes.length;i++)

		
		
		/* Since every projection overlaps they intersect */
			return true;
		
	}// End method intersects
	
	
}// End class Hitbox
