package git_cisc191Project;

import javax.swing.JButton;

public class ExploreButton extends JButton
{
	private int row;
	private int column;

	public ExploreButton(int row, int column)
	{
		//sets this row/column to the given row and column
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		//gets the row
		return row;
	}
	
	public int getColumn() {
		//gets the column
		return column;
	}
}
