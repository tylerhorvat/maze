package search;

import java.awt.Dimension;

/**
 * Class Maze - private class for representing search space 
 * as a two-dimensional maze
 * Modified by Li Xu on Jan 22, 2010
 */
public class Maze {
    public static short OBSTICLE = -1;
    public static short START_LOC_VALUE = -2;
    public static short GOAL_LOC_VALUE = -3;
    

    
    private int width = 0;
    private int height = 0;
    public Dimension startLoc = new Dimension();
    public Dimension goalLoc  = new Dimension();
    /**
     * The maze (or search space) data is stored as a short integer rather than
     * as a boolean so that bread-first style searches can use the array to store
     * search depth. A value of -1 indicates a barrier in the maze.
     */
    private short [][]maze;
    public Maze(int width, int height) {
    	
    	if (MyMazes.DEBUG)
            System.out.println("New maze of size " + width + " by " + height);
    	
        this.width = width;
        this.height = height;
        maze = new short[width+2][height+2];
        for (int i=0; i<width+2; i++) {
            for (int j=0; j<height+2; j++) {
                maze[i][j] = 0;
            }
        }
        for (int i=0; i<height+2; i++) {
            maze[0][i] = maze[width+1][i] = OBSTICLE;
        }
        for (int i=0; i<width+2; i++) {
            maze[i][0] = maze[i][height+1] = OBSTICLE;
        }
        /**
         * Randomize the maze by putting up arbitrary obstacles
         */
        int max_obsticles = (width * height) / 3;
        for (int i=0; i<max_obsticles; i++) {
            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);
            setValue(x, y, OBSTICLE);
        }
        /**
         * Specify the starting location
         */
         startLoc.width = 0;
         startLoc.height = 0;
       
         setValue(0, 0, START_LOC_VALUE);
        /**
         * Specify the goal location
         */
        goalLoc.width = width - 1;
        goalLoc.height = height - 1;
        setValue(width - 1, height - 1, GOAL_LOC_VALUE);
    }
    
    public Maze(Maze maze){
    	this(maze.getWidth(), maze.getHeight());

        //int max_obsticles = (width * height) / 3;
        for (int i=1; i<width+2; i++) {
            for (int j=1; j<height+2; j++) {
                this.maze[i][j] = maze.getValue(i-1, j-1);
            }
        }
    }
  
    synchronized public short getValue(int x, int y) { return maze[x+1][y+1]; }
    synchronized public void setValue(int x, int y, short value) { maze[x+1][y+1] = value; }
    public int getWidth() { return width; }
    public int getHeight() { return height; }
     
}
