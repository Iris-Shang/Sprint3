package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.ViewTransitionModelInterface;

public class Maincontroller
{
    ViewTransitionModelInterface model;

    @FXML
    private Button Entitybutton;

    @FXML
    private Button Personbutton;

    @FXML
    private Button companybutton;

    @FXML
    private Button skillbutton;
    
    public void setModel(ViewTransitionModelInterface newModel)
    {
      model=newModel;
    }
    @FXML
    void onClickEntity(ActionEvent event) {
    	model.showpage2();

    }

    @FXML
    void onClickJob(ActionEvent event) {
    	model.showpage3();

    }

    @FXML
    void onClickPerson(ActionEvent event) {
    	model.showpage1();
    }
    @FXML
    void onClickcompanybutton(ActionEvent event) {
    	model.showcompage();
    }

    @FXML
    void onClickskillbutton(ActionEvent event) {
    	model.showskillpage();
    }

    
    

}


