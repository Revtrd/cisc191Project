package git_cisc191Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ProjectView extends JFrame
{
	private ProjectModel projectModel;
	private JLabel healthLabel;
	private JLabel treasureLabel;
	private JLabel messageLabel;
	private JLabel instructionsLabel;
	private JLabel treasureFoundLabel;
	private ExploreButton[][] exploreButtons = new ExploreButton[ProjectModel.DIMENSION][ProjectModel.DIMENSION];
	private JLabel goblinLegendLabel;
	private JLabel mimicLegendLabel; 
	private JLabel trapLegendLabel;
	private JLabel potionLegendLabel;
	private JLabel shieldLegendLabel;
	private JLabel shieldInfoLabel;
	private JLabel emptyLegendLabel;
	
	public ProjectView(ProjectModel model) {
		//sets this model to model
		this.projectModel = model;
		//creates a default opperation to close the frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//packs the game on launch
		pack();
		//makes the game visible
		setVisible(true);
		//sets the title
		setTitle("The Grand Hunt");
		//sets the layout
		setLayout(new BorderLayout());
		//creates a new JPanel, grid, instructions, and legend panel
		JPanel gridPanel = new JPanel();
		JPanel instructionsPanel = new JPanel();
		JPanel legendPanel = new JPanel();
		//sets the layout for the grid panel to be the size of the project model's dimensions
		gridPanel.setLayout(new GridLayout(ProjectModel.DIMENSION, ProjectModel.DIMENSION));
		//sets layout for the legendPanel
		legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
		//loops for the length of the model's rows and columns
		for(int row = 0; row < ProjectModel.DIMENSION; row++) {
			//loops for the length of the gonefishing models columns
			for(int col = 0; col < ProjectModel.DIMENSION; col++) {
				//sets the explore button to the given row and column
				ExploreButton button = new ExploreButton(row,col);
				//sets  the size of the button
				button.setPreferredSize(new Dimension(50,50));
				//adds an action listener to this button
				button.addActionListener(new ExploreButtonListener(model, this, button));
				//adds this to the grid panel
				gridPanel.add(button);
				//sets the button to the explore button of the row and column
				exploreButtons[row][col] = button;
			}
		}
		//creates a health label JLabel
		healthLabel = new JLabel("Health Remaining: " + model.getHealthRemaining());
		//creates a treasure label
		treasureLabel = new JLabel("Treasures Remaining: " + model.getTreasureRemaining());
		//creates an instructions label
		instructionsLabel = new JLabel("Click on the buttons to explore the dungeon.");
		//creates a message label
		messageLabel = new JLabel("Game Message: ");
		//sets the message label's size
		messageLabel.setPreferredSize(new Dimension(500, 50));
		//sets a treasure found label
		treasureFoundLabel = new JLabel("Treasures Found: " + model.getTreasuresFound());
		//sets a label for each event
		goblinLegendLabel = new JLabel("Goblin - Decrease HP by 1-10, Gain 1-5 Treasure");
		mimicLegendLabel = new JLabel("Mimic - Does Damage According to Your Treasures Found");
		trapLegendLabel = new JLabel("Trap - Does 15-25 Damage");
		potionLegendLabel = new JLabel("Potion - Restores 5-25 HP");
		shieldLegendLabel = new JLabel("Shields - Negate One Encounter's Damage");
		shieldInfoLabel = new JLabel("Shields: " + model.getShieldCount());
		emptyLegendLabel = new JLabel("Empty - Nothing Happens, Continue");
		//creates a JPanel called toppanel and infopanel
		JPanel topPanel = new JPanel();
		JPanel infoPanel = new JPanel();
		//sets the top panel to have a flow layout
		topPanel.setLayout(new FlowLayout());
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		//adds the specified labels to the given JPanels
		topPanel.add(healthLabel);
		topPanel.add(treasureLabel);
		infoPanel.add(messageLabel);
		infoPanel.add(shieldInfoLabel);
		topPanel.add(treasureFoundLabel);
		instructionsPanel.add(instructionsLabel);
		legendPanel.add(goblinLegendLabel);
		legendPanel.add(mimicLegendLabel);
		legendPanel.add(trapLegendLabel);
		legendPanel.add(potionLegendLabel);
		legendPanel.add(shieldLegendLabel);
		legendPanel.add(emptyLegendLabel);
		
		
		//sets the position for each jlabel
		add(topPanel, BorderLayout.NORTH);
		add(gridPanel, BorderLayout.CENTER);
		add(instructionsPanel, BorderLayout.WEST);
		add(infoPanel, BorderLayout.EAST);
		add(legendPanel, BorderLayout.SOUTH);
	}
	
	public static void main(String[] args)
	{
		//creates a new instance of the project model
		new ProjectView(new ProjectModel());
	}
	
	public void updateUI(String eventMessage) {
		//updates the ui each time a player interacts with the game
		healthLabel.setText("Health Remaining: " + projectModel.getHealthRemaining());
		treasureLabel.setText("Treasures Remaining: " + projectModel.getTreasureRemaining());
		treasureFoundLabel.setText("Treasures Found: " + projectModel.getTreasuresFound());
		messageLabel.setText("Game Message: " + eventMessage);
		shieldInfoLabel.setText("Shields: " + projectModel.getShieldCount());
		//calls out if the player loses
		if(projectModel.dungeonWin()) {
			messageLabel.setText("Game Message: Out of Health, You Lose!");
		}
		//calls out if the player wins
		else if(projectModel.playerWins()) {
			messageLabel.setText("Game Message: No More Treasures Remaining, You Win!");
		}
		//whether the player wins or loses, the remaining buttons are turned off
		if(projectModel.dungeonWin() || projectModel.playerWins()) {
			for(int row = 0; row < ProjectModel.DIMENSION; row++) {
				for(int col = 0; col < ProjectModel.DIMENSION; col++) {
					exploreButtons[row][col].setEnabled(false);
				}
			}
		}
	}
}
