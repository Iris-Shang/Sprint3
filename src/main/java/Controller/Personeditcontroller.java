package Controller;

import java.io.IOException;

import BBridge.Company;
import BBridge.JobPosting;
import BBridge.Project;
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


public class Personeditcontroller
{
	BBModel model;
	BBModel edit;
	public void setModel(BBModel newModel)
	 {
	    model = newModel;
	    edit = new BBModel(this.model.stage,this.model.p.getID(),this.model.com.getID(),this.model.ski.getID(),this.model.example.getID());
	    //companyLabel.setText(model.getCompany());
	    this.model.updateper();
	    joblist.setItems(model.getJoblist());
	    perprolist.setItems(model.getProlist());
	    skilllist.setItems(model.getSkilllist());
	   //joblist.set
	   // Bindings.bindBidirectional(companyLabel.textProperty(),model.getCompany());
	   // companyLabel.textProperty().bindBidirectional((model.getCompany()));
	    nameLabel.textProperty().bindBidirectional(model.getName());
	    //occupatationlabel.textProperty().bindBidirectional(model.getOccupatation());
	    availjoblist.setItems(model.getPrJoblist());
	    avaprolist.setItems(model.getPrprolist());
	    avaskilllist.setItems(model.getPrskilllist());
	    editoccupatation.setText(model.p.getOccupation());
	    companylist.setItems(model.getPrcomlist());
	    companynoew.setItems(model.getPncomlist());
	    
	  }
	

    @FXML
    private ListView<Company> companynoew;

    @FXML
    private ListView<Company> companylist;
    @FXML
    private Button Cancelbutton;

    @FXML
    private Button addjobbutton;

    @FXML
    private Button addprobutton;

    @FXML
    private Button addskillbutton;

    @FXML
    private ListView<JobPosting> availjoblist;

    @FXML
    private ListView<Project> avaprolist;

    @FXML
    private ListView<Skill> avaskilllist;

    //@FXML
   // private Label companyLabel;

    @FXML
    private TextField editoccupatation;


    @FXML
    private ListView<JobPosting> joblist;

    @FXML
    private Label nameLabel;

    @FXML
    private Label occupatationlabel;

    @FXML
    private ListView<Project> perprolist;

    @FXML
    private Button removejobbutton;

    @FXML
    private Button removeprobutton;

    @FXML
    private Button removeskillbutton;

    @FXML
    private Button savebutton;

    @FXML
    private ListView<Skill> skilllist;
    
    @FXML
    private Button changecombutton;
    
    @FXML
    void pressededitTF(MouseEvent event) {
    	editoccupatation.clear();
    	

    }

    @FXML
    void clickonaddjobbutton(ActionEvent event) {
    	JobPosting choose = availjoblist.getSelectionModel().getSelectedItem();
    	this.model.p.applyjob(choose);
    	choose.updateinrest();
    	this.model.p.updater();
    	this.model.setP(choose.findanperson(this.model.p.getID()));
		this.model.updateper();
    	

    }
    

    @FXML
    void clickonchangecombutton(ActionEvent event) {
    	Company choose = companylist.getSelectionModel().getSelectedItem();
		Company origin = this.model.p.findacom(this.model.p.getCompany());
		origin.removeEmployee(this.model.p);
    	this.model.p.setCompany(choose.getID());
		choose.addEmployee(this.model.p, this.model.p.getOccupation());
		choose.updateinrest();
		origin.updateinrest();
		this.model.p.updater();
		this.model.setP(this.model.getP().findanperson((this.model.getP().getID())));
		this.model.updateper();
		


    }

    @FXML
    void clickonaddprobutton(ActionEvent event) {
    	Project choose = avaprolist.getSelectionModel().getSelectedItem();
    	this.model.p.addProject(choose);
    	choose.updateinrest();
    	this.model.p.updater();
    	this.model.setP(choose.findanperson(this.model.p.getID()));
		this.model.updateper();

    }

    @FXML
    void clickonaddskillbutton(ActionEvent event) {
    	Skill choose = avaskilllist.getSelectionModel().getSelectedItem();
    	this.model.p.addskill(choose);
    	choose.updater();
    	this.model.p.updater();
    	this.model.setP(choose.findanperson(this.model.p.getID()));
		this.model.updateper();

    }

    @FXML
    void clickonremovejobbutton(ActionEvent event) {
    	JobPosting choose = joblist.getSelectionModel().getSelectedItem();
    	this.model.p.removejob(choose);
    	choose.updateinrest();
    	this.model.p.updater();
    	this.model.setP(choose.findanperson(this.model.p.getID()));
		this.model.updateper();

    }

    @FXML
    void clickonremoveprobutton(ActionEvent event) {
    	Project choose = perprolist.getSelectionModel().getSelectedItem();
    	this.model.p.removeProjects(choose);
    	choose.updateinrest();
    	this.model.p.updater();
    	this.model.setP(choose.findanperson(this.model.p.getID()));
		this.model.updateper();

    }

    @FXML
    void clickonremoveskillbutton(ActionEvent event) {
    	Skill choose = skilllist.getSelectionModel().getSelectedItem();
    	this.model.p.removeskill(choose);
    	choose.updater();
    	this.model.p.updater();
    	this.model.setP(choose.findanperson(this.model.p.getID()));
		this.model.updateper();
    }

    @FXML
    void handleCancelbutton(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/Mainview.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,edit); 
    	    cont.setModel(vm);
    	    vm.showpage1();
    	       
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
    void handlesaveButtonAction(ActionEvent event) {
    	skilllist.setEditable(false);
    	joblist.setEditable(false);
    	perprolist.setEditable(false);
    	
    	String newdesc = editoccupatation.getText();
    	if (newdesc != null) 
    	{
    		this.model.p.setOccupation(newdesc);
    		this.model.p.updater();
    		this.model.setP(this.model.getP().findanperson((this.model.getP().getID())));
    		this.model.updateper();
    		

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
    	    vm.showpage1();
    	       
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


