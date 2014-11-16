import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * The USCB Buliding that has Health and a Death Animation
 * 
 * @author NecroTheif
 * @version 2014.14.11
 */
public class USCB extends Entity{
    
    /**
     * Creates a USCB Bulding With Max Health being the given value and with the given size
     * 
     * @param maxHealth   The max health of the USCB Bulding
     * @param size        The physical size of the bulding
     */
    public USCB(int maxHealth, int size){
        
        /* Call the superclass' constructor to initlize behaivor and attribute variables */
            super();
            
            
        /* Resize this entity to the given size */
            getImage().scale(size, size);
            
            
        /* Get and Store the start and end death animation images */
            GreenfootImage images[] = new GreenfootImage[27];
            images[0] = new GreenfootImage("USCBSandSharks.png");
            images[0].scale(size, size);
            images[26] = new GreenfootImage("skull-crossbones.png");
            images[26].scale(size, size);
            
            
        /* Load and Store the exlposion animation images for the death animation */
            for(int i=0;i<25;i++){
                
                /* Check if in first or second half of animation */
                    if(i<12){
                        
                        /* Load the bulding for under the explosion */
                            images[i+1] = new GreenfootImage("USCBSandSharks.png");
                        
                    }// Emd if(1<12)
                    else{
                        
                        /* Load the skull for under the explosion */
                            images[i+1] = new GreenfootImage("skull-crossbones.png");

                    }// End else for if(i<12)
                    
                
                /* Get and Overlay the current explosion image */
                    GreenfootImage curImage = new GreenfootImage(
                                                    "explosion"+String.valueOf(i)+".png");
                    curImage.scale(size, size);
                    images[i+1].scale(size, size);
                    images[i+1].drawImage(curImage, 0, 0);
                
            }// End for(int i=0;i<25;i++)
            
            
        /* Store the death animation images as an attribute and the building health */
            attributes.put(Attribute.DEATH_ANIMATION, images);
            attributes.put(Attribute.MAX_HP, maxHealth);
            attributes.put(Attribute.HP, maxHealth);
            
        /* Initlize and store the Animation Behavior */
            behaviors.put(Behavior.Type.ANIMATION, new Animation(this));
            
    }// End one-argument constructor
    
    
    /**
     * Act - do whatever the USCB wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if((Integer)attributes.get(Attribute.HP)==0)((Animation)behaviors.get(Behavior.Type.ANIMATION)).loopThroughTimes(Attribute.DEATH_ANIMATION, 1);
    }    
}
