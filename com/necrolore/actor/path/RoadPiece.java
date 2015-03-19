package com.necrolore.actor.path;



import com.necrolore.actor.Actors;

import greenfoot.GreenfootImage;

/**
 * A Piece of a road with it's image.
 * 
 * @author NecroTheif
 * @version 2014.10.11
 */
public class RoadPiece extends Actors{
    
    private int globalX, globalY; // The global coordinates of the road piece
    
    
    /**
     * Creates a road with given size at the given global coordinates
     * 
     * @param size      The size of the road
     * @param globalX   The global X coordinate of the road piece
     * @param globalY   The global Y coordinate of the road piece
     */
    public RoadPiece(int size, int globalX, int globalY){
    	
    	/* Set this objects' image */
        	setImage(new GreenfootImage("images/road.png"));
        
        	
        /* Initializes the class' variables to given ones */
            this.globalX = globalX;
            this.globalY = globalY;
        
        /* Scale the road piece to the given size */
            this.getImage().scale(size, size);
        
    }// End one-argument constructor
    
    
    /**
     * Gets the global X coordinate of the road piece
     * 
     * @return   The global X coordinate
     */
    public int getGlobalX(){
        
        /* Return the global X coordinate */
            return globalX;
        
    }// End method getGlobalX
    
    
    /**
     * Gets the global Y coordinate of the road piece
     * 
     * @return   The global Y coordinate
     */
    public int getGlobalY(){
        
        /* Return the global Y coordinate */
            return globalY;
        
    }// End method getGlobalY
    
    
    /**
     * Act - do whatever the wq wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }   
    
}// End class RoadPiece
