package BBridge;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Objects;

import BBridge.Entity.ent;


@SuppressWarnings("unused")
public class Person extends Entity
{
	String occupation;
	Integer company;
	String password;
	public static final String PATH = "http://localhost:9000/";
	
	public Person()
	{
		super(0, "", "");
		
	}

	public Person(int ID, String name, String description, String password)
	{
		super(ID, name, description);
		// TODO Auto-generated constructor stub
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(this.ID);
		lis.add(1000);
		this.links.put(ent.edit,lis);
		this.password = password;

	}
	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	public Person(int ID, String name, String occupation,String description, Integer company, String password)
	{
		super(ID, name,description);
		// TODO Auto-generated constructor stub
		this.occupation = occupation;
		this.company = company;
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(this.ID);
		lis.add(1000);
		this.links.put(ent.edit,lis);
		this.password = password;
		
	}
	public void addskill(Skill skill)
	{
		if (this.links.get(ent.skill) == null) 
		{
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(skill.ID);
			this.links.put(ent.skill, lis);
		}
		else {
			ArrayList<Integer> lis = this.links.get(ent.skill);
			lis.add(skill.ID);
			this.links.put(ent.skill, lis);
		}	
		if (skill.links.get(ent.skilled_person) == null) 
		{
			ArrayList<Integer> lispro = new ArrayList<Integer>();
			lispro.add(this.ID);
			skill.links.put(ent.skilled_person,lispro);
			
		}
		else 
		{
			ArrayList<Integer> lispro = skill.links.get(ent.skilled_person);
			lispro.add(this.ID);
			skill.links.put(ent.skilled_person,lispro);
			
		}
		updater();
	}
	
	public void removeskill(Skill s) 
	{
		ArrayList<Integer> lis = this.links.get(ent.skill);
		lis.remove(s.ID);
		this.links.put(ent.skill, lis);
		lis = s.links.get(ent.skilled_person);
		lis.remove(this.ID);
		s.links.put(ent.skilled_person, lis);
		s.updater();

		updater();
	}
	public void post(int ID, String name, String description,String article) 
	{
		News n = new News(ID, name, description,article,this);
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
		updater();
		
	}
	public void addProject(Project pro) 
	{
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
		if (pro.links.get(ent.participant) == null) 
		{
			ArrayList<Integer> lispro = new ArrayList<Integer>();
			lispro.add(this.ID);
			pro.links.put(ent.participant,lispro);
			
		}
		else 
		{
			ArrayList<Integer> lispro = pro.links.get(ent.participant);
			lispro.add(this.ID);
			pro.links.put(ent.participant,lispro);
			
		}
		updater();
		
	}
	public void removeProjects(Project pro) 
	{
		ArrayList<Integer> lis = this.links.get(ent.project);
		lis.remove(pro.ID);
		this.links.put(ent.project, lis);
		ArrayList<Integer> lispro = pro.links.get(ent.participant);
		lispro.remove(this.getID());
		pro.links.put(ent.participant,lispro);
		pro.updateinrest();
		updater();
	}
	public void applyjob(JobPosting job) 
	{
		if (this.links.get(ent.applyjob) == null) 
		{
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(job.ID);
			this.links.put(ent.applyjob, lis);
		}
		else {
			ArrayList<Integer> lis = this.links.get(ent.applyjob);
			lis.add(job.ID);
			this.links.put(ent.applyjob, lis);
		}	
		if (job.links.get(ent.applied) == null) 
		{
			ArrayList<Integer> lispro = new ArrayList<Integer>();
			lispro.add(this.getID());
			job.links.put(ent.applied,lispro);
			
		}
		else 
		{
			ArrayList<Integer> lispro = job.links.get(ent.applied);
			lispro.add(this.ID);
			job.links.put(ent.applied,lispro);
			
		}
		updater();
	}
	
	public void removejob(JobPosting job) 
	{
		ArrayList<Integer> lis = this.links.get(ent.applyjob);
		lis.remove(job.ID);
		this.links.put(ent.applyjob, lis);
		lis = job.links.get(ent.applied);
		lis.remove(this.ID);
		job.updateinrest();

		updater();
	}
	
	public void follow(Entity account) 
	{
		if (this.links.get(ent.followacount) == null) 
		{
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(account.ID);
			this.links.put(ent.followacount, lis);
		}
		else {
			ArrayList<Integer> lis = this.links.get(ent.followacount);
			lis.add(account.ID);
			this.links.put(ent.followacount, lis);
		}	
		if (account.links.get(ent.follower) == null) 
		{
			ArrayList<Integer> lispro = new ArrayList<Integer>();
			lispro.add(this.ID);
			account.links.put(ent.follower,lispro);
			
		}
		else 
		{
			ArrayList<Integer> lispro = account.links.get(ent.follower);
			lispro.add(this.ID);
			account.links.put(ent.follower,lispro);
			
		}
		updater();
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

	/**
	 * @return the occupation
	 */
	public String getOccupation()
	{
		return occupation;
	}

	/**
	 * @param occupation the occupation to set
	 */
	public void setOccupation(String occupation)
	{
		this.occupation = occupation;
		updater();
	}


	/**
	 * @return the company
	 */
	public Integer getCompany()
	{
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(Integer company)
	{
		this.company = company;
	}

	public void createinrest() 
	{
		//when user create an account, create an object at the same time to rest to save data
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Person/"+this.ID;

		String savedata = client.post()
		.uri(urib)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);
		//System.out.println(savedata);
		
		String urie = uriBase+"/Project1/Entity/"+this.ID;		
		String saved = client.post()
		.uri(urie)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);
		
		

		
		
	}
	public void updater() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Person/"+this.ID;

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
	
	public DSPerson readfromserver() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Person/"+this.ID;
		DSPerson read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSPerson.class);
		return read;
		
	}
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(company, occupation);
		return result;
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
		Person other = (Person) obj;
		return Objects.equals(company, other.company) && Objects.equals(occupation, other.occupation);
	}

	public void jobrecommended(JobPosting job) 
	{
		if (this.links.get(ent.recommendjob) == null) 
		{
			ArrayList<Integer> lis = new ArrayList<Integer>();
			lis.add(job.ID);
			this.links.put(ent.recommendjob, lis);
		}
		else {
			ArrayList<Integer> lis = this.links.get(ent.recommendjob);
			lis.add(job.ID);
			this.links.put(ent.recommendjob, lis);
		}
		//updater();
		
	}

	/*@Override
	public String toString()
	{
		return "ID " + ID + " Person " + name + description;
	}
*/

}
