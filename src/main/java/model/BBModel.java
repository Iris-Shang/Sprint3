package model;

import java.util.ArrayList;


import BBridge.Company;

import BBridge.Entity;

import BBridge.JobPosting;

import BBridge.Person;
import BBridge.Project;

import BBridge.Skill;
import BBridge.Entity.ent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class BBModel
{
	public Stage stage;
	public Person p; //default person
	StringProperty Company;
	StringProperty name;
	StringProperty occupatation;
	ObservableList<Project> prolist= FXCollections.observableArrayList();  
	ObservableList<Skill> skilllist= FXCollections.observableArrayList(); 
	ObservableList<JobPosting> Joblist= FXCollections.observableArrayList();
	ObservableList<Project> prprolist= FXCollections.observableArrayList();  
	ObservableList<Skill> prskilllist= FXCollections.observableArrayList(); 
	ObservableList<JobPosting> prJoblist= FXCollections.observableArrayList();
	ObservableList<Company> prcomlist = FXCollections.observableArrayList();
	ObservableList<Company> pncomlist = FXCollections.observableArrayList();
	
	public JobPosting example;
	StringProperty jobCompany;
	StringProperty jobname;
	StringProperty jobdescription;
	ObservableList<Skill> jobskilllist= FXCollections.observableArrayList(); 
	ObservableList<Person> jobapplist= FXCollections.observableArrayList(); 
	ObservableList<Skill> jobrestskilllist= FXCollections.observableArrayList(); 
	ObservableList<Person> jobrestapplist= FXCollections.observableArrayList(); 
	ObservableList<Company> jrcomlist = FXCollections.observableArrayList();
	ObservableList<Company> jncomlist = FXCollections.observableArrayList();	
	
	
	public Skill ski;
	StringProperty skillname;
	StringProperty skilldesc;
	ObservableList<Person> skilled= FXCollections.observableArrayList(); 
	ObservableList<Person> unskilled= FXCollections.observableArrayList();
	
	public Company com;
	StringProperty comname;
	public StringProperty comdesc;
	ObservableList<Person> employeelist = FXCollections.observableArrayList(); 
	ObservableList<Person> restperson = FXCollections.observableArrayList(); 
	ObservableList<JobPosting> compostjob = FXCollections.observableArrayList(); 
	ObservableList<JobPosting> restjob = FXCollections.observableArrayList(); 
	ObservableList<Project> compostpro = FXCollections.observableArrayList(); 
	ObservableList<Project> restpro = FXCollections.observableArrayList(); 
	
	
	


	Company A;

	Skill python;

	Project P;
	Project R;
	JobPosting JP;
	JobPosting jp;


	Integer per;
	Integer comp;
	Integer sk;
	Integer jobp;
	

	ObservableList<Entity> entlist= FXCollections.observableArrayList(); 

	

	public BBModel(Stage stage, Integer per, Integer comp, Integer sk, Integer jobp)
	{	
		this.per = per;
		this.comp = comp;
		this.sk = sk;
		this.jobp = jobp;
		A = new Company(1,"A","companyA");
		this.stage = stage;
		//person page
		this.p = A.findanperson(per);
		this.name = new SimpleStringProperty(this,p.getName(),p.getName());
		if (p.getCompany()!= null)
		{
			this.Company = new SimpleStringProperty(this,"person's company",p.findacom(p.getCompany()).getName());
		}
		//else {this.Company = new SimpleStringProperty(this,"null","no job");}

		this.occupatation = new SimpleStringProperty(this,p.getOccupation(),p.getOccupation());
		if( (p.links.get(ent.applyjob)!= null))
		{
			this.Joblist.clear();
			this.Joblist.addAll(this.p.applyjobl());
		}			
		
		if( (p.links.get(ent.project)!= null))
		{
			this.prolist.clear();
			this.prolist.addAll(this.p.prol());
		}
		if( (p.links.get(ent.skill)!= null))
		{
			this.skilllist.clear();
			this.skilllist.addAll(this.p.skil());
		}
		
		
		//
		this.example = A.findajob(jobp);
		String examplecompany = example.findacom(example.getCompany()).getName();
		this.jobCompany=new SimpleStringProperty(this,examplecompany,examplecompany);
		this.jobdescription = new SimpleStringProperty(this,example.getDescription(),example.getDescription());
		this.jobname = new SimpleStringProperty(this,example.getName(),example.getName());
		this.jobskilllist = FXCollections.observableArrayList(this.example.getskill());
		this.jobapplist = FXCollections.observableArrayList(this.example.jobappl());
		updatejob();
		
		//skill
		this.ski = A.findaskill(sk);
		this.skillname = new SimpleStringProperty(this,ski.getName(),ski.getName());
		this.skilldesc = new SimpleStringProperty(this,ski.getDescription(),ski.getDescription());
		this.skilled.addAll(this.ski.findskilledperson());
		updateskill();
		//company page

		this.com = A.findacom(comp);
		this.comname = new SimpleStringProperty(this,com.getName(),com.getName());
		this.comdesc  = new SimpleStringProperty(this,com.getDescription(),com.getDescription());
		this.employeelist.addAll(com.emplyl());
		this.compostjob.addAll(com.jobl());
		this.compostpro.addAll(com.prol());
		updatecom();
		this.entlist.addAll(p.findallEntity());
		//System.out.println(this.entlist);
		
		updateper();
	}
	

	public void updatecom() 
	{
		ArrayList <Person> restp = new ArrayList <Person> ();
		ArrayList <Person> empl = this.com.emplyl();
		ArrayList <Person> allp = this.com.findallPerson();
		for (Person i : allp) {
			if (!empl.contains(i)) 
			{
				restp.add(i);
			}
		}
		this.restperson.clear();
		this.restperson.addAll(restp);
		
		ArrayList <Project> restpro1 = new ArrayList <Project> ();
		ArrayList <Project> expro = this.com.prol();
		ArrayList <Project> alpro = this.com.findallProject();
		for (Project i : alpro) {
			if (!expro.contains(i)) 
			{
				restpro1.add(i);
			}
		}
		this.restpro.clear();
		this.restpro.addAll(restpro1);
		
		ArrayList <JobPosting> restjob1 = new ArrayList <JobPosting> ();
		ArrayList <JobPosting> exjob = this.com.jobl();
		ArrayList <JobPosting> aljob = this.com.findallJobPosting();
		for (JobPosting i : aljob) {
			if (!exjob.contains(i)) 
			{
				restjob1.add(i);
			}
		}
		this.restjob.clear();
		this.restjob.addAll(restjob1);

		this.comname = new SimpleStringProperty(this,com.getName(),com.getName());
		this.comdesc  = new SimpleStringProperty(this,com.getDescription(),com.getDescription());
		this.employeelist.clear();
		this.employeelist.addAll(com.emplyl());
		this.compostjob.clear();
		this.compostjob.addAll(com.jobl());
		this.compostpro.clear();
		this.compostpro.addAll(com.prol());

		
		
		
		
	}
	
	public void updateper() 
	{
		
		ArrayList <Project> restpro = new ArrayList <Project> ();
		ArrayList <Project> expro = this.p.prol();
		ArrayList <Project> alpro = this.p.findallProject();
		for (Project i : alpro) {
			if (!expro.contains(i)) 
			{
				restpro.add(i);
			}
		}
		this.prprolist.clear();
		this.prprolist.addAll(restpro);
		
		ArrayList <JobPosting> restjob = new ArrayList <JobPosting> ();
		ArrayList <JobPosting> exjob = this.p.applyjobl();
		ArrayList <JobPosting> aljob = this.p.findallJobPosting();
		for (JobPosting i : aljob) {
			if (!exjob.contains(i)) 
			{
				restjob.add(i);
			}
		}
		this.prJoblist.clear();
		this.prJoblist.addAll(restjob);
		
		ArrayList <Skill> restski = new ArrayList <Skill> ();
		ArrayList <Skill> exski = this.p.skil();
		ArrayList <Skill> alski = this.p.findallSkill();
		for (Skill i : alski) {
			if (!exski.contains(i)) 
			{
				restski.add(i);
			}
		}
		this.prskilllist.clear();
		this.prskilllist.addAll(restski);
		
		ArrayList <Company> restcom = new ArrayList <Company> ();
		Company excom = this.p.findacom(this.p.getCompany());
		ArrayList <Company> alcom = this.p.findallCompany();
		alcom.remove(excom);
		this.prcomlist.clear();
		this.prcomlist.addAll(alcom);
		
		restcom.add(excom);
		this.pncomlist.clear();
		this.pncomlist.addAll(restcom);
		
		
		this.name = new SimpleStringProperty(this,p.getName(),p.getName());
		if (p.getCompany()!= null)
		{this.Company = new SimpleStringProperty(this,"person's company",p.findacom(p.getCompany()).getName());}
		//else {this.Company = new SimpleStringProperty(this,"null","no job");}

		this.occupatation = new SimpleStringProperty(this,p.getOccupation(),p.getOccupation());
		if( (p.links.get(ent.applyjob)!= null))
		{
			this.Joblist.clear();
			this.Joblist.addAll(this.p.applyjobl());
		}			
		
		if( (p.links.get(ent.project)!= null))
		{
			this.prolist.clear();
			this.prolist.addAll(this.p.prol());
		}
		if( (p.links.get(ent.skill)!= null))
		{
			this.skilllist.clear();
			this.skilllist.addAll(this.p.skil());
		}
		
		
	}
	
	public void updateskill() 
	{
		ArrayList <Person> restp = new ArrayList <Person> ();
		ArrayList <Person> empl = this.ski.findskilledperson();
		ArrayList <Person> allp = this.p.findallPerson();
		for (Person i : allp) {
			if (!empl.contains(i)) 
			{
				restp.add(i);
			}
		}
		this.unskilled.clear();
		this.unskilled.addAll(restp);
		this.skilldesc = new SimpleStringProperty(this,ski.getDescription(),ski.getDescription());
		this.skilled.clear();
		//ArrayList<Person> temp = this.ski.findskilledperson();
		//for (Person i: temp ) {
		//System.out.println(i);}
		this.skilled.addAll(this.ski.findskilledperson());
		
	}
	
	public void updatejob() 
	{
		ArrayList <Person> restp = new ArrayList <Person> ();
		ArrayList <Person> empl = this.example.jobappl();
		ArrayList <Person> allp = this.p.findallPerson();
		for (Person i : allp) {
			if (!empl.contains(i)) 
			{
				restp.add(i);
			}
		}
		this.jobrestapplist.clear();
		this.jobrestapplist.addAll(restp);

		ArrayList <Skill> restski = new ArrayList <Skill> ();
		ArrayList <Skill> exski = this.example.skil();
		ArrayList <Skill> alski = this.p.findallSkill();
		for (Skill i : alski) {
			if (!exski.contains(i)) 
			{
				restski.add(i);
			}
		}
		this.jobrestskilllist.clear();
		this.jobrestskilllist.addAll(restski);
		
		ArrayList <Company> restcom = new ArrayList <Company> ();
		Company excom = this.p.findacom(this.example.getCompany());
		ArrayList <Company> alcom = this.p.findallCompany();
		alcom.remove(excom);
		this.jrcomlist.clear();
		this.jrcomlist.addAll(alcom);
		
		restcom.add(excom);
		this.jncomlist.clear();
		this.jncomlist.addAll(restcom);
		
		
		
		
		String examplecompany = example.findacom(example.getCompany()).getName();
		this.jobCompany=new SimpleStringProperty(this,examplecompany,examplecompany);
		this.jobdescription = new SimpleStringProperty(this,example.getDescription(),example.getDescription());
		this.jobname = new SimpleStringProperty(this,example.getName(),example.getName());
		this.jobapplist.clear();
		this.jobskilllist.clear();
		this.jobskilllist.addAll(example.skil());
		this.jobapplist.addAll(example.jobappl());
		
	}
	
/**
	 * @return the pncomlist
	 */
	public ObservableList<Company> getPncomlist()
	{
		return pncomlist;
	}


	/**
	 * @param pncomlist the pncomlist to set
	 */
	public void setPncomlist(ObservableList<Company> pncomlist)
	{
		this.pncomlist = pncomlist;
	}


	/**
	 * @return the jncomlist
	 */
	public ObservableList<Company> getJncomlist()
	{
		return jncomlist;
	}


	/**
	 * @param jncomlist the jncomlist to set
	 */
	public void setJncomlist(ObservableList<Company> jncomlist)
	{
		this.jncomlist = jncomlist;
	}


/**
	 * @return the prcomlist
	 */
	public ObservableList<Company> getPrcomlist()
	{
		return prcomlist;
	}


	/**
	 * @param prcomlist the prcomlist to set
	 */
	public void setPrcomlist(ObservableList<Company> prcomlist)
	{
		this.prcomlist = prcomlist;
	}


	/**
	 * @return the jrcomlist
	 */
	public ObservableList<Company> getJrcomlist()
	{
		return jrcomlist;
	}


	/**
	 * @param jrcomlist the jrcomlist to set
	 */
	public void setJrcomlist(ObservableList<Company> jrcomlist)
	{
		this.jrcomlist = jrcomlist;
	}


	/*	public void updatecompany() 
	{
		ArrayList <Person> restp = new ArrayList <Person> ();
		ArrayList <Person> empl = this.com.emplyl()
		ArrayList <Person> allp = this.p.findallPerson();
		for (Person i : allp) {
			if (!empl.contains(i)) 
			{
				restp.add(i);
			}
		}

		
	}

*/
	/**
	 * @return the stage
	 */
	public Stage getStage()
	{
		return stage;
	}


	/**
	 * @param stage the stage to set
	 */
	public void setStage(Stage stage)
	{
		this.stage = stage;
	}


	/**
	 * @return the p
	 */
	public Person getP()
	{
		return p;
	}


	/**
	 * @param p the p to set
	 */
	public void setP(Person p)
	{
		this.p = p;
	}


	/**
	 * @return the company
	 */
	public StringProperty getCompany()
	{
		return Company;
	}


	/**
	 * @param company the company to set
	 */
	public void setCompany(StringProperty company)
	{
		Company = company;
	}


	/**
	 * @return the name
	 */
	public StringProperty getName()
	{
		return name;
	}


	/**
	 * @param name the name to set
	 */
	public void setName(StringProperty name)
	{
		this.name = name;
	}


	/**
	 * @return the occupatation
	 */
	public StringProperty getOccupatation()
	{
		return occupatation;
	}


	/**
	 * @param occupatation the occupatation to set
	 */
	public void setOccupatation(StringProperty occupatation)
	{
		this.occupatation = occupatation;
	}


	/**
	 * @return the prolist
	 */
	public ObservableList<Project> getProlist()
	{
		return prolist;
	}


	/**
	 * @param prolist the prolist to set
	 */
	public void setProlist(ObservableList<Project> prolist)
	{
		this.prolist = prolist;
	}


	/**
	 * @return the skilllist
	 */
	public ObservableList<Skill> getSkilllist()
	{
		return skilllist;
	}


	/**
	 * @param skilllist the skilllist to set
	 */
	public void setSkilllist(ObservableList<Skill> skilllist)
	{
		this.skilllist = skilllist;
	}


	/**
	 * @return the joblist
	 */
	public ObservableList<JobPosting> getJoblist()
	{
		return Joblist;
	}


	/**
	 * @param joblist the joblist to set
	 */
	public void setJoblist(ObservableList<JobPosting> joblist)
	{
		Joblist = joblist;
	}


	/**
	 * @return the prprolist
	 */
	public ObservableList<Project> getPrprolist()
	{
		return prprolist;
	}


	/**
	 * @param prprolist the prprolist to set
	 */
	public void setPrprolist(ObservableList<Project> prprolist)
	{
		this.prprolist = prprolist;
	}


	/**
	 * @return the prskilllist
	 */
	public ObservableList<Skill> getPrskilllist()
	{
		return prskilllist;
	}


	/**
	 * @param prskilllist the prskilllist to set
	 */
	public void setPrskilllist(ObservableList<Skill> prskilllist)
	{
		this.prskilllist = prskilllist;
	}


	/**
	 * @return the prJoblist
	 */
	public ObservableList<JobPosting> getPrJoblist()
	{
		return prJoblist;
	}


	/**
	 * @param prJoblist the prJoblist to set
	 */
	public void setPrJoblist(ObservableList<JobPosting> prJoblist)
	{
		this.prJoblist = prJoblist;
	}


	/**
	 * @return the example
	 */
	public JobPosting getExample()
	{
		return example;
	}


	/**
	 * @param example the example to set
	 */
	public void setExample(JobPosting example)
	{
		this.example = example;
	}


	/**
	 * @return the jobCompany
	 */
	public StringProperty getJobCompany()
	{
		return jobCompany;
	}


	/**
	 * @param jobCompany the jobCompany to set
	 */
	public void setJobCompany(StringProperty jobCompany)
	{
		this.jobCompany = jobCompany;
	}


	/**
	 * @return the jobname
	 */
	public StringProperty getJobname()
	{
		return jobname;
	}


	/**
	 * @param jobname the jobname to set
	 */
	public void setJobname(StringProperty jobname)
	{
		this.jobname = jobname;
	}


	/**
	 * @return the jobdescription
	 */
	public StringProperty getJobdescription()
	{
		return jobdescription;
	}


	/**
	 * @param jobdescription the jobdescription to set
	 */
	public void setJobdescription(StringProperty jobdescription)
	{
		this.jobdescription = jobdescription;
	}


	/**
	 * @return the ski
	 */
	public Skill getSki()
	{
		return ski;
	}


	/**
	 * @param ski the ski to set
	 */
	public void setSki(Skill ski)
	{
		this.ski = ski;
	}


	/**
	 * @return the skillname
	 */
	public StringProperty getSkillname()
	{
		return skillname;
	}


	/**
	 * @param skillname the skillname to set
	 */
	public void setSkillname(StringProperty skillname)
	{
		this.skillname = skillname;
	}


	/**
	 * @return the skilldesc
	 */
	public StringProperty getSkilldesc()
	{
		return skilldesc;
	}


	/**
	 * @param skilldesc the skilldesc to set
	 */
	public void setSkilldesc(StringProperty skilldesc)
	{
		this.skilldesc = skilldesc;
	}


	/**
	 * @return the skilled
	 */
	public ObservableList<Person> getSkilled()
	{
		return skilled;
	}


	/**
	 * @param skilled the skilled to set
	 */
	public void setSkilled(ObservableList<Person> skilled)
	{
		this.skilled = skilled;
	}


	/**
	 * @return the unskilled
	 */
	public ObservableList<Person> getUnskilled()
	{
		return unskilled;
	}


	/**
	 * @param unskilled the unskilled to set
	 */
	public void setUnskilled(ObservableList<Person> unskilled)
	{
		this.unskilled = unskilled;
	}


	/**
	 * @return the com
	 */
	public Company getCom()
	{
		return com;
	}


	/**
	 * @param com the com to set
	 */
	public void setCom(Company com)
	{
		this.com = com;
	}


	/**
	 * @return the comname
	 */
	public StringProperty getComname()
	{
		return comname;
	}


	/**
	 * @param comname the comname to set
	 */
	public void setComname(StringProperty comname)
	{
		this.comname = comname;
	}


	/**
	 * @return the comdesc
	 */
	public StringProperty getComdesc()
	{
		return comdesc;
	}


	/**
	 * @param comdesc the comdesc to set
	 */
	public void setComdesc(StringProperty comdesc)
	{
		this.comdesc = comdesc;
	}


	/**
	 * @return the employeelist
	 */
	public ObservableList<Person> getEmployeelist()
	{
		return employeelist;
	}


	/**
	 * @param employeelist the employeelist to set
	 */
	public void setEmployeelist(ObservableList<Person> employeelist)
	{
		this.employeelist = employeelist;
	}


	/**
	 * @return the restperson
	 */
	public ObservableList<Person> getRestperson()
	{
		return restperson;
	}


	/**
	 * @param restperson the restperson to set
	 */
	public void setRestperson(ObservableList<Person> restperson)
	{
		this.restperson = restperson;
	}


	/**
	 * @return the compostjob
	 */
	public ObservableList<JobPosting> getCompostjob()
	{
		return compostjob;
	}


	/**
	 * @param compostjob the compostjob to set
	 */
	public void setCompostjob(ObservableList<JobPosting> compostjob)
	{
		this.compostjob = compostjob;
	}


	/**
	 * @return the restjob
	 */
	public ObservableList<JobPosting> getRestjob()
	{
		return restjob;
	}


	/**
	 * @param restjob the restjob to set
	 */
	public void setRestjob(ObservableList<JobPosting> restjob)
	{
		this.restjob = restjob;
	}


	/**
	 * @return the compostpro
	 */
	public ObservableList<Project> getCompostpro()
	{
		return compostpro;
	}


	/**
	 * @param compostpro the compostpro to set
	 */
	public void setCompostpro(ObservableList<Project> compostpro)
	{
		this.compostpro = compostpro;
	}


	/**
	 * @return the restpro
	 */
	public ObservableList<Project> getRestpro()
	{
		return restpro;
	}


	/**
	 * @param restpro the restpro to set
	 */
	public void setRestpro(ObservableList<Project> restpro)
	{
		this.restpro = restpro;
	}


	/**
	 * @return the python
	 */
	public Skill getPython()
	{
		return python;
	}


	/**
	 * @param python the python to set
	 */
	public void setPython(Skill python)
	{
		this.python = python;
	}


	/**
	 * @return the p
	 */
	/*public Project getP()
	{
		return P;
	}


	/**
	 * @param p the p to set
	 */
	public void setP(Project p)
	{
		P = p;
	}


	/**
	 * @return the r
	 */
	public Project getR()
	{
		return R;
	}


	/**
	 * @param r the r to set
	 */
	public void setR(Project r)
	{
		R = r;
	}


	/**
	 * @return the jP
	 */
	public JobPosting getJP()
	{
		return JP;
	}


	/**
	 * @param jP the jP to set
	 */
	public void setJP(JobPosting jP)
	{
		JP = jP;
	}


	/**
	 * @return the jp
	 */
	public JobPosting getJp()
	{
		return jp;
	}


	/**
	 * @param jp the jp to set
	 */
	public void setJp(JobPosting jp)
	{
		this.jp = jp;
	}


	/**
	 * @return the entlist
	 */
	public ObservableList<Entity> getEntlist()
	{
		return entlist;
	}


	/**
	 * @param entlist the entlist to set
	 */
	public void setEntlist(ObservableList<Entity> entlist)
	{
		this.entlist = entlist;
	}


	/**
	 * @return the jobskilllist
	 */
	public ObservableList<Skill> getJobskilllist()
	{
		return jobskilllist;
	}


	/**
	 * @param jobskilllist the jobskilllist to set
	 */
	public void setJobskilllist(ObservableList<Skill> jobskilllist)
	{
		this.jobskilllist = jobskilllist;
	}


	/**
	 * @return the jobapplist
	 */
	public ObservableList<Person> getJobapplist()
	{
		return jobapplist;
	}


	/**
	 * @param jobapplist the jobapplist to set
	 */
	public void setJobapplist(ObservableList<Person> jobapplist)
	{
		this.jobapplist = jobapplist;
	}


	/**
	 * @return the jobrestskilllist
	 */
	public ObservableList<Skill> getJobrestskilllist()
	{
		return jobrestskilllist;
	}


	/**
	 * @param jobrestskilllist the jobrestskilllist to set
	 */
	public void setJobrestskilllist(ObservableList<Skill> jobrestskilllist)
	{
		this.jobrestskilllist = jobrestskilllist;
	}


	/**
	 * @return the jobrestapplist
	 */
	public ObservableList<Person> getJobrestapplist()
	{
		return jobrestapplist;
	}


	/**
	 * @param jobrestapplist the jobrestapplist to set
	 */
	public void setJobrestapplist(ObservableList<Person> jobrestapplist)
	{
		this.jobrestapplist = jobrestapplist;
	}
	
	
	
	
	
	
}



