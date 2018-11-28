import java.util.Arrays;
import java.util.*;

public class PercolationBFS extends PercolationDFSFast{
	public PercolationBFS(int n) {
		super(n);
		myGrid = new int[n][n];
		myOpenCount = 0;
		for (int[] row : myGrid)
			Arrays.fill(row, BLOCKED);
	}
	
	@Override
	protected void dfs(int row, int col) {//breadth first search looks at children first, hence the name breadth
		
		Queue<Integer> que= new LinkedList<>();
		myGrid[row][col] = FULL;
		
		
		if (! inBounds(row,col)) return;
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;
		
		que.add(row*myGrid.length + col);
		
		while(que.size() != 0)
		{//queue is first in, first out. keeps going until no more neighbors are checked
			
			int newBlock = que.remove();//dequeue for breadth of search
			
			//need to define the new rows and columns. If we just use row and col, we wouldn't be checking the entire block
			//for recursive solution, this isn't a problem as each iteration is just moving through and checking each cell and the adjacent ones. 
			//then, those adjacent cells are called recursively.
			int colTemp = newBlock%myGrid.length;
			int rowTemp = newBlock/myGrid.length;
			
			
			//add it to the queue, once it has been filled. Need to check its neighbors
			if(isOpen(rowTemp-1,colTemp)) que.add((rowTemp-1)*myGrid.length + colTemp);
			
			if(isOpen(rowTemp+1,colTemp)) que.add((rowTemp+1)*myGrid.length + colTemp);
			
			if(isOpen(rowTemp,colTemp+1)) que.add((rowTemp)*myGrid.length + colTemp+1);
			
			if(isOpen(rowTemp,colTemp-1)) que.add((rowTemp)*myGrid.length + colTemp-1);
		}
	}
	
	
}
