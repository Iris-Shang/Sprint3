package BBridge;

import java.util.ArrayList;

import BBridge.Entity.ent;


@SuppressWarnings("unused")
public class JRskill extends JobRecommender
{
	ArrayList<Integer> ski;
	public JRskill(int ID, String name, String description, JobPosting job)
	{
		super(ID, name, description, job);
		this.ski = job.links.get(ent.skill);
		// TODO Auto-generated constructor stub
		this.listeners = getskilllisteners();

		
	}

	

	public ArrayList<Person> getskilllisteners() 
	{
		ArrayList<Person> all= this.getalllisteners();
		ArrayList<Person> haveskills = new ArrayList<Person> ();
		//ArrayList<Integer> listeners = new ArrayList<Integer> ();
		Entity addent = new Entity();
		for (Person i : all) 
		{
			for (Integer j : this.ski) 
			{
				if(i.links.get(ent.skill)!= null) 
				{
					if (i.links.get(ent.skill).contains(j)) 
					{
						//listeners.add(j);
						//i.jobrecommended(job);
						//i.updater();
						haveskills.add(i);
					}
				}
			}
			
			
		}

		return haveskills;
		
	}
	

}
