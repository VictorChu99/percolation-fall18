import java.util.Arrays;
import java.util.*;

public class PercolationBFS extends PercolationDFSFast {
	public PercolationBFS(int n) {
		super(n);

	}

	@Override
	protected void dfs(int row, int col) {// breadth first search looks at children first, hence the name breadth

		
		Queue<Integer> que = new LinkedList<>();
		
		if (!inBounds(row, col))//just check our exceptions
			return;
		// full or NOT open, don't process
		if (isFull(row, col) || !isOpen(row, col))
			return;

		myGrid[row][col] = FULL;
		que.add(row * myGrid.length + col);//add to our queue first

		while (que.size() != 0) {// queue is first in, first out. keeps going until no more neighbors are checked

			int newBlock = que.remove();// dequeue for breadth of search
			

			// need to define the new rows and columns. If we just use row and col, we
			// wouldn't be checking the entire block
			// for recursive solution, this isn't a problem as each iteration is just moving
			// through and checking each cell and the adjacent ones.
			// then, those adjacent cells are called recursively.
			int colTemp = newBlock % myGrid.length;
			int rowTemp = newBlock / myGrid.length;

			// add it to the queue, once it has been filled. Need to check its neighbors
			if (inBounds(rowTemp - 1, colTemp))
				if (isOpen(rowTemp - 1, colTemp) && !isFull(rowTemp - 1, colTemp)) {
					myGrid[rowTemp - 1][colTemp] = FULL;
					que.add((rowTemp - 1) * myGrid.length + colTemp);
				}

			if (inBounds(rowTemp + 1, colTemp))
				if (isOpen(rowTemp + 1, colTemp) && !isFull(rowTemp + 1, colTemp)) {
					myGrid[rowTemp + 1][colTemp] = FULL;
					que.add((rowTemp + 1) * myGrid.length + colTemp);
				}

			if (inBounds(rowTemp, colTemp + 1))
				if (isOpen(rowTemp, colTemp + 1) && !isFull(rowTemp, colTemp + 1)) {
					myGrid[rowTemp][colTemp + 1] = FULL;
					que.add((rowTemp) * myGrid.length + colTemp + 1);
				}

			if (inBounds(rowTemp, colTemp - 1))
				if (isOpen(rowTemp, colTemp - 1) && !isFull(rowTemp, colTemp - 1)) {
					myGrid[rowTemp][colTemp - 1] = FULL;
					que.add((rowTemp) * myGrid.length + colTemp - 1);
				}
		}
	}

}
