package main;

//import java.util.ArrayList;

//import BBridge.Company;
//import BBridge.JobPosting;
//import BBridge.Person;
//import BBridge.Skill;
import Controller.LoginController;
//import Controller.Maincontroller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.BBModel;
//import model.ViewTransitionalModel;


public class BB extends Application
{

	@Override
	public void start(Stage stage) throws Exception
	{

		BBModel model = new BBModel(stage,1000,3,7,12);
				
        FXMLLoader loader = new FXMLLoader(BB.class.getResource("../View/Loginview.fxml"));
        Scene scene = new Scene(loader.load());
        LoginController con = loader.getController();
        con.setModel(model);
        
        stage.setScene(scene);
        stage.setTitle("ByteBridge");
        stage.show();
/*
	    //model.setCompany(C.getName());

	    
		loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("MainView.fxml"));
	    BorderPane view = loader.load();
	    Maincontroller cont = loader.getController();
	    ViewTransitionalModel vm =new ViewTransitionalModel(view,model); 
	    cont.setModel(vm);
	    vm.showpage1();
	       
	    Scene s = new Scene(view);
	    stage.setScene(s);
	    stage.show();
*/
	}

	public static void main(String[] args)
	{
		launch(args);

	}

}
