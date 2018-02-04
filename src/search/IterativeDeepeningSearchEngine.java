package search;
import java.awt.Dimension;


public class IterativeDeepeningSearchEngine extends AbstractSearchEngine {

	public IterativeDeepeningSearchEngine(Maze maze) {
		super(maze);
		long startTimeMs = System.currentTimeMillis( );
		iterativeDeepeningSearch();
        System.out.println("IDS time: " + (System.currentTimeMillis() - startTimeMs));
		// TODO Auto-generated constructor stub
	}

	
	Dimension cutoff = new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE);
	Dimension failure = new Dimension(Integer.MIN_VALUE, Integer.MIN_VALUE);
	
	public void iterativeDeepeningSearch() {
		
		for (int depth = 0; depth < Integer.MAX_VALUE; depth++) {
			System.out.println("Depth: " + depth);
			Dimension result = depthLimitedSearch(startLoc, depth);
			
			if (result.equals(failure)) {
				System.out.println("Not found");
				return;
			}
			if(!result.equals(cutoff)) {
				System.out.println("Found at depth " + depth);
				maxDepth = depth;
				 Dimension[] path = this.getPath(); 
		            for (int i = 1; i < (path.length); i ++){
		            	int x = path[i].width; 
		            	int y = path[i].height; 
		            	maze.setValue(x, y, (short)(path.length - i + 1)); 
		            }
				return;
			}
		}
	}

	public Dimension depthLimitedSearch(Dimension current, int limit) {
		
	   if (current.equals(goalLoc)) {
		   
		   return current;
	   }
	   else if (limit == 0)
		   return cutoff;
	   else {
	       boolean cutoffOccurred = false;
		   Dimension [] moves = getPossibleMoves(current);
		   for (int i = 0; i < moves.length; i++) {
			   if (moves[i] == null) 
			        break;
			   else {
					   Dimension result = depthLimitedSearch(moves[i], limit - 1);
					   if (result.equals(cutoff))
						   cutoffOccurred = true;
					   else if (!result.equals(failure)) {
						   searchPath[limit] = current;
						   return result;
					   }
			   }
		   }
		   if (cutoffOccurred)
			   return cutoff;
		   else
			   return failure;
	   }
	}
}
