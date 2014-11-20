/**
 * A Tower that shots out shots when enemy's are in it's range
 * 
 * @author NecroTheif
 * @version 2014.16.11
 */
public class Tower extends Entity
{
    
    /**
     * Creates a Tower with the given range and cooldown
     * 
     * @param range      The range of the tower
     * @param cooldown   The cooldown of the tower (how long it must wait between shots)
     */
    public Tower(int range, int cooldown){
        
        /* Call the superclass' constructor to Initialize behavior and attribute variables */
            super();
            
        
        /* Store the given range and cooldown as attributes */
            attributes.put(Attribute.RANGE, range);
            attributes.put(Attribute.MAX_COOLDOWN, cooldown);
            attributes.put(Attribute.CUR_COOLDOWN, 0);
            
            
        /* Store a shot as it's entity Spawn attribute */
            attributes.put(Attribute.SPAWNS, new Shot(range));
            
            
        /* Give spawning behavior to spawn shots */
            behaviors.put(Behavior.Type.SPAWNER, new Spawner(this));
        
    }// End no-argument constructor Tower
    
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        /* Check to see if the tower is not in cooldown */
            if((Integer)attributes.get(Attribute.CUR_COOLDOWN)==0){
                
                /* Get this tower's range */
                   int range =  (Integer) attributes.get(Attribute.RANGE);
                
                
                /* Test to see if there is an enemy is this tower's range */
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
    
}// End class Tower
