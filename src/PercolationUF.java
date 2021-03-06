import java.util.*;

public class PercolationUF implements IPercolate{
	
	//Union stores what cells are in the same set
	//adjacent cells are added to the same union
	//open a cell, and connect to other cells
	
	//open a cell, connect it if open
	//if open, in top or bottom, conenct to vtop or vbottom
	//if vtop is connected to vbottom the system percolates
	
	protected boolean[][] myGrid;//boolean grid
	protected int myOpenCount;
	protected IUnionFind myFinder;
	
	private final int VTOP;
	private final int VBOTTOM;
	
	//constructor
	public PercolationUF( int size, IUnionFind finder) //should I switch the order of the parameters?
	{
		
		myGrid = new boolean[size][size];//create our grid
		myOpenCount = 0;
		
		//important for checking if the system percolates- check if VTOp and VBOTTOM in the same set
		VTOP = size * size;
		VBOTTOM = size * size + 1;
		finder.initialize(size*size + 2);
		myFinder = finder;//can't do myFinder = finder.in
//		for (boolean[] row : myGrid)
//		{
//			Arrays.fill(row, false);
//		}
	}

	@Override
	//open cells are set to true. 
	public void open(int row, int col) {
		
		
		
		// TODO Auto-generated method stub
		if (!inBounds(row,col)) {//check exceptions first
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		if (myGrid[row][col] != false)
			return;
		myOpenCount += 1;//increase our open count
		myGrid[row][col] = true;
		
		//check the four neighbors. Essentially, we then add these to a union together
				if(inBounds(row+1,col)) {
				if( myGrid[row+1][col] == true) {
					myFinder.union(indexValue(row,col),indexValue(row+1,col));
				}
				}
				
				if(inBounds(row-1,col)) {
					if( myGrid[row-1][col] == true) {
						myFinder.union(indexValue(row,col),indexValue(row-1,col));
					}
					}
					
				
				if(inBounds(row,col+1)) {
					if( myGrid[row][col+1] == true) {
						myFinder.union(indexValue(row,col),indexValue(row,col+1));
					}
					}
					
				
				if(inBounds(row,col-1)) {
					if( myGrid[row][col-1] == true) {
						myFinder.union(indexValue(row,col),indexValue(row,col-1));
					}
					}
		
		
		//special case of being connected to top or bottom
		if(row == 0)
			myFinder.union(indexValue(row,col),VTOP);
		
		if(row == myGrid.length-1)
			myFinder.union(indexValue(row,col),VBOTTOM);
		
			
		
	
	}

	@Override//check if the cell is open or not
	public boolean isOpen(int row, int col) {
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds", row,col));
		}
		return myGrid[row][col];
	}

	@Override//checks if the cell is full or not
	public boolean isFull(int row, int col) {
		// TODO Auto-generated method stub
		if (!inBounds(row,col)) {
			throw new IndexOutOfBoundsException(
					String.format("(%d,%d) not in bounds" , row,col));
		}
		
		
		//use our indexValue to find the numbers so we can connected
		return myFinder.connected(indexValue(row,col),VTOP);
	
	}

	@Override
	public boolean percolates() {
		// TODO Auto-generated method stub
		if(myFinder.connected(VTOP, VBOTTOM))return true;//if VTOP and VBOTTOM are connected- the system percolates
		
		return false;
	}

	@Override
	public int numberOfOpenSites() {
		// TODO Auto-generated method stub
		return myOpenCount;
	}
	
	public int indexValue(int row, int col)//we need the indexValue and just have quick method,
											//since we use these index values so much
	{
		return row * myGrid.length + col;
	}
	
	protected boolean inBounds(int row, int col) {
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}
}
