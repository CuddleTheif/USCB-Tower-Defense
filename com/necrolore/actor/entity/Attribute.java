package com.necrolore.actor.entity;



import java.util.ArrayList;
import java.util.List;

import greenfoot.GreenfootImage;

/**
 * An attribute of an Entity
 * 
 * @author NecroTheif
 * @version 2014.15.14
 */
public enum Attribute{
    
    MAX_HP(Type.COMBAT, 2, 1, "The Health (number of damage can receive before death)"), HP(Type.COMBAT), 
    	ATK(Type.COMBAT, 2, 1, "The attack (damage done on attack)"), 
    	DEF(Type.COMBAT, 2, 1, "The defense (damage reduced per attack taken)"),
        DEATH_ANIMATION(Type.ANIMATION), DEATH_SOUND(Type.ANIMATION), ENEMY(Type.TARGET),
        SPAWNS(Type.OTHER), MAX_SPAWNS(Type.OTHER), MAX_COOLDOWN(Type.OTHER, 2000, -1, "Time between attacks"), 
        CUR_COOLDOWN(Type.OTHER), RANGE(Type.OTHER, .5, 5, "Radius of attack of tower"), 
        DIE(Type.OTHER), NUM_SPAWNS(Type.OTHER), PATH(Type.OTHER), PRICE(Type.OTHER, -0.5, 0, "Sell");
    
        
    /**
     * The Types of Attributes possible
     */
    public enum Type{
        
        COMBAT, ANIMATION, TARGET, OTHER;
        
    }// End enum Type
    
        
    private Type type; // The type of the Attribute
    private double priceMuti; // The price multiplier of the Attribute (zero if not upgradeable)
    private int increase; // The amount the attribute increases when upgraded (zero doesn't mean not upgradeable)
    private String description; // The description of the Attribute
        
        
    /**
     * Creates an Attribute of the given type that is not upgradeable with no description
     * 
     * @param type   The type of the Attribute
     */
    private Attribute(Type type){
        
        /* Initialize this attribute with default values */
    		this(type, null);
        
    }// End one-argument constructor for Attribute
    
    
    /**
     * Creates an Attribute of the given type that is not upgradeable
     * 
     * @param type          The type of the Attribute
     * @param description   The description of the Attribute
     */
    private Attribute(Type type, String description){
        
        /* Initialize this attribute with default values and the given description */
    		this(type, 0, 0, description);
        
    }// End one-argument constructor for Attribute
    
    
    /**
     * Creates an Attribute of the given type that has the given price multiplier and increase value
     * 
     * @param type          The type of the Attribute
     * @param priceMuti     The price multiplier of the Attribute
     * @param increase      The amount the Attribute increases when upgraded
     * @param description   The description of the Attribute
     */
    private Attribute(Type type, double priceMuti, int increase, String description){
        
        /* Set this Attribute's values to the give ones */
            this.type = type;
            this.priceMuti = priceMuti;
            this.increase = increase;
            this.description = description;
        
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
    public int getIncreaseVal(){
        
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
    
    
    /**
     * Get this Attribute's description (null if it has none)
     * 
     * @return this Attribute's description
     */
    public String getDescription(){
    	
    	/* Return this Attribute's description */
    		return description;
    	
    }// End method getDescription


    /**
     * Get all the Attributes that are upgradeable
     * @return   All Attributes that are upgradeable
     */
	public static Attribute[] upgradeValues() {
		
		/* Initialize variable for holding found attributes */
			List<Attribute> foundAttr = new ArrayList<Attribute>();
			
			
		/* Find all the attributes that are upgradeable */
			for(Attribute attr : Attribute.values())
				if(attr.getPriceMuti()!=0)
					foundAttr.add(attr);
			
			
		/* Return the found attributes */
			return foundAttr.toArray(new Attribute[foundAttr.size()]);
			
	}// End method upgradeValues
    
}// End enum Attribute
