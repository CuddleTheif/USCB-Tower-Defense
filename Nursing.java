


public class Nursing extends Tower {

	/**
	 * Creates a nursing tower with the given 
	 * 
	 * @param range
	 * @param cooldown
	 * @param attack
	 */
	public Nursing(int range, int cooldown, int attack) {
		
		/* Call the superclass' constructor with a shot */
			super(range, cooldown, new Shot(range, attack));
		
	}// End two-argument constructor for Nursing

	
	
	public void act() {
		
    	/* Make sure the game is not paused */
			if(pause)return;
			
			
		/* Call super classes act method to check for clicking */
			super.act();
		
    	
        /* Check to see if the nursing is not in cooldown */
            if((Integer)attributes.get(Attribute.CUR_COOLDOWN)==0){
                
                /* Get this nursing's range */
                   int range =  (Integer) attributes.get(Attribute.RANGE);
                
                
                /* Test to see if there is an enemy is this nursing's range */
                    if(getEntitiesInRange(range, Attribute.ENEMY).size()!=0){
                        
                        /* Spawn a shot that will attack the entity */
                            Spawner spawner = (Spawner) behaviors.get(Behavior.Type.SPAWNER);
                            spawner.spawn(1, Attribute.SPAWNS);
                            
                        /* Set the cooldown to max */
                            int maxCooldown = (Integer) attributes.get(Attribute.MAX_COOLDOWN);
                            attributes.replace(Attribute.CUR_COOLDOWN, maxCooldown);
                        
                    }// End if(hasEntityInRange(range, Attribute.ENEMY))
                    
            }// End if(attributes.get(Attribute.CUR_COOLDOWN)==0)
            else{
                
                /* Reduce the cooldown by one */
                    int cooldown = (Integer) attributes.get(Attribute.CUR_COOLDOWN);
                    attributes.replace(Attribute.CUR_COOLDOWN, --cooldown);
                
            }// End else for if(attributes.get(Attribute.CUR_COOLDOWN)==0)
            
    }// End method act

}// End class Nursing
