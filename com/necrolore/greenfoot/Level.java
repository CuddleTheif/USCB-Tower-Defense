package com.necrolore.greenfoot;
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import com.necrolore.entity.*;
import com.necrolore.road.Path;
import com.necrolore.road.PathShape;

/**
 * A Test level of USCB Tower Defense
 * 
 * @author NecroTheif
 * @version 2014.14.14
 */
public class Level extends World{

    private Path path; // The current Path of the Level
    private USCB uscb; // The current USCB Building of the Level
    
    
    /**
     * Create A Level with road shape "Z"
     */
    public Level()
    {    
        
        /* 
         * Create a new world with 600x400 cells with a cell size of 1x1 pixels 
         * and green background. 
         */
            super(600, 400, 1, false); 
            setBackground(new GreenfootImage("images/green-grass-texture.jpg"));
            
        /* Create Road */
            path = new Path(25, PathShape.HORIZONTAL_BOLT, 0, 50, 550, 50);
            path.createPath();
            path.addToWorld(this);
            
            
        /* Create the Building */
            uscb = new USCB(100, 50);
            addObject(uscb, 550, 50);
            
            
        /* Create And Place Random EntitySpawner at the start of the path */
            EntitySpawner spawner = new EntitySpawner(new TestEntity());
            addObject(spawner, 0, 50);
            
        
        /* Build a tower */
            Tower tower = new Tower(75, 50);
            addObject(tower, 300, 75);
            
            
    }// End zero-argument constructor for USCB
    
    
    /**
     * Gets the current Path of the Level
     * 
     * @return   The current Path of the Level
     */
    public Path getPath(){
        
        /* Return the current Path */
            return path;
        
    }// End method getPath
    
    
    /**
     * Gets the current USCB Building of the Level
     * 
     * @return   The current USCB Building of the Level
     */
    public USCB getUSCB(){
        
        /* Return the current USCB */
            return uscb;
            
    }// End method USCB
    
}// End class USCB