import greenfoot.*;

/**
 * A group of Road pieces
 * 
 * @author NecroTheif
 * @version 2014.10.11
 */
public class Road{
    
    private RoadPiece roadPieces[]; // The road pieces this road contains
    private int pieces; // The number of road pieces this road contains
    private int size; // The size of road pieces of this road
    private int length, width, height; // The dimensions of the road itself
    private int xPosStart, yPosStart; // The coordinates of the center of the first road piece
    private int xPosEnd, yPosEnd; // The coordinates of the center of the last road piece
    
    
    /**
     * Initializes a road with the given starting position, the given ending position, and the given size of 
     * road pieces,. Must run createRoad method to actually create the Road from 
     * road pieces.
     * 
     * @param size        The size of the road pieces
     * @param xPosStart   The starting position of the road on the x-axis
     * @param yPosStart   The starting position of the road on the y-axis
     * @param xPosEnd     The ending position of the road on the x-axis
     * @param yPosEnd     The ending position of the road on the y-axis
     */
    public Road(int size, int xPosStart, int yPosStart, int xPosEnd, int yPosEnd)
    {
            
        /* Calculate and store the dimensions of the Road */
            width = xPosEnd-xPosStart;
            height = yPosEnd-yPosStart;
            length = (int) Math.sqrt(width*width+height*height);
            
            
        /* Calculate the number of road pieces along the length */
            pieces = (int)Math.round((double)length/size)+1;
            
        
        /* Initialize all class variables */
            this.size = size;
            this.xPosStart = xPosStart;
            this.yPosStart = yPosStart;
            this.xPosEnd = xPosEnd;
            this.yPosEnd = yPosEnd;
        
    }// End six-argument constructor for Road
    
    
    /**
     * Change the size of each road piece to the given size
     * 
     * @param size   The new size of the road pieces
     */
    public void setPieceSize(int size){
        
        /* Change the class variable size to the given size */
            this.size = size;
            
            
        /* Recreate the road with the new size */
            createRoad();
        
    }// End method setPieceSize
    
    
    /**
     * Changes the X starting position of the road
     * 
     * @param xPosStart   The new X starting position of the road
     */
    public void setXStartPostion(int xPosStart){
        
        /* Change the class variable xPosStart to the given xPosStart */
            this.xPosStart = xPosStart;
            
            
        /* Calculate and store the new dimensions of the Road */
            width = xPosStart-xPosEnd;
            height = yPosStart-yPosEnd;
            length = (int) Math.sqrt(width*width+height*height);
            
            
        /* Recreate the road with the new position and size */
            createRoad();
            
    }// End method setXStartPostion
    
    
    /**
     * Changes the Y starting position of the road
     * 
     * @param yPosStart   The new Y starting position of the road
     */
    public void setYStartPostion(int yPosStart){
        
        /* Change the class variable yPosStart to the given yPosStart */
            this.yPosStart = yPosStart;
            
            
        /* Calculate and store the new dimensions of the Road */
            width = xPosStart-xPosEnd;
            height = yPosStart-yPosEnd;
            length = (int) Math.sqrt(width*width+height*height);
            
            
        /* Recreate the road with the new position and size */
            createRoad();
            
    }// End method setYStartPostion
    
    
    /**
     * Changes the X ending position of the road
     * 
     * @param xPosEnd   The new X ending position of the road
     */
    public void setXEndPostion(int xPosEnd){
        
        /* Change the class variable xPosEnd to the given xPosEnd */
            this.xPosEnd = xPosEnd;
            
            
        /* Calculate and store the new dimensions of the Road */
            width = xPosStart-xPosEnd;
            height = yPosStart-yPosEnd;
            length = (int) Math.sqrt(width*width+height*height);
            
            
        /* Recreate the road with the new position and size */
            createRoad();
            
    }// End method setXEndPostion
    
    
    /**
     * Changes the Y ending position of the road
     * 
     * @param yPosEnd   The new Y ending position of the road
     */
    public void setYEndPostion(int yPosEnd){
        
        /* Change the class variable yPosEnd to the given yPosEnd */
            this.yPosEnd = yPosEnd;
            
            
        /* Calculate and store the new dimensions of the Road */
            width = xPosStart-xPosEnd;
            height = yPosStart-yPosEnd;
            length = (int) Math.sqrt(width*width+height*height);
            
            
        /* Recreate the road with the new position and size */
            createRoad();
            
    }// End method setYEndPostion
    
    
    /**
     * Creates the Road's road pieces from the current postions, size, and shape.
     */
    public void createRoad(){
        
        /* Initialize the roadPieces again to "delete" the previous */
            roadPieces = new RoadPiece[pieces];
        
        
        /* Initialize variables for holding the "drawing" position */
            int drawX = xPosStart;
            int drawY = yPosStart;
            
        /* Create and set the location of each road piece */
            for(int piece=0;piece<roadPieces.length;piece++){
                
                /* Create the current RoadPiece and set it's location */
                    roadPieces[piece] = new RoadPiece(size, drawX, drawY);
                    
                /* Calculate the drawing x and y position of the next RoadPiece */
                    drawX += size*width/length;
                    drawY += size*height/length;
                
            }// End for(int piece=0;piece<roadPieces.length;piece++)
            
    }// End method createRoad
    
    
    /**
     * Adds this road to the given world
     * 
     * @param world   The world to add this road to.
     */
    public void addToWorld(World world){
        
        /* Add each road piece, at it's own location, to the given world */
            for(int piece=0;piece<roadPieces.length;piece++){
                
                /* Add current road piece to the given world */
                    world.addObject(roadPieces[piece],
                        roadPieces[piece].getGlobalX(), 
                        roadPieces[piece].getGlobalY());
                
            }// End for(int piece=0;piece<roadPieces.length;piece++)
        
    }// End method addToWorld
    
    
    /**
     * Gets the road pieces that make up this road
     * 
     * @return   The road pieces that make up this road
     */
    public RoadPiece[] getRoadPieces(){
        
        /* Return the road pieces */
            return roadPieces;
        
    }// End method getRoadPieces
    
    
    /**
     * Gets the direction the road is facing
     * 
     * @return   The direction the road is facing
     */
    public int getDirection(){
        
        /* Calculate and return the direction from the start and end points of the road */
            return (int) Math.toDegrees(Math.atan2(yPosEnd-yPosStart, xPosEnd-xPosStart));
        
    }// End method getDirection
}