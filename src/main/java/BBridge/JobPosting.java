package BBridge;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import BBridge.Entity.ent;
@SuppressWarnings("unused")
public class JobPosting extends Entity
{
	Integer company;
	ArrayList<Integer> skills;
	public JobPosting() {};
	public JobPosting(int ID, String name,String description,Company com, ArrayList<Integer> skills)
	{
		super(ID, name, description);
		// TODO Auto-generated constructor stub
		this.company = com.ID;
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(com.ID);
		lis.add(1000);
		this.links.put(ent.edit,lis);
		this.links.put(ent.skill,skills);
		this.skills = skills;

		
	}
	public void createinrest() 
	{
		//when user create an account, create an object at the same time to rest to save data
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/JobPosting/"+this.ID;

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
		String urib = uriBase+"/Project1/JobPosting/"+this.ID;

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

	public DSJobPosting readfromserver() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/JobPosting/"+this.ID;
		DSJobPosting read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSJobPosting.class);
		return read;
		
	}
	/*public void deleter() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/JobPosting/"+this.name;
		Datasave del = client.delete()
				  .uri(urib)
				  .retrieve()
				  .body(Datasave.class);

	}*/
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(company, skills);
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
		JobPosting other = (JobPosting) obj;
		return Objects.equals(company, other.company) && Objects.equals(skills, other.skills);
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
	/**
	 * @return the skills
	 */
	public ArrayList<Integer> getSkills()
	{
		return skills;
	}
	/**
	 * @param skills the skills to set
	 */
	public void setSkills(ArrayList<Integer> skills)
	{
		this.skills = skills;
	}
	
	
	
	public ArrayList<Skill> getskill()
	{
		ArrayList<Skill> ski = new ArrayList<Skill>();
		//if(this.skills == null) {return null;}
		for (Integer i : this.skills) 
		{
			Skill a = this.findaskill(i);
			ski.add(a);
		}
		return ski;
	}
	
	public void addskill(Skill s) 
	{
		this.skills.add(s.ID);
		this.links.put(ent.skill,skills);	
		
	}
	public void remove(Skill s) 
	{
		this.skills.remove(s.ID);
		this.links.put(ent.skill,skills);	
		
	}


	

	

}
