//package com.necrolore.entity;
//
//import greenfoot.GreenfootImage;
//
//import com.necrolore.entity.behavior.Behavior;
//import com.necrolore.entity.behavior.Combat;
//import com.necrolore.entity.behavior.Movement;
//import com.necrolore.greenfoot.GifImage;
//import com.necrolore.greenfoot.Level;
//
//public class Robot extends Entity {
//
//    /**
//     * Creates a Robot with the given range, health, attack, and defense
//     * 
//     * @param range    Range of the robot
//     * @param health   The health of the robot
//     * @param attack   The attack of the robot
//     * @param defense   The defense of the robot
//     */
//    public Robot(int range, int health, int attack, int defense){
//        
//    	/* Call the super class' constructor to initialize behaviors and attributes */
//            super();
//            
//            
//        /* Get this objects' image */
//            GifImage robotImage = new GifImage("images/robot.gif");
//            attributes.put(Attribute.GIF, robotImage);
//            
//            
//        /* Create and Add the Robot's Behaviors */
//            behaviors.put(Behavior.Type.MOVEMENT, new Movement(this));
//            behaviors.put(Behavior.Type.COMBAT, new Combat(this));
//        
//            
//        /* Add the Robot's Attributes */
//            attributes.put(Attribute.ENEMY, true);
//            attributes.put(Attribute.HP, health);
//            attributes.put(Attribute.MAX_HP, health);
//            attributes.put(Attribute.ATK, attack);
//            attributes.put(Attribute.DEF, defense);
//            attributes.put(Attribute.RANGE, range);
//        
//    }// End no-argument constructor
//    
//    /**
//     * Act - do whatever the BeeEnemy wants to do. This method is called whenever
//     * the 'Act' or 'Run' button gets pressed in the environment.
//     */
//    public void act(){
//    	
//    	/* Make sure the game is not paused */
//    		if(pause)return;
//            setImage(((GifImage)attributes.get(Attribute.GIF)).getCurrentImage());
//            getImage().scale(25, 25);
//    		
//    		
//		/* Get the behaviors this entity will use */
//    		Combat combat = ((Combat)behaviors.get(Behavior.Type.COMBAT));
//    		Movement movement = ((Movement)behaviors.get(Behavior.Type.MOVEMENT));
//    	
//    		
//    	/* Check to see if the entity is in combat */
//    		Entity target = combat.getFightingEntity();
//    		if(target!=null){
//    			
//    			/* Attack the entity this entity is in combat with */
//    				combat.attackEntity(target, Combat.Maneuver.NORMAL);
//    				
//    				
//				/* Check to see if the entity this entity is in combat is dead */
//    				if(target.hasAttribute(Attribute.HP) &&
//    						(int)target.getAttribute(Attribute.HP)<=0){
//    					
//    					/* Disengage from combat */
//    						combat.disengage();
//    					
//    				}// End if(target.hasAttribute(Attribute.HP) &&...
//    			
//    		}// End if(target!=null)
//        /* Go to the closest enemy in range if not fighting */
//    		else if(getEntitiesInRange((int)attributes.get(Attribute.RANGE), Attribute.ENEMY).size()!=0 &&
//    					movement.moveTowards(getClosestEntity(Attribute.ENEMY))){
//    			
//    			/* If reached an enemy attack it */
//    				combat.engage(getClosestEntity(Attribute.ENEMY));
//    				combat.attackEntity(getClosestEntity(Attribute.ENEMY), Combat.Maneuver.NORMAL);
//                
//            }// End else if(getEntitiesInRange((int)attributes.get(Attribute.RANGE), Attribute.ENEMY).size()!=0 &&...
//
//        /* If the entity is dead remove it */
//            if((Integer)attributes.get(Attribute.HP)==0){
//            	
//            	getWorld().removeObject(this);
//            	
//            }// End if((Integer)attributes.get(Attribute.HP)==0)
//            
//    }// End method act
//	
//	
//}// End class Robot
