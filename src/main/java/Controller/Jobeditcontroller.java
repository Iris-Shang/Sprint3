package Controller;
import java.io.IOException;
//import java.util.ArrayList;

import BBridge.Company;
//import BBridge.Entity.ent;
import BBridge.Person;
import BBridge.Skill;
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

public class Jobeditcontroller
{
	BBModel model;
	BBModel edit;
	public void setModel(BBModel newModel)
	 {
	    model = newModel;
	    edit = new BBModel(this.model.stage,this.model.p.getID(),this.model.com.getID(),this.model.ski.getID(),this.model.example.getID());
	    this.model.updatecom();
	    skilllist.setItems(model.getJobskilllist());
	    rskilllist.setItems(model.getJobrestskilllist());
	    applylist.setItems(model.getJobapplist());
	    restperson.setItems(model.getJobrestapplist());
	    //descriptionlabel.textProperty().bindBidirectional(model.getJobdescription());
	    nameLabel.textProperty().bindBidirectional(model.getJobname());
	   // companylabel.textProperty().bindBidirectional((model.getJobCompany()));
	    editdesc.setText(model.example.getDescription());
	    companylist.setItems(model.getJrcomlist());
	    nowcom.setItems(model.getJncomlist());
	    
	 }
	
    @FXML
    private ListView<Company> nowcom;
	
    @FXML
    private ListView<Company> companylist;

    @FXML
    private Button addperb;

    @FXML
    private Button addskillbutton;

    @FXML
    private ListView<Person> applylist;

    @FXML
    private Button cancelbutton;

  //  @FXML
  //  private Label companylabel;

    @FXML
    private Label descriptionlabel;

    @FXML
    private TextField editdesc;

    @FXML
    private Label nameLabel;

    @FXML
    private Button removepersonb;

    @FXML
    private Button removeskillbutton;

    @FXML
    private ListView<Person> restperson;

    @FXML
    private ListView<Skill> rskilllist;

    @FXML
    private Button savebutton;

    @FXML
    private ListView<Skill> skilllist;
    

    @FXML
    private Button changecom;
    

    
    @FXML
    void clickonchangecom(ActionEvent event) {
    	Company choose = companylist.getSelectionModel().getSelectedItem();
		this.model.example.setCompany(choose.getID());
		this.model.example.updateinrest();
		this.model.setExample(this.model.getP().findajob((this.model.getExample().getID())));
		this.model.updatejob();

    }
    @FXML
    void editdescpress(MouseEvent event) {
    	editdesc.clear();

    }


    @FXML
    void clickonaddperb(ActionEvent event) {
    	Person choose = restperson.getSelectionModel().getSelectedItem();
    	choose.applyjob(this.model.example);
    	choose.updater();
    	this.model.example.updateinrest();
    	//Skill s = choose.findaskill(this.model.getSki().getID());
    	this.model.setExample(this.model.getP().findajob((this.model.getExample().getID())));
    	this.model.updatejob();

    }

    @FXML
    void clickonaddskillbutton(ActionEvent event) {
    	Skill choose = rskilllist.getSelectionModel().getSelectedItem();
    	this.model.example.addskill(choose);
    	this.model.example.updateinrest();
    	this.model.setExample(this.model.getP().findajob((this.model.getExample().getID())));
    	this.model.updatejob();

    }

    @FXML
    void clickonremovepersonb(ActionEvent event) {
    	Person choose = applylist.getSelectionModel().getSelectedItem();
    	choose.removejob(this.model.example);
    	choose.updater();
    	this.model.example.updateinrest();
    	//Skill s = choose.findaskill(this.model.getSki().getID());
    	this.model.setExample(this.model.getP().findajob((this.model.getExample().getID())));
    	this.model.updatejob();

    }

    @FXML
    void clickonremoveskillbutton(ActionEvent event) {
    	Skill choose = skilllist.getSelectionModel().getSelectedItem();
    	this.model.example.remove(choose);
    	this.model.example.updateinrest();
    	this.model.setExample(this.model.getP().findajob((this.model.getExample().getID())));
    	this.model.updatejob();
    	


    }


    @FXML
    void cancelbuttononclick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/Mainview.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,edit); 
    	    cont.setModel(vm);
    	    vm.showpage3();
    	       
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
    void savebuttononclick(ActionEvent event) {
    	//skilllist.setEditable(false);
    	String newdesc = editdesc.getText();
    	if (newdesc != null) 
    	{
    		this.model.getExample().setDescription(newdesc);
    		this.model.getExample().updateinrest();
    		this.model.setExample(model.example.findajob(this.model.example.getID()));
    		this.model.updatejob();
    	}



  
    	
		
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/Mainview.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,model); 
    	    cont.setModel(vm);
    	    vm.showpage3();
    	       
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
