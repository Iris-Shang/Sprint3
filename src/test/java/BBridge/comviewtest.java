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
class comviewtest extends ApplicationTest
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
		p.createinrest();
		C.addJobPosting(12,"jp","Job",sjp,"all");
		C.addEmployee(p, "programmer");
		C.addEmployee(D, "manager");
		C.addProject(9,"p","project p");
		p.addskill(python);
		D.addskill(python);
		p.applyjob(jp);
		p.addProject(R);
		R.updateinrest();
		p.updater();
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
    	    vm.showcompage();
    	       
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
     return (ListView<Person>) robot.lookup("#employeelist")
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
	ListView<Project> getprl1(FxRobot robot)

    {
     return (ListView<Project>) robot.lookup("#comprolist")
         .queryAll().iterator().next();
    } 
    private void sktprl1(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Project> pro1 = getprl1(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    
    @SuppressWarnings("unchecked")
	ListView<Project> getprl2(FxRobot robot)

    {
     return (ListView<Project>) robot.lookup("#restpro")
         .queryAll().iterator().next();
    } 
    private void sktprl2(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<Project> pro1 = getprl2(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    
    @SuppressWarnings("unchecked")
	ListView<JobPosting> getjl1(FxRobot robot)

    {
     return (ListView<JobPosting>) robot.lookup("#comjoblist")
         .queryAll().iterator().next();
    } 
    
    private void sktjl1(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<JobPosting> pro1 = getjl1(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    
    @SuppressWarnings("unchecked")
	ListView<JobPosting> getjl2(FxRobot robot)

    {
     return (ListView<JobPosting>) robot.lookup("#restjob")
         .queryAll().iterator().next();
    } 
    
    private void sktjl2(FxRobot robot, int index)
    {
  	  Platform.runLater(()->{
  		  ListView<JobPosting> pro1 = getjl2(robot);
  		  pro1.scrollTo(index);
  		  pro1.getSelectionModel().select(index);
  		  //clearAndSelect(index);
  	  });
  	  WaitForAsyncUtils.waitForFxEvents();
  }
    private Person selectper(FxRobot robot, int index)
    {
  		  ListView<Person> plist = getpl1(robot);
  		  plist.scrollTo(index);
  		  plist.getSelectionModel().select(index);
  		  Person s = plist.getSelectionModel().getSelectedItem();
  		  return s;
  		  

  }


    public boolean checkini(FxRobot robot) 
    {
    	Person A = new Person();
        Assertions.assertThat(robot.lookup("#nameLabel")
                .queryAs(Label.class)).hasText(m.com.name);
        Assertions.assertThat(robot.lookup("#descLabel")
                .queryAs(Label.class)).hasText(m.com.description);  	
    	Person [] l1 = {
      		 A.findanperson(1000),A.findanperson(4)
      		  
      	};

      	ListView<Person> c1 = getpl1(robot);
        
      	Assertions.assertThat(c1).hasExactlyNumItems(l1.length);
        
      	for(Person i: l1)
      	{
      		Assertions.assertThat(c1).hasListCell(i); 
      		
      	}
      	
    	JobPosting [] l2 = {A.findajob(12),    		  
      	};
    	ListView<JobPosting> c2 = getjl1(robot);
        
    	Assertions.assertThat(c2).hasExactlyNumItems(l2.length);
      
    	for(JobPosting i: l2)
    	{
    		Assertions.assertThat(c2).hasListCell(i); 
    		
    	}
    	
        Project [] l0 = {A.findapro(9),    		  
      	};
    	ListView<Project> c0 = getprl1(robot);//checklist 1
        
    	Assertions.assertThat(c0).hasExactlyNumItems(l0.length);
      
    	for(Project i: l0)
    	{
    		Assertions.assertThat(c0).hasListCell(i); 
    		
    	}
    	
    	return true;
    }
    public boolean checkiniedit(FxRobot robot) 
    {
    	Person A = new Person();
        Assertions.assertThat(robot.lookup("#nameLabel")
                .queryAs(Label.class)).hasText(m.com.name);
        Assertions.assertThat(robot.lookup("#editdesc")
                .queryAs(TextField.class)).hasText(m.com.description);  	
    	Person [] l1 = {
      		 A.findanperson(1000),A.findanperson(4)
      		  
      	};

      	ListView<Person> c1 = getpl1(robot);
        
      	Assertions.assertThat(c1).hasExactlyNumItems(l1.length);
        
      	for(Person i: l1)
      	{
      		Assertions.assertThat(c1).hasListCell(i); 
      		
      	}
      	
    	JobPosting [] l2 = {A.findajob(12),    		  
      	};
    	ListView<JobPosting> c2 = getjl1(robot);
        
    	Assertions.assertThat(c2).hasExactlyNumItems(l2.length);
      
    	for(JobPosting i: l2)
    	{
    		Assertions.assertThat(c2).hasListCell(i); 
    		
    	}
    	
        Project [] l0 = {A.findapro(9),    		  
      	};
    	ListView<Project> c0 = getprl1(robot);//checklist 1
        
    	Assertions.assertThat(c0).hasExactlyNumItems(l0.length);
      
    	for(Project i: l0)
    	{
    		Assertions.assertThat(c0).hasListCell(i); 
    		
    	}
    	
    	return true;
    }

    @Test    
    public void comtest(FxRobot robot) throws InterruptedException 
    {
    	Person A = new Person();
    	boolean a = checkini(robot);
    	assertTrue(a);
    	robot.clickOn("#Editbutton");
    	a = checkiniedit(robot);
    	assertTrue(a);
    	Person [] l1 = {
         		 A.findanperson(5),A.findanperson(6)
         		  
         	};

         	ListView<Person> c1 = getpl2(robot);
           
         	Assertions.assertThat(c1).hasExactlyNumItems(l1.length);
           
         	for(Person i: l1)
         	{
         		Assertions.assertThat(c1).hasListCell(i); 
         		
         	}
         	
       	JobPosting [] l2 = {A.findajob(11),    		  
         	};
       	ListView<JobPosting> c2 = getjl2(robot);
           
       	Assertions.assertThat(c2).hasExactlyNumItems(l2.length);
         
       	for(JobPosting i: l2)
       	{
       		Assertions.assertThat(c2).hasListCell(i); 
       		
       	}
       	
           Project [] l0 = {A.findapro(10),    		  
         	};
       	ListView<Project> c0 = getprl2(robot);//checklist 1
           
       	Assertions.assertThat(c0).hasExactlyNumItems(l0.length);
         
       	for(Project i: l0)
       	{
       		Assertions.assertThat(c0).hasListCell(i); 
       		
       	}
       	//end check rest lists
       	//check add button
       	sktpl2(robot,1);
       	robot.clickOn("#addpersonbutton");
       	sktprl2(robot,0);
       	robot.clickOn("#addprobutton");
       	sktjl2(robot,0);
       	robot.clickOn("#addjobbutton");
       	JobPosting [] ej = {};
       	c2 = getjl2(robot);
        
       	Assertions.assertThat(c2).hasExactlyNumItems(ej.length);
         
       	for(JobPosting i: ej)
       	{
       		Assertions.assertThat(c2).hasListCell(i); 
       		
       	}
        Project [] e = {};
    	c0 = getprl2(robot);//checklist 1
        
    	Assertions.assertThat(c0).hasExactlyNumItems(e.length);
      
    	for(Project i: e)
    	{
    		Assertions.assertThat(c0).hasListCell(i); 
    		
    	}
    	Person [] l1a = {
        		 A.findanperson(5)};

        ListView<Person> c1a = getpl2(robot);
    	Assertions.assertThat(c1a).hasExactlyNumItems(l1a.length);
    	for(Person i: l1a)
    	{
    		Assertions.assertThat(c1a).hasListCell(i); 
    		
    	}
    	
    	
    	Person [] l1q = {
        		 A.findanperson(1000),A.findanperson(4),A.findanperson(6)
        		  
        	};
    	Person temp = selectper(robot,1);
    	assertEquals(true,A.findanperson(4).equals(temp));
    	temp = selectper(robot,0);
    	assertEquals(true,A.findanperson(1000).equals(temp));
    	temp = selectper(robot,2);
    	assertEquals(true,A.findanperson(6).equals(temp));    	
     /*   	ListView<Person> c1q = getpl1(robot);
          
        	Assertions.assertThat(c1q).hasExactlyNumItems(l1q.length);
          
        	for(Person i: l1q)
        	{
        		
        		Assertions.assertThat(c1q).hasListCell(i); 
        		
        	}
        	*/
        	
      	JobPosting [] l2q = {A.findajob(12),A.findajob(11),  		  
        	};
      	ListView<JobPosting> c2q = getjl1(robot);
          
      	Assertions.assertThat(c2q).hasExactlyNumItems(l2q.length);
        
      	for(JobPosting i: l2q)
      	{
      		Assertions.assertThat(c2q).hasListCell(i); 
      		
      	}
      	
          Project [] l0a = {A.findapro(9), A.findapro(10),    		  
        	};
      	ListView<Project> c0a = getprl1(robot);//checklist 1
          
      	Assertions.assertThat(c0a).hasExactlyNumItems(l0a.length);
        
      	for(Project i: l0a)
      	{
      		Assertions.assertThat(c0a).hasListCell(i); 
      		
      	}
      	robot.clickOn("#editdesc");
      	robot.write("new com desc");
      	robot.clickOn("#savebutton");
    	ListView<Person> c1q = getpl1(robot);
    
    	Assertions.assertThat(c1q).hasExactlyNumItems(l1q.length);
  
    	for(Person i: l1q)
    	{
    		Assertions.assertThat(c1q).hasListCell(i); 
    		
    	}
    	
    	c2q = getjl1(robot);
	
    	Assertions.assertThat(c2q).hasExactlyNumItems(l2q.length);

    	for(JobPosting i: l2q)
    	{
    		Assertions.assertThat(c2q).hasListCell(i); 
		
    	}
    	c0a = getprl1(robot);//checklist 1
  
    	Assertions.assertThat(c0a).hasExactlyNumItems(l0a.length);

    	for(Project i: l0a)
    	{
    		Assertions.assertThat(c0a).hasListCell(i); 
		
    	}
	
    	robot.clickOn("#Editbutton");
    	//robot.clickOn("#editdesc");
    	//robot.write("companyC");
    	c1q = getpl1(robot);
    
    	Assertions.assertThat(c1q).hasExactlyNumItems(l1q.length);
	
    	for(Person i: l1q)
    	{
    		Assertions.assertThat(c1q).hasListCell(i); 
		
    	}
	
    	c2q = getjl1(robot);
  
    	Assertions.assertThat(c2q).hasExactlyNumItems(l2q.length);

    	for(JobPosting i: l2q)
    	{
    		Assertions.assertThat(c2q).hasListCell(i); 
		
    	}
    	c0a = getprl1(robot);//checklist 1
  
    	Assertions.assertThat(c0a).hasExactlyNumItems(l0a.length);

    	for(Project i: l0a)
    	{
    		Assertions.assertThat(c0a).hasListCell(i); }
       	
    	c2 = getjl2(robot);
        
       	Assertions.assertThat(c2).hasExactlyNumItems(ej.length);
         
       	for(JobPosting i: ej)
       	{
       		Assertions.assertThat(c2).hasListCell(i); 
       		
       	}

    	c0 = getprl2(robot);//checklist 1
        
    	Assertions.assertThat(c0).hasExactlyNumItems(e.length);
      
    	for(Project i: e)//
    	{
    		Assertions.assertThat(c0).hasListCell(i); 
    		
    	}
    	c1a = getpl2(robot);
    	Assertions.assertThat(c1a).hasExactlyNumItems(l1a.length);
    	for(Person i: l1a)
    	{
    		Assertions.assertThat(c1a).hasListCell(i); 
    		
    	}
    	sktpl1(robot,2);
    	robot.clickOn("#removeemplbutton");
    	sktprl1(robot,1);
    	robot.clickOn("#removeprobutton");
    	sktjl1(robot,1);
    	robot.clickOn("#removejobpostbutton");
    	a = checkiniedit(robot);
    	assertTrue(a);
    	robot.clickOn("#savebutton");
    	a = checkini(robot);
    	assertTrue(a);
    	robot.clickOn("#Editbutton");
       	sktpl2(robot,1);
       	robot.clickOn("#addpersonbutton");
       	sktprl2(robot,0);
       	robot.clickOn("#addprobutton");
    	sktpl1(robot,1);
    	robot.clickOn("#removeemplbutton");
       	sktjl2(robot,0);
       	robot.clickOn("#addjobbutton");
    	robot.clickOn("#editdesc");
    	robot.write("Cancel");
    	robot.clickOn("#cancelbutton");

        Assertions.assertThat(robot.lookup("#nameLabel")
                .queryAs(Label.class)).hasText(m.com.name);
        Assertions.assertThat(robot.lookup("#descLabel")
                .queryAs(Label.class)).hasText(m.com.description);  	
    	temp = selectper(robot,0);
    	assertEquals(true,A.findanperson(1000).equals(temp));
    	temp = selectper(robot,1);
    	assertEquals(true,A.findanperson(4).equals(temp));  
      	
		 ListView<JobPosting> plist = getjl1(robot);
		 plist.scrollTo(0);
		 plist.getSelectionModel().select(0);
		JobPosting s = plist.getSelectionModel().getSelectedItem();
	    assertEquals(true,A.findajob(12).equals(s)); 
       
	    
	    Project [] l0W = {A.findapro(9),    		  
      	};
    	ListView<Project> c0W = getprl1(robot);//checklist 1
        
    	Assertions.assertThat(c0W).hasExactlyNumItems(l0W.length);
      
    	for(Project i: l0W)
    	{
    		Assertions.assertThat(c0W).hasListCell(i); 
    		
    	}
    	
    	
    	
    
	
	
    
    
          

       	
       	
       	
       	
       	
       	
    	
    	
    	
		
	}

}
