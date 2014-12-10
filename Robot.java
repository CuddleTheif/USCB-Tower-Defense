

import sun.net.ftp.FtpDirEntry.Type;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

public class Robot extends Entity {

    /**
     * Creates a Robot with the given range, health, attack, and defense
     * 
     * @param range      Range of the robot
     * @param cooldown   The cooldown of the robots attacks
     * @param health     The health of the robot
     * @param attack     The attack of the robot
     * @param defense    The defense of the robot
     */
    public Robot(int range, int cooldown, int health, int attack, int defense){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
            
        /* Set the robot's image */
            int xCell = Greenfoot.getRandomNumber(10);
            int yCell = Greenfoot.getRandomNumber(10);
            setImage("images/bots/robot-0"+xCell+"-0"+yCell+".png");
            getImage().scale(Tower.WIDTH/2, Tower.HEIGHT/2);
            
            
        /* Create and Add the Robot's Behaviors */
            behaviors.put(Behavior.Type.MOVEMENT, new Movement(this));
            behaviors.put(Behavior.Type.COMBAT, new Combat(this));
        
            
        /* Add the Robot's Attributes */
            attributes.put(Attribute.HP, health);
            attributes.put(Attribute.ATK, attack);
            attributes.put(Attribute.DEF, defense);
            attributes.put(Attribute.RANGE, range);
            attributes.put(Attribute.MAX_COOLDOWN, cooldown);
            attributes.put(Attribute.CUR_COOLDOWN, 0);
            
    }// End no-argument constructor
    
    /**
     * Act - do whatever the BeeEnemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
    	
    	/* Make sure the game is not paused */
    		if(pause)return;
    		
    		
		/* Get the behaviors this entity will use */
    		Combat combat = ((Combat)behaviors.get(Behavior.Type.COMBAT));
    		Movement movement = ((Movement)behaviors.get(Behavior.Type.MOVEMENT));
    	
    		
    	/* Check to see if the entity is in combat */
    		Entity target = combat.getFightingEntity();
    		if(target!=null){
    			
    			/* Check if in cooldown from last attack */
    				if((Integer)attributes.get(Attribute.CUR_COOLDOWN)==0){
    					
    	    			/* Attack the entity this entity is in combat with */
    	    				combat.attackEntity(target, Combat.Maneuver.NORMAL);
    	    				
	    				/* Set cooldown to max */
    	    				int maxCool = (Integer) attributes.get(Attribute.MAX_COOLDOWN);
    	    				attributes.replace(Attribute.CUR_COOLDOWN, maxCool);
    					
    				}// End if((int)attributes.get(Attribute.CUR_COOLDOWN)==0)
    				else{
    					
    					/* Reduce cooldown */
	    					int cooldown = (Integer) attributes.get(Attribute.CUR_COOLDOWN);
	    	                attributes.replace(Attribute.CUR_COOLDOWN, --cooldown);
    					
    				}// End else for if((int)attributes.get(Attribute.CUR_COOLDOWN)==0)
    			
    				
    				
				/* Check to see if the entity this entity is in combat is dead */
    				if((Integer)target.getAttribute(Attribute.HP)==0){
    					
    					/* Disengage from combat */
    						combat.disengage();
    					
    				}// End if(target.hasAttribute(Attribute.HP) &&...
    			
    		}// End if(target!=null)
        /* Go to the closest enemy in range if not fighting */
    		else if(movement.moveToLastInRange( 
	    				(Integer) attributes.get(Attribute.RANGE), 
	    				Attribute.ENEMY)){
    			
    			/* If reached an enemy engage it */
    				combat.engage(getClosestEntity(Attribute.ENEMY));
                
            }// End else if(getEntitiesInRange((int)attributes.get(Attribute.RANGE), Attribute.ENEMY).size()!=0 &&...

    		
        /* If the entity is dead remove it */
            if((Integer)attributes.get(Attribute.HP)==0){
            	
            	getWorld().removeObject(this);
            	
            }// End if((Integer)attributes.get(Attribute.HP)==0)
            
    }// End method act
	
	
}// End class Robot
