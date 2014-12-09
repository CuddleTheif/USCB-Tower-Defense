package com.necrolore.entity.behavior;

import com.necrolore.entity.Attribute;
import com.necrolore.entity.Entity;

/**
 * The Behavior of an Entity to Fight.
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class Combat extends Behavior 
{
        
    /**
     * Types of Combat Maneuvers an entity can make
     */
    public enum Maneuver{
        
        STRONG(2), NORMAL(1), WEAK(.5);
        
        private double muti; // The multiplier to do to Maneuvers of this Maneuver type
        
        
        /**
         * Creates an Maneuver Type with the given multiplier
         * 
         * @param muti   The multiplier of Maneuvers of this Maneuver type
         */
        private Maneuver(double muti){
            
            /* Initialize class' variable muti to given value */
                this.muti = muti;
            
        }// End one-argument constructor for Maneuver
        
        
        /**
         * Gets the multiplier of this Maneuver type
         * 
         * @return   The multiplier of Maneuvers of this Maneuver type
         */
        public double getMuti(){
            
            /* Return the multiplier */
                return muti;
                
        }// End method getMuti
        
    }// End enum Maneuver
    
    
    private Maneuver defending; // If this entity is defending this is equal to it's level of defense
    private Entity fighting; // If this entity is fighting this is equal to the entity it is fighting
    
    /**
     * Initialize a Combat Behavior for the given entity
     * 
     * @param entity      The entity that has this behavior
     */
    public Combat(Entity entity){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
            
    }// End no-argument constructor for Combat
    
    /**
     * Engages the given entity in combat
     * 
     * @param entity   The entity to engage in combat with
     */
    public void engage(Entity entity){
    	
    	/* Make this entity engaged with the given one */
    		engageWith(entity);
    		
    		
		/* Check to see if the given entity can fight */
    		if(entity.hasBehavior(Type.COMBAT)){
    			
    			/* Make the given entity engaged with this one */
    				Combat combat = (Combat) entity.getBehavior(Type.COMBAT);
    				combat.engageWith(this.entity);
    			
    		}// End if(entity.hasBehavior(Type.COMBAT))
    	
    }// End method engage
    
    /**
     * Become engaged in combat with another entity
     * 
     * @param entity   The entity that this entity is now engaged with
     */
    public void engageWith(Entity entity){
    			
			/* Set fighting to the given entity */
                fighting = entity;
                				
    }// End method engageWith
    
    
    /**
     * Become disengaged from combat
     */
    public void disengage(){
    	
    	/* Set fighting back to null since this entity is no longer fighting anyone */
    	fighting = null;
    	
    }// End method disengage
    
    
    /**
     * Gets the entity this entity is in combat with if any
     * 
     * @return   the entity this entity is in combat with (null if none)
     */
    public Entity getFightingEntity(){
    	
    	/* Return the entity this entity is fighting with (if any) */
    		return fighting;
    	
    }// End getFightingEntity
    
    
    /**
     * Start Defending, Increasing Defense but Can't Attack
     * 
     * @param defenseType   The level of defense to start.
     */
    public void startDefending(Maneuver defenseType){
    	
    	/* Make sure the entity has defense to defend with */
    		if(entity.hasAttribute(Attribute.DEF)){
    			
    			/* Set defending to the given defend type to store the defense Type */
	                defending = defenseType;
	                
	                
	            /* Increase This Entity's Defense by the given level */
	                int defense = (int) entity.getAttribute(Attribute.DEF);
	                defense += defense*defenseType.getMuti();
	                entity.setAttribute(Attribute.DEF, defense);
	                
	                
	            /* Reset this entity's defense to the new value */
	                entity.setAttribute(Attribute.DEF, defense);
    			
    		}// End if(entity.hasAttribute(Attribute.DEF))
    		
    }// End method startDefending
    
    
    /**
     * Decrease defense back to normal and can attack now
     */
    public void stopDefending(){
    	
    	/* Make sure the entity was defending so it can stop */
    		if(defending!=null){
    			
    			/* Calculate This Entity's Defense original defense with the defense level it had */
	            	int defense = (int) entity.getAttribute(Attribute.DEF);
	                defense /= 1+defending.getMuti();
	                entity.setAttribute(Attribute.DEF, defense);
	                
	                
	            /* Reset the entity's defense to it's original value */
	                entity.setAttribute(Attribute.DEF, defense);
	            
	            
	            /* Reset the defending variable to null to indicate no longer defending */
	                defending = null;
    			
    		}// End if(defending!=null)
    		
    }// End method stopDefending
    
    
    /**
     * Attack the given entity with the given attack
     * 
     * @param targetEntity   The entity to attack
     * @param attackType     The type of attack to use
     * @return               If the attack was successful
     */
    public boolean attackEntity(Entity targetEntity, Maneuver attackType){
    	
        /* 
         * Make sure the entity being attacked has health, this entity is not 
         * defending and this entity has attack
         */
            if(!targetEntity.hasAttribute(Attribute.HP) || defending!=null 
            		|| !entity.hasAttribute(Attribute.ATK)){
                
                /* Return false because the entity can't be attacked */
                    return false;
                
            }// End if(!targetEntity.hasBehavior(Type.COMBAT))
            
            
            
        /* 
         * Get the current Health of the entity being attacked and Get the current
         * Defense of the entity being attacked if it has one
         */
            int targetHealth = (Integer) targetEntity.getAttribute(Attribute.HP);
            int targetDefense = targetEntity.hasAttribute(Attribute.DEF) ? 
            						(Integer) targetEntity.getAttribute(Attribute.DEF) : 0;
            
            
        /* Get the attack of this entity with the given attack type */
            int attack = entity.hasAttribute(Attribute.ATK) ? 
            				(Integer) entity.getAttribute(Attribute.ATK) : 0;
            attack *= attackType.getMuti();
            
            
        /* Calculate and Do the damage to the health of the target entity (reducing it to a min of 0) */
            int damage = attack-targetDefense;
            damage = damage<0 ? 0 : damage;
            targetHealth -= damage;
            targetHealth = targetHealth<0 ? 0 : targetHealth;
            
            
        /* Reset the target entity's health to the new value */
            targetEntity.setAttribute(Attribute.HP, targetHealth);
            
            
        /* Return true since the attack was successful */
            return true;
        
    }// End method attackEntity
    
}// End class Combat
