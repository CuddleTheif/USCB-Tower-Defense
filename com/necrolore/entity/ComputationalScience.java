package com.necrolore.entity;
import com.necrolore.entity.behavior.Behavior;
import com.necrolore.entity.behavior.Spawner;
import com.necrolore.entity.behavior.Behavior.Type;




public class ComputationalScience extends Tower {

	/**
	 * Creates a ComputationalScience major with the 
	 * given range and cooldown
	 * 
	 * @param range      The range of its attacks
	 * @param cooldown   The cooldown of the tower (how long it must wait between attacks)
     * @param maxBots    The max number of bots out of this tower
     * @param botStats   The stats of the bot (health, attack, defense)
	 */
	public ComputationalScience(int range, int cooldown, int maxBots, int... botStats) {
		
		/* Call the super class' constructor to create the tower */
			super(range, cooldown, new Robot(range, cooldown, botStats[0], botStats[1], botStats[2]));
			
			setImage("images/bots/robot-00-00.png");
			
		/* Store attribute that holds the count of robots out */
			attributes.put(Attribute.MAX_SPAWNS, maxBots);
			attributes.put(Attribute.NUM_SPAWNS, 0);
			
	}// End two-argument Constructor for ComputationalScience

	
	public void act(){
		
    	/* Make sure the game is not paused */
			if(pause)return;
			
			
		/* Call super classes act method to check for clicking */
			super.act();
			
			
		/* 
		 * Check if the cooldown is done and there are less than the 
		 * max amount of robots on the field
		 */
			if((Integer)attributes.get(Attribute.CUR_COOLDOWN)==0 &&
					(Integer)attributes.get(Attribute.NUM_SPAWNS)<
						(Integer)attributes.get(Attribute.MAX_SPAWNS)){
				
				/* Summon a new bot */
	                Spawner spawner = (Spawner) behaviors.get(Behavior.Type.SPAWNER);
	                spawner.spawn(1, Attribute.SPAWNS);
	                int numSpawns = (Integer) attributes.get(Attribute.NUM_SPAWNS);
	                attributes.replace(Attribute.NUM_SPAWNS, ++numSpawns);
	                
	            /* Set the cooldown to max */
	                int maxCooldown = (Integer) attributes.get(Attribute.MAX_COOLDOWN);
	                attributes.replace(Attribute.CUR_COOLDOWN, maxCooldown);
				
			}// End if((int)attributes.get(Attribute.CUR_COOLDOWN)==0 &&...
			else if((Integer)attributes.get(Attribute.CUR_COOLDOWN)!=0){
				
				/* Reduce the cooldown by one */
	                int cooldown = (Integer) attributes.get(Attribute.CUR_COOLDOWN);
	                attributes.replace(Attribute.CUR_COOLDOWN, --cooldown);
				
			}// End else if((int)attributes.get(Attribute.CUR_COOLDOWN)!=0)
			
			if(getWorld().getObjects(Robot.class).size()<(Integer)attributes.get(Attribute.NUM_SPAWNS)){
			    
			    int numSpawns = (Integer) attributes.get(Attribute.NUM_SPAWNS);
	                attributes.replace(Attribute.NUM_SPAWNS, --numSpawns);
			    
			 }
		
	}// End method act
	
}// End class ComputationalScience
