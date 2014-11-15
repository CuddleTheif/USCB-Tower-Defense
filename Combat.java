import java.util.HashMap;

/**
 * The Beahvior of an Entity to Fight.
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
        
        private double muti; // The mutiplyer to do to Maneuvers of this Maneuver type
        
        
        /**
         * Creates an Maneuver Type with the given mutiplyer
         * 
         * @param muti   The mutiplyer of Maneuvers of this Maneuver type
         */
        private Maneuver(double muti){
            
            /* Intilize class' variable muti to given value */
                this.muti = muti;
            
        }// End one-argument constructor for Maneuver
        
        
        /**
         * Gets the mutiplyer of this Maneuver type
         * 
         * @return   The mutiplyer of Maneuvers of this Maneuver type
         */
        public double getMuti(){
            
            /* Return the mutiplyer */
                return muti;
                
        }// End method getMuti
        
    }// End enum Maneuver
    
    
    private Maneuver defending; // If this entity is defending this is equal to it's level of defense
    private int maxHealth, health, attack, defense; // The combat stats of this entity
    
    
    /**
     * Initilizes a Combat Behavior for the given entity with the given max health, attack, and defense
     * 
     * @param entity      The entity that has this behavior
     * @param maxHealth   The max health of the entity
     * @param attack      The attack of the entity
     * @param defense     The defense of the entity
     */
    public Combat(Entity entity, int maxHealth, int attack, int defense){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
        
        
        /* Intilize and store the attributes for holding Health, Attack, and Defense */
            this.maxHealth = maxHealth;
            health = maxHealth;
            this.attack = attack;
            this.defense = defense;
            entity.setAttribute(Attribute.MAX_HP, maxHealth);
            entity.setAttribute(Attribute.HP, health);
            entity.setAttribute(Attribute.ATK, attack);
            entity.setAttribute(Attribute.DEF, defense);
            
    }// End no-argument constructor for Combat
    
    
    /**
     * Start Defending, Increasing Defense but Can't Attack
     * 
     * @param defenseType   The level of defense to start.
     */
    public void startDefending(Maneuver defenseType){
        
        /* Set defending to the given defend type to store the defense Type */
            defending = defenseType;
            
            
        /* Increase This Entity's Defense by the given level */
            defense += defense*defenseType.getMuti();
            
            
        /* Reset this entity's defense to the new value */
            entity.setAttribute(Attribute.DEF, defense);
        
    }// End method startDefending
    
    
    /**
     * Decrease defense back to normal and can attack now
     */
    public void stopDefending(){
            
        /* Calculate This Entity's Defense original defense with the defense level it had */
            defense /= 1+defending.getMuti();
            
            
        /* Reset the entity's defense to it's original value */
            entity.setAttribute(Attribute.DEF, defense);
        
        
        /* Reset the defening varibale to null to indicate no longer defending */
            defending = null;
        
    }// End method stopDefending
    
    
    /**
     * Attack the given entity with the given attack
     * 
     * @param targetEntity   The entity to attack
     * @param attackType     The type of attack to use
     * @return               If the attack was succesful
     */
    public boolean attackEntity(Entity targetEntity, Maneuver attackType){
        
        /* Make sure the entity being attacked has health and this entity is not defending */
            if(!targetEntity.hasAttribute(Attribute.HP) || defending!=null){
                
                /* Return false because the entity can't be attacked */
                    return false;
                
            }// End if(!targetEntity.hasBehavior(Type.COMBAT))
            
            
            
        /* 
         * Get the current Health of the entity being attacked and intilize a varible 
         * to hold it's defense 
         */
            int targetHealth = (Integer) targetEntity.getAttribute(Attribute.HP);
            int targetDefense = 0;
            
            
        /* Check to see if the entity being attacked has a defense */
            if(targetEntity.hasAttribute(Attribute.DEF)){
                
                /* Get the targets defense and store it */
                    targetDefense = (Integer) targetEntity.getAttribute(Attribute.DEF);
                    
                
            }// End if(targetEntity.hasAttribute(Entity.Attribute.DEF))
            
            
        /* Get the attack of this entity wiht the given attack type */
            int totAttack = (int) (attack*attackType.getMuti());
            
            
        /* Calculate and Do the damage to the health of the target entity (reducing it to a min of 0) */
            int damage = attack-targetDefense;
            damage = damage<0 ? 0 : damage;
            targetHealth -= damage;
            targetHealth = targetHealth<0 ? 0 : targetHealth;
            
            
        /* Reset the target entity's health to the new value */
            targetEntity.setAttribute(Attribute.HP, targetHealth);
            
            
        /* Return true since the attack was succesful */
            return true;
        
    }// End method attackEntity
    
}// End class Combat
