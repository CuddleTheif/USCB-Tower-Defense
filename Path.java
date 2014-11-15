import greenfoot.*;
import java.awt.Point;
import java.util.List;


/**
 * A group of roads that make up a Path
 * 
 * @author NecroTheif
 * @version 2014.11.11
 */
public class Path{

    private int size; // The size of the road pieces that make up the path
    private int[] xPoints, yPoints; // The coordinates of the junctions of the path
    private Road roads[]; // The roads that make up the path
    
    
    /**
     * Initlize a path with the given junctions' postions and road piece size.
     * Run createPath method to actualy create the path.
     * 
     * @param size      The size of the road pieces that make up the path
     * @param xPoints   The junctions' postions on the x-axis
     * @param yPoints   The junctions' postions on the y-axis
     */
    public Path(int size, int xPoints[], int yPoints[])
    {
        
        /* Initlize class variables */
            this.size = size;
            this.xPoints = xPoints;
            this.yPoints = yPoints;
            roads = new Road[xPoints.length-1];
        
    }// End two-argument constructor for Path
    
    
    /**
     * Initlizes a path with the given size, shape, staring point, and ending point.
     * Run createPath method to actually create the path.
     * 
     * @param size   The size of the road pieces that make up the path
     * @param shape   The shape of the path
     * @param startX   The starting postion of the path on the x-axis
     * @param startY   The starting postion of the path on the y-axis
     * @param endX     The ending postion of the path on the x-axis
     * @param endY     The ending postion of the path on the y-axis
     */
    public Path(int size, PathShape shape, int startX, int startY, int endX, int endY){
        
        /* Get points of junctions of the given shape */
            Point junctions[] = shape.getPoints(startX, startY, endX, endY);
            
        
        /* Initlize class variables */
            this.size = size;
            this.xPoints = new int[junctions.length];
            this.yPoints = new int[junctions.length];
            roads = new Road[xPoints.length-1];
            
        /* 
         * Convert the points of the shape's conjuction to two arrays holding the x and y 
         * coordiantes and store them into the class variables 
         */
        for(int i=0;i<junctions.length;i++){
            
            /* Get and store the current x and y postions */
                xPoints[i] = (int) junctions[i].getX();
                yPoints[i] = (int) junctions[i].getY();
            
        }// End for(int i=0;i<junctions.length;i++)
        
    }// End six-argument constructor for Path
    
    
    /**
     * Creates the Path from the path's current junction postions.
     */
    public void createPath(){
        
        /* Create each road that make up the path */
            for(int i=0;i<roads.length;i++){
                
                /* Initlize current road with the x and y postions */
                    roads[i] = new Road(size,
                                    xPoints[i], yPoints[i],
                                    xPoints[i+1], yPoints[i+1]);
                                    
                
                /* Create the current road */
                    roads[i].createRoad();
                
            }// End for(int road=0;road<roads.length;road++)
        
    }// End method createPath
    
    
    /**
     * Adds the Path to the given world
     * 
     * @param wolrd   The world to add this path to
     */
    public void addToWorld(World world){
        
        /* Add each road to the world */
            for(int i=0;i<roads.length;i++){
                
                /* Add the current road to the given world */
                    roads[i].addToWorld(world);
                
            }// End for(int i=0;i<roads.length;i++)
        
    }// End method addToWorld
    
    
    /**
     * Gets the road piece of the given number. Road piece 0 is the 
     * first piece and it counts up from there.
     * 
     * @param number   The number of the road piece number
     * @return         The road piece wanted
     */
    public RoadPiece getRoadPiece(int number){
        
        /* Initlize variable to hold the wanted road piece */
            RoadPiece roadPiece = null;
        
            
        /* Check each road to see which road the piece is in */
            for (int i=0;i<roads.length;i++){
                
                /* Check to see if the road piece is in the current road */
                    if(number<roads[i].getRoadPieces().length){
                        
                        /* Find the piece in the road, store it, and break */
                            roadPiece = roads[i].getRoadPieces()[number];
                            break;
                        
                    }// End if(piece<roads[i].getRoadPieces().length)
                    else{
                        
                        /* 
                         * Update the number of the road piece wanted to not
                         * include the current road 
                         */
                            number -= roads[i].getRoadPieces().length;
                        
                    }// End else for if(piece<roads[i].getRoadPieces().length)
                
            }// End for (int i=0;i<roads.length;i++)
        
            
        /* Return the wanted road piece */
            return roadPiece;
        
    }// End method getRoadPiece
    
    
    /**
     * Gets the last road piece in the path
     * 
     * @return   The last road piece in the path
     */
    public RoadPiece getLastRoadPiece(){
        
        /* Get the road pieces of the last road of the path */
            RoadPiece roadPieces[] = roads[roads.length-1].getRoadPieces();
        
        
        /* Return the last road piece from the last road */
            return roadPieces[roadPieces.length-1];
        
    }// End method getLastRoadPiece
    
    
    /**
     * Determines if the Road piece at the given number is at the given postion
     * 
     * @param number   The number of the road piece to look for
     * @param xPos     The postion on the x-axis to look at
     * @param yPos     The postion on the y-axis to look at
     * @return         If the road piece is at the given postion or not
     */
    public boolean isRoadPieceAt(int number, int xPos, int yPos){
        
        /* Get the world the road pieces are in */
            World world = getRoadPiece(0).getWorld();
            
            
        /* Get the RoadPieces at the given postion */
            List<RoadPiece> roadPieces = world.getObjectsAt(xPos, yPos, RoadPiece.class);
            
        /* Get the road piece of the given number */
            RoadPiece roadPiece = getRoadPiece(number);
            
            
        /* Return if the given road piece is at the given postion */
            return roadPieces.contains(roadPiece);
        
    }// End method isRoadPieceAt
    
    
    /**
     * Gets the road piece's number of the given road piece. Road piece 0 
     * is the first piece and it counts up from there.
     * 
     * @param roadPiece   The road piece
     * @return        The number of the road piece given (-1 if it can't find it)
     */
    public int getRoadPieceNum(RoadPiece roadPiece){
        
        /* Intilize a counter to count which number road piece is on */
            int counter = 0;
            
        /* Check each road to see which road the piece is in */
            for(int road=0;road<roads.length;road++){
                
                /* Get and store the road pieces of the current road */
                    RoadPiece roadPieces[] = roads[road].getRoadPieces();
                
                    
                /* Check each piece in the current road to see if it's the given one */
                    for(int piece=0;piece<roadPieces.length;piece++){
                        
                        /* Check to see if the current road piece is the one given */
                            if(roadPieces[piece]==roadPiece){
                                
                                /* Return the current piece's number */
                                    return counter;
                                    
                            }// End if(roadPieces[piece]==roadPiece)
                        
                            
                        /* Incriment the counter to count another road piece */
                            counter++;
                            
                    }// End for(int piece=0;piece<roadPieces.length;piece++)
                
            }// End for(int road=0;road<roads.length;road++)
        
            
        /* Return the -1 to say it couldn't find the piece */
            return -1;
        
    }// End method getRoadPiece
    
}// End class Path