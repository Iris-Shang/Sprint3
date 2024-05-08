package BBridge;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import BBridge.Entity.ent;

@SuppressWarnings("unused")
public class Skill extends Entity
{

	public Skill(int ID, String name,String description)
	{
		super(ID, name,description);
		// TODO Auto-generated constructor stub
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(1000);
		this.links.put(ent.edit,lis);

	}
	public Skill() {};
	public void createinrest() 
	{
		//when user create an account, create an object at the same time to rest to save data
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Skill/"+this.ID;

		@SuppressWarnings("unused")
		String savedata = client.post()
		.uri(urib)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);
		
		String urie = uriBase+"/Project1/Entity/"+this.ID;		
		@SuppressWarnings("unused")
		String saved = client.post()
		.uri(urie)
		.contentType(MediaType.APPLICATION_JSON)
		.body(this)
		.retrieve()
		.body(String.class);

		
		
	}
	
	public DSSkill readfromserver() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Skill/"+this.ID;
		DSSkill read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSSkill.class);
		
		return read;
		
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
	
	public ArrayList<Person> findskilledperson()
	{
		ArrayList<Person> ski = new ArrayList<Person>();
		
		if(this.links.get(ent.skilled_person) == null) {return null;}
		for (Integer i : this.links.get(ent.skilled_person)) 
		{
			Person a = this.findanperson(i);
			ski.add(a);
		}
		return ski;
	}
	public void updater() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Skill/"+this.ID;

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

}
