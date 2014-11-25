package com.necrolore.entity;

import java.awt.Point;

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
	
}// End class Hitbox
