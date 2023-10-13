package git_cisc191Project;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

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
	
	public ProjectView(ProjectModel model) {
		this.projectModel = model;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pack();
		setVisible(true);
		setTitle("The Grand Hunt");
		setLayout(new BorderLayout());
		JPanel gridPanel = new JPanel();
		JPanel instructionsPanel = new JPanel();
		gridPanel.setLayout(new GridLayout(ProjectModel.DIMENSION, ProjectModel.DIMENSION));
		for(int row = 0; row < ProjectModel.DIMENSION; row++) {
			//loops for the length of the gonefishing models columns
			for(int col = 0; col < ProjectModel.DIMENSION; col++) {
				//sets the fishing button to the given row and column
				ExploreButton button = new ExploreButton(row,col);
				//sets  the size of the button
				button.setPreferredSize(new Dimension(50,50));
				//adds an action listener to this button
				button.addActionListener(new ExploreButtonListener(model, this, button));
				//adds this to the grid panel
				gridPanel.add(button);
			}
		}
		healthLabel = new JLabel("Health Remaining: " + model.getHealthRemaining());
		treasureLabel = new JLabel("Treasures Remaining: " + model.getTreasureRemaining());
		instructionsLabel = new JLabel("Click on the buttons to explore the dungeon.");
		messageLabel = new JLabel("Game Message: ");
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		topPanel.add(healthLabel);
		topPanel.add(treasureLabel);
		topPanel.add(messageLabel);
		instructionsPanel.add(instructionsLabel);
		add(topPanel, BorderLayout.NORTH);
		add(gridPanel, BorderLayout.CENTER);
		add(instructionsPanel, BorderLayout.WEST);
	}
	
	public static void main(String[] args)
	{
		new ProjectView(new ProjectModel());
	}
	
	public void updateUI() {
		healthLabel.setText("Health Remaining: " + projectModel.getHealthRemaining());
		treasureLabel.setText("Treasures Remaining: " + projectModel.getTreasureRemaining());
		if(projectModel.dungeonWin()) {
			messageLabel.setText("Game Message: Out of Health, You Lose!");
		}
		else if(projectModel.playerWins()) {
			messageLabel.setText("Game Message: All Treasures Found, You Win!");
		}
	}
}
