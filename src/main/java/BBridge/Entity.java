package BBridge;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

import org.springframework.web.client.RestClient;

//import BBridge.Entity.ent;

public class Entity
{
	
	public enum ent
	{
		skilled_person,
		company,
		project,
		news,
		skill,
		jobposting,
		edit,
		view,
		follower,
		followacount,
		employee,
		collaborator,
		applied,
		applyproject,
		applyjob,
		participant,
		recommendjob,
		
		
		
		
		
	}
	
	Integer ID;
	String name;
	String description;
	public Map<ent,ArrayList<Integer>> links;
	public Entity(int ID, String name, String description) 
	{
		this.ID = ID;
		this.name = name;
		this.description = description;
		this.links = new EnumMap<>(ent.class);
		
	}
	
	public Entity() {}

	/**
	 * @return the iD
	 */
	public Integer getID()
	{
		return ID;
	}

	/**
	 * @param iD the iD to set
	 */
	public void setID(Integer iD)
	{
		ID = iD;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the description
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}

	/**
	 * @return the links
	 */
	public Map<ent, ArrayList<Integer>> getLinks()
	{
		return links;
	}

	/**
	 * @param links the links to set
	 */
	public void setLinks(Map<ent, ArrayList<Integer>> links)
	{
		this.links = links;
	}


	@Override

	public String toString()
	{
		return "ID " + ID + ": " + name + description;
	}
	
	public Project findapro(Integer check) 
	{
		RestClient client = RestClient.create();
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Project"+"/"+check;
		DSProject read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSProject.class);
		return read.data();

	}
	
	public JobPosting findajob(Integer check) 
	{
		RestClient client = RestClient.create();
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/JobPosting"+"/"+check;
		DSJobPosting read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSJobPosting.class);
		return read.data();

	}
	public News findanews(Integer check) 
	{
		RestClient client = RestClient.create();
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/News"+"/"+check;
		DSNews read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSNews.class);
		return read.data();

	}
	public Person findanperson(Integer check) 
	{
		RestClient client = RestClient.create();
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Person"+"/"+check;
		DSPerson read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSPerson.class);
		return read.data();

	}
	
	public Company findacom(Integer check) 
	{
		RestClient client = RestClient.create();
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Company"+"/"+check;
		DSCompany read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSCompany.class);
		return read.data();

	}
	
	public Skill findaskill(Integer check) 
	{
		RestClient client = RestClient.create();
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Skill"+"/"+check;
		DSSkill read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSSkill.class);
		return read.data();

	}
	
	
	public ArrayList<Entity> findallEntity() 
	{
		RestClient client = RestClient.create();
		ArrayList<Entity> listeners = new ArrayList<Entity>();
		String uriBase = "http://localhost:9000/v1/Project1/Entity";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSEntity read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSEntity.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
		
	}
	
	public ArrayList<Skill> findallSkill() 
	{
		RestClient client = RestClient.create();
		ArrayList<Skill> listeners = new ArrayList<Skill>();
		String uriBase = "http://localhost:9000/v1/Project1/Skill";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSSkill read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSSkill.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
	}
	
	public ArrayList<Company> findallCompany() 
	{
		RestClient client = RestClient.create();
		ArrayList<Company> listeners = new ArrayList<Company>();
		String uriBase = "http://localhost:9000/v1/Project1/Company";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSCompany read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSCompany.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
	}
	public ArrayList<Person> findallPerson() 
	{
		RestClient client = RestClient.create();
		ArrayList<Person> listeners = new ArrayList<Person>();
		String uriBase = "http://localhost:9000/v1/Project1/Person";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSPerson read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSPerson.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
	}

		
	public ArrayList<JobPosting> findallJobPosting() 
	{
		RestClient client = RestClient.create();
		ArrayList<JobPosting> listeners = new ArrayList<JobPosting>();
		String uriBase = "http://localhost:9000/v1/Project1/JobPosting";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSJobPosting read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSJobPosting.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
	}
	
	public ArrayList<Project> findallProject() 
	{
		RestClient client = RestClient.create();
		ArrayList<Project> listeners = new ArrayList<Project>();
		String uriBase = "http://localhost:9000/v1/Project1/Project";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSProject read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSProject.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
	}
	
	public ArrayList<News> findallnews() 
	{
		RestClient client = RestClient.create();
		ArrayList<News> listeners = new ArrayList<News>();
		String uriBase = "http://localhost:9000/v1/Project1/News";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSNews read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSNews.class);

			listeners.add(read.data());

			
		}
		return listeners;
		
	}
	
	
	
	
	@Override
	public int hashCode()
	{
		return Objects.hash(ID, description, links, name);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		Entity other = (Entity) obj;
		
		for(ent e: ent.values()) 
		{
			ArrayList<Integer> i1 = this.links.get(e);
			ArrayList<Integer> i2 = other.links.get(e);
			if (i1 != null && i2 == null) {
				//System.out.println("something is null,this is not null"+e+this+other);
				return false;}
			if (i2 != null && i1 == null) {
				//System.out.println("something is null"+e+this+other);
				return false;}
			if (i1 != null && i2 != null) 
			{
				if(!i1.equals(i2)) 
				{
					System.out.println(e + "has some problem"+this+other);
					System.out.println(other);
					System.out.println(i2);
					System.out.println(i1);
					return false;
				}
			}


			
		}
		return Objects.equals(ID, other.ID) && Objects.equals(description, other.description)
				 && Objects.equals(name, other.name);
	}
	public ArrayList<JobPosting> applyjobl()
	{
		ArrayList<JobPosting> jobs = new ArrayList<JobPosting> ();
		ArrayList<Integer> lis = this.links.get(ent.applyjob);
		if (lis != null) {
		for (Integer i : lis) 
		{
			JobPosting a = this.findajob(i);
			jobs.add(a);
		}}
		return jobs;
		
	}
	public ArrayList<JobPosting> jobl()
	{
		ArrayList<JobPosting> jobs = new ArrayList<JobPosting> ();
		ArrayList<Integer> lis = this.links.get(ent.jobposting);
		if (lis != null) {
		for (Integer i : lis) 
		{
			JobPosting a = this.findajob(i);
			jobs.add(a);
		}}
		return jobs;
		
	}
	public ArrayList<Project> prol()
	{
		ArrayList<Project> li = new ArrayList<Project> ();
		ArrayList<Integer> lis = this.links.get(ent.project);
		if (lis != null) 
		{
		for (Integer i : lis) 
		{
			Project a = this.findapro(i);
			li.add(a);
		}
		}
		return li;
		
	}
	/*public ArrayList<Project> applyprol()
	{
		ArrayList<Project> li = new ArrayList<Project> ();
		ArrayList<Integer> lis = this.links.get(ent.applyproject);
		if (lis != null) {
		for (Integer i : lis) 
		{
			Project a = this.findapro(i);
			li.add(a);
		}
		}
		return li;
		
	}*/
	public ArrayList<Skill> skil()
	{
		ArrayList<Skill> li = new ArrayList<Skill> ();
		ArrayList<Integer> lis = this.links.get(ent.skill);
		if (lis != null) {
		for (Integer i : lis) 
		{
			Skill a = this.findaskill(i);
			li.add(a);
		}}
		return li;
		
	}
	public ArrayList<Person> emplyl()
	{
		ArrayList<Person> li = new ArrayList<Person> ();
		ArrayList<Integer> lis = this.links.get(ent.employee);
		if (lis != null) {
		for (Integer i : lis) 
		{
			Person a = this.findanperson(i);
			li.add(a);
		}}
		return li;
		
	}
	
	public ArrayList<Person> jobappl()
	{
		ArrayList<Person> li = new ArrayList<Person> ();
		ArrayList<Integer> lis = this.links.get(ent.applied);
		if (lis != null) {
		for (Integer i : lis) 
		{
			Person a = this.findanperson(i);
			li.add(a);
		}}
		return li;
		
	}
	
	
	
	
	public ArrayList<Integer> retrieveRecomJob()
	{
		ArrayList<Integer> recjobs= this.links.get(ent.recommendjob);
		
		return recjobs;
	}
	

	
	/*
	 * 
	 * 
	 * 
	 * 	public ArrayList<Project> findallProject() 
	{
		RestClient client = RestClient.create();
		ArrayList<Project> listeners = new ArrayList<Project>();
		String uriBase = "http://localhost:9000/v1/Project1/Project";

		Reteam persons = client.get()
				  .uri(uriBase)
				  .retrieve()
				  .body(Reteam.class);
		ArrayList<RDesc> rdsec= persons.data();
		for (RDesc i : rdsec) 
		{
			String urib = i.location();
			DSProject read = client.get()
					  .uri(urib)
					  .retrieve()
					  .body(DSProject.class);

			listeners.add(read.data());

			
		}
		return listeners;
	 * 
	 * 
	 * public ArrayList<Entity> readallentities()
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Entity";
		Rteam read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(Rteam.class);
		return read.data(); 
		

	}*/
	/*public Entity giveanentity(Integer check) 
	{
		 ArrayList<Entity> all = readallentities();
		 Entity ret = null;
		 for (Entity i : all) 
		 {
			 if ((i.ID).equals(check))
			 {
				 ret = i;
			 }
		 }
		 return ret;
		
		
	}*/
}
