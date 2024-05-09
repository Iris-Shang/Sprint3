package BBridge;

import org.testfx.assertions.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.ArrayList;

//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;
//import org.testfx.assertions.api.Assertions;

import Controller.Maincontroller;
//import Controller.Skillcontroller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
//import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.BB;
import model.BBModel;
import model.ViewTransitionalModel;

@ExtendWith(ApplicationExtension.class)
public class Skillviewtest extends ApplicationTest {
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
        FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(BB.class.getResource("../main/Mainview.fxml"));
	    BorderPane view;
		try
		{
			view = loader.load();
    	    Maincontroller cont = loader.getController();
    	    ViewTransitionalModel vm =new ViewTransitionalModel(view,m); 
    	    cont.setModel(vm);
    	    vm.showskillpage();
    	       
    	    Scene s = new Scene(view);
    	    m.stage.setScene(s);
    	    m.stage.show();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
    
    @SuppressWarnings("unchecked")
	ListView<Person> getlist(FxRobot robot)

    {
     return (ListView<Person>) robot.lookup("#skilledperson")
         .queryAll().iterator().next();
    }
    
    
    
    @SuppressWarnings("unchecked")
	ListView<Person> getunskillplist(FxRobot robot)

    {
     return (ListView<Person>) robot.lookup("#restperson")
         .queryAll().iterator().next();
    }
    
    private void selectskilledP(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Person> plist = getlist(robot);
  		  plist.scrollTo(index);
  		  plist.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    
    
    private Person selectskilledPtemp(FxRobot robot, int index)
    {
  		  ListView<Person> plist = getlist(robot);
  		  plist.scrollTo(index);
  		  plist.getSelectionModel().select(index);
  		  Person s = plist.getSelectionModel().getSelectedItem();
  		  return s;
  		  

  }
    
    private void selectunskilledP(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Person> plist = getunskillplist(robot);
  		  plist.scrollTo(index);
  		  plist.getSelectionModel().select(index);
  		  //Person s =  plist.getSelectionModel().getSelectedItem();
  		 
  		  //clearAndSelect(index);
  	  });
  	  
  	  WaitForAsyncUtils.waitForFxEvents();
  	  
  }
    
    @Test
    public void testskillP(FxRobot robot) throws InterruptedException
    {

    	Person A = new Person();
    	Person [] items = {
    		  //new Person(1000,"user","CEO","default user",3,"abc"),
    		  //new Person (4,"D","manager","personD",3,"abc"),
    		 A.findanperson(1000),
    		 A.findanperson(4),
    		  
    	};
      
 

    	ListView<Person> plist = getlist(robot);
      
    	Assertions.assertThat(plist).hasExactlyNumItems(items.length);
      
    	for(Person i:items)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
    	
        Assertions.assertThat(robot.lookup("#skinameLabel")
                .queryAs(Label.class)).hasText(m.ski.name);
        Assertions.assertThat(robot.lookup("#desclabel")
                .queryAs(Label.class)).hasText(m.ski.description);
        robot.clickOn("#Editbutton");
        //check contents of the lists
        ListView<Person> rplist = getunskillplist(robot);
        Person [] l2  = {A.findanperson(5), A.findanperson(6),};
    	plist = getlist(robot);
    	
    	Assertions.assertThat(plist).hasExactlyNumItems(items.length);
    	for(Person i:items)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
    	
    	Assertions.assertThat(rplist).hasExactlyNumItems(l2.length);
    	for(Person i: l2)
    	{
    		Assertions.assertThat(rplist).hasListCell(i); 
    		
    	}  
    	
        robot.clickOn("#editskidesc");
        robot.write("new desc");
        //check remove button
        selectskilledP(robot,1);
        assertEquals(A.findanperson(4),selectskilledPtemp(robot,1));       

        selectskilledP(robot,1);
        robot.clickOn("#removeskilledperson");
      //selectskilledP(robot,1);
      //robot.clickOn("#removeskilledperson");
        
        Person [] l3  = {A.findanperson(1000),};
        //Thread.sleep(1000);
    	plist = getlist(robot);
    	//Thread.sleep(1000);
    	for (Person i : plist.getItems()) 
    	{
    		System.out.println(i);
    	}
    	
    	Assertions.assertThat(plist).hasExactlyNumItems(l3.length);
    	for(Person i:l3)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
        rplist = getunskillplist(robot);
        Person [] l4  = {A.findanperson(4),A.findanperson(5), A.findanperson(6),};
    	Assertions.assertThat(rplist).hasExactlyNumItems(l4.length);
    	for(Person i: l4)
    	{
    		Assertions.assertThat(rplist).hasListCell(i); 
    		
    	} 
    	
    	robot.clickOn("#savebutton");
    	//check saved data
    	plist = getlist(robot);
    	Assertions.assertThat(plist).hasExactlyNumItems(l3.length);
    	for(Person i:l3)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
    	
    	Assertions.assertThat(robot.lookup("#desclabel")
                .queryAs(Label.class)).hasText(m.ski.description);
        Assertions.assertThat(robot.lookup("#desclabel")
                .queryAs(Label.class)).hasText("new desc");
        
    	robot.clickOn("#Editbutton");
    	selectunskilledP(robot,2);
    	robot.clickOn("#addskilledperson");
    	
    	
        Person [] l5  = {A.findanperson(4),A.findanperson(5),};
        rplist = getunskillplist(robot);
    	Assertions.assertThat(rplist).hasExactlyNumItems(l5.length);
    	for(Person i: l5)
    	{
    		Assertions.assertThat(rplist).hasListCell(i); 
    		
    	} 
    	
        Person [] l6  = {A.findanperson(1000),A.findanperson(6),};
    	plist = getlist(robot);
    	Assertions.assertThat(plist).hasExactlyNumItems(l6.length);
    	for(Person i :l3)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
    	
    	robot.clickOn("#savebutton");
    	plist = getlist(robot);
    	Assertions.assertThat(plist).hasExactlyNumItems(l6.length);
    	for(Person i :l3)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
    	
    	
    	//check cancel situation
    	robot.clickOn("#Editbutton");
    	selectunskilledP(robot,0);
    	robot.clickOn("#addskilledperson");
        selectskilledP(robot,1);
        robot.clickOn("#removeskilledperson");
    	robot.clickOn("#cancelbutton");
    	
    	plist = getlist(robot);
    	Assertions.assertThat(plist).hasExactlyNumItems(l6.length);
    	for(Person i :l3)
    	{
    		Assertions.assertThat(plist).hasListCell(i); 
    		
    	}
    	
        
    	
    	
    	
    	
    	
    	
    	
    	
      
    	
    	
    	

        
        
    	
    	
    	
    	
    	
    	
    	
        
        
        
        
        
        
        
    	
    	
    
    
    
    
    }    
}
