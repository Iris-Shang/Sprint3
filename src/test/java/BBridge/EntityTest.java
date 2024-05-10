package BBridge;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import BBridge.Entity.ent;
//import simpleRESTServer.RDesc;
//import simpleRESTServer.SimpleRESTServerTest.RResponseArray;

class EntityTest
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
	static String uriBase;
	JobRecommender all;
	JRskill jrs;
	JRfollow jrf;
	Integer i1;
	//ArrayList<Entity> alltest = new ArrayList<Entity>();	
	@SuppressWarnings("unused")
	private static <T> void assertArray(ArrayList<T> A, ArrayList<T> B)
	{

		
		assertTrue(A.size() == B.size()); 
		assertTrue(A.containsAll(B));
	}
	
	
	@SuppressWarnings("unused")
	@BeforeAll
	public static void init()
	{
		//create team and classes
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

		

	@BeforeEach
	void setUp() throws Exception
	{
		i1 = 1;

		defaul = new Person();
		A = new Company(i1,"A","companyA");
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


		
		
	}



	
	@Test
	void JobPostingtest()
	{
		//constructor method
		assertEquals(JP.name,"JP");
		assertEquals(JP.ID,11);
		assertEquals(JP.description,"Job");
		assertEquals(JP.company,B.ID);
		assertEquals(JP.getCompany(),B.ID);
		//check creator
		DSJobPosting JPdata = JP.readfromserver();
		JobPosting readfromrest = JPdata.data();;
		assertEquals(JP.equals(readfromrest),true);
		
		JP.setCompany(A.ID);
		assertEquals(JP.company,A.ID);
		JP.updateinrest();
		JPdata = JP.readfromserver();
		readfromrest = JPdata.data();;
		assertEquals(JP.equals(readfromrest),true);
		
		ArrayList<Integer> check = new ArrayList<Integer>();
		check.add(python.ID);
		JP.addskill(java);
		JP.updateinrest();
		check.add(java.ID);
		assertArray(check,JP.skills);
		JP.remove(java);
		check.remove(java.ID);
		JP.updateinrest();
		assertArray(check,JP.skills);
		

		}



	@Test	
	void Newstest()
	{	
		Entity equ = new Entity(100, "dnews", "news post by D");
		assertEquals(N.name,"dnews");
		assertEquals(N.ID,100);
		assertEquals(N.description,"news post by D");
		assertEquals(N.article,"here is an article");
		assertEquals(N.owner,D.ID);
		assertEquals(N.getOwner(),D.ID);
		N.setOwner(E.ID);
		assertEquals(N.getOwner(),E.ID);
		N.setOwner(D.ID);
		
		DSNews Ndata = N.readfromserver();
		News check = Ndata.data();
		assertEquals(true,check.equals(N));
		assertEquals(N.getArticle(),"here is an article");
		N.setArticle("here is an updated article");		
		assertEquals(N.getArticle(),"here is an updated article");
		N.updateinrest();
		Ndata = N.readfromserver();
		check = Ndata.data();
		assertEquals(true,check.equals(N));
		N.updateinrest();
		assertEquals(true,check.equals(check));
		assertEquals(false,check.equals(null));
		assertEquals(false,check.equals(equ));
		

		
		
		
		
	}

	@Test
	void skilltest()
	{

		assertEquals(python.description,"skill python");
		assertEquals(python.name,"Python");
		assertEquals(python.ID,7);
		DSSkill SKdata = python.readfromserver();
		Skill check = SKdata.data();
		assertEquals(check.equals(python),true);
		python.setDescription("newpythondesc");
		python.updater();
		SKdata = python.readfromserver();
		check = SKdata.data();
		assertEquals(check.equals(python),true);
		//assertEquals(null,matlab.findskilledperson());

		
		
		}
		
		
	@Test
	void Persontest()
	{		
		assertEquals(defaul.name,"");
		assertEquals(defaul.ID,0);
		assertEquals(defaul.description,"");
		assertEquals(D.company,C.ID);
		assertEquals(D.ID,4);
		assertEquals(D.description,"personD");
		assertEquals(D.occupation,"manager");
		assertEquals(D.name,"D");
		assertEquals(D.getOccupation(),"manager");
		assertEquals(D,D.findanperson(D.ID));
		DSPerson Pdata = D.readfromserver();
		Person check = Pdata.data();
		assertEquals(true,check.equals(D));
		D.setOccupation("CEO");
		assertEquals(D.getOccupation(),"CEO");
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		D.setOccupation("manager");
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		assertEquals(D.getCompany(),C.ID);
		D.setCompany(A.ID);	
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		assertEquals(D.getCompany(),A.ID);
		D.setCompany(C.ID);
		 
		
		
		
		
		assertEquals(E.name,"E");
		assertEquals(E.ID,5);
		assertEquals(E.description,"personE");
		Pdata = E.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(E));
		
		
		D.addskill(python);
		//check updater
		E.updater();
		Pdata = E.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(E));
		
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(python.ID);
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		
		assertEquals(lis,D.links.get(ent.skill)); 
		D.addskill(java);
		lis.add(java.ID);
		assertEquals(lis,D.links.get(ent.skill));
		ArrayList<Integer> d = new ArrayList<Integer>();
		d.add(D.ID);
		assertEquals(d,python.links.get(ent.skilled_person));
		assertEquals(d,java.links.get(ent.skilled_person));
		D.removeskill(java);
		D.updater();
		java.updater();
		lis.remove(java.ID);
		assertEquals(lis,D.links.get(ent.skill));
		D.addskill(java);
		
// skill two people
		ArrayList<Integer> lpeople =  new ArrayList<Integer>();
		lpeople.add(D.ID);
		lpeople.add(E.ID);
		E.addskill(java);
		assertEquals(lpeople,java.links.get(ent.skilled_person));
		java.updater();
		
		ArrayList <Person> skilledperson = new ArrayList <Person>();
		skilledperson.add(D);
		skilledperson.add(E);
		assertArray(skilledperson,java.findskilledperson());
		
		
		
		//post news method
		D.post(100, "dnews", "news post by D", "here is an article");
		lis.clear();
		Integer inid = D.links.get(ent.news).get(0);
		News in = D.findanews(inid);
		lis.add(in.ID);
		assertEquals(lis,D.links.get(ent.news));
		assertEquals(in.name,"dnews");
		assertEquals(in.ID,100);
		assertEquals(in.description,"news post by D");
		assertEquals(in.article,"here is an article");
		assertEquals(in.owner,D.ID);
		D.post(100, "dnews", "news post by D", "here is an article");
		Integer in2id = D.links.get(ent.news).get(1);
		News in2 = D.findanews(in2id);
		lis.add(in2.ID);
		assertEquals(lis,D.links.get(ent.news));		
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		
		
		
		
		D.addProject(P);
		lis.clear();
		lis.add(P.ID);
		assertEquals(lis,D.links.get(ent.project));
		Integer proid =  D.links.get(ent.project).get(0);
		Project pro = D.findapro(proid);
		assertEquals(pro.name,"p");
		assertEquals(pro.ID,9);
		assertEquals(pro.description,"project p");
		assertEquals(pro.company,B.ID);
		assertEquals(d,P.links.get(ent.participant));
		D.addProject(R);
		lis.add(R.ID);
		assertEquals(lis,D.links.get(ent.project));
		D.removeProjects(P);
		lis.remove(P.ID);
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));

		assertEquals(lis,D.links.get(ent.project));
		lis.clear();
		assertEquals(lis,P.links.get(ent.participant));
		//one project two people test
		D.addProject(P);
		E.addProject(P);
		assertEquals(lpeople,P.links.get(ent.participant));
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		
			
		D.applyjob(JP);
		lis.add(JP.ID);
		assertEquals(lis,D.links.get(ent.applyjob));
		D.applyjob(jp);
		lis.add(jp.ID);
		assertEquals(lis,D.links.get(ent.applyjob));
		assertEquals(d,jp.links.get(ent.applied));
		E.applyjob(jp);
		assertEquals(lpeople,jp.links.get(ent.applied));
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		D.removejob(jp);
		lis.remove(jp.ID);
		assertEquals(lis,D.links.get(ent.applyjob));
		D.updater();
		jp.updateinrest();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		
		//person read and edit test add false read
		assertEquals(true,D.read(A));
		assertEquals(true,D.read(N));
		assertEquals(true,D.read(python));
		assertEquals(true,D.read(P));
		assertEquals(true,D.read(JP));
		assertEquals(true,D.read(D));
		lis.clear();
		lis.add(C.ID);
		lis.add(E.ID);
		P.links.put(ent.view,lis);
		assertEquals(true,C.read(P));
		assertEquals(false,D.read(P));
		assertEquals(true,E.read(P));
		assertEquals(false,A.read(P));
		
		
		
		
		assertEquals(false,D.edit(A));
		assertEquals(true,D.edit(N));
		assertEquals(false,D.edit(python));
		assertEquals(false,D.edit(P));
		assertEquals(false,D.edit(JP));
		assertEquals(true,D.edit(D));
		
		
		D.follow(C);
		lis.clear();
		lis.add(C.ID);
		assertEquals(lis,D.links.get(ent.followacount));
		D.follow(E);
		lis.add(E.ID);
		assertEquals(lis,D.links.get(ent.followacount));
		assertEquals(d,C.links.get(ent.follower));
		assertEquals(d,E.links.get(ent.follower));
		E.follow(C);
		assertEquals(lpeople,C.links.get(ent.follower));
		D.updater();
		Pdata = D.readfromserver();
		check = Pdata.data();
		assertEquals(true,check.equals(D));
		
		lis.clear();

		lis.add(JP.ID);
		lis.add(jp.ID);
		E.jobrecommended(JP);
		E.jobrecommended(jp);
		ArrayList<Integer> recjobs = E.retrieveRecomJob();
		assertEquals(lis,recjobs);
		
		
		
		
		
	}

	
	@Test
	void companyltest() 
	{
		
		
		ArrayList<Integer> lis = new ArrayList<Integer>();	
		assertEquals(C.name,"C");
		assertEquals(C.ID,3);
		assertEquals(C.description,"companyC");
		//Datasave Cdata = C.getr();
		DSCompany comp = C.readfromServer();
		Company check = comp.data();
		assertEquals(true,check.equals(C));
		
		C.addEmployee(F, "CEO");
		lis.add(F.ID);
		assertEquals(lis,C.links.get(ent.employee));
		assertEquals(F.occupation,"CEO");
		assertEquals(F.company,C.ID);
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));

		
		C.addEmployee(E, "CEO");
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		lis.add(E.ID);
		C.removeEmployee(F);
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		

		lis.remove(F.ID);
		assertEquals(lis,C.links.get(ent.employee));
		
		lis.clear();
		C.addProject(20, "Cpro", "Cproject");
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		
		Integer pro1 = C.links.get(ent.project).get(0);

		Project pro = C.findapro(pro1);
		assertEquals(pro.name,"Cpro");
		assertEquals(pro.ID,20);
		assertEquals(pro.description,"Cproject");
		assertEquals(pro.company,C.ID);
		lis.add(pro.ID);
		assertEquals(lis,C.links.get(ent.project));
		C.addProject(25, "Cpro", "Cproject");
		Integer pro2ID =  C.links.get(ent.project).get(1);
		Project pro2 =  C.findapro(pro2ID);
		lis.add(pro2.ID);
		assertEquals(lis,C.links.get(ent.project));
		C.removeProjects(pro);
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		
		lis.remove(pro.ID);
		assertEquals(lis,C.links.get(ent.project));
		assertEquals(null,pro.company);
		assertEquals(null,pro.links.get(ent.edit));
		
		lis.clear();
	
		ArrayList<Integer> skill_JP = new ArrayList<Integer>();
		ArrayList<Integer> sjp = new ArrayList<Integer>();
		skill_JP.add(python.getID());
		sjp.add(python.getID());
		sjp.add(java.getID());
		C.addJobPosting(40, "jp", "Cjp",sjp,"skilled");
		Integer jpid = C.links.get(ent.jobposting).get(0);
		JobPosting jp = C.findajob(jpid);
		assertEquals(jp.name,"jp");
		assertEquals(jp.ID,40);
		assertEquals(jp.description,"Cjp");
		assertEquals(jp.company,C.ID);
		lis.add(jp.ID);
		assertEquals(lis,C.links.get(ent.jobposting));

		C.addJobPosting(25, "Cpro", "Cproject",skill_JP,"follow");
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		Integer jp2id = C.links.get(ent.jobposting).get(1);
		JobPosting jp2 = C.findajob(jp2id);
		lis.add(jp2.ID);
		assertEquals(lis,C.links.get(ent.jobposting));
		
		C.addJobPosting(30, "Cproall", "Cproject",skill_JP,"all");
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		Integer jp3id = C.links.get(ent.jobposting).get(2);
		JobPosting jp3 = C.findajob(jp3id);
		lis.add(jp3.ID);
		assertEquals(lis,C.links.get(ent.jobposting));
	
		C.removeJobPosting(jp);
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		lis.remove(jp.ID);
		assertEquals(lis,C.links.get(ent.jobposting));
		assertEquals(null,jp.company);
		assertEquals(null,jp.links.get(ent.edit));
		
		C.post(101, "cnews", "news post by C", "here is an article");
		lis.clear();
		Integer inid = C.links.get(ent.news).get(0);
		News in = C.findanews(inid);
		lis.add(in.ID);
		assertEquals(lis,C.links.get(ent.news));
		assertEquals(in.name,"cnews");
		assertEquals(in.ID,101);
		assertEquals(in.description,"news post by C");
		assertEquals(in.article,"here is an article");
		assertEquals(in.owner,C.ID);
		C.post(102, "dnews", "news post by C", "here is an article");
		C.updateinrest();
		comp = C.readfromServer();
		check = comp.data();
		assertEquals(true,check.equals(C));
		Integer in2id = C.links.get(ent.news).get(1);
		News in2 = C.findanews(in2id);
		lis.add(in2.ID);
		assertEquals(lis,C.links.get(ent.news));
		
		
		
		
		assertEquals(true,C.read(A));
		assertEquals(true,C.read(N));
		assertEquals(true,C.read(python));
		assertEquals(true,C.read(P));
		assertEquals(true,C.read(JP));
		assertEquals(true,C.read(D));
		//test read false
		
		
		
		assertEquals(false,C.edit(A));
		assertEquals(false,C.edit(N));
		assertEquals(false,C.edit(python));
		assertEquals(false,C.edit(P));
		assertEquals(false,C.edit(JP));
		assertEquals(true,C.edit(C));
		assertEquals(true,C.edit(jp2));
		assertEquals(true,C.edit(pro2));
		


		
		

		
	}

	@Test
	void Recommendertest() 
	{

		

		
		
		Company jrc = new Company(200,"jrc","Company");
		jrc.createinrest();
		Person a = new Person(201,"a","skillmat","abc");
		a.createinrest();
		Person b = new Person(202,"b","noskill,followC","abc");
		b.createinrest();
		Person d = new Person(203,"d","skillmatfollowC","abc");
		d.createinrest();
		matlab = new Skill(204,"matlab","skillmatlab");
		assertEquals(null,matlab.findskilledperson());
		a.addskill(matlab);
		d.addskill(matlab);
		b.follow(jrc);
		d.follow(jrc);
		ArrayList<Integer> skill_JP = new ArrayList<Integer>();
		ArrayList<Integer> sjp = new ArrayList<Integer>();
		skill_JP.add(matlab.getID());
		sjp.add(matlab.getID());
		sjp.add(java.getID());
		JobPosting jp1 = new JobPosting(11,"JP","Job",jrc,skill_JP);
		ArrayList<Person> lis1 = new ArrayList<Person>();
		ArrayList<Person> lis2 = new ArrayList<Person>();
		ArrayList<Person> lis3 = new ArrayList<Person>();

		JobPosting jp2 = new JobPosting(12,"jp","Job",jrc,sjp);
		JobPosting jp3 = new JobPosting(13,"jp3","Job3",jrc,sjp);

		all = new JobRecommender(0,"JRall","notify all",jp2);
		//test all
		ArrayList<Integer> lisa = new ArrayList<Integer>();
		ArrayList<Integer> lisb = new ArrayList<Integer>();
		ArrayList<Integer> lisd = new ArrayList<Integer>();
		lisa.add(jp2.ID);
		lisb.add(jp2.ID);
		lisd.add(jp2.ID);
		ArrayList<Person> check = all.getalllisteners();
		all.recommendp();
		check = all.getalllisteners();
		a = a.findanperson(a.ID);
		b = b.findanperson(b.ID);
		d = d.findanperson(d.ID);
		D = a.findanperson(D.ID);
		E = b.findanperson(E.ID);
		F = d.findanperson(F.ID);
		lis2.add(a);
		lis2.add(D);
		lis2.add(b);
		lis2.add(E);
		lis2.add(d);
		lis2.add(F);
		assertArray(check,lis2);
		//assertEquals(a.links.get(ent.recommendjob),null);

		assertEquals(a.links.get(ent.recommendjob),lisa);
		assertEquals(b.links.get(ent.recommendjob),lisb);
		assertEquals(d.links.get(ent.recommendjob),lisd);
		
		jrs = new JRskill(0,"JRskill","notify skilled",jp1);
		lisa.add(jp1.ID);
		lisd.add(jp1.ID);
		lis1.add(a);
		lis1.add(d);
		check = jrs.getListeners();
		assertArray(check,lis1);
		jrs.recommendp();
		a = a.findanperson(a.ID);
		b = b.findanperson(b.ID);
		d = d.findanperson(d.ID);
		assertEquals(a.links.get(ent.recommendjob),lisa);
		assertEquals(b.links.get(ent.recommendjob),lisb);
		assertEquals(d.links.get(ent.recommendjob),lisd);
		lis1.clear();
		lis1.add(a);
		lis1.add(d);
		assertArray(check,lis1);
		
		
		jrf = new JRfollow(0,"JRfollow","notify skilled",jp3);
		lisb.add(jp3.ID);
		lisd.add(jp3.ID);
		check = jrf.getListeners();
		a = a.findanperson(a.ID);
		b = b.findanperson(b.ID);
		d = d.findanperson(d.ID);
		lis3.add(b);
		lis3.add(d);
		assertArray(check,lis3);
		jrf.recommendp();
		a = a.findanperson(a.ID);
		b = b.findanperson(b.ID);
		d = d.findanperson(d.ID);
		lis3.clear();
		lis3.add(b);
		lis3.add(d);
		assertArray(check,lis3);
		assertEquals(a.links.get(ent.recommendjob),lisa);
		assertEquals(b.links.get(ent.recommendjob),lisb);
		assertEquals(d.links.get(ent.recommendjob),lisd);
		
		
		
		
		
		assertEquals(jrs.name,"JRskill");
		assertEquals(jrs.ID,0);
		assertEquals(jrs.description,"notify skilled");
		assertEquals(jrf.name,"JRfollow");
		assertEquals(jrf.ID,0);
		assertEquals(jrf.description,"notify skilled");
		assertEquals(all.name,"JRall");
		assertEquals(all.ID,0);
		assertEquals(all.description,"notify all");


		

		
		
		

		
	}


}
