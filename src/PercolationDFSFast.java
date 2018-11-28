import java.util.Arrays;

public class PercolationDFSFast extends PercolationDFS {
	protected int[][] myGrid;
	protected int myOpenCount;
	/**
	 * Initialize a grid so that all cells are blocked.
	 * 
	 * @param n
	 *            is the size of the simulated (square) grid
	 */
	public PercolationDFSFast(int n) {
		super(n);
		myGrid = new int[n][n];
		myOpenCount = 0;
		for (int[] row : myGrid)
			Arrays.fill(row, BLOCKED);
	}
	
	@Override
	protected void updateOnOpen(int row, int col) {
		if(!inBounds(row,col)) return;
		
		if(row == 0) 
		{
			dfs(row,col);//case of top row. Mark as full
		}
		else
		{
		if(inBounds(row,col-1))
			if(isFull(row,col-1)) dfs(row,col);
		
		if(inBounds(row,col+1))
			if(isFull(row,col+1)) dfs(row,col);
		
		if(inBounds(row+1,col))
			if(isFull(row+1,col)) dfs(row,col);
		
		if(inBounds(row-1,col))
			if(isFull(row-1,col)) dfs(row,col);
		}
	}

	
}
