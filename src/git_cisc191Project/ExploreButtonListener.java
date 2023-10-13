package git_cisc191Project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExploreButtonListener implements ActionListener
{
	private ProjectModel projectModel;
	private ProjectView projectView;
	private ExploreButton exploreButton;

	public ExploreButtonListener(ProjectModel model, ProjectView projectView,
			ExploreButton button)
	{
		this.projectModel = model;
		this.projectView = projectView;
		this.exploreButton = button;
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		boolean treasureFound = projectModel.treasureAt(exploreButton.getRow(), exploreButton.getColumn());
		if(treasureFound) {
			exploreButton.setText("Treasure Found");
		} else {
			exploreButton.setText("No Treasure");
		} //IF ELSE VARIOUS EVENTS
		exploreButton.setEnabled(false);
		projectView.updateUI();
	}

}
