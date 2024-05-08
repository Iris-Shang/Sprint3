package BBridge;

import java.util.ArrayList;

import org.springframework.web.client.RestClient;

public class JobRecommender extends Entity
{
	ArrayList<Person> listeners;
	JobPosting job;

	public JobRecommender(int ID, String name, String description,JobPosting job)
	{
		super(ID, name, description);
		this.job = job;
		this.listeners = getalllisteners();
	}
	public ArrayList<Person> getalllisteners() 
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
			//read.data().jobrecommended(job);
			//read.data().updater();
			listeners.add(read.data());
		//System.out.println(listeners+"check listeners");
			
		}
		return listeners;
		
	}
	public void recommendp() 
	{
		for (Person i : this.listeners) 
		{

			i.jobrecommended(this.job);
			i.updater();
			
		}
	}
	/**
	 * @return the listeners
	 */
	public ArrayList<Person> getListeners()
	{
		return listeners;
	}
	/**
	 * @param listeners the listeners to set
	 */
/*	public void setListeners(ArrayList<Person> listeners)
	{
		this.listeners = listeners;
	}*/


	

}
