package BBridge;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import BBridge.Entity.ent;

@SuppressWarnings("unused")
public class Company extends Entity
{

	public Company() {};
	public Company(int ID, String name, String description)
	{
		super(ID, name, description);
		// TODO Auto-generated constructor stub
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(this.ID);
		lis.add(1000);
		this.links.put(ent.edit,lis);

	}
	
	public void addEmployee(Person Employ, String occupation) 
	{
		if (this.links.get(ent.employee) == null) 
		{
			ArrayList<Integer> empl = new ArrayList<Integer>();
			empl.add(Employ.ID);
			this.links.put(ent.employee, empl);
		}
		else {
			ArrayList<Integer> empl = this.links.get(ent.employee);
			empl.add(Employ.ID);
			this.links.put(ent.employee, empl);
		}
		Employ.occupation = occupation;
		Employ.company = this.ID;
		Employ.updater();
		updateinrest();
	}
	public void addProject(int ID, String name,String description) 
		{
			Project pro = new Project(ID,name,description,this);
			if (this.links.get(ent.project) == null) 
			{
				ArrayList<Integer> lis = new ArrayList<Integer>();
				lis.add(pro.ID);
				this.links.put(ent.project, lis);
			}
			else {
				ArrayList<Integer> lis = this.links.get(ent.project);
				lis.add(pro.ID);
				this.links.put(ent.project, lis);
			}
			pro.createinrest();
			updateinrest();

	}
	public void addJobPosting(int ID, String name,String description,ArrayList<Integer> skills,String recommendpeople) 
	{
		JobPosting jp = new JobPosting(ID,name,description,this,skills);
		if (this.links.get(ent.jobposting) == null) 
		{
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(jp.ID);
			this.links.put(ent.jobposting, lis);
		}
		else {
			ArrayList<Integer> lis = this.links.get(ent.jobposting);
			lis.add(jp.ID);
			this.links.put(ent.jobposting, lis);
		}
		if (recommendpeople == "skilled") 
		{
			JobRecommender jr = new JRskill(ID+1, name+"JR", "recommend to skilled people", jp);
			jr.recommendp();
		}
		else if (recommendpeople == "follow") 
		{
			JobRecommender jr = new JRfollow(ID+1, name+"JR", "recommend to follower", jp);
			jr.recommendp();
		}
		else 
		{
		
			JobRecommender jr = new JobRecommender(ID+1, name+"JR", "recommend to everyone", jp);
			jr.recommendp();
		}
		jp.createinrest();
		updateinrest();
	}
	public void post(int ID, String name, String description, String article) 
	{
		News n = new News(ID, name, description,article, this);
		if (this.links.get(ent.news) == null) 
		{
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(n.ID);
			this.links.put(ent.news, lis);
		}
		else {
			ArrayList<Integer> lis = this.links.get(ent.news);
			lis.add(n.ID);
			this.links.put(ent.news, lis);
		}
		n.createinrest();
		updateinrest();
		
	}
	public void removeJobPosting(JobPosting jp) 
	{
		ArrayList<Integer> lis = this.links.get(ent.jobposting);
		lis.remove(jp.ID);
		this.links.put(ent.jobposting, lis);
		jp.links.put(ent.edit, null);
		jp.company = null;
	
	}
	public void removeEmployee(Person Employ) 
	{
		ArrayList<Integer> lis = this.links.get(ent.employee);
		lis.remove(Employ.ID);
		this.links.put(ent.employee, lis);
		Employ.company = null;
		Employ.occupation = null;
		Employ.updater();

		
	}
	public void removeProjects(Project pro) 
	{
		ArrayList<Integer> lis = this.links.get(ent.project);
		lis.remove(pro.getID());
		this.links.put(ent.project, lis);
		pro.company = null;
		pro.links.put(ent.edit, null);

	}
	
	public boolean read(Entity page) 
	{
		if (page.links.get(ent.view) == null) 
		{
			return true;
		}
		ArrayList<Integer> lis = page.links.get(ent.view);
		if (lis.contains(this.ID)) 
		{
			return true;
		}
		return false;
		
	}
	
	public boolean edit(Entity page) 
	{
		/*if (page.links.get(ent.edit) == null) 
		{
			return false;
		}*/
		ArrayList<Integer> lis = page.links.get(ent.edit);
		if (lis.contains(this.ID)) 
		{
			return true;
		}
		return false;
	}
	
	public void createinrest() 
	{
		//when user create an account, create an object at the same time to rest to save data
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Company/"+this.ID;

		String savedata = client.post()
		.uri(urib)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);

		String urie = uriBase+"/Project1/Entity/"+this.ID;		
		String saved = client.post()
		.uri(urie)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);

		
		
	}
	public void updateinrest() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Company/"+this.ID;

		String savedata = client.put()
		.uri(urib)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);
		
		String urie = uriBase+"/Project1/Entity/"+this.ID;		
		String saved = client.put()
		.uri(urie)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);
		
	}
	
	@Override
	public int hashCode()
	{
		return super.hashCode();
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		return true;
	}

	public DSCompany readfromServer() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Company/"+this.ID;
		DSCompany read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSCompany.class);
		return read;
		
	}



}
