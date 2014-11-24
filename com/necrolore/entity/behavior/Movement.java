package com.necrolore.entity.behavior;
import greenfoot.*;

import java.util.List;
import java.util.ArrayList;

import com.necrolore.entity.Attribute;
import com.necrolore.entity.Entity;
import com.necrolore.road.Path;
import com.necrolore.road.RoadPiece;

/**
 * The Behavior of an Entity to Move
 * 
 * @author NecroTheif 
 * @version 2014.13.11
 */
public class Movement extends Behavior {
    
    private Entity target; // The target of movements
    private Path path; // The path this entity is following
    
    /**
     * Initializes a Movement Behavior for the given entity
     * 
     * @param entity   The entity that has this behavior
     */
    public Movement(Entity entity){
        
        /* Call the superclass' constructor to store the given entity */
            super(entity);
            
            
        /* Set path and target to null since it's not following or targeting anything */
            path = null;
            target = null;
        
    }// End one-argument constructor for Movement
    
    
    /**
     * Moves the Entity to the closest part of the path and 
     * moves it along the path until it reaches the end.
     * 
     * @return   If the Entity has reached the end of the path.
     */
    public boolean moveAlongPath(Path path){
        
        /* 
         * Initialize a variable to hold the road piece at the entity's position and set it to 
         * the road piece at the entity's position if there is one or set it to null if 
         * there isn't 
         */
            @SuppressWarnings("unchecked")
			List<RoadPiece> roadPieces = entity.getObjectsInRange(0, RoadPiece.class);
            RoadPiece roadPiece = roadPieces.size()!=0 ? roadPieces.get(0) : null;
            
                                    
        /* Check to see if the Entity is on the path yet or not */
            if(step==-1){
                    
                /* Set the class variable to hold the given path to indicate this entity is following it */
                    this.path = path;
                
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
                                
                                /* Increment the step variable to the next path step */
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
     * Gets the path this entity is following if any.
     * 
     * @return   The path this entity is following (null if it's not following any)
     */
    public Path getPath(){
        
        /* return the class variable holding the path */
            return path;
        
    }// End method getPath
    
    
    /**
     * Moves the Entity to the closest actor of a given class
     * 
     * @param cls      The class of the actor to move to
     * @return         If the Entity has reached one yet
     */
    @SuppressWarnings("rawtypes")
	public boolean moveToClosest(Class cls){
        
        /* Get and store the closest Actor of the given class */
            Actor actor = entity.getClosestActor(cls);
                
            
        /* Turn and move towards the actor */
            entity.turnTowards(actor.getX(), actor.getY());
            entity.move(1);
            
            
        /* Return If the Entity has reached that actor */
            return entity.getIntersectingObjects(cls).contains(actor);
        
    }// End method moveToClosest
    
    
    /**
     * Moves the Entity to the closest entity with a given attribute at true
     * 
     * @param attribute      The attribute the target entity must have set to true
     * @return               If the Entity has reached one yet
     */
    public boolean moveToClosest(Attribute attribute){
                
        /* Get and store the closest Actor of the with the given attribute at true */
            Entity closeEntity = entity.getClosestEntity(attribute);
            
            
        /* Make sure it found one */
            if(closeEntity==null){
                
                /* Move the entity forward and return false since it didn't reach one */
                    entity.move(1);
                    return false;
                
            }// End if(closeEntity==null)
        
        
        /* Turn and move towards the entity */
            entity.turnTowards(closeEntity.getX(), closeEntity.getY());
            entity.move(1);
            
            
        /* Return If the Entity has reached that actor */
            return entity.getIntersectingObjects(closeEntity.getClass()).contains(closeEntity);
        
    }// End method moveToClosest
    
    /**
     * Gets the Entities in the given range of this entity with the given attribute set to true
     * And in the given path.
     * 
     * @param path        The path to look on
     * @param range       The range to look in
     * @param attribute   The attribute the target entity must have set to true
     * @return            The Entities in the range, in the path, and with the attribute set to true
     */
    @SuppressWarnings("rawtypes")
	private List getEntitiesInRangeAndPath(Path path, int range, Attribute attribute){
        
        /* Get all entities in the given range with the given attribute set to true */
            List<Entity> entities = entity.getEntitiesInRange(range, attribute);
            
            
        /* Initialize a variable to hold the entities found on the path */
            List<Entity> foundEntities = new ArrayList<Entity>();
            
            
        /* Check each entity to see if any are on the path */
            for(int i=0;i<entities.size();i++){
                
                /* Check if the current entity has the behavior movement and get it */
                    Movement movement = entities.get(i).hasBehavior(Type.MOVEMENT) ?
                                        (Movement)entities.get(i).getBehavior(Type.MOVEMENT) :
                                        null;
                
                /* 
                 * Check the current entity to see if it can move and 
                 * is following the given path 
                 */
                    if(movement!=null && movement.getPath()==path){
                        
                        /* Add the found entity to the list */
                            foundEntities.add(entities.get(i));
                            
                    }// End if(entities.hasBehavior(Type.MOVEMENT) &&...
                
            }// End for(int i=0;i<entities.size();i++)
            
        /* Return the entities found */
            return foundEntities;
        
    }// End method getEntitiesInRangeAndPath
    
    
    /**
     * Moves the Entity to the last entity on the given path within the given range with the 
     * given attribute at true
     * 
     * @param path        The path to look on
     * @param range       The range that it can target in
     * @param attribute   The attribute the target entity must have set to true
     * @return            If the Entity has reached one yet
     */
    public boolean moveToLastInRange(Path path, int range, Attribute attribute){
        
        /* Check if targeted on the entity or not yet */
            if(step==-1){
                
                /* Get the entities on the path with the attribute and in the range */
                    @SuppressWarnings("unchecked")
					List<Entity> entities = getEntitiesInRangeAndPath(path, range, attribute);
                    
                    
                /* Make sure entities were found */
                    if(entities.size()==0){
                        
                        /* Return false because none were found so none were reached */
                            return false;
                        
                    }// End if(entities.size()==0)
                    
                    
                /* 
                 * Initialize variables to hold the farthest entity on the path and how far it is
                 * and set them to the first entity on the list
                 */
                    Entity lastEntity = entities.get(0);
                    int lastStep = lastEntity.getBehavior(Type.MOVEMENT).getStep();
                    
                /* Check each entity to find the farthest entity */
                    for(int i=0;i<entities.size();i++){
                        
                        /* Get the current entities Step */
                            int currentStep = entities.get(i).getBehavior(Type.MOVEMENT).getStep();
                            
                            
                        /* Check if the current step is later than the current last one */
                            if(currentStep>lastStep){
                                
                                /* Set the new last step and entity to the current one */
                                    lastStep = currentStep;
                                    lastEntity = entities.get(i);
                                
                            }// End if(currentStep>lastStep)
                        
                    }// End for(int i=0;i<entities.size();i++)
                
                    
                /* Make the last entity this entities target and set step to the next phase */
                   target = lastEntity;
                   step = 0;
                   
                   
                /* return false because entity didn't even move */
                    return false;
                   
            }// End if(step==-1)
            else{
                
                /* Make sure the target exist */
                    if(target.getWorld()==null){
                        
                        /* Return false because there was no target and set step back to retarget */
                            step = -1;
                            return false;
                        
                    }// End if(target.getWorld()==null)
                    
                    
                /* Turn and move towards the target entity */
                    entity.turnTowards(target.getX(), target.getY());
                    entity.move(1);
                    
                    
                /* Check to see if it has reached the entity yet */
                    return entity.intersects(target);
                
            }// End else for if(step==-1)
        
    }// End method moveToLastInRange
    
    
    /**
     * Gets the target this entity is moving to if any.
     * 
     * @return   The target this entity is moving to (null if it's not moving to any)
     */
    public Entity getTarget(){
        
        /* return the class variable holding the target */
            return target;
        
    }// End method getTarget
    
    
}// End class Movement
