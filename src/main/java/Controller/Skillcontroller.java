package Controller;

import model.BBModel;
import model.ViewTransitionalModel;

import java.io.IOException;

import BBridge.Person;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import main.BB;

public class Skillcontroller {

	
	BBModel model;

    @FXML
    private Label Description;

    @FXML
    private Button Editbutton;

    @FXML
    private Label Namefield;

    @FXML
    private Label desclabel;

    @FXML
    private ListView<Person> skilledperson;

    @FXML
    private Label skinameLabel;
	public void setModel(BBModel newModel)
	 {
		
	    model = newModel;

	    //joblist.set
	   // Bindings.bindBidirectional(companyLabel.textProperty(),model.getCompany());
	   
	    skinameLabel.textProperty().bindBidirectional((model.getSkillname()));
	    desclabel.textProperty().bindBidirectional(model.getSkilldesc());
	    skilledperson.setItems(model.getSkilled());
	  }
    @FXML
    void handleEditProfileButtonAction(ActionEvent event) {
    	
    	
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/MainView.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,model); 
    	    cont.setModel(vm);
    	    vm.showskilleditpage();
    	       
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
