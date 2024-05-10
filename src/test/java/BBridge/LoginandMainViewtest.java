package BBridge;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
//import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;

import Controller.LoginController;
//import Controller.Maincontroller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.control.ListView;
//import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.BB;
import model.BBModel;
//import model.ViewTransitionalModel;


@ExtendWith(ApplicationExtension.class)
class LoginandMainViewtest extends ApplicationTest
{


	Company A;
	Company B;
	Company C;
	Person D;
	Person E;
	Person F;
	Person defaul;
	Skill python;
	Skill matlab;
	Skill java;
	News N;
	Person p;
	Project P;
	Project R;
	JobPosting JP;
	JobPosting jp;
	static String uriBase;
	JobRecommender all;
	JRskill jrs;
	JRfollow jrf;
	Integer i1;
	Stage stage;
	BBModel m;
	//ArrayList<Entity> alltest = new ArrayList<Entity>();	
	@SuppressWarnings("unused")
	private static <T> void assertArray(ArrayList<T> A, ArrayList<T> B)
	{

		
		assertTrue(A.size() == B.size()); 
		assertTrue(A.containsAll(B));
	}

	 
    @SuppressWarnings("unused")
	@Start
    public void start(Stage stage)
    {
		RestClient client = RestClient.create();
		
		uriBase = "http://localhost:9000/v1";
		
		client.delete()
		.uri(uriBase+"/Project1")
		.retrieve();


		
		RDesc desc = new RDesc("Project1","Project",uriBase+"/Project1");

		String resp = client.post()
		.uri(uriBase+"/Project1")
		.contentType(MediaType.APPLICATION_JSON)
		.body(desc)
		.retrieve()
		.body(String.class);
		

		RDesc per = new RDesc("Person","Personlists",uriBase+"/Project1/Person");
		String pers = client.post()
		.uri(uriBase+"/Project1/Person")
		.contentType(MediaType.APPLICATION_JSON)
		.body(per)
		.retrieve()
		.body(String.class);
		
		RDesc c = new RDesc("Company","Companylists",uriBase+"/Project1/Company");
		String com = client.post()
		.uri(uriBase+"/Project1/Company")
		.contentType(MediaType.APPLICATION_JSON)
		.body(c)
		.retrieve()
		.body(String.class);
		
		RDesc jop = new RDesc("JobPosting","JobPostinglists",uriBase+"/Project1/JobPosting");
		String jobp = client.post()
		.uri(uriBase+"/Project1/JobPosting")
		.contentType(MediaType.APPLICATION_JSON)
		.body(jop)
		.retrieve()
		.body(String.class);
		
		RDesc pro = new RDesc("Project","Projectlists",uriBase+"/Project1/Project");
		String po = client.post()
		.uri(uriBase+"/Project1/Project")
		.contentType(MediaType.APPLICATION_JSON)
		.body(pro)
		.retrieve()
		.body(String.class);
		
		RDesc ski = new RDesc("Skill","Skilllists",uriBase+"/Project1/Skill");
		String sk = client.post()
		.uri(uriBase+"/Project1/Skill")
		.contentType(MediaType.APPLICATION_JSON)
		.body(ski)
		.retrieve()
		.body(String.class);
		
		RDesc News = new RDesc("News","Newslists",uriBase+"/Project1/News");
		String n = client.post()
		.uri(uriBase+"/Project1/News")
		.contentType(MediaType.APPLICATION_JSON)
		.body(News)
		.retrieve()
		.body(String.class);
		
		RDesc Ents = new RDesc("Entity","Entitylists",uriBase+"/Project1/Entity");
		String ent = client.post()
		.uri(uriBase+"/Project1/Entity")
		.contentType(MediaType.APPLICATION_JSON)
		.body(Ents)
		.retrieve()
		.body(String.class);
		
		A = new Company(1,"A","companyA");
		A.createinrest();
		B = new Company(2,"B","companyB");
		B.createinrest();
		C = new Company(3,"C","companyC");
		C.createinrest();
		D = new Person(4,"D","manager","personD",C.getID(),"abc");
		D.createinrest();
		E = new Person(5,"E","personE","abc");
		E.createinrest();
		F = new Person(6,"F","personF","abc");
		F.createinrest();
		python = new Skill(7,"Python","skill python");
		python.createinrest();
		java = new Skill(8,"java","skill java");
		java.createinrest();

		N = new News(100, "dnews", "news post by D", "here is an article",D);
		N.createinrest();
		P =  new Project(9,"p","project p",B);
		P.createinrest();
		R =  new Project(10,"r","project r",C);
		R.createinrest();
		ArrayList<Integer> skill_JP = new ArrayList<Integer>();
		ArrayList<Integer> sjp = new ArrayList<Integer>();
		skill_JP.add(python.getID());
		sjp.add(python.getID());
		sjp.add(java.getID());
		JP = new JobPosting(11,"JP","Job",B,skill_JP);
		JP.createinrest();
		jp = new JobPosting(12,"jp","Job",C,sjp);
		jp.createinrest();
		p = new Person(1000,"user","CEO","default user",C.getID(),"abc");

		C.addJobPosting(12,"jp","Job",sjp,"all");
		C.addEmployee(p, "programmer");
		C.addEmployee(D, "manager");
		C.addProject(9,"p","project p");
		p.addskill(python);
		D.addskill(python);
		p.applyjob(jp);
		p.addProject(R);
		R.updateinrest();
		p.createinrest();
		python.updater();
		C.updateinrest();
		D.updater();
		jp.updateinrest();

		
		
        m = new BBModel(stage,1000,3,7,12);
        FXMLLoader loader = new FXMLLoader(BB.class.getResource("../View/Loginview.fxml"));
        Scene scene;
		try
		{
			scene = new Scene(loader.load());
		    LoginController con = loader.getController();
		    con.setModel(m);
		        
		    stage.setScene(scene);
		    stage.setTitle("login page");
		    stage.show();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
      }
    
    @Test    
    public void logintest(FxRobot robot) 
    {
    	robot.clickOn("#useridarea");
    	robot.write("4");
    	robot.clickOn("#userpasswordarea");
    	robot.write("a");
    	robot.clickOn("#loginbutton");
    	//valid user this time
    	robot.clickOn("#useridarea");
    	robot.write("1000");
    	robot.clickOn("#userpasswordarea");
    	robot.write("abc");
    	robot.clickOn("#loginbutton");
    	robot.clickOn("#Entitybutton");
    	robot.clickOn("#Personbutton");
    	robot.clickOn("#Jobbutton");
    	robot.clickOn("#companybutton");
    	robot.clickOn("#skillbutton");
    	
    	
    }
  }
