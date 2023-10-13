package git_cisc191Project;

import javax.swing.JButton;

public class ExploreButton extends JButton
{
	private int row;
	private int column;

	public ExploreButton(int row, int column)
	{
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
}
