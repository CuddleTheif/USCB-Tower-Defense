import greenfoot.*;
import java.util.List;

/**
 * The Beahvior of an Entity to Move
 * 
 * @author NecroTheif 
 * @version 2014.13.11
 */
public class Movement extends Behavior {

    private Actor actor; // An actor for the target of movement
    private int counter; // Counter used for turing every so often
    
    
    /**
     * Initilizes a Movement Behavior for the given entity
     * 
     * @param entity   The entity that has this behavior
     */
    public Movement(Entity entity){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
        
    }// End one-argument constructor for Movement
    
    
    /**
     * Moves the Entity to the closest part of the path and 
     * moves it along the path until it reaches the end.
     * 
     * @return   If the Entity has reached the end of the path.
     */
    public boolean moveAlongPath(Path path){
        
        /* 
         * Intilize a varible to hold the road piece at the entity's postion and set it to 
         * the road piece at the entity's position if there is one or set it to null if 
         * there isn't 
         */
            List<RoadPiece> roadPieces = entity.getObjectsInRange(0, RoadPiece.class);
            RoadPiece roadPiece = roadPieces.size()!=0 ? roadPieces.get(0) : null;
            
                                    
        /* Check to see if the Entity is on the path yet or not */
            if(step==-1){
                    
                /* Check if the entity is on the path yet and if on the center */
                    if(roadPiece!=null){
                        
                        /* Set the step variable to the path step the entity is at */
                            step = path.getRoadPieceNum(roadPiece);
                            
                    }// End if(path.isRoadAt(entity.getX(), entity.getY()))
                    else{
                        
                        /* Find the closest road piece */
                            roadPiece = (RoadPiece) entity.getClosestActor(RoadPiece.class);
                        
                            
                        /* Move closer to the center of the square */
                            entity.turnTowards(roadPiece.getX(), roadPiece.getY());
                            entity.move(1);
                        
                    }// End else for if(path.isRoadAt(entity.getX(), entity.getY()))
                
            }// End if(step==-1)
            else{// Entity is following the path
                    
                /* Find the next road piece */
                    roadPiece = path.getRoadPiece(step+1);
                    
                    
                /* Check to make sure there is a next road piece */
                    if(roadPiece!=null){
                        
                        /* Check if the entity is on the next path step yet */
                            if(roadPiece.getX()==entity.getX() &&
                                roadPiece.getY()==entity.getY()){
                                
                                /* Incriment the step variable to the next path step */
                                    step++;
                                    
                            }// End if(roadPiece==path.getRoadPiece(step+1))
                            else{
                                
                                /* Turn and move towards the next road piece */
                                    entity.turnTowards(roadPiece.getX(), roadPiece.getY());
                                    entity.move(1);
                                
                            }// End else for (roadPiece.getX()==entity.getX()...
                        
                    }// End if(roadPiece!=null)
               
            }// End else for if(step==-1)
            
            
        /* Return if the entity has reached the end of the path yet or not */
            return roadPiece!=null && path.getLastRoadPiece()==roadPiece;
        
    }// End method moveAlongPath
    
    
    /**
     * Moves the Entity to the closest actor of a given class
     * 
     * @param cls      The class of the actor to move to
     * @return         If the Entity has reached one yet
     */
    public boolean moveToClosest(Class cls){
        
        /* Check to see if the Entity is moving towards the Actor yet or not */
            if(step==-1){
                
                /* Get and store the closest Actor of the given class */
                    actor = entity.getClosestActor(cls);
                
                /* Set the step variable to 0 to indicate starting movement */
                    step = 0;
                
            }// End if(step==-1)
                
            
        /* Turn and move towards the actor */
            entity.turnTowards(actor.getX(), actor.getY());
            entity.move(1);
            
            
        /* Return If the Entity has reached that actor */
            return entity.getIntersectingObjects(cls).contains(actor);
        
    }// End method moveToClosest
    
    
    /**
     * Moves the Entity to the closest actor with a given attribute at true
     * 
     * @param attribute      The attribute the target entity must have set to true
     * @return               If the Entity has reached one yet
     */
    public boolean moveToClosest(Attribute attribute){
        
        /* Check to see if the Entity is moving towards the Actor yet or not */
            if(step==-1){
                
                /* Get and store the closest Actor of the with the given attribute at true */
                    actor = entity.getClosestEntity(attribute);
                
                /* Set the step variable to 0 to indicate starting movement */
                    step = 0;
                
            }// End if(step==-1)
                
            
        /* Turn and move towards the actor */
            entity.turnTowards(actor.getX(), actor.getY());
            entity.move(1);
            
            
        /* Return If the Entity has reached that actor */
            return entity.getIntersectingObjects(actor.getClass()).contains(actor);
        
    }// End method moveToClosest
    
    
}// End class Movement
