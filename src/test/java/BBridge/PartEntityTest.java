package BBridge;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import static org.junit.jupiter.api.Assertions.*;

//import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

//import BBridge.Entity.ent;

class PartEntityTest
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
	Project P;
	Project R;
	JobPosting JP;
	JobPosting jp;
	
	@SuppressWarnings("unused")
	private static <T> void assertArray(ArrayList<T> A, ArrayList<T> B)
	{

		
		assertTrue(A.size() == B.size()); 
		assertTrue(A.containsAll(B));
	}

	@SuppressWarnings("unused")
	@BeforeEach
	void setUp() throws Exception
	{
		//create team and classes
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		
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
		
		RDesc jp = new RDesc("JobPosting","JobPostinglists",uriBase+"/Project1/JobPosting");
		String jobp = client.post()
		.uri(uriBase+"/Project1/JobPosting")
		.contentType(MediaType.APPLICATION_JSON)
		.body(jp)
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
		
	}

	@Test
	void test()
	{

		ArrayList<Entity> alltest = new ArrayList<Entity>();
		defaul = new Person();
		A = new Company(1,"A","companyA");
		A.createinrest();
		B = new Company(2,"B","companyB");

		B.createinrest();
		C = new Company(3,"C","companyC");
		C.createinrest();
		D = new Person(4,"D","manager","personD",C.ID,"abc");
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


		alltest.add(JP);
		alltest.add(jp);
		alltest.add(A);
		alltest.add(N);
		alltest.add(B);
		alltest.add(C);
		alltest.add(D);
		alltest.add(E);
		alltest.add(F);
		alltest.add(python);
		alltest.add(java);
		alltest.add(P);
		alltest.add(R);
		assertEquals(alltest.toString(),A.findallEntity().toString());
		//assertEquals((Entity)alltest.get(1),A.findallEntity().get(1));
		assertEquals(B,B.findacom(B.ID));
		assertEquals(python,python.findaskill(python.ID));
		
		ArrayList<Person> check = new ArrayList<Person>();
		check.add(D);
		check.add(E);
		check.add(F);
		ArrayList<Person> restp = D.findallPerson();
		assertArray(check,restp);
		ArrayList<Project> check2 = new ArrayList<Project>();
		check2.add(P);
		check2.add(R);
		ArrayList<Project> restpro = D.findallProject();
		assertArray(check2,restpro);
		ArrayList<News> check3 = new ArrayList<News>();
		check3.add(N);
		ArrayList<News> restnews = D.findallnews();
		assertArray(check3,restnews);
		ArrayList<JobPosting> check4 = new ArrayList<JobPosting>();
		check4.add(JP);
		check4.add(jp);
		ArrayList<JobPosting> check4c = jp.findallJobPosting();
		assertArray(check4,check4c);
		
		ArrayList<Skill> check5 = new ArrayList<Skill>();
		check5.add(python);
		check5.add(java);
		ArrayList<Skill> check5c = jp.findallSkill();
		assertArray(check5,check5c);
		
		ArrayList<Company> check6 = new ArrayList<Company>();
		check6.add(A);
		check6.add(B);
		check6.add(C);
		ArrayList<Company> check6c = jp.findallCompany();
		assertArray(check6,check6c);
		
		
		assertEquals(P.name,"p");
		assertEquals(P.ID,9);
		assertEquals(P.description,"project p");
		assertEquals(P.company,B.ID);
		assertEquals(P.getCompany(),B.ID);	
		DSProject Pdata = P.ReadfromServer();
		Project checkp = Pdata.data();
		assertEquals(checkp.equals(P),true);
		P.setCompany(A.ID);
		assertEquals(P.company,A.ID);
		P.updateinrest();
		Pdata = P.ReadfromServer();
		checkp = Pdata.data();
		assertEquals(checkp.equals(P),true);
		
		assertEquals(P.company,A.ID);
		P.setCompany(B.ID);
		//get the data from the rest and test
		//Pdata = P.ReadfromServer();

		
	}

}
