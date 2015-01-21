package com.necrolore.entity;


import greenfoot.GreenfootImage;

/**
 * An attribute of an Entity
 * 
 * @author NecroTheif
 * @version 2014.15.14
 */
public enum Attribute{
    
    MAX_HP(Type.COMBAT, 2, 1), HP(Type.COMBAT), ATK(Type.COMBAT, 2, 1), DEF(Type.COMBAT, 2, 1),
        DEATH_ANIMATION(Type.ANIMATION), DEATH_SOUND(Type.ANIMATION), ENEMY(Type.TARGET),
        SPAWNS(Type.OTHER), MAX_SPAWNS(Type.OTHER), MAX_COOLDOWN(Type.OTHER, 2000, -1), 
        CUR_COOLDOWN(Type.OTHER), RANGE(Type.OTHER, 2, 1), DIE(Type.OTHER), NUM_SPAWNS(Type.OTHER),
        PATH(Type.OTHER), PRICE(Type.OTHER, 0.5, 0);
    
        
    /**
     * The Types of Attributes possible
     */
    public enum Type{
        
        COMBAT, ANIMATION, TARGET, OTHER;
        
    }// End enum Type
    
        
    private Type type; // The type of the Attribute
    private double priceMuti; // The price multiplier of the Attribute (zero if not upgradeable)
    private int increase; // The amount the attribute increases when upgraded (zero doesn't mean not upgradeable)
        
        
    /**
     * Creates an Attribute of the given type that is not upgradeable
     * 
     * @param type   The type of the Attribute
     */
    private Attribute(Type type){
        
        /* Initialize this attribute with a 0 price multiplier and 0 increase */
    		this(type, 0, 0);
        
    }// End one-argument constructor for Attribute
    
    
    /**
     * Creates an Attribute of the given type that has the given price multiplier and increase value
     * 
     * @param type        The type of the Attribute
     * @param priceMuti   The price multiplier of the Attribute
     * @param increase    The amount the Attribute increases when upgraded
     */
    private Attribute(Type type, double priceMuti, int increase){
        
        /* Set this Attribute's type, price multiplier, and increase to the given type, price multiplier, and increase */
            this.type = type;
            this.priceMuti = priceMuti;
            this.increase = increase;
        
    }// End three-argument constructor for Attribute
    
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
     * this will be the price divider if increase value is negative
     * 
     * @return   The attribute's price multiplier (0 if not upgradeable)
     */
    public double getPriceMuti(){
        
        /* Return the attrbute's price multiplier */
            return priceMuti;
        
    }// End method getPriceMuti
    
    
    /**
     * Get this Attribute's increase value
     * 
     * @return   The amount the attribute increases when upgraded (0 doesn't mean not upgradeable)
     */
    public double getIncreaseVal(){
        
        /* Return the attrbute's increase value */
            return increase;
        
    }// End method getIncreaseVal
    
    
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
