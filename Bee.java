

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * An Entity to test Behaviors
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class Bee extends Entity{
    
    /**
     * Creates a Bee with the given health, cooldown, attack and value
     */
    public Bee(int health, int cooldown, int attack, int value){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
            
        /* Set this objects' image */
            setImage(new GreenfootImage("images/bee2.png"));
            getImage().scale(Level.WORLD_WIDTH/40, Level.WORLD_HEIGHT/20);
            
            
        /* Create and Add the Bee's Behaviors */
            behaviors.put(Behavior.Type.MOVEMENT, new Movement(this));
            behaviors.put(Behavior.Type.COMBAT, new Combat(this));
        
            
        /* Add the Bee's Attributes */
            attributes.put(Attribute.ENEMY, true);
            attributes.put(Attribute.HP, health);
            attributes.put(Attribute.ATK, attack);
            attributes.put(Attribute.MAX_COOLDOWN, cooldown);
            attributes.put(Attribute.CUR_COOLDOWN, 0);
            attributes.put(Attribute.VALUE, value);
        
    }// End no-argument constructor
    
    /**
     * Act - do whatever the Bee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
    	/* Make sure the game is not paused */
    		if(pause)return;
    		
		/* Get the path this entity will follow */
    		if(!attributes.containsKey(Attribute.PATH)){
    			
    			Path paths[] = ((Level)getWorld()).getPaths();
    			attributes.put(Attribute.PATH, paths[Greenfoot.getRandomNumber(paths.length)]);
    			
    		}
    		
    		
		/* Get the behaviors this entity will use */
    		Movement movement = (Movement)behaviors.get(Behavior.Type.MOVEMENT);
    		Combat combat = (Combat)behaviors.get(Behavior.Type.COMBAT);
    		
    		
		/* Check if in combat */
    		if(combat.getFightingEntity()!=null){
    			
    			/* Check if in cooldown from last attack */
				if((Integer)attributes.get(Attribute.CUR_COOLDOWN)==0){
					
	    			/* Attack the entity this entity is in combat with */
	    				combat.attackEntity(combat.getFightingEntity(), Combat.Maneuver.NORMAL);
	    				
	    				
    				/* Set cooldown to max */
	    				int maxCool = (Integer) attributes.get(Attribute.MAX_COOLDOWN);
	    				attributes.replace(Attribute.CUR_COOLDOWN, maxCool);
					
				}// End if((int)attributes.get(Attribute.CUR_COOLDOWN)==0)
				else{
					
					/* Reduce cooldown */
    					int cooldown = (Integer) attributes.get(Attribute.CUR_COOLDOWN);
    	                attributes.replace(Attribute.CUR_COOLDOWN, --cooldown);
					
				}// End else for if((int)attributes.get(Attribute.CUR_COOLDOWN)==0)
    				
    				
				/* Check if the entity this entity is fighting is dead yet */
    				if((Integer)combat.getFightingEntity().getAttribute(Attribute.HP)==0){
    					
    					/* Disengage since there is no one left to fight */
    						combat.disengage();
    					
    				}// End if((int)combat.getFightingEntity().getAttribute(Attribute.HP)==0)
    			
    		}// End if
        /* Follow the path */
    		else if(movement.moveAlongPath((Path) attributes.get(Attribute.PATH))){
            	
            	/* If reached the end of the path, attack the building and die */
	            	combat.attackEntity(((Level)getWorld()).getUSCB(), Combat.Maneuver.NORMAL);
	                getWorld().removeObject(this);
                
            }// End else if(movement.moveAlongPath(((Level)getWorld()).getPath())){

        /* If the entity is dead remove it and add it's value to the gold */
            if((Integer)attributes.get(Attribute.HP)==0){
            	
            	((Level)getWorld()).addGold((Integer) attributes.get(Attribute.VALUE));
            	getWorld().removeObject(this);
            	
            }// End if((int)attributes.get(Attribute.HP)==0)
            
    }    
}