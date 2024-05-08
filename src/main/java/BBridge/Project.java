package BBridge;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import BBridge.Entity.ent;
@SuppressWarnings("unused")
public class Project extends Entity
{

	Integer company;
	public Project() {};
	public Project(int ID, String name,String description,Company company)
	{
		super(ID, name,description);
		// TODO Auto-generated constructor stub
		this.company = company.ID;
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(company.ID);
		lis.add(1000);
		this.links.put(ent.edit,lis);

	}
	public void createinrest() 
	{
		//when user create an account, create an object at the same time to rest to save data
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Project/"+this.ID;

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
		String urib = uriBase+"/Project1/Project/"+this.ID;

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

	public DSProject ReadfromServer() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Project/"+this.ID;
		DSProject read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSProject.class);
		return read;
		
	}
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(company);
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
		Project other = (Project) obj;
		return Objects.equals(company, other.company);
	}
	/*public void deleter() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/Project/"+this.name;
		Datasave del = client.delete()
				  .uri(urib)
				  .retrieve()
				  .body(Datasave.class);

	}
	*/
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
	


}
