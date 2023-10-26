package git_cisc191Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExploreButtonListener implements ActionListener
{
	private ProjectModel projectModel;
	private ProjectView projectView;
	private ExploreButton exploreButton;

	public ExploreButtonListener(ProjectModel model, ProjectView projectView, ExploreButton button)
	{
		//sets this projectmodel/projectview/explorebutton to the given project model, project view, button
		this.projectModel = model;
		this.projectView = projectView;
		this.exploreButton = button;
		
	}

	@Override
	//when an action takes place
	public void actionPerformed(ActionEvent e)
	{
		//sets the button message and event message to be blank
		String buttonMessage = "";
		String eventMessage = "";
		//checks if a treasure was found
		boolean treasureFound = projectModel.treasureAt(exploreButton.getRow(), exploreButton.getColumn());
		if(treasureFound) {
			//sets the button message to state, treasure found
			buttonMessage = "Treasure Found";
			//sets the event message to state what happened
			eventMessage = "Treasure Found, Total Treasure: " + projectModel.getTreasuresFound();
		//if treasure was not found
		} else {
			//creates a random number between 0 and 3
			int randomEvent = (int)(Math.random()*5);
			//switches based on the random number
			switch(randomEvent) {
				//if it is 0
				case 0:
					//if the amount of treasures the player has is greater than 0 and player doesnt have a shield
					if(projectModel.getTreasuresFound() > 0 && projectModel.getShieldCount() == 0) {
						//random number is generated between 1 and 11
						int randomMimicAmount = (int)(Math.random()*10) + 1;
						//sets treasures taken to the amount of treasures the player has
						int treasureTaken = projectModel.getTreasuresFound();
						//reduces the player treasures to zero
						projectModel.reduceTreasuresFound(treasureTaken);
						//mimic does damage based on random number + treasures taken
						projectModel.reduceHealth(treasureTaken + randomMimicAmount);
						//sets the button to state that it was a mimic
						buttonMessage = "Mimic";
						//sets the event message to state what happened
						eventMessage = "Mimic Encounter! All Treasures Taken, Health Reduced By: " + treasureTaken + " + " + randomMimicAmount;
						//if player has shield
					} else if(projectModel.getShieldCount()>0){
						//button states that it was a mimic
						buttonMessage = "Mimic";
						//states that mimic was blocked
						eventMessage = "Mimic Encounter! Mimic Blocked, Shield Lost!";
						//decrease shield count
						projectModel.decreaseSheildCount(1);
					}else {
						//if the player does not have more than 0 treasure
						//button message states that it is a mimic
						buttonMessage = "Mimic";
						//mimic does not attack since there is no treasure
						eventMessage = "Mimic Encounter! No Treasures Had";
					}
					//stops this case
					break;
				//if it is 1
				case 1:
					//sets a random number from 1 to 11 | 1 to 5
					int randomGoblinAmount = (int)(Math.random()*9) + 1;
					int randomGoblinLootAmount = (int)(Math.random()*4) + 1;
					if(projectModel.getShieldCount() == 0) {
					//reduces the player health by random number
					projectModel.reduceHealth(randomGoblinAmount);
					//player gains treaure based on a random number
					projectModel.increaseTreasuresFound(randomGoblinLootAmount);
					//Button is changed to state "goblin"
					buttonMessage = "Goblin";
					//sets the event message to state what happened
					eventMessage = "Goblin Encounter! Reduced Health By: " + randomGoblinAmount + ", Looted " + randomGoblinLootAmount + " Treasure";
					}else if(projectModel.getShieldCount() == 0) {
						//Button is changed to state "goblin"
						buttonMessage = "Goblin";
						eventMessage = "Goblin Encounter! Goblin Blocked, Shield Lost! Looted " + randomGoblinLootAmount + " Treasure";
						projectModel.increaseTreasuresFound(randomGoblinLootAmount);
						projectModel.decreaseSheildCount(1);
					}
					//stops this case
					break;
				//if it is 2
				case 2:
					//creates a random number between 15 and 25
					int randomTrapAmount = (int)(Math.random()*10) + 15;
					if(projectModel.getShieldCount() == 0) {
					//reduces health based off random number
					projectModel.reduceHealth(randomTrapAmount);
					//button states that it was a trap case
					buttonMessage = "Trap";
					//sets the event message to state what happened
					eventMessage = "Trap Encounter! Reduced Health By: " + randomTrapAmount;
					//stops this case
					}else if(projectModel.getShieldCount() > 0) {
						buttonMessage = "Trap";
						eventMessage = "Trap Encounter! Trap Blocked, Shield Lost!";
						projectModel.decreaseSheildCount(1);
					}
					break;
				//if it was 3
				case 3:
					//creates a random number between 5 and 25
					int randomPotionAmount = (int)(Math.random()*20) + 5;
					//restores player health based off that random number
					projectModel.increaseHealth(randomPotionAmount);
					//the button states potion was this case
					buttonMessage = "Potion";
					//sets the event message to state what happened
					eventMessage = "Potion Found! Increase Health By: " + randomPotionAmount;
					//stops this case
					break;
				//if it was case 4
				case 4:
					//adds 1 to shield count
					projectModel.setShieldCount(1);
					//the button says shield
					buttonMessage = "Shield";
					//the event says that you found a shield
					eventMessage = "Found A Shield!";
					//stops this case
					break;
			}
		} 
		
		//sets the explore button text to the button message
		exploreButton.setText(buttonMessage);
		//when clicked, a button is turned off
		exploreButton.setEnabled(false);
		//updates ui to display the event message
		projectView.updateUI(eventMessage);
	}

}
