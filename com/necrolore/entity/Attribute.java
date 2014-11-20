package com.necrolore.entity;
/**
 * An attribute of an Entity
 * 
 * @author NecroTheif
 * @version 2014.15.14
 */
public enum Attribute{
    
    MAX_HP(Type.COMBAT), HP(Type.COMBAT), ATK(Type.COMBAT), DEF(Type.COMBAT), 
        DEATH_ANIMATION(Type.ANIMATION), DEATH_SOUND(Type.ANIMATION), SPAWNS(Type.SPAWN),
        ENEMY(Type.TARGET), RANGE(Type.OTHER), MAX_COOLDOWN(Type.OTHER), CUR_COOLDOWN(Type.OTHER),
        DIE(Type.OTHER);
    
        
    /**
     * The Types of Attributes possible
     */
    public enum Type{
        
        COMBAT, ANIMATION, SPAWN, TARGET, OTHER;
        
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
    
}// End enum Attribute
