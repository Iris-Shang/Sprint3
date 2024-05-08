package model;

import java.io.IOException;

import Controller.ComController;
import Controller.Comeditcontroller;
import Controller.EntityController;
import Controller.JobController;
import Controller.Jobeditcontroller;
import Controller.PersonController;
import Controller.Personeditcontroller;
import Controller.Skillcontroller;
import Controller.Skilleditcontroller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
//import javafx.scene.layout.Pane;

public class ViewTransitionalModel implements ViewTransitionModelInterface
{
	BorderPane mainview;
	BBModel model;
	public ViewTransitionalModel(BorderPane view,BBModel newModel)
	{
		mainview = view;
	    model = newModel;
	}
	

	@Override
	public void showpage1()
	{
		//person
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/PersonalView.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      PersonController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	    }
	}

	@Override
	public void showpage2()
	{
		//entity list
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/EntityView.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      EntityController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
	}

	@Override
	public void showpage3()
	{
		
		// TODO Auto-generated method stub
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/JobView.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      JobController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
	}


	public void showpersoneditpage()
	{
		
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/Personaledit.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      Personeditcontroller cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
		
		
	}
	public void showjobeditpage()
	{
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/Jobeditview.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      Jobeditcontroller cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
		
		
	}
	
	public void showcompage() 
	{
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/Companyview.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      ComController cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
		
	}
	public void showskillpage() 
	{
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/SkillView.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      Skillcontroller cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
	    
		
	}


	public void showskilleditpage()
	{
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/SkilleditView.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      Skilleditcontroller cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
	    
		
	}
	public void showcomeditpage()
	{
	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(ViewTransitionalModel.class
	        .getResource("../View/Companyeditview.fxml"));
	    try {
	      Node view = loader.load();
	      mainview.setCenter(view);
	      Comeditcontroller cont = loader.getController();
	      cont.setModel(model);
	      
	      
	    } catch (IOException e) {
	      // TODO Auto-generated catch block
	      e.printStackTrace();
	     }
	    
		
	}
	

}
