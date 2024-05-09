package Controller;

import java.io.IOException;

import BBridge.JobPosting;
import BBridge.Person;
import BBridge.Project;
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

public class Comeditcontroller {

    @FXML
    private Button addjobbutton;

    @FXML
    private Button addpersonbutton;

    @FXML
    private Button addprobutton;

    @FXML
    private Button cancelbutton;

    @FXML
    private ListView<JobPosting> comjoblist;

    @FXML
    private Label olddescLabel;

    @FXML
    private ListView<Project> comprolist;

    @FXML
    private TextField editdesc;

    @FXML
    private ListView<Person> employeelist;

    @FXML
    private Label nameLabel;

    @FXML
    private Button removeemplbutton;

    @FXML
    private Button removejobpostbutton;

    @FXML
    private Button removeprobutton;

    @FXML
    private ListView<JobPosting> restjob;

    @FXML
    private ListView<Person> restperson;

    @FXML
    private ListView<Project> restpro;

    @FXML
    private Button savebutton;
    
	BBModel model;
	BBModel edit;
	
	
	public void setModel(BBModel newModel)
	 {
	    model = newModel;
	    this.edit = new BBModel(this.model.stage,this.model.p.getID(),this.model.com.getID(),this.model.ski.getID(),this.model.example.getID());
	    restjob.setItems(model.getRestjob());
	    restperson.setItems(model.getRestperson());
	    restpro.setItems(model.getRestpro());
	    nameLabel.textProperty().bindBidirectional((model.getComname()));
	   // olddescLabel.textProperty().bindBidirectional((model.getComdesc()));
	    comjoblist.setItems(model.getCompostjob());
	    comprolist.setItems(model.getCompostpro());
	    employeelist.setItems(model.getEmployeelist());
	    editdesc.setText(model.com.getDescription());
	    
	  }
	
    @FXML
    void editdescpressed(MouseEvent event) {
    	editdesc.clear();
    }

    @FXML
    void cilckonaddjobbutton(ActionEvent event) {
    	JobPosting choose = restjob.getSelectionModel().getSelectedItem();
    	this.model.com.addJobPosting(choose.getID(), choose.getName(),choose.getDescription(),choose.getSkills(),"random");
    	choose.setCompany(this.model.com.getID());
    	choose.updateinrest();
		this.model.com.updateinrest();
    	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
    	this.model.updatecom();
    	

    }

    @FXML
    void cilckonaddpersonbutton(ActionEvent event) {
    	Person choose = restperson.getSelectionModel().getSelectedItem();
    	this.model.com.addEmployee(choose, "some job name");
    	choose.setCompany(this.model.com.getID());
    	choose.updater();
		this.model.com.updateinrest();
    	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
    	this.model.updatecom();
    	

    }

    @FXML
    void cilckonaddprobutton(ActionEvent event) {
       	Project choose = restpro.getSelectionModel().getSelectedItem();
    	this.model.com.addProject(choose.getID(), choose.getName(), choose.getDescription());
    	choose.setCompany(this.model.com.getID());
    	choose.updateinrest();
		this.model.com.updateinrest();
    	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
    	this.model.updatecom();

    }

    @FXML
    void cilckonremoveemplbutton(ActionEvent event) {
    	Person choose = employeelist.getSelectionModel().getSelectedItem();
    	this.model.com.removeEmployee(choose);
    	choose.setCompany(null);
    	choose.updater();
		this.model.com.updateinrest();
    	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
    	this.model.updatecom();

    }

    @FXML
    void cilckonsavebutton(ActionEvent event) {
    	
    	String newdesc = editdesc.getText();
    	if (newdesc != null) 
    	{
    		this.model.com.setDescription(newdesc);
    		this.model.com.updateinrest();
        	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
        	this.model.updatecom();
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
    	    vm.showcompage();
    	       
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
    void clickonremovejobpostbutton(ActionEvent event) {
    	JobPosting choose = comjoblist.getSelectionModel().getSelectedItem();
    	this.model.com.removeJobPosting(choose);
    	choose.setCompany(null);
    	choose.updateinrest();
		this.model.com.updateinrest();
    	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
    	this.model.updatecom();

    }

    @FXML
    void clickonremoveprobutton(ActionEvent event) {
       	Project choose = comprolist.getSelectionModel().getSelectedItem();
    	this.model.com.removeProjects(choose);
    	choose.setCompany(null);
    	choose.updateinrest();
		this.model.com.updateinrest();
    	this.model.setCom(this.model.com.findacom(this.model.com.getID()));
    	this.model.updatecom();
    	

    }

    @FXML
    void handlecancelbutton(ActionEvent event) {
    	FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/Mainview.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,edit); 
    	    cont.setModel(vm);
    	    vm.showcompage();
    	       
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
