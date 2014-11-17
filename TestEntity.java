import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Map;
import java.util.HashMap;

/**
 * An Entity to test Beahviors
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class TestEntity extends Entity{
    
    /**
     * Creates a TestEntity
     */
    public TestEntity(){
        
        /* Call the superclass' constructor to Initilize Behaviors and Attributes */
            super();
            getImage().scale(25, 25);
            
        /* Create and Add the TestEntity's Behaviors */
            behaviors.put(Behavior.Type.MOVEMENT, new Movement(this));
            behaviors.put(Behavior.Type.COMBAT, new Combat(this, 20, 10, 0));
        
        /* Add the TestEntity's Attributes */
            attributes.put(Attribute.ENEMY, true);
        
    }// End no-argument constructor
    
    /**
     * Act - do whatever the TestEntity wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        /* Make sure this actor is still in the world */
            if(getWorld()!=null){
        /* Follow the path */
            if(((Movement)behaviors.get(Behavior.Type.MOVEMENT)).moveAlongPath(((Level)getWorld()).getPath())){
                ((Combat)behaviors.get(Behavior.Type.COMBAT)).attackEntity(((Level)getWorld()).getUSCB(), Combat.Maneuver.NORMAL);
                getWorld().removeObject(this);
            }
            
        /* If the entity is dead remove it */
            if((Integer)attributes.get(Attribute.HP)==0)getWorld().removeObject(this);}
    }    
}
