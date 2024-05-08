package BBridge;

import java.util.ArrayList;

import BBridge.Entity.ent;

@SuppressWarnings("unused")
public class JRfollow extends JobRecommender
{

	public JRfollow(int ID, String name, String description, JobPosting job)
	{
		super(ID, name, description, job);
		// TODO Auto-generated constructor stub
		this.listeners = getfollowlisteners();
		
	}
	public ArrayList<Person> getfollowlisteners() 
	{
		ArrayList<Person> all= this.getalllisteners();
		ArrayList<Person> followcompany = new ArrayList<Person> ();
		//ArrayList<Integer> listeners = new ArrayList<Integer> ();
		//Entity addent = new Entity();
		for (Person i : all) 
		{
			if(i.links.get(ent.followacount)!= null)
			{
			for (Integer j : i.links.get(ent.followacount)) 
			{
				if(i.links.get(ent.followacount)!= null) {
					if (i.links.get(ent.followacount).contains(job.company)) 
					{
						followcompany.add(i);					
					}
				}
			}
			}
			
		}

		return followcompany;
		
	}

}
