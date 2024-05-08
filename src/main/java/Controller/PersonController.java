package Controller;

import java.io.IOException;

import BBridge.JobPosting;
import BBridge.Project;
import BBridge.Skill;
//import javafx.beans.binding.Bindings;
//import javafx.beans.property.Property;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
//import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import main.BB;
import model.BBModel;
import model.ViewTransitionalModel;

public class PersonController
{
	
	BBModel model;
	public void setModel(BBModel newModel)
	 {
		
	    model = newModel;
	    //companyLabel.setText(model.getCompany());
	    joblist.setItems(model.getJoblist());
	    projectlist.setItems(model.getProlist());
	    skillslist.setItems(model.getSkilllist());
	    //joblist.set
	   // Bindings.bindBidirectional(companyLabel.textProperty(),model.getCompany());
	    companyLabel.textProperty().bindBidirectional((model.getCompany()));
	    nameLabel.textProperty().bindBidirectional(model.getName());
	    occupatationlabel.textProperty().bindBidirectional(model.getOccupatation());
    	///skillslist.setEditable(false);
    //	joblist.setEditable(false);
    	//projectlist.setEditable(false);
	  }
    @FXML
    private Button Editbutton;

    @FXML
    private Label companyLabel;

    @FXML
    private ListView<JobPosting> joblist;

    @FXML
    private Label nameLabel;

    @FXML
    private Label occupatationlabel;
 
    @FXML
    private ListView<Project> projectlist;

    @FXML
    private ListView<Skill> skillslist;

    @FXML
    void handleEditProfileButtonAction(ActionEvent event) {
    	projectlist.setEditable(true);
    	skillslist.setEditable(true);
    	joblist.setEditable(true);
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/MainView.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,model); 
    	    cont.setModel(vm);
    	    vm.showpersoneditpage();
    	       
    	    Scene s = new Scene(view);
    	    model.stage.setScene(s);
    	    model.stage.show();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

}
