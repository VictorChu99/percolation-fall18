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
	public PercolationDFSFast(int n) {
		super(n);
		
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if(!inBounds(row,col)) return;
		
		int count = 0;
		
		if(row == 0 && inBounds(row,col))
		{
			dfs(row,col);//case of top row. Mark as full
		}
		
		
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
		
		if(count == 1) dfs(row,col);
	}

	
}
