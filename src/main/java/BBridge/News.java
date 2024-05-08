package BBridge;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import BBridge.Entity.ent;
@SuppressWarnings("unused")
public class News extends Entity
{
	String article;
	Integer owner;
	public News() {};
	public News(int ID, String name, String description, String article,Entity owner)
	{
		super(ID, name,description);
		// TODO Auto-generated constructor stub
		this.article = article;
		this.owner = owner.ID;
		ArrayList<Integer> lis = new ArrayList<Integer>();
		lis.add(owner.ID);
		lis.add(1000);
		this.links.put(ent.edit,lis);
	}
	



	/**
	 * @return the article
	 */
	public String getArticle()
	{
		return article;
	}




	/**
	 * @param article the article to set
	 */
	public void setArticle(String article)
	{
		this.article = article;
	}






	/**
	 * @return the owner
	 */
	public Integer getOwner()
	{
		return owner;
	}




	/**
	 * @param owner the owner to set
	 */
	public void setOwner(Integer owner)
	{
		this.owner = owner;
	}




	public void createinrest() 
	{
		//when user create an account, create an object at the same time to rest to save data
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/News/"+this.ID;

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
	public void updateinrest() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/News/"+this.ID;

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
	
	public DSNews readfromserver() 
	{
		RestClient client = RestClient.create();
		
		String uriBase = "http://localhost:9000/v1";
		String urib = uriBase+"/Project1/News/"+this.ID;
		DSNews read = client.get()
				  .uri(urib)
				  .retrieve()
				  .body(DSNews.class);
		//System.out.println(read);
		return read;
		
	}




	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(article, owner);
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
		News other = (News) obj;
		return Objects.equals(article, other.article) && Objects.equals(owner, other.owner);
	}

}
