package git_cisc191Project;
import java.util.Random;

public class ProjectModel
{
	public static int DIMENSION = 10;
	private boolean[][] grid = new boolean[DIMENSION][DIMENSION];
	private int healthRemaining = 100;
	private int treasureRemaining = 25;
	private int treasuresFound = 0;
	private int shieldCount;
	
	public ProjectModel()
	{
		//creates a random number generator
		Random randomNumberGenerator = new Random();
		//loops for the amount of treasures remaining
		for (int treasureCounter = 0; treasureCounter < treasureRemaining; treasureCounter++)
		{
			//creates an int row and column
			int row, column;
			//do while loop
			do
			{
				//sets a random number for row
				row = randomNumberGenerator.nextInt(DIMENSION);
				//sets a random number for column
				column = randomNumberGenerator.nextInt(DIMENSION);
			//loops for the length of the row and column
			} while (grid[row][column]);
			//makes these locations have treasure
			grid[row][column] = true;
		}
		
	}
	//sets whether treasure exist at a given row and column
	public boolean treasureAt(int row, int column)
	{
		boolean treasureFound = grid[row][column];
		//if treasure is found
		if (treasureFound)
		{
			//random number between 1 and 11
			int randomAmount = (int)(Math.random()*10) + 1;
			//sets treasure found to itself and that random number
			treasuresFound += randomAmount;
			//sets treasures remaining to one less than before
			treasureRemaining--;
		}
		//returns true or false
		return treasureFound;
	}
	
	public int getHealthRemaining()
	{
		//returns health
		return healthRemaining;
	}
	
	public int getTreasureRemaining()
	{
		//returns treasure remaining
		return treasureRemaining;
	}
	
	public int getShieldCount()
	{
		//returns shield count
		return shieldCount;
	}
	
	public boolean dungeonWin()
	{
		//determines if the dungeon won
		return healthRemaining <= 0 && treasureRemaining > 0;
	}
	
	public boolean playerWins()
	{
		//determines if the player won
		return treasureRemaining == 0;
	}

	public void reduceHealth(int amount)
	{
		//sets health minus the amount given
		healthRemaining -= amount;
	}

	public void increaseHealth(int amount)
	{
		//sets health plus the amount given
		healthRemaining += amount;
	}
	
	public int getTreasuresFound()
	{
		//returns the amount of treasure found
		return treasuresFound;
	}
	
	public void reduceTreasuresFound(int amount) {
		//sets the treasure found minus the amount
		treasuresFound -= amount;
		//if treasure is less than 0
		if(treasuresFound < 0) {
			//treasures found is set to 0
			treasuresFound = 0;
		}
	}
	
	public void increaseTreasuresFound(int amount) {
		//increases treasure by the amount given
		treasuresFound += amount;
	}
	
	public void setShieldCount(int amount)
	{
		shieldCount += amount;
	}
	
	public void decreaseSheildCount(int amount)
	{
		shieldCount -= amount;
		if(shieldCount < 0) {
			shieldCount = 0;
		}
	}
}
