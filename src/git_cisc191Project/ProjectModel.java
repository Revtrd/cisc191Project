package git_cisc191Project;
import java.util.Random;

public class ProjectModel
{
	public static int DIMENSION = 10;
	private boolean[][] grid = new boolean[DIMENSION][DIMENSION];
	private int healthRemaining = 100;
	private int treasureRemaining = 25;
	public ProjectModel()
	{
		Random randomNumberGenerator = new Random();
		for (int treasureCounter = 0; treasureCounter < treasureRemaining; treasureCounter++)
		{
			int row, column;
			do
			{
				row = randomNumberGenerator.nextInt(DIMENSION);
				column = randomNumberGenerator.nextInt(DIMENSION);
			} while (grid[row][column]);
			grid[row][column] = true;
		}
	}
	
	public boolean treasureAt(int row, int column)
	{
		boolean treasureFound = grid[row][column];
		if (treasureFound)
		{
			treasureRemaining--;
		}
		return treasureFound;
	}
	
	public int getHealthRemaining()
	{
		return healthRemaining;
	}
	
	public int getTreasureRemaining()
	{
		return treasureRemaining;
	}
	
	public boolean dungeonWin()
	{
		return healthRemaining <= 0 && treasureRemaining > 0;
	}
	
	public boolean playerWins()
	{
		return treasureRemaining == 0;
	}
	
}
