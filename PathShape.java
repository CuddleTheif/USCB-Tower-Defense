import java.awt.Point;

/**
 * Possible Shapes of a Path
 * 
 * @author NecroTheif
 * @version 2014.11.11
 */
public enum PathShape{
    
    /**
     * Shape of a Horizontal Z
     */
    HORIZONTAL_Z{
        
        /**
         * Gets the junctions' postions of the Horizontal Z shape
         * 
         * @param startX   The starting postion of the shape on the x-axis
         * @param startY   The starting postion of the shape on the y-axis
         * @param endX     The ending postion of the shape on the x-axis
         * @param endY     The ending postion of the shape on the y-axis
         * @return         The junctions' postions of the shape
         */
        @Override
        public Point[] getPoints(int startX, int startY, int endX, int endY){
            
            /* Intilize the Array to hold the points */
                Point points[] = new Point[4];
                
                
            /* Set each point to each junction in the shape */
                points[0] = new Point(startX, startY);
                points[1] = new Point(endX, startY);
                points[2] = new Point(startX, endY);
                points[3] = new Point(endX, endY);
                
                
            /* Return the points */
                return points;
            
        }// End method getPoints
        
    },// End HORIZONTAL_Z PathShape
    
    /**
     * Shape of a Vertical Z
     */
    VERTICAL_Z{
        
        /**
         * Gets the junctions' postions of the Vertical Z shape
         * 
         * @param startX   The starting postion of the shape on the x-axis
         * @param startY   The starting postion of the shape on the y-axis
         * @param endX     The ending postion of the shape on the x-axis
         * @param endY     The ending postion of the shape on the y-axis
         * @return         The junctions' postions of the shape
         */
        @Override
        public Point[] getPoints(int startX, int startY, int endX, int endY){
            
            /* Intilize the Array to hold the points */
                Point points[] = new Point[4];
                
                
            /* Set each point to each junction in the shape */
                points[0] = new Point(startX, startY);
                points[1] = new Point(startX, endY);
                points[2] = new Point(endX, startY);
                points[3] = new Point(endX, endY);
                
                
            /* Return the points */
                return points;
            
        }// End method getPoints
        
    },// End VERTICAL_Z PathShape
    
    /**
     * Shape of a Horizontal S
     */
    HORIZONTAL_S{
        
        /**
         * Gets the junctions' postions of the Horizontal S shape
         * 
         * @param startX   The starting postion of the shape on the x-axis
         * @param startY   The starting postion of the shape on the y-axis
         * @param endX     The ending postion of the shape on the x-axis
         * @param endY     The ending postion of the shape on the y-axis
         * @return         The junctions' postions of the shape
         */
        @Override
        public Point[] getPoints(int startX, int startY, int endX, int endY){
            
            /* Intilize the Array to hold the points */
                Point points[] = new Point[6];
                
                
            /* Calculate and store the shape's height */
                int height = endY-startY;
                
            /* Set each point to each junction in the shape */
                points[0] = new Point(startX, startY);
                points[1] = new Point(endX, startY);
                points[2] = new Point(endX, startY+height/2);
                points[3] = new Point(startX, startY+height/2);
                points[4] = new Point(startX, endY);
                points[5] = new Point(endX, endY);
                
                
            /* Return the points */
                return points;
            
        }// End method getPoints
        
    },// End HORIZONTAL_S PathShape
    
    /**
     * Shape of a Vertical S
     */
    VERTICAL_S{
        
        /**
         * Gets the junctions' postions of the Vertical S shape
         * 
         * @param startX   The starting postion of the shape on the x-axis
         * @param startY   The starting postion of the shape on the y-axis
         * @param endX     The ending postion of the shape on the x-axis
         * @param endY     The ending postion of the shape on the y-axis
         * @return         The junctions' postions of the shape
         */
        @Override
        public Point[] getPoints(int startX, int startY, int endX, int endY){
            
            /* Intilize the Array to hold the points */
                Point points[] = new Point[6];
                
                
            /* Calculate and store the shape's width */
                int width = endX-startX;
                
            /* Set each point to each junction in the shape */
                points[0] = new Point(startX, startY);
                points[1] = new Point(startX, endY);
                points[2] = new Point(startX+width/2, endY);
                points[3] = new Point(startX+width/2, startY);
                points[4] = new Point(endX, startY);
                points[5] = new Point(endX, endY);
                
                
            /* Return the points */
                return points;
            
        }// End method getPoints
        
    },// End VERTICAL_S PathShape
    
    /**
     * Shape of a Horizontal bolt.
     */
    HORIZONTAL_BOLT{
        
        /**
         * Gets the junctions' postions of the Horizontal Bolt shape
         * 
         * @param startX   The starting postion of the shape on the x-axis
         * @param startY   The starting postion of the shape on the y-axis (must be = endY)
         * @param endX     The ending postion of the shape on the x-axis
         * @param endY     The ending postion of the shape on the y-axis (must be = startY)
         * @return         The junctions' postions of the shape
         */
        @Override
        public Point[] getPoints(int startX, int startY, int endX, int endY){
            
            /* Intilize the Array to hold the points */
                Point points[] = new Point[10];
                
                
            /* Calculate and store the shape's width */
                int width = endX - startX;
                
            /* Set each point to each junction in the shape */
                points[0] = new Point(startX, startY);
                points[1] = new Point(startX+width*2/5, startY);
                points[2] = new Point(startX+width*2/5, startY+width/5);
                points[3] = new Point(startX+width/5, startY+width/5);
                points[4] = new Point(startX+width/5, startY+width*2/5);
                points[5] = new Point(startX+width*4/5, startY+width*2/5);
                points[6] = new Point(startX+width*4/5, startY+width/5);
                points[7] = new Point(startX+width*3/5, startY+width/5);
                points[8] = new Point(startX+width*3/5, startY);
                points[9] = new Point(endX, endY);
                
                
            /* Return the points */
                return points;
            
        }// End method getPoints
        
    },// End HORIZONTAL_BOLT PathShape
    
    /**
     * Shape of a Vertical bolt.
     */
    VERTICAL_BOLT{
        
        /**
         * Gets the junctions' postions of the Vertical Bolt shape
         * 
         * @param startX   The starting postion of the shape on the x-axis (must be = endX)
         * @param startY   The starting postion of the shape on the y-axis
         * @param endX     The ending postion of the shape on the x-axis (must be = startX)
         * @param endY     The ending postion of the shape on the y-axis
         * @return         The junctions' postions of the shape
         */
        @Override
        public Point[] getPoints(int startX, int startY, int endX, int endY){
            
            /* Intilize the Array to hold the points */
                Point points[] = new Point[10];
                
                
            /* Calculate and store the shape's height */
                int height = endY - startY;
                
            /* Set each point to each junction in the shape */
                points[0] = new Point(startX, startY);
                points[1] = new Point(startX, startY+height*2/5);
                points[2] = new Point(startX+height/5, startY+height*2/5);
                points[3] = new Point(startX+height/5, startY+height/5);
                points[4] = new Point(startX+height*2/5, startY+height/5);
                points[5] = new Point(startX+height*2/5, startY+height*4/5);
                points[6] = new Point(startX+height/5, startY+height*4/5);
                points[7] = new Point(startX+height/5, startY+height*3/5);
                points[8] = new Point(startX, startY+height*3/5);
                points[9] = new Point(endX, endY);
                
                
            /* Return the points */
                return points;
            
        }// End method getPoints
        
    };// End VERTICAL_BOLT PathShape
    
    
    /**
     * Gets the junctions' postions of the shape
     * 
     * @param startX   The starting postion of the shape on the x-axis
     * @param startY   The starting postion of the shape on the y-axis
     * @param endX     The ending postion of the shape on the x-axis
     * @param endY     The ending postion of the shape on the y-axis
     * @return         The junctions' postions of the shape
     */
    public abstract Point[] getPoints(int startX, int startY, int endX, int endY);
    
}// End enum PathShape
