package Controller;
import java.io.IOException;

import BBridge.Person;
import BBridge.Skill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import main.BB;
import model.BBModel;
import model.ViewTransitionalModel;

public class JobController
{
	BBModel model;
	public void setModel(BBModel newModel)
	 {
	    model = newModel;
	    skilllist.setItems(model.getJobskilllist());
	    skilllist.setEditable(false);
	    appliedpersonlist.setItems(model.getJobapplist());
	    descriptionlabel.textProperty().bindBidirectional(model.getJobdescription());
	    nameLabel.textProperty().bindBidirectional(model.getJobname());
	    companylabel.textProperty().bindBidirectional((model.getJobCompany()));
	 }

    @FXML
    private ListView<Person> appliedpersonlist;

    @FXML
    private Label companylabel;

    @FXML
    private Label descriptionlabel;

    @FXML
    private Button editbutton;

    @FXML
    private Label nameLabel;

    @FXML
    private ListView<Skill> skilllist;

    @FXML
    void editbuttononclick(ActionEvent event) {
    	//skilllist.setEditable(true);
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/MainView.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,model); 
    	    cont.setModel(vm);
    	    vm.showjobeditpage();
    	       
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
