package Controller;

import java.io.IOException;

import BBridge.Person;
//import BBridge.Skill;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import main.BB;
import model.BBModel;
import model.ViewTransitionalModel;

public class Skilleditcontroller {

	
	BBModel model;
	BBModel edit;
    @FXML
    private Label Description;

    @FXML
    private Label Namefield;

    @FXML
    private Button addskilledperson;

    @FXML
    private Button cancelbutton;

    @FXML
    private Label desclabel;

    @FXML
    private TextField editskidesc;

    @FXML
    private Button removeskilledperson;

    @FXML
    private Button savebutton;

    @FXML
    private ListView<Person> skilledperson;

    @FXML
    private ListView<Person> restperson;

    @FXML
    private Label skinameLabel;

    
	public void setModel(BBModel newModel)
	 {
		
	    model = newModel;
	    edit = new BBModel(this.model.stage,this.model.p.getID(),this.model.com.getID(),this.model.ski.getID(),this.model.example.getID());

	    //joblist.set
	   // Bindings.bindBidirectional(companyLabel.textProperty(),model.getCompany());
	   
	    skinameLabel.textProperty().bindBidirectional((model.getSkillname()));
	    //desclabel.textProperty().bindBidirectional(model.getSkilldesc());
	    skilledperson.setItems(model.getSkilled());
	    restperson.setItems(model.getUnskilled());
	    editskidesc.setText(model.ski.getDescription());
	  }
    @FXML
    void clickonaddskilledperson(ActionEvent event) {
    	Person choose = restperson.getSelectionModel().getSelectedItem();
    	choose.addskill(this.model.getSki());
    	choose.updater();
    	this.model.getSki().updater();
    	//Skill s = choose.findaskill(this.model.getSki().getID());
    	this.model.setSki(choose.findaskill(this.model.getSki().getID()));
    	this.model.updateskill();
    	//Skill updat = this.model.getSki();
    	//if (s.equals(updat)) {System.out.println("equal skill");}
    }
    

    @FXML
    void clickonremoveskilledperson(ActionEvent event) {
    	Person choose = skilledperson.getSelectionModel().getSelectedItem();
    	choose.removeskill(this.model.getSki());
    	//choose.updater();
    	//this.model.getSki().updater();
    	//Skill s = choose.findaskill(this.model.getSki().getID());
    	this.model.setSki(choose.findaskill(this.model.getSki().getID()));
    	this.model.updateskill();

    }

    @FXML
    void descTFpressed(MouseEvent event) {
    	editskidesc.clear();

    }

    @FXML
    void handlecancelbutton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/MainView.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,edit); 
    	    cont.setModel(vm);
    	    vm.showskillpage();
    	       
    	    Scene s = new Scene(view);
    	    model.stage.setScene(s);
    	    model.stage.show();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    @FXML
    void handlesavebutton(ActionEvent event) {
    	
    	String newdesc = editskidesc.getText();
    	if (newdesc != null) 
    	{
    		this.model.getSki().setDescription(newdesc);
    		this.model.getSki().updater();
        	this.model.setSki(this.model.getSki().findaskill(this.model.getSki().getID()));
        	this.model.updateskill();
    	}
    	
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/MainView.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,model); 
    	    cont.setModel(vm);
    	    vm.showskillpage();
    	       
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
