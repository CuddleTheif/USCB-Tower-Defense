package com.necrolore.entity;

import greenfoot.GreenfootImage;

/**
 * An attribute of an Entity
 * 
 * @author NecroTheif
 * @version 2014.15.14
 */
public enum Attribute{
    
    MAX_HP(Type.COMBAT), HP(Type.COMBAT), ATK(Type.COMBAT), DEF(Type.COMBAT),
        DEATH_ANIMATION(Type.ANIMATION), DEATH_SOUND(Type.ANIMATION), ENEMY(Type.TARGET),
        SPAWNS(Type.OTHER), MAX_SPAWNS(Type.OTHER), MAX_COOLDOWN(Type.OTHER), VALUE(Type.OTHER),
        CUR_COOLDOWN(Type.OTHER), RANGE(Type.OTHER), DIE(Type.OTHER), NUM_SPAWNS(Type.OTHER),
        PATH(Type.OTHER);
    
        
    /**
     * The Types of Attributes possible
     */
    public enum Type{
        
        COMBAT, ANIMATION, TARGET, OTHER;
        
    }// End enum Type
    
        
    private Type type; // The type of the Attribute
        
        
    /**
     * Creates an Attribute of the given type
     * 
     * @param type   The type of the new Attribute
     */
    private Attribute(Type type){
        
        /* Set this Attribute's type to the given type */
            this.type = type;
        
    }// End one-argument constructor for Attribute
    
    
    /**
     * Get this Attribute's type
     * 
     * @return   The attribute's type
     */
    public Type getType(){
        
        /* Return the attrbute's type */
            return type;
        
    }// End method getType
    
    
    /**
     * Get this Attribute's image
     * 
     * @return   The attribute's image
     */
    public GreenfootImage getImage(){
        
        /* Return the attrbute's image */
            return new GreenfootImage("images/attributes/"+this.name().toLowerCase()+".png");
        
    }// End method getImage
    
}// End enum Attribute
