import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class Shot here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Shot extends Entity
{
    
    public Shot(){
        
        /* Call the superclass' constructor to initlize behaivor and attribute variables */
            super();
            
        /* Initilize variable to hold the images for the death animation */
            GreenfootImage images[] = new GreenfootImage[25];
            
            
        /* Load and Store the exlposion animation images for the death animation */
            for(int i=0;i<25;i++){
                
                /* Get and Overlay the current explosion image */
                    images[i] = new GreenfootImage("explosion"+String.valueOf(i)+".png");
                    images[i].scale(16, 16);
                
            }// End for(int i=0;i<25;i++)
            
            
        /* Store the death animation images, death sound, and bulding health as an attribute */
            attributes.put(Attribute.DEATH_ANIMATION, images);
            attributes.put(Attribute.DEATH_SOUND, "Explosion.wav");
            
        /* Initlize and store the Animation and Movement and Combat Behavior */
            behaviors.put(Behavior.Type.ANIMATION, new Animation(this));
            behaviors.put(Behavior.Type.MOVEMENT, new Movement(this));
            behaviors.put(Behavior.Type.COMBAT, new Combat(this, 0, 10, 0));
        
    }// End no-argument constructor for Shot
    
    /**
     * Act - do whatever the Shot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        
        /* Get Movement Behavior to move towards the closest Entity */
            Movement movement = (Movement)behaviors.get(Behavior.Type.MOVEMENT);
            
            
        /* Move towards the closest Enemy and explode when it reaches it */
            if(movement!=null && movement.moveToClosest(Attribute.ENEMY)){
                
                /* Get all the entities the entity is touching */
                    List<Entity> entities = getIntersectingObjects(Entity.class);
                
                /* Attack the entities it is touching */
                    Combat combat = (Combat)behaviors.get(Behavior.Type.COMBAT);
                    for(int i=0;i<entities.size();i++){
                        
                        /* Attack the current entity */
                            combat.attackEntity(entities.get(i), Combat.Maneuver.NORMAL);
                        
                    }// End for(int i=0;i<entities.size();i++)
                    
                /* Play explosion animation and sound */
                    Animation animation = (Animation) behaviors.get(
                                                            Behavior.Type.ANIMATION);
                    animation.loopThroughTimes(Attribute.DEATH_ANIMATION, 1,
                                                    Attribute.DEATH_SOUND);
                                                    
                /* Remove this actor */
                    behaviors.replace(Behavior.Type.MOVEMENT, null);
                    getWorld().removeObject(this);
                
            }// End if(movement.moveToClosest(Attribute.ENEMY))
            
    }// End method act
}
