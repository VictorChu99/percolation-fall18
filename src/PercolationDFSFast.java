import java.util.Arrays;

public class PercolationDFSFast extends PercolationDFS {
	//protected int[][] myGrid;
	//protected int myOpenCount;
	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n
	 *            is the size of the simulated (square) grid
	 */
	
	//constructor
	public PercolationDFSFast(int n) {
		super(n);
		
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if(!inBounds(row,col)) return;
		
		int count = 0;//I used an integer instead of boolean values of true and false
		
		if(row == 0 && inBounds(row,col))//special case of the top row
		{
			dfs(row,col);//case of top row. Mark as full
		}
		
		
		//now, we just need to check the four neighbors
		//call DFS if they are full
		else
		{
		if(inBounds(row,col-1))
			if(isFull(row,col-1)) 
			{
				count = 1;
			}
		
		if(inBounds(row,col+1))
			if(isFull(row,col+1)) 
			{
				
				count = 1;
			}
		
		if(inBounds(row+1,col))
			if(isFull(row+1,col))
			{
				
				count = 1;
			}
		
		if(inBounds(row-1,col))
			if(isFull(row-1,col)) 
			{
				
				count = 1;
			}
		}
		
		//if we one of neighbors are full, call dfs on the row,col cell
		if(count == 1) dfs(row,col);
	}

	
}
