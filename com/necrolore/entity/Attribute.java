package com.necrolore.entity;


import greenfoot.GreenfootImage;

/**
 * An attribute of an Entity
 * 
 * @author NecroTheif
 * @version 2014.15.14
 */
public enum Attribute{
    
    MAX_HP(Type.COMBAT, 2), HP(Type.COMBAT), ATK(Type.COMBAT, 2), DEF(Type.COMBAT, 2),
        DEATH_ANIMATION(Type.ANIMATION), DEATH_SOUND(Type.ANIMATION), ENEMY(Type.TARGET),
        SPAWNS(Type.OTHER), MAX_SPAWNS(Type.OTHER), MAX_COOLDOWN(Type.OTHER, -2), 
        CUR_COOLDOWN(Type.OTHER), RANGE(Type.OTHER, 2), DIE(Type.OTHER), NUM_SPAWNS(Type.OTHER),
        PATH(Type.OTHER), PRICE(Type.OTHER, 0.5);
    
        
    /**
     * The Types of Attributes possible
     */
    public enum Type{
        
        COMBAT, ANIMATION, TARGET, OTHER;
        
    }// End enum Type
    
        
    private Type type; // The type of the Attribute
    private double priceMuti; // The price multiplier of the Attribute (zero if not upgradeable)
        
        
    /**
     * Creates an Attribute of the given type that is not upgradeable
     * 
     * @param type   The type of the Attribute
     */
    private Attribute(Type type){
        
        /* Initialize this attribute with a 0 price multiplier */
    		this(type, 0);
        
    }// End one-argument constructor for Attribute
    
    
    /**
     * Creates an Attribute of the given type that has the given price multiplier
     * 
     * @param type        The type of the Attribute
     * @param priceMuti   The price multiplier of the Attribute
     */
    private Attribute(Type type, double priceMuti){
        
        /* Set this Attribute's type and price multiplier to the given type and price multiplier */
            this.type = type;
            this.priceMuti = priceMuti;
        
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
     * Get this Attribute's price multiplier (returns 0 if not upgradeable)
     * 
     * @return   The attribute's price multiplier (0 if not upgradeable)
     */
    public double getPriceMuti(){
        
        /* Return the attrbute's price multiplier */
            return priceMuti;
        
    }// End method getPriceMuti
    
    
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
