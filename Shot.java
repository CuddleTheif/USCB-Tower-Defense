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
    
    /**
     * Initializes a shot entity that targets enemy's in it's given range
     * 
     * @param   The range of this shot
     */
    public Shot(int range){
        
        /* Call the superclass' constructor to initialize behavior and attribute variables */
            super();
            
        /* Make the shot bigger */
            getImage().scale(16,4);
            
        /* Initialize variable to hold the images for the death animation */
            GreenfootImage images[] = new GreenfootImage[25];
            
            
        /* Load and Store the explosion animation images for the death animation */
            for(int i=0;i<25;i++){
                
                /* Get and Overlay the current explosion image */
                    images[i] = new GreenfootImage("images/explosion"+String.valueOf(i)+".png");
                    images[i].scale(16, 16);
                
            }// End for(int i=0;i<25;i++)
            
            
        /* Store the death animation images, death sound, range, and death state as attributes */
            attributes.put(Attribute.DEATH_ANIMATION, images);
            attributes.put(Attribute.DEATH_SOUND, "sounds/Explosion.wav");
            attributes.put(Attribute.DIE, false);
            attributes.put(Attribute.RANGE, range);
            
            
        /* Initialize and store the Animation and Movement and Combat Behavior */
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
            
            
        /* If not exploding, Move towards the closest Enemy and explode when it reaches it */
            if(!((Boolean)attributes.get(Attribute.DIE)) &&
                movement.moveToLastInRange(((Level)getWorld()).getPath(), 
                    (Integer)attributes.get(Attribute.RANGE),Attribute.ENEMY) &
                movement.moveToLastInRange(((Level)getWorld()).getPath(), 
                    (Integer)attributes.get(Attribute.RANGE),Attribute.ENEMY) &
                movement.moveToLastInRange(((Level)getWorld()).getPath(), 
                    (Integer)attributes.get(Attribute.RANGE),Attribute.ENEMY)){
                        
                        /* Get all the entities the entity is touching */
                            @SuppressWarnings("unchecked")
							List<Entity> entities = getObjectsInRange(25-getImage().getWidth(), Entity.class);
                    
                        
                        /* Attack the entities it is touching */
                            Combat combat = (Combat)behaviors.get(Behavior.Type.COMBAT);
                            for(int i=0;i<entities.size();i++){
                                
                                /* Attack the current entity */
                                    combat.attackEntity(entities.get(i), Combat.Maneuver.NORMAL);
                                
                            }// End for(int i=0;i<entities.size();i++)
                            
                            
                        /* Set to exploding */
                            attributes.replace(Attribute.DIE, true);
                            
            }// End if(!((Boolean)attributes.get(Attribute.DIE)) &&...
            else if((Boolean)attributes.get(Attribute.DIE)){
                    
                /* Play explosion animation and sound */
                    Animation animation = (Animation) behaviors.get(
                                                            Behavior.Type.ANIMATION);
                    boolean doneAni = animation.loopThroughTimes(Attribute.DEATH_ANIMATION, 1,
                                                                    Attribute.DEATH_SOUND);
                                                    
                /* Remove this actor if the animation is done */
                    if(doneAni){
                        
                        /* Remove this actor from the world */
                        getWorld().removeObject(this);
                        
                    }// End if(doneAni)
                    
            }// End else if((Boolean)attributes.get(Attribute.DIE))
            
    }// End method act
}