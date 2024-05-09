package BBridge;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;
import org.testfx.api.FxRobot;
import org.testfx.assertions.api.Assertions;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.framework.junit5.Start;
import org.testfx.util.WaitForAsyncUtils;

import Controller.Maincontroller;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.BB;
import model.BBModel;
import model.ViewTransitionalModel;


@ExtendWith(ApplicationExtension.class)
class jobpagetest extends ApplicationTest
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
		//sjp.add(python.getID());
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
    	    vm.showpage3();
    	       
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
	ListView<Person> getpl1(FxRobot robot)

    {
     return (ListView<Person>) robot.lookup("#applylist")
         .queryAll().iterator().next();
    }
    
    
    
    @SuppressWarnings("unchecked")
	ListView<Person> getpl2(FxRobot robot)

    {
     return (ListView<Person>) robot.lookup("#restperson")
         .queryAll().iterator().next();
    }
    
    private void sktpl1(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Person> plist = getpl1(robot);
  		  plist.scrollTo(index);
  		  plist.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    
    
    
    private void sktpl2(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Person> plist = getpl2(robot);
  		  plist.scrollTo(index);
  		  plist.getSelectionModel().select(index);
  		  //Person s =  plist.getSelectionModel().getSelectedItem();
  		 
  		  //clearAndSelect(index);
  	  });
  	  
  	  WaitForAsyncUtils.waitForFxEvents();
  	  
  }
    
    @SuppressWarnings("unchecked")
	ListView<Skill> getsl1(FxRobot robot)

    {
     return (ListView<Skill>) robot.lookup("#skilllist")
         .queryAll().iterator().next();
    } 
    
    private void sktsl1(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Skill> pro1 = getsl1(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
      
    @SuppressWarnings("unchecked")
	ListView<Skill> getsl2(FxRobot robot)

    {
     return (ListView<Skill>) robot.lookup("#rskilllist")
         .queryAll().iterator().next();
    } 
    
    private void sktsl2(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Skill> pro1 = getsl2(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    
    @SuppressWarnings("unchecked")
	ListView<Company> getnowcom(FxRobot robot)

    {
     return (ListView<Company>) robot.lookup("#nowcom")
         .queryAll().iterator().next();
    } 
    

    @SuppressWarnings("unchecked")
	ListView<Company> getcl(FxRobot robot)

    {
     return (ListView<Company>) robot.lookup("#companylist")
         .queryAll().iterator().next();
    } 
     
    private void sktcl(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Company> pro1 = getcl(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    public boolean checkini(FxRobot robot) 
    {
    	Person A = new Person();
        Assertions.assertThat(robot.lookup("#jobname")
                .queryAs(Label.class)).hasText(m.example.name);
        Assertions.assertThat(robot.lookup("#comname")
                .queryAs(Label.class)).hasText(A.findacom(m.example.company).name);
        Assertions.assertThat(robot.lookup("#descname")
                .queryAs(Label.class)).hasText(m.example.description);
    	Skill [] l3 = {A.findaskill(8),    		  
      	};
    	ListView<Skill> c3 = getsl1(robot);
        
    	Assertions.assertThat(c3).hasExactlyNumItems(l3.length);
      
    	for(Skill i: l3)
    	{
    		Assertions.assertThat(c3).hasListCell(i); 
    		
    	}    	
    	Person [] l1 = {
      		 A.findanperson(1000),
      		  
      	};

      	ListView<Person> c1 = getpl1(robot);
        
      	Assertions.assertThat(c1).hasExactlyNumItems(l1.length);
        
      	for(Person i: l1)
      	{
      		Assertions.assertThat(c1).hasListCell(i); 
      		
      	}
    	
    	return true;
    }
    
    @Test    
    public void jobtest(FxRobot robot) throws InterruptedException 
    
    {
    	Person A = new Person();
    	boolean a = checkini(robot);
    	assertTrue(a);
    	robot.clickOn("#editbutton");
    	Skill [] l3 = {A.findaskill(8),    		  
      	};
    	ListView<Skill> c3 = getsl1(robot);        
    	Assertions.assertThat(c3).hasExactlyNumItems(l3.length);
      
    	for(Skill i: l3)
    	{
    		Assertions.assertThat(c3).hasListCell(i);   		
    	}    	
    	Person [] l1 = {
      		 A.findanperson(1000),    		  
      	};

      	ListView<Person> c1 = getpl1(robot);
        
      	Assertions.assertThat(c1).hasExactlyNumItems(l1.length);    
      	for(Person i: l1)
      	{
      		Assertions.assertThat(c1).hasListCell(i); 
      		
      	}
      	
        Assertions.assertThat(robot.lookup("#jobname")
                .queryAs(Label.class)).hasText(m.example.name);
        Assertions.assertThat(robot.lookup("#editdesc")
                .queryAs(TextField.class)).hasText(m.example.description);
        robot.clickOn("#editdesc");
        robot.write("newjobdesc");
        
    	Skill [] l0 = {A.findaskill(7),    		  
      	};
    	ListView<Skill> c0 = getsl2(robot);        
    	Assertions.assertThat(c0).hasExactlyNumItems(l0.length);
      
    	for(Skill i: l0)
    	{
    		Assertions.assertThat(c0).hasListCell(i);   		
    	}    	
    	Person [] l1a = {
      		 A.findanperson(4),  A.findanperson(5), A.findanperson(6),
      	};

      	ListView<Person> c1a = getpl2(robot);
        
      	Assertions.assertThat(c1a).hasExactlyNumItems(l1a.length);    
      	for(Person i: l1a)
      	{
      		Assertions.assertThat(c1a).hasListCell(i); 
      		
      	}
      	
      	
        
    	Company [] l4 = {A.findacom(3),    		  
      	};
    	ListView<Company> c4 = getnowcom(robot);
        
    	Assertions.assertThat(c4).hasExactlyNumItems(l4.length);
      
    	for(Company i: l4)
    	{
    		Assertions.assertThat(c4).hasListCell(i); 
    	}
    	Company [] l5 = {A.findacom(1),  A.findacom(2), 		  
      	};
    	ListView<Company> c5 = getcl(robot);
        
    	Assertions.assertThat(c5).hasExactlyNumItems(l5.length);
      
    	for(Company i: l5)
    	{
    		Assertions.assertThat(c5).hasListCell(i); 
    	}
    	sktcl(robot,1);
    	Thread.sleep(1000);
    	robot.clickOn("#changecom");
    	Company [] l6 = {A.findacom(1),  A.findacom(3), 		  
      	};
    	ListView<Company> c6 = getcl(robot);
        
    	Assertions.assertThat(c6).hasExactlyNumItems(l6.length);
   	
    	for(Company i: l6)
    	{
    		Assertions.assertThat(c6).hasListCell(i); 
    	}
    	
    	Company [] l7 = {A.findacom(2),    		  
      	};
    	ListView<Company> c7 = getnowcom(robot);
        
    	Assertions.assertThat(c7).hasExactlyNumItems(l7.length);
      
    	for(Company i: l7)
    	{
    		Assertions.assertThat(c7).hasListCell(i); 
    	}
    	
    	
    	
        //check add
        sktsl2(robot,0);
        robot.clickOn("#addskillbutton");
        sktpl2(robot,1);
        robot.clickOn("#addperb");
    	Skill [] l0a = {   		  
      	};
    	ListView<Skill> c0a = getsl2(robot);        
    	Assertions.assertThat(c0a).hasExactlyNumItems(l0a.length);
      
    	for(Skill i: l0a)
    	{
    		Assertions.assertThat(c0a).hasListCell(i);   		
    	}    	
    	Person [] l1aq = {
      		 A.findanperson(4), A.findanperson(6),
      	};

      	ListView<Person> c1aq = getpl2(robot);
        
      	Assertions.assertThat(c1aq).hasExactlyNumItems(l1aq.length);    
      	for(Person i: l1aq)
      	{
      		Assertions.assertThat(c1aq).hasListCell(i); 
      		
      	}
      	
    	Skill [] l3a = {A.findaskill(8),  A.findaskill(7)  		  
      	};
    	ListView<Skill> c3a = getsl1(robot);        
    	Assertions.assertThat(c3a).hasExactlyNumItems(l3a.length);
      
    	for(Skill i: l3a)
    	{
    		Assertions.assertThat(c3a).hasListCell(i);   		
    	}    	
    	Person [] l1w = {
      		 A.findanperson(1000), A.findanperson(5), 		  
      	};

      	ListView<Person> c1w = getpl1(robot);
        
      	Assertions.assertThat(c1w).hasExactlyNumItems(l1w.length);    
      	for(Person i: l1w)
      	{
      		Assertions.assertThat(c1w).hasListCell(i); 
      		
      	}
      	
    	Skill [] l0w = {   };
    	ListView<Skill> c0w = getsl2(robot);        
    	Assertions.assertThat(c0w).hasExactlyNumItems(l0w.length);
      
    	for(Skill i: l0w)
    	{
    		Assertions.assertThat(c0w).hasListCell(i);   		
    	}  
    	
    	
      	robot.clickOn("#savebutton");
      	c3a = getsl1(robot);        
    	Assertions.assertThat(c3a).hasExactlyNumItems(l3a.length);
      
    	for(Skill i: l3a)
    	{
    		Assertions.assertThat(c3a).hasListCell(i);   		
    	}    	
    	c1w = getpl1(robot);
        
      	Assertions.assertThat(c1w).hasExactlyNumItems(l1w.length);    
      	for(Person i: l1w)
      	{
      		Assertions.assertThat(c1w).hasListCell(i); 
      	}
        Assertions.assertThat(robot.lookup("#comname")
                .queryAs(Label.class)).hasText(A.findacom(m.example.company).name);
        Assertions.assertThat(robot.lookup("#descname")
                .queryAs(Label.class)).hasText(m.example.description);
    	robot.clickOn("#editbutton");
        robot.clickOn("#editdesc");
        robot.write("Job");
    	Company [] l4a = {A.findacom(2),    		  
      	};
    	ListView<Company> c4a = getnowcom(robot);
        
    	Assertions.assertThat(c4a).hasExactlyNumItems(l4a.length);
      
    	for(Company i: l4a)
    	{
    		Assertions.assertThat(c4).hasListCell(i); 
    	}
    	Company [] l5a = {A.findacom(1),  A.findacom(3), 		  
      	};
    	ListView<Company> c5a = getcl(robot);
        
    	Assertions.assertThat(c5a).hasExactlyNumItems(l5a.length);
      
    	for(Company i: l5a)
    	{
    		Assertions.assertThat(c5a).hasListCell(i); 
    	}
    	
    	sktcl(robot,1);
    	Thread.sleep(1000);
    	robot.clickOn("#changecom");
    	Company [] l6a = {A.findacom(1),  A.findacom(2), 		  
      	};
    	ListView<Company> c6a = getcl(robot);
        
    	Assertions.assertThat(c6a).hasExactlyNumItems(l6.length);
   	
    	for(Company i: l6a)
    	{
    		Assertions.assertThat(c6a).hasListCell(i); 
    	}
    	
    	Company [] l7a = {A.findacom(3),    		  
      	};
    	ListView<Company> c7a = getnowcom(robot);
        
    	Assertions.assertThat(c7a).hasExactlyNumItems(l7a.length);
      
    	for(Company i: l7a)
    	{
    		Assertions.assertThat(c7a).hasListCell(i); 
    	}
    	
    	sktpl1(robot,1);
    	robot.clickOn("#removepersonb");
    	sktsl1(robot,1);
    	robot.clickOn("#removeskillbutton");
    	
    	c3 = getsl1(robot);        
    	Assertions.assertThat(c3).hasExactlyNumItems(l3.length);
      
    	for(Skill i: l3)
    	{
    		Assertions.assertThat(c3).hasListCell(i);   		
    	}    	
    	c1 = getpl1(robot);
        
      	Assertions.assertThat(c1).hasExactlyNumItems(l1.length);    
      	for(Person i: l1)
      	{
      		Assertions.assertThat(c1).hasListCell(i);  		
      	}
      	c0 = getsl2(robot);        
    	Assertions.assertThat(c0).hasExactlyNumItems(l0.length);
      
    	for(Skill i: l0)
    	{
    		Assertions.assertThat(c0).hasListCell(i);   		
    	}
    	
    	c1a = getpl2(robot);
        
      	Assertions.assertThat(c1a).hasExactlyNumItems(l1a.length);    
      	for(Person i: l1a)
      	{
      		Assertions.assertThat(c1a).hasListCell(i); 
      		
      	}
      	robot.clickOn("#savebutton");
    	a = checkini(robot);
    	assertTrue(a);
    	robot.clickOn("#editbutton");
    	
        sktsl2(robot,0);
        robot.clickOn("#addskillbutton");
        sktpl2(robot,0);
        robot.clickOn("#addperb");
    	sktpl1(robot,0);
    	robot.clickOn("#removepersonb");
    	sktsl1(robot,0);
    	robot.clickOn("#removeskillbutton");
    	robot.clickOn("#cancelbutton");
    	a = checkini(robot);
    	assertTrue(a);
    	
      	
      	
    	
    	
    	
      	
      	
        
        
        
        
        
        
      	
    	
    	
    }
    
    
    

}
