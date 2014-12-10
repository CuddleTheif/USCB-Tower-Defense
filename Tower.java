

import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;

/**
 * A Tower that shots out shots when enemy's are in it's range
 * 
 * @author NecroTheif
 * @version 2014.16.11
 */
public abstract class Tower extends Entity
{
	public static final int WIDTH=Level.WORLD_WIDTH/20;
	public static final int HEIGHT=Level.WORLD_HEIGHT/10;
	protected UpgradeMenu upgradeMenu; // The upgradeMenu fot this tower
    
    /**
     * Creates a Tower with the given range, cooldown, and spawns
     * 
     * @param range      The range of the tower
     * @param cooldown   The cooldown of the tower (how long it must wait between shots)
     * @param spawns     What the tower spawns or attacks with
     */
    public Tower(int range, int cooldown, Entity spawns){
        
    	/* Call the super class' constructor to initialize behaviors and attributes */
            super();
            
        
        /* Set this objects' image */
            setImage(new GreenfootImage("images/Tower.png"));
            getImage().scale(WIDTH, HEIGHT);
            
            
        /* Store the given range and cooldown as attributes */
            attributes.put(Attribute.RANGE, range);
            attributes.put(Attribute.MAX_COOLDOWN, cooldown);
            attributes.put(Attribute.CUR_COOLDOWN, 0);
            
            
        /* Store a shot as it's entity Spawn attribute */
            attributes.put(Attribute.SPAWNS, spawns);
            
            
        /* Give spawning behavior to spawn shots */
            behaviors.put(Behavior.Type.SPAWNER, new Spawner(this));
        
    }// End no-argument constructor Tower
    
    
    /**
     * Act - do whatever the Tower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act(){
    	
    	/* Check to see if this tower was clicked */
    		if(Greenfoot.mouseClicked(this)){
    			
    			/* Create a Upgrade menu for this tower at this location */
    				upgradeMenu = new UpgradeMenu(this);
    				getWorld().addObject(upgradeMenu, getX()+upgradeMenu.getImage().getWidth(), getY());
    			
    		}// End if(Greenfoot.mouseClicked(this))
    		else if(Greenfoot.mouseClicked(null) && !Greenfoot.mouseClicked(upgradeMenu)){
    			
    			/* Remove the Upgrade menu from the world */
    				getWorld().removeObject(upgradeMenu);
    			
    		}// End else if(Greenfoot.mouseClicked(null))
    	
    }// End method act
    
}// End class Tower
